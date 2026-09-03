package com.agon.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agon.app.data.AgentState
import com.agon.app.ui.components.*
import com.agon.app.ui.theme.*
import com.agon.app.viewmodel.MusgoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FederationScreen(vm: MusgoViewModel){
    var selectedAgent by remember { mutableStateOf<com.agon.app.data.AgentNode?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = 20.dp)) {
        item {
            Box(Modifier.fillMaxWidth().clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)).background(SovereignNavy).padding(20.dp)){
                Column{
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Box(Modifier.size(40.dp).clip(CircleShape).background(SovereignTeal), contentAlignment = Alignment.Center){ Icon(Icons.Filled.Hub, null, tint = Color.White) }
                        Spacer(Modifier.width(12.dp))
                        Column{
                            Text("FEDERATION SWARM", color = Color.White, fontWeight = FontWeight.Black, fontSize = 14.sp, letterSpacing = 1.sp)
                            Text("6 Agent • Multi-Agent Coordination • Zero-Trust Mesh", color = Color.White.copy(0.72f), fontSize = 11.sp)
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)){
                        FedStat("4", "Active", SovereignEmerald)
                        FedStat("1", "Healing", SovereignAmber)
                        FedStat("1", "Isolated", SovereignCrimson)
                    }
                    Spacer(Modifier.height(16.dp))
                    // topology visual简
                    Card(shape = RoundedCornerShape(14.dp), colors = CardDefaults.cardColors(containerColor = Color.White.copy(0.08f))) {
                        Column(Modifier.padding(14.dp)){
                            Text("TOPOLOGI FEDERASI — TRUST PROPAGATION", color = SovereignGold, fontSize = 10.sp, fontWeight = FontWeight.Black, letterSpacing = 0.8.sp)
                            Spacer(Modifier.height(10.dp))
                            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically){
                                TopologyNode("Governor", SovereignGold, true)
                                Box(Modifier.width(16.dp).height(2.dp).background(Color.White.copy(0.3f)))
                                TopologyNode("Planner", SovereignTeal, true)
                                Box(Modifier.width(16.dp).height(2.dp).background(Color.White.copy(0.3f)))
                                TopologyNode("Executor", SovereignGold, true)
                            }
                            Spacer(Modifier.height(10.dp))
                            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically){
                                TopologyNode("Auditor", SovereignEmerald, true)
                                Box(Modifier.width(16.dp).height(2.dp).background(SovereignCrimson.copy(0.6f)))
                                TopologyNode("Guardian", SovereignCrimson, false)
                                Box(Modifier.width(16.dp).height(2.dp).background(SovereignAmber.copy(0.6f)))
                                TopologyNode("Orchestrator", SovereignAmber, false)
                            }
                            Spacer(Modifier.height(8.dp))
                            Text("Garis putus = trust propagation tertunda/isolasi. Tap agent untuk intervensi Founder.", color = Color.White.copy(0.6f), fontSize = 10.sp)
                        }
                    }
                }
            }
        }
        item { Spacer(Modifier.height(16.dp)) }
        item {
            Text("SWARM INTELLIGENCE — SEMUA AGENT", Modifier.padding(horizontal = 16.dp), fontWeight = FontWeight.Black, fontSize = 12.sp, letterSpacing = 0.8.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.height(10.dp))
        }
        item {
            // Use Column grid manually inside LazyColumn to avoid nested lazy
            Column(Modifier.padding(horizontal = 12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)){
                vm.agents.chunked(2).forEach { row ->
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()){
                        row.forEach { agent ->
                            AgentCard(agent, Modifier.weight(1f), onClick = { selectedAgent = agent }, onToggle = { vm.toggleAgent(agent.id) })
                        }
                        if(row.size==1) Spacer(Modifier.weight(1f))
                    }
                }
            }
        }
        item { Spacer(Modifier.height(16.dp)) }
        item {
            Card(Modifier.padding(horizontal = 16.dp).fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), elevation = CardDefaults.cardElevation(2.dp)){
                Column(Modifier.padding(16.dp)){
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Icon(Icons.Filled.Policy, null, tint = SovereignNavyLight)
                        Spacer(Modifier.width(8.dp))
                        Text("POLICY ENGINE — CONSTITUTIONAL GUARD", fontWeight = FontWeight.Black, fontSize = 11.sp, letterSpacing = 0.8.sp)
                    }
                    Spacer(Modifier.height(12.dp))
                    PolicyRow("Replay-Safe Enforcement", "Aktif", true)
                    PolicyRow("Immutable Ledger Check", "Aktif", true)
                    PolicyRow("Anomaly Auto-Isolate", "Trigger 0.85", true)
                    PolicyRow("Quantum-Resilient KEM", "Healing", false)
                }
            }
        }
    }
    selectedAgent?.let { a ->
        ModalBottomSheet(onDismissRequest = { selectedAgent = null }, sheetState = sheetState){
            Column(Modifier.padding(20.dp).fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)){
                Row(verticalAlignment = Alignment.CenterVertically){
                    Box(Modifier.size(48.dp).clip(CircleShape).background(agentStateColor(a.state).copy(0.15f)), contentAlignment = Alignment.Center){ Icon(when(a.role){ com.agon.app.data.AgentRole.GOVERNOR -> Icons.Filled.Shield; com.agon.app.data.AgentRole.PLANNER -> Icons.Filled.Lightbulb; com.agon.app.data.AgentRole.EXECUTOR -> Icons.Filled.Bolt; com.agon.app.data.AgentRole.AUDITOR -> Icons.Filled.VerifiedUser; com.agon.app.data.AgentRole.GUARDIAN -> Icons.Filled.Security; com.agon.app.data.AgentRole.ORCHESTRATOR -> Icons.Filled.Hub }, null, tint = agentStateColor(a.state)) }
                    Spacer(Modifier.width(12.dp))
                    Column{ Text(a.name, fontWeight = FontWeight.Black, fontSize = 16.sp); Text("${a.role.name} • ${a.id}", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Bold) }
                    Spacer(Modifier.weight(1f))
                    Surface(color = agentStateColor(a.state).copy(0.14f), shape = RoundedCornerShape(8.dp)){ Text(a.state.name, Modifier.padding(horizontal = 8.dp, vertical = 4.dp), fontSize = 10.sp, fontWeight = FontWeight.Black, color = agentStateColor(a.state)) }
                }
                HorizontalDivider()
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)){
                    StatMini("Load", "${(a.load*100).toInt()}%", a.load, agentStateColor(a.state), Modifier.weight(1f))
                    StatMini("Uptime", a.uptime, 0.99f, SovereignTeal, Modifier.weight(1f))
                    StatMini("Handled", "${a.handled}", 0.8f, SovereignGold, Modifier.weight(1f))
                }
                Card(shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)){
                    Column(Modifier.padding(14.dp)){
                        Text("TRUST PROPAGATION", fontSize = 10.sp, fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Spacer(Modifier.height(6.dp))
                        Row(verticalAlignment = Alignment.CenterVertically){
                            Box(Modifier.size(10.dp).clip(CircleShape).background(trustColor(a.trust)))
                            Spacer(Modifier.width(8.dp))
                            Text(a.trust.name, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = trustColor(a.trust))
                            Spacer(Modifier.weight(1f))
                            Text(when(a.trust){ com.agon.app.data.TrustLevel.SOVEREIGN -> "Sovereign Verified"; com.agon.app.data.TrustLevel.VERIFIED -> "Federation Verified"; com.agon.app.data.TrustLevel.PENDING -> "Validasi Tertunda"; com.agon.app.data.TrustLevel.ANOMALOUS -> "Anomalous — Isolated" }, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        Spacer(Modifier.height(8.dp))
                        LinearProgressIndicator(progress = { when(a.trust){ com.agon.app.data.TrustLevel.SOVEREIGN -> 1f; com.agon.app.data.TrustLevel.VERIFIED -> 0.75f; com.agon.app.data.TrustLevel.PENDING -> 0.45f; com.agon.app.data.TrustLevel.ANOMALOUS -> 0.15f } }, modifier = Modifier.fillMaxWidth().height(6.dp).clip(CircleShape), color = trustColor(a.trust), drawStopIndicator = {})
                    }
                }
                Text("Aksi Founder Dhani: Agent akan saling memvalidasi, mengaudit, dan mengisolasi jika anomalous. Founder dapat memicu healing manual.", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, lineHeight = 15.sp)
                Button(onClick = { vm.toggleAgent(a.id); selectedAgent = null }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = if(a.state==AgentState.ISOLATED) SovereignEmerald else SovereignCrimson), shape = RoundedCornerShape(12.dp)){
                    Icon(if(a.state==AgentState.ISOLATED) Icons.Filled.Healing else Icons.Filled.Block, null, Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text(if(a.state==AgentState.ISOLATED) "Pulihkan & Heal Agent" else if(a.state==AgentState.HEALING) "Aktifkan Kembali" else "Isolasi Agent (Karina)")
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun FedStat(value: String, label: String, color: Color){
    Surface(color = Color.White.copy(0.10f), shape = RoundedCornerShape(12.dp)){
        Column(Modifier.padding(horizontal = 16.dp, vertical = 10.dp), horizontalAlignment = Alignment.CenterHorizontally){
            Text(value, color = color, fontWeight = FontWeight.Black, fontSize = 18.sp)
            Text(label, color = Color.White.copy(0.7f), fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
    }
}
@Composable
private fun TopologyNode(name: String, color: Color, ok: Boolean){
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Box(Modifier.size(36.dp).clip(CircleShape).background(if(ok) color else color.copy(0.35f)), contentAlignment = Alignment.Center){ Icon(Icons.Filled.Circle, null, Modifier.size(14.dp), tint = Color.White) }
        Spacer(Modifier.height(4.dp))
        Text(name, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Box(Modifier.size(6.dp).clip(CircleShape).background(if(ok) SovereignEmerald else SovereignCrimson))
    }
}
@Composable
private fun AgentCard(agent: com.agon.app.data.AgentNode, modifier: Modifier = Modifier, onClick: ()->Unit, onToggle: ()->Unit){
    Card(modifier = modifier, shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), elevation = CardDefaults.cardElevation(2.dp), onClick = onClick){
        Column(Modifier.padding(12.dp)){
            Row(verticalAlignment = Alignment.CenterVertically){
                Box(Modifier.size(32.dp).clip(CircleShape).background(agentStateColor(agent.state).copy(0.14f)), contentAlignment = Alignment.Center){ Text(agent.name.take(1), fontWeight = FontWeight.Black, color = agentStateColor(agent.state)) }
                Spacer(Modifier.width(8.dp))
                Column(Modifier.weight(1f)){
                    Text(agent.name, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface, maxLines = 1)
                    Text(agent.role.name, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Bold)
                }
                Surface(color = trustColor(agent.trust).copy(0.14f), shape = CircleShape){ Box(Modifier.size(10.dp).padding(2.dp).clip(CircleShape).background(trustColor(agent.trust))) }
            }
            Spacer(Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically){
                Box(Modifier.weight(1f).height(6.dp).clip(CircleShape).background(MaterialTheme.colorScheme.surfaceVariant)){ Box(Modifier.fillMaxHeight().fillMaxWidth(agent.load).clip(CircleShape).background(agentStateColor(agent.state))) }
                Spacer(Modifier.width(8.dp))
                Text("${(agent.load*100).toInt()}%", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = agentStateColor(agent.state))
            }
            Spacer(Modifier.height(6.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
                Text(agent.uptime, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("${agent.handled} tasks", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Spacer(Modifier.height(8.dp))
            Surface(color = agentStateColor(agent.state).copy(0.12f), shape = RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth()){ Text(agent.state.name, Modifier.padding(vertical = 6.dp).wrapContentWidth(Alignment.CenterHorizontally), fontSize = 10.sp, fontWeight = FontWeight.Black, color = agentStateColor(agent.state)) }
        }
    }
}
@Composable
private fun StatMini(label: String, value: String, progress: Float, color: Color, modifier: Modifier = Modifier){
    Card(modifier = modifier, shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)){
        Column(Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally){
            Text(label, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Bold)
            Text(value, fontWeight = FontWeight.Black, fontSize = 14.sp, color = color)
            Spacer(Modifier.height(4.dp))
            LinearProgressIndicator(progress = { progress.coerceIn(0f,1f) }, modifier = Modifier.fillMaxWidth().height(4.dp).clip(CircleShape), color = color, drawStopIndicator = {})
        }
    }
}
@Composable
private fun PolicyRow(name: String, status: String, ok: Boolean){
    Row(Modifier.fillMaxWidth().padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically){
        Icon(if(ok) Icons.Filled.CheckCircle else Icons.Filled.Error, null, Modifier.size(16.dp), tint = if(ok) SovereignEmerald else SovereignAmber)
        Spacer(Modifier.width(8.dp))
        Text(name, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
        Surface(color = if(ok) SovereignEmerald.copy(0.14f) else SovereignAmber.copy(0.14f), shape = RoundedCornerShape(6.dp)){ Text(status, Modifier.padding(horizontal = 6.dp, vertical = 2.dp), fontSize = 10.sp, fontWeight = FontWeight.Black, color = if(ok) SovereignEmerald else SovereignAmber) }
    }
}
