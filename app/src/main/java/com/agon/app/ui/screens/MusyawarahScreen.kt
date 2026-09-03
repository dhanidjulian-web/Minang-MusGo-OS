package com.agon.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agon.app.data.ProposalDomain
import com.agon.app.data.ProposalStatus
import com.agon.app.ui.components.*
import com.agon.app.ui.theme.*
import com.agon.app.viewmodel.MusgoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusyawarahScreen(vm: MusgoViewModel) {
    var showAdd by remember { mutableStateOf(false) }
    var newTitle by remember { mutableStateOf("") }
    var newDomain by remember { mutableStateOf(ProposalDomain.GOVERNANCE) }
    val proposals = vm.filteredProposals()

    var selectedProposal by remember { mutableStateOf<com.agon.app.data.Proposal?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("MUSYAWARAH HALL", fontWeight = FontWeight.Black, fontSize = 14.sp, letterSpacing = 1.sp)
                        Text("Musyawarah untuk Mufakat • Quorum 85%", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                },
                navigationIcon = { Box(Modifier.padding(start = 12.dp).size(36.dp).clip(CircleShape).background(SovereignGold), contentAlignment = Alignment.Center){ Icon(Icons.Filled.Gavel, null, tint = Color.White, modifier = Modifier.size(20.dp)) } }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = { showAdd = true }, containerColor = SovereignNavy, contentColor = Color.White, icon = { Icon(Icons.Filled.Add, null) }, text = { Text("Usul Baru", fontWeight = FontWeight.Bold) })
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding), contentPadding = PaddingValues(bottom = 80.dp)) {
            item {
                Card(Modifier.padding(16.dp).fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = SovereignNavySurface)) {
                    Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.weight(1f)) {
                            Text("PRINSIP 2in1 — MUSYAWARAH & GOTONG-ROYONG", color = SovereignGold, fontSize = 10.sp, fontWeight = FontWeight.Black, letterSpacing = 0.8.sp)
                            Spacer(Modifier.height(6.dp))
                            Text("Setiap proposal wajib melalui musyawarah, validasi konstitusi, lalu gotong-royong eksekusi. Founder Dhani memegang hak veto sovereign.", color = Color.White.copy(0.85f), fontSize = 12.sp, lineHeight = 16.sp)
                        }
                        Spacer(Modifier.width(12.dp))
                        Surface(color = SovereignGold.copy(0.18f), shape = RoundedCornerShape(12.dp)) {
                            Column(Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("${vm.proposals.size}", color = SovereignGold, fontWeight = FontWeight.Black, fontSize = 20.sp)
                                Text("TOTAL USUL", color = Color.White.copy(0.7f), fontSize = 9.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
            item {
                Text("FILTER DOMAIN", Modifier.padding(horizontal = 16.dp), fontSize = 11.sp, fontWeight = FontWeight.Black, letterSpacing = 0.8.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                LazyRow(Modifier.padding(top = 8.dp), contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    item {
                        FilterChip(selected = vm.selectedDomain==null, onClick = { vm.selectedDomain = null }, label = { Text("Semua") }, leadingIcon = { Icon(Icons.Filled.AllInclusive, null, Modifier.size(16.dp)) })
                    }
                    items(ProposalDomain.values()) { d ->
                        FilterChip(selected = vm.selectedDomain==d, onClick = { vm.selectedDomain = if(vm.selectedDomain==d) null else d }, label = { Text(d.name) }, leadingIcon = { Icon(proposalDomainIcon(d), null, Modifier.size(16.dp)) })
                    }
                }
                Spacer(Modifier.height(10.dp))
                Text("FILTER STATUS", Modifier.padding(horizontal = 16.dp), fontSize = 11.sp, fontWeight = FontWeight.Black, letterSpacing = 0.8.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                LazyRow(Modifier.padding(top = 8.dp), contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    item { FilterChip(selected = vm.selectedStatus==null, onClick = { vm.selectedStatus=null }, label = { Text("Semua") }) }
                    items(ProposalStatus.values()) { s ->
                        FilterChip(selected = vm.selectedStatus==s, onClick = { vm.selectedStatus = if(vm.selectedStatus==s) null else s }, label = { Text(s.name) }, colors = FilterChipDefaults.filterChipColors(selectedContainerColor = proposalStatusColor(s).copy(0.18f)))
                    }
                }
                Spacer(Modifier.height(12.dp))
            }
            items(proposals) { p ->
                Card(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp).fillMaxWidth(), shape = RoundedCornerShape(18.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), elevation = CardDefaults.cardElevation(2.dp), onClick = { selectedProposal = p }) {
                    Column(Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(color = proposalStatusColor(p.status).copy(0.14f), shape = RoundedCornerShape(8.dp)) { Text(p.status.name, Modifier.padding(horizontal = 8.dp, vertical = 4.dp), fontSize = 10.sp, fontWeight = FontWeight.Black, color = proposalStatusColor(p.status)) }
                            Spacer(Modifier.width(8.dp))
                            StatusChip(p.domain.name, SovereignNavyLight, proposalDomainIcon(p.domain))
                            Spacer(Modifier.weight(1f))
                            Text(p.createdAt, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        Spacer(Modifier.height(10.dp))
                        Text(p.title, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = MaterialTheme.colorScheme.onSurface, lineHeight = 19.sp)
                        Spacer(Modifier.height(6.dp))
                        Text(p.description, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, lineHeight = 16.sp)
                        Spacer(Modifier.height(12.dp))
                        // Risk & Impact
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Surface(color = MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(8.dp)) { Row(Modifier.padding(horizontal = 8.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically){ Icon(Icons.Filled.WarningAmber, null, Modifier.size(12.dp), tint = SovereignAmber); Spacer(Modifier.width(4.dp)); Text("Risiko: ${p.risk}", fontSize = 10.sp, fontWeight = FontWeight.Bold) } }
                            Surface(color = MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(8.dp)) { Row(Modifier.padding(horizontal = 8.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically){ Icon(Icons.Filled.Bolt, null, Modifier.size(12.dp), tint = SovereignTeal); Spacer(Modifier.width(4.dp)); Text(p.impact, fontSize = 10.sp, fontWeight = FontWeight.Bold) } }
                        }
                        Spacer(Modifier.height(12.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(Modifier.weight(1f).height(8.dp).clip(CircleShape).background(MaterialTheme.colorScheme.surfaceVariant)) {
                                Box(Modifier.fillMaxHeight().fillMaxWidth(p.quorum).clip(CircleShape).background(Brush.horizontalGradient(listOf(proposalStatusColor(p.status), proposalStatusColor(p.status).copy(0.7f)))))
                            }
                            Spacer(Modifier.width(10.dp))
                            Text("${(p.quorum*100).toInt()}%", fontWeight = FontWeight.Black, color = proposalStatusColor(p.status), fontSize = 13.sp)
                        }
                        Spacer(Modifier.height(4.dp))
                        LinearProgressIndicator(progress = { p.quorum }, modifier = Modifier.fillMaxWidth().height(0.dp), drawStopIndicator = {})
                        Text("Quorum: ${p.votesFor} Setuju • ${p.votesAgainst} Tolak • ${p.abstain} Abstain • Threshold 85%", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Spacer(Modifier.height(12.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Button(onClick = { vm.vote(p.id, "FOR") }, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = SovereignEmerald), shape = RoundedCornerShape(12.dp), contentPadding = PaddingValues(vertical = 10.dp)) { Icon(Icons.Filled.ThumbUp, null, Modifier.size(16.dp)); Spacer(Modifier.width(6.dp)); Text("Setuju", fontSize = 12.sp, fontWeight = FontWeight.Bold) }
                            OutlinedButton(onClick = { vm.vote(p.id, "AGAINST") }, modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp), contentPadding = PaddingValues(vertical = 10.dp)) { Icon(Icons.Filled.ThumbDown, null, Modifier.size(16.dp)); Spacer(Modifier.width(6.dp)); Text("Tolak", fontSize = 12.sp) }
                            OutlinedButton(onClick = { vm.vote(p.id, "ABSTAIN") }, modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp), contentPadding = PaddingValues(vertical = 10.dp)) { Icon(Icons.Filled.PanTool, null, Modifier.size(16.dp)); Spacer(Modifier.width(4.dp)); Text("Abstain", fontSize = 11.sp) }
                        }
                        Spacer(Modifier.height(6.dp))
                        Text("Inisiator: ${p.initiator} • Tap kartu untuk detail konstitusi", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
            if(proposals.isEmpty()) {
                item {
                    Box(Modifier.fillMaxWidth().padding(32.dp), contentAlignment = Alignment.Center){ Column(horizontalAlignment = Alignment.CenterHorizontally){ Icon(Icons.Filled.SearchOff, null, Modifier.size(48.dp), tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.5f)); Spacer(Modifier.height(12.dp)); Text("Tidak ada proposal sesuai filter", color = MaterialTheme.colorScheme.onSurfaceVariant) } }
                }
            }
        }
    }

    if (showAdd) {
        AlertDialog(
            onDismissRequest = { showAdd = false },
            title = { Text("Usul Musyawarah Baru", fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("Founder Dhani, silakan ajukan usul sovereign. Akan masuk tahap Musyawarah dengan quorum awal 12%.", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    OutlinedTextField(value = newTitle, onValueChange = { newTitle = it }, label = { Text("Judul Proposal") }, placeholder = { Text("Contoh: Federasi Ekonomi Gotong-Royong") }, modifier = Modifier.fillMaxWidth(), singleLine = false, minLines = 2)
                    Text("Domain", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(ProposalDomain.values()) { d ->
                            FilterChip(selected = newDomain==d, onClick = { newDomain=d }, label = { Text(d.name, fontSize = 12.sp) }, leadingIcon = { Icon(proposalDomainIcon(d), null, Modifier.size(16.dp)) })
                        }
                    }
                }
            },
            confirmButton = {
                Button(onClick = {
                    if(newTitle.isNotBlank()){ vm.addProposal(newTitle, newDomain); newTitle=""; showAdd=false }
                }, colors = ButtonDefaults.buttonColors(containerColor = SovereignNavy)) { Text("Ajukan Musyawarah") }
            },
            dismissButton = { TextButton(onClick = { showAdd=false }){ Text("Batal") } }
        )
    }

    selectedProposal?.let { p ->
        ModalBottomSheet(onDismissRequest = { selectedProposal = null }, sheetState = sheetState, shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)) {
            Column(Modifier.padding(20.dp).fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(40.dp).clip(CircleShape).background(proposalStatusColor(p.status).copy(0.15f)), contentAlignment = Alignment.Center){ Icon(proposalDomainIcon(p.domain), null, tint = proposalStatusColor(p.status)) }
                    Spacer(Modifier.width(12.dp))
                    Column{ Text(p.id, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Bold); Text(p.title, fontWeight = FontWeight.Bold, fontSize = 16.sp) }
                }
                HorizontalDivider()
                DetailRow("Status", p.status.name, proposalStatusColor(p.status))
                DetailRow("Domain", p.domain.name, MaterialTheme.colorScheme.onSurface)
                DetailRow("Inisiator", p.initiator, SovereignGoldDark)
                DetailRow("Dibuat", p.createdAt, MaterialTheme.colorScheme.onSurfaceVariant)
                DetailRow("Hash Konstitusi", "0x9f3a...c1d2 (immutable)", SovereignTealDark)
                Text(p.description, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, lineHeight = 18.sp)
                Card(shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
                    Column(Modifier.padding(14.dp)) {
                        Text("VALIDASI KONSTITUSI", fontSize = 10.sp, fontWeight = FontWeight.Black, letterSpacing = 0.8.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Spacer(Modifier.height(6.dp))
                        ValidationLine("Replay-Safe", true)
                        ValidationLine("Constitutional-Aware", true)
                        ValidationLine("Zero-Trust Verified", p.quorum > 0.5f)
                        ValidationLine("Immutable Hash", true)
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = { vm.vote(p.id, "FOR"); selectedProposal = null }, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = SovereignEmerald)) { Text("Setuju & Validasi") }
                    OutlinedButton(onClick = { selectedProposal = null }, modifier = Modifier.weight(1f)) { Text("Tutup") }
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String, color: Color){
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
        Text(label, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.width(120.dp))
        Text(value, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = color)
    }
}
@Composable
private fun ValidationLine(name: String, ok: Boolean){
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 2.dp)){
        Icon(if(ok) Icons.Filled.CheckCircle else Icons.Filled.Cancel, null, Modifier.size(16.dp), tint = if(ok) SovereignEmerald else SovereignCrimson)
        Spacer(Modifier.width(8.dp))
        Text(name, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Medium)
        Spacer(Modifier.weight(1f))
        Text(if(ok) "VERIFIED" else "PENDING", fontSize = 10.sp, fontWeight = FontWeight.Black, color = if(ok) SovereignEmerald else SovereignAmber)
    }
}
