package com.agon.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.SwapHorizontalCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.agon.app.ui.theme.GotongRoyongGold
import com.agon.app.ui.theme.SovereignTeal
import com.agon.app.ui.theme.TrustGreen

@Composable
fun SettingsScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Pengaturan",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.onSurface,
        )

        // --- Founder Profile Card ---
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = SovereignTeal.copy(alpha = 0.1f),
            ),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = SovereignTeal.copy(alpha = 0.2f),
                ) {
                    Icon(
                        Icons.Default.AccountCircle,
                        contentDescription = null,
                        tint = SovereignTeal,
                        modifier = Modifier
                            .size(56.dp)
                            .padding(8.dp),
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Dhani Yuliawan",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = "Founder — MUSGO-OS 2in1",
                        style = MaterialTheme.typography.bodyMedium,
                        color = SovereignTeal,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = "Sovereign AI Operating Civilization",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }

        // --- Security Settings ---
        SettingsSectionHeader("Keamanan & Sovereignty")

        var zeroTrustEnabled by remember { mutableStateOf(true) }
        var constitutionalGuard by remember { mutableStateOf(true) }
        var selfHealing by remember { mutableStateOf(true) }
        var quantumResistant by remember { mutableStateOf(false) }

        SettingsToggleItem(
            icon = Icons.Default.Shield,
            title = "Zero-Trust Enforcement",
            subtitle = "Setiap akses diverifikasi secara eksplisit",
            checked = zeroTrustEnabled,
            onCheckedChange = { zeroTrustEnabled = it },
        )

        SettingsToggleItem(
            icon = Icons.Default.Lock,
            title = "Constitutional AI Guard",
            subtitle = "Konstitusi AI sebagai guard utama",
            checked = constitutionalGuard,
            onCheckedChange = { constitutionalGuard = it },
        )

        SettingsToggleItem(
            icon = Icons.Default.Build,
            title = "Self-Healing Resilience",
            subtitle = "Recovery otomatis dari failure",
            checked = selfHealing,
            onCheckedChange = { selfHealing = it },
        )

        SettingsToggleItem(
            icon = Icons.Default.Security,
            title = "Quantum-Resistant Crypto",
            subtitle = "Algoritma quantum-resistant (beta)",
            checked = quantumResistant,
            onCheckedChange = { quantumResistant = it },
        )

        // --- System Settings ---
        SettingsSectionHeader("Sistem")

        var notificationsEnabled by remember { mutableStateOf(true) }
        var darkModeEnabled by remember { mutableStateOf(true) }
        var federationSync by remember { mutableStateOf(true) }

        SettingsToggleItem(
            icon = Icons.Default.Notifications,
            title = "Notifikasi Governance",
            subtitle = "Alert perubahan kebijakan dan keamanan",
            checked = notificationsEnabled,
            onCheckedChange = { notificationsEnabled = it },
        )

        SettingsToggleItem(
            icon = Icons.Default.Palette,
            title = "Mode Gelap",
            subtitle = "Tema gelap untuk operasi cyber",
            checked = darkModeEnabled,
            onCheckedChange = { darkModeEnabled = it },
        )

        SettingsToggleItem(
            icon = Icons.Default.SwapHorizontalCircle,
            title = "Sinkronisasi Federasi",
            subtitle = "Sinkronisasi otomatis antar node federasi",
            checked = federationSync,
            onCheckedChange = { federationSync = it },
        )

        // --- About ---
        SettingsSectionHeader("Tentang")

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        Icons.Default.Info,
                        contentDescription = null,
                        tint = SovereignTeal,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "MUSGO-OS 2in1",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Text(
                    text = "Versi 2.0.0 — Sovereign Build",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )

                Text(
                    text = "Musyawarah & Gotong-Royong",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = GotongRoyongGold,
                )

                Text(
                    text = "Sovereign AI Operating Civilization",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "© 2026 — Dhani Yuliawan",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = SovereignTeal,
                )
                Text(
                    text = "All Rights Reserved",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun SettingsSectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(top = 4.dp),
    )
}

@Composable
private fun SettingsToggleItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = if (checked) SovereignTeal else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(24.dp),
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
            )
        }
    }
}
