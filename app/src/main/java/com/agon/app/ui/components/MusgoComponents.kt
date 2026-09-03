package com.agon.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agon.app.data.*
import com.agon.app.ui.theme.*

@Composable
fun SovereignHeader(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp))
            .background(
                Brush.verticalGradient(listOf(SovereignNavy, SovereignNavyLight, Color(0xFF1A365D)))
            )
            .padding(20.dp)
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier.size(44.dp).clip(CircleShape).background(SovereignGold),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.Shield, contentDescription = null, tint = SovereignNavy, modifier = Modifier.size(26.dp))
                }
                Spacer(Modifier.width(12.dp))
                Column {
                    Text("MUSGO-OS 2in1", color = Color.White, fontWeight = FontWeight.Black, fontSize = 15.sp, letterSpacing = 1.2.sp)
                    Text("Sovereign AI Operating Civilization", color = SovereignGoldLight, fontSize = 11.sp, letterSpacing = 0.8.sp)
                }
                Spacer(Modifier.weight(1f))
                Surface(color = Color.White.copy(0.12f), shape = CircleShape) {
                    Row(Modifier.padding(horizontal = 10.dp, vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(8.dp).clip(CircleShape).background(SovereignEmerald))
                        Spacer(Modifier.width(6.dp))
                        Text("SOVEREIGN ACTIVE", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.8.sp)
                    }
                }
            }
            Spacer(Modifier.height(18.dp))
            Text("Assalamu'alaikum, Founder Dhani", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text("Kamis, 03 September 2026 • 09:45 WIB  •  Sesi Musyawarah Ke-041", color = Color.White.copy(0.72f), fontSize = 12.sp)
            Spacer(Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                HeaderStat("99.98%", "Uptime Civilization", SovereignTealLight)
                HeaderStat("0.94", "Trust Index", SovereignGold)
                HeaderStat("6/6", "Agent Swarm", Color.White)
            }
        }
    }
}

@Composable
private fun HeaderStat(value: String, label: String, accent: Color) {
    Surface(color = Color.White.copy(0.10f), shape = RoundedCornerShape(14.dp)) {
        Column(Modifier.padding(horizontal = 14.dp, vertical = 10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(value, color = accent, fontWeight = FontWeight.Black, fontSize = 14.sp)
            Text(label, color = Color.White.copy(0.75f), fontSize = 10.sp)
        }
    }
}

@Composable
fun StatusChip(text: String, color: Color, icon: ImageVector? = null) {
    Surface(color = color.copy(0.14f), shape = RoundedCornerShape(8.dp)) {
        Row(Modifier.padding(horizontal = 8.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
            if (icon != null) { Icon(icon, null, Modifier.size(12.dp), tint = color); Spacer(Modifier.width(4.dp)) }
            Text(text, color = color, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.6.sp)
        }
    }
}

fun proposalStatusColor(s: ProposalStatus): Color = when(s){
    ProposalStatus.MUSYAWARAH -> SovereignAmber
    ProposalStatus.KONSENSUS -> SovereignTeal
    ProposalStatus.EKSEKUSI -> SovereignEmerald
    ProposalStatus.TERTUNDA -> SovereignSlate
}
fun proposalDomainIcon(d: ProposalDomain): ImageVector = when(d){
    ProposalDomain.GOVERNANCE -> Icons.Filled.Gavel
    ProposalDomain.SECURITY -> Icons.Filled.Security
    ProposalDomain.INFRA -> Icons.Filled.Memory
    ProposalDomain.MONETIZATION -> Icons.Filled.Payments
    ProposalDomain.FEDERATION -> Icons.Filled.Hub
}
fun agentStateColor(s: AgentState): Color = when(s){
    AgentState.ACTIVE -> SovereignEmerald
    AgentState.VALIDATING -> SovereignAmber
    AgentState.ISOLATED -> SovereignCrimson
    AgentState.HEALING -> SovereignTeal
}
fun trustColor(t: TrustLevel): Color = when(t){
    TrustLevel.SOVEREIGN -> SovereignGold
    TrustLevel.VERIFIED -> SovereignTeal
    TrustLevel.PENDING -> SovereignAmber
    TrustLevel.ANOMALOUS -> SovereignCrimson
}
fun severityColor(s: String): Color = when(s){
    "CRITICAL" -> SovereignCrimson
    "WARN" -> SovereignAmber
    else -> SovereignTeal
}
