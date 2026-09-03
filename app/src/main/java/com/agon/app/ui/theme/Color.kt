package com.agon.app.ui.theme

import androidx.compose.ui.graphics.Color

// ============================================
// MUSGO-OS 2in1 — Sovereign Color System
// © 2026 Dhani Yuliawan
// ============================================

// Primary — Sovereign Teal (authoritative, cyber-native)
val SovereignTeal = Color(0xFF00897B)
val SovereignTealLight = Color(0xFF4DB6AC)
val SovereignTealDark = Color(0xFF00564A)

// Secondary — GotongRoyong Gold (warmth, cooperation, community)
val GotongRoyongGold = Color(0xFFFFB300)
val GotongRoyongGoldLight = Color(0xFFFFD54F)
val GotongRoyongGoldDark = Color(0xFFC68A00)

// Tertiary — Governance Navy (authority, constitution, security)
val GovernanceNavy = Color(0xFF1A237E)
val GovernanceNavyLight = Color(0xFF534BAE)
val GovernanceNavyDark = Color(0xFF000051)

// Accent — Cyber Red (alerts, security threats, critical)
val CyberRed = Color(0xFFE53935)
val CyberRedLight = Color(0xFFEF5350)
val CyberRedDark = Color(0xFFB71C1C)

// Status Colors
val TrustGreen = Color(0xFF43A047)
val TrustGreenLight = Color(0xFF66BB6A)
val WarningAmber = Color(0xFFFFA000)
val AlertOrange = Color(0xFFFB8C00)

// Surface System — Dark-first (cyber operations)
val SurfaceDark = Color(0xFF0D1117)
val SurfaceDarkVariant = Color(0xFF161B22)
val SurfaceDarkElevated = Color(0xFF1C2333)
val SurfaceDarkCard = Color(0xFF21262D)

val SurfaceLight = Color(0xFFF8F9FA)
val SurfaceLightVariant = Color(0xFFE8ECF0)
val SurfaceLightElevated = Color(0xFFFFFFFF)
val SurfaceLightCard = Color(0xFFFFFFFF)

// On-Surface
val OnSurfaceDark = Color(0xFFE6EDF3)
val OnSurfaceDarkVariant = Color(0xFF8B949E)
val OnSurfaceLight = Color(0xFF1F2328)
val OnSurfaceLightVariant = Color(0xFF656D76)

// Outline
val OutlineDark = Color(0xFF30363D)
val OutlineLight = Color(0xFFD0D7DE)

// Light Theme
val MusgoLightPrimary = SovereignTeal
val MusgoLightOnPrimary = Color(0xFFFFFFFF)
val MusgoLightPrimaryContainer = Color(0xFFB2DFDB)
val MusgoLightOnPrimaryContainer = Color(0xFF003D33)

val MusgoLightSecondary = GotongRoyongGoldDark
val MusgoLightOnSecondary = Color(0xFFFFFFFF)
val MusgoLightSecondaryContainer = Color(0xFFFFE082)
val MusgoLightOnSecondaryContainer = Color(0xFF4A3800)

val MusgoLightTertiary = GovernanceNavy
val MusgoLightOnTertiary = Color(0xFFFFFFFF)
val MusgoLightTertiaryContainer = Color(0xFFC5CAE9)
val MusgoLightOnTertiaryContainer = Color(0xFF00003D)

// Dark Theme
val MusgoDarkPrimary = SovereignTealLight
val MusgoDarkOnPrimary = Color(0xFF003D33)
val MusgoDarkPrimaryContainer = SovereignTealDark
val MusgoDarkOnPrimaryContainer = Color(0xFFB2DFDB)

val MusgoDarkSecondary = GotongRoyongGold
val MusgoDarkOnSecondary = Color(0xFF4A3800)
val MusgoDarkSecondaryContainer = GotongRoyongGoldDark
val MusgoDarkOnSecondaryContainer = Color(0xFFFFE082)

val MusgoDarkTertiary = GovernanceNavyLight
val MusgoDarkOnTertiary = Color(0xFFC5CAE9)
val MusgoDarkTertiaryContainer = GovernanceNavyDark
val MusgoDarkOnTertiaryContainer = Color(0xFFC5CAE9)

// ============================================
// SOVEREIGN ALIASES — Compatibility layer
// Memetakan nama Sovereign* lama ke palet baru agar seluruh screen tetap kompil
// ============================================
val SovereignNavy = GovernanceNavyDark
val SovereignNavyLight = GovernanceNavy
val SovereignNavySurface = SurfaceDarkElevated
val SovereignGold = GotongRoyongGold
val SovereignGoldLight = GotongRoyongGoldLight
val SovereignGoldDark = GotongRoyongGoldDark
val SovereignCrimson = CyberRed
val SovereignAmber = WarningAmber
val SovereignEmerald = TrustGreen
val SovereignSlate = Color(0xFF64748B)
val SovereignSteel = Color(0xFF334155)
val BgLight = SurfaceLight
val SurfaceVariantLight = SurfaceLightVariant

// Legacy purple retained for compat
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)
val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
