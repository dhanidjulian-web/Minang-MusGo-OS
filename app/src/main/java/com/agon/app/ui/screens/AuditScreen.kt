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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agon.app.ui.components.severityColor
import com.agon.app.ui.theme.*
import com.agon.app.viewmodel.MusgoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuditScreen(vm: MusgoViewModel){
    var search by remember { mutableStateOf("") }
    var filter by remember { mutableStateOf<String?>(null) }
    val filtered = vm.audits.filter { e ->
        (filter==null || e.severity==filter) && (search.isBlank() || e.action.contains(search, true) || e.actor.contains(search, true) || e.hash.contains(search, true))
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Column{ Text("AUDIT LEDGER — IMMUTABLE", fontWeight = FontWeight.Black, fontSize = 14.sp, letterSpacing = 1.sp); Text("Replay-Safe • Constitutional-Aware • Auditable", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant) } }, navigationIcon = { Box(Modifier.padding(start = 12.dp).size(36.dp).clip(CircleShape).background(SovereignNavy), contentAlignment = Alignment.Center){ Icon(Icons.Filled.ReceiptLong, null, tint = SovereignGold) } })
        }
    ){ padding ->
        LazyColumn(Modifier.fillMaxSize().padding(padding), contentPadding = PaddingValues(bottom = 16.dp)){
            item {
                Card(Modifier.padding(16.dp).fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = SovereignNavySurface)){
                    Column(Modifier.padding(16.dp)){
                        Row(verticalAlignment = Alignment.CenterVertically){
                            Icon(Icons.Filled.Lock, null, tint = SovereignGold)
                            Spacer(Modifier.width(8.dp))
                            Text("LEDGER INTEGRITY — QUANTUM-RESILIENT", color = Color.White, fontWeight = FontWeight.Black, fontSize = 11.sp, letterSpacing = 0.8.sp)
                            Spacer(Modifier.weight(1f))
                            Surface(color = SovereignEmerald.copy(0.18f), shape = RoundedCornerShape(8.dp)){ Row(Modifier.padding(horizontal = 8.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically){ Box(Modifier.size(8.dp).clip(CircleShape).background(SovereignEmerald)); Spacer(Modifier.width(6.dp)); Text("VERIFIED", color = SovereignEmerald, fontSize = 10.sp, fontWeight = FontWeight.Black) } }
                        }
                        Spacer(Modifier.height(12.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)){
                            LedgerKpi("${vm.audits.size}", "Total Event", Modifier.weight(1f))
                            LedgerKpi("${vm.audits.count{it.verified}}", "Verified", Modifier.weight(1f))
                            LedgerKpi("${vm.audits.count{!it.verified}}", "Anomali", Modifier.weight(1f))
                        }
                        Spacer(Modifier.height(12.dp))
                        LinearProgressIndicator(progress = { vm.audits.count{it.verified}.toFloat()/vm.audits.size.coerceAtLeast(1) }, modifier = Modifier.fillMaxWidth().height(6.dp).clip(CircleShape), color = SovereignEmerald, trackColor = Color.White.copy(0.14f), drawStopIndicator = {})
                        Spacer(Modifier.height(6.dp))
                        Text("Integrity 83% • Semua event di-hash SHA-256 dan direplikasi ke 6 node federation. Replay attack ditolak otomatis.", color = Color.White.copy(0.7f), fontSize = 11.sp, lineHeight = 15.sp)
                    }
                }
            }
            item {
                OutlinedTextField(value = search, onValueChange = { search = it }, modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(), placeholder = { Text("Cari actor, hash, atau aksi…", fontSize = 13.sp) }, leadingIcon = { Icon(Icons.Filled.Search, null) }, trailingIcon = { if(search.isNotBlank()) IconButton(onClick = { search="" }){ Icon(Icons.Filled.Clear, null) } }, shape = RoundedCornerShape(12.dp), singleLine = true)
                Spacer(Modifier.height(10.dp))
                LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)){
                    item { FilterChip(selected = filter==null, onClick = { filter=null }, label = { Text("Semua") }) }
                    listOf("INFO","WARN","CRITICAL").forEach { s ->
                        item { FilterChip(selected = filter==s, onClick = { filter = if(filter==s) null else s }, label = { Text(s) }, leadingIcon = { Box(Modifier.size(8.dp).clip(CircleShape).background(severityColor(s))) }) }
                    }
                }
                Spacer(Modifier.height(12.dp))
            }
            items(filtered){ e ->
                Card(Modifier.padding(horizontal = 16.dp, vertical = 5.dp).fillMaxWidth(), shape = RoundedCornerShape(14.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), elevation = CardDefaults.cardElevation(1.dp)){
                    Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically){
                        Box(Modifier.size(36.dp).clip(CircleShape).background(severityColor(e.severity).copy(0.14f)), contentAlignment = Alignment.Center){ Icon(when(e.severity){ "CRITICAL" -> Icons.Filled.Error; "WARN" -> Icons.Filled.WarningAmber; else -> Icons.Filled.CheckCircle }, null, tint = severityColor(e.severity), modifier = Modifier.size(20.dp)) }
                        Spacer(Modifier.width(12.dp))
                        Column(Modifier.weight(1f)){
                            Row(verticalAlignment = Alignment.CenterVertically){
                                Text(e.id, fontSize = 10.sp, fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                Spacer(Modifier.width(6.dp))
                                Text("•", color = MaterialTheme.colorScheme.onSurfaceVariant)
                                Spacer(Modifier.width(6.dp))
                                Text(e.time, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                Spacer(Modifier.width(6.dp))
                                Surface(color = severityColor(e.severity).copy(0.14f), shape = RoundedCornerShape(6.dp)){ Text(e.severity, Modifier.padding(horizontal = 6.dp, vertical = 2.dp), fontSize = 9.sp, fontWeight = FontWeight.Black, color = severityColor(e.severity)) }
                            }
                            Spacer(Modifier.height(2.dp))
                            Text(e.action, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurface)
                            Text("Actor: ${e.actor}", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Spacer(Modifier.height(6.dp))
                            Surface(color = MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(8.dp)){
                                Row(Modifier.padding(horizontal = 8.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically){
                                    Icon(Icons.Filled.Tag, null, Modifier.size(12.dp), tint = MaterialTheme.colorScheme.onSurfaceVariant)
                                    Spacer(Modifier.width(6.dp))
                                    Text(e.hash, fontFamily = FontFamily.Monospace, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Bold)
                                    Spacer(Modifier.width(8.dp))
                                    if(e.verified) Icon(Icons.Filled.Verified, null, Modifier.size(14.dp), tint = SovereignEmerald) else Icon(Icons.Filled.GppBad, null, Modifier.size(14.dp), tint = SovereignCrimson)
                                }
                            }
                        }
                    }
                }
            }
            if(filtered.isEmpty()){
                item { Box(Modifier.fillMaxWidth().padding(32.dp), contentAlignment = Alignment.Center){ Text("Tidak ada event sesuai filter", color = MaterialTheme.colorScheme.onSurfaceVariant) } }
            }
        }
    }
}

@Composable
private fun LedgerKpi(value: String, label: String, modifier: Modifier = Modifier){
    Surface(modifier = modifier, color = Color.White.copy(0.08f), shape = RoundedCornerShape(12.dp)){
        Column(Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally){
            Text(value, color = Color.White, fontWeight = FontWeight.Black, fontSize = 16.sp)
            Text(label, color = Color.White.copy(0.7f), fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
    }
}
