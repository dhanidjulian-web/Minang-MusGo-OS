package com.agon.app.data

import androidx.compose.ui.graphics.Color

enum class ProposalStatus { MUSYAWARAH, KONSENSUS, EKSEKUSI, TERTUNDA }
enum class ProposalDomain { GOVERNANCE, SECURITY, INFRA, MONETIZATION, FEDERATION }
enum class AgentRole { GOVERNOR, PLANNER, EXECUTOR, AUDITOR, GUARDIAN, ORCHESTRATOR }
enum class AgentState { ACTIVE, VALIDATING, ISOLATED, HEALING }
enum class TrustLevel { SOVEREIGN, VERIFIED, PENDING, ANOMALOUS }
enum class TaskColumn { BACKLOG, GOTONG, SELESAI }

data class Proposal(
    val id: String,
    val title: String,
    val domain: ProposalDomain,
    val status: ProposalStatus,
    val quorum: Float, // 0..1
    val votesFor: Int,
    val votesAgainst: Int,
    val abstain: Int,
    val initiator: String,
    val risk: String,
    val impact: String,
    val createdAt: String,
    val description: String
)

data class GotongTask(
    val id: String,
    val title: String,
    val column: TaskColumn,
    val assignee: String,
    val priority: String,
    val domain: String
)

data class AgentNode(
    val id: String,
    val name: String,
    val role: AgentRole,
    val state: AgentState,
    val trust: TrustLevel,
    val load: Float,
    val uptime: String,
    val handled: Int
)

data class AuditEvent(
    val id: String,
    val time: String,
    val actor: String,
    val action: String,
    val severity: String,
    val hash: String,
    val verified: Boolean
)

data class ArchitectureLayer(
    val order: Int,
    val name: String,
    val subtitle: String,
    val modules: List<String>,
    val color: Color,
    val status: String
)

data class MonetizationStream(
    val name: String,
    val model: String,
    val arr: String,
    val market: String,
    val icon: String
)

object MusgoRepository {
    val proposals = listOf(
        Proposal("MUS-2026-041", "Konstitusi AI: Immutable Governance Ratifikasi", ProposalDomain.GOVERNANCE, ProposalStatus.MUSYAWARAH, 0.72f, 12, 2, 3, "Governor-AI", "Medium", "Sovereign Civilization", "03 Sep 2026 07:00", "Ratifikasi konstitusi untuk validasi replay-safe dan constitutional-aware pada seluruh agent federation."),
        Proposal("MUS-2026-042", "Zero-Trust Federation untuk Inter-Node Sync", ProposalDomain.SECURITY, ProposalStatus.KONSENSUS, 0.94f, 17, 0, 0, "Guardian-AI", "High", "Federation Security", "02 Sep 2026 21:30", "Enkripsi mutual-TLS + attestation antar node dengan quantum-resilient KEM."),
        Proposal("MUS-2026-043", "SaaS Licensing: White-Label Enterprise Tier", ProposalDomain.MONETIZATION, ProposalStatus.EKSEKUSI, 1.0f, 15, 1, 1, "Founder Dhani", "Low", "Revenue Scale", "01 Sep 2026 14:10", "Peluncuran paket white-label untuk B2B dengan recurring ARR model."),
        Proposal("MUS-2026-044", "Self-Healing Fabric v2.1 Rollout", ProposalDomain.INFRA, ProposalStatus.MUSYAWARAH, 0.58f, 8, 4, 5, "Orchestrator-AI", "Medium", "Resilience", "03 Sep 2026 09:12", "Orkestrasi healing otomatis jika anomalous detection > 0.85 threshold."),
        Proposal("MUS-2026-045", "Distributed Consensus: PBFT untuk Civilization Ledger", ProposalDomain.FEDERATION, ProposalStatus.TERTUNDA, 0.31f, 4, 6, 2, "Auditor-AI", "High", "Ledger Integrity", "03 Sep 2026 10:00", "Migrasi ledger ke PBFT dengan immutable audit trail dan replay-safe guarantee.")
    )
    val tasks = listOf(
        GotongTask("GT-01", "Audit Konstitusi Layer Kernel", TaskColumn.GOTONG, "Auditor AI", "HIGH", "Governance"),
        GotongTask("GT-02", "Implementasi Quantum KEM di Vault", TaskColumn.BACKLOG, "Guardian AI", "CRITICAL", "Security"),
        GotongTask("GT-03", "Desain Monetization Dashboard ARR", TaskColumn.GOTONG, "Planner AI", "MEDIUM", "Business"),
        GotongTask("GT-04", "Federation Sync 12 Node", TaskColumn.SELESAI, "Orchestrator", "HIGH", "Infra"),
        GotongTask("GT-05", "Trust Propagation Protocol", TaskColumn.BACKLOG, "Governor AI", "HIGH", "Trust"),
        GotongTask("GT-06", "Self-Healing Drill Simulation", TaskColumn.SELESAI, "Executor AI", "MEDIUM", "Resilience")
    )
    val agents = listOf(
        AgentNode("A-01", "Governor-Prime", AgentRole.GOVERNOR, AgentState.ACTIVE, TrustLevel.SOVEREIGN, 0.42f, "99.98%", 1842),
        AgentNode("A-02", "Planner-Nusantara", AgentRole.PLANNER, AgentState.VALIDATING, TrustLevel.VERIFIED, 0.68f, "99.91%", 932),
        AgentNode("A-03", "Executor-Gotong", AgentRole.EXECUTOR, AgentState.ACTIVE, TrustLevel.VERIFIED, 0.81f, "99.95%", 2103),
        AgentNode("A-04", "Auditor-Constitutional", AgentRole.AUDITOR, AgentState.ACTIVE, TrustLevel.SOVEREIGN, 0.35f, "100%", 5421),
        AgentNode("A-05", "Guardian-ZeroTrust", AgentRole.GUARDIAN, AgentState.HEALING, TrustLevel.PENDING, 0.92f, "98.7%", 412),
        AgentNode("A-06", "Orchestrator-Federation", AgentRole.ORCHESTRATOR, AgentState.ISOLATED, TrustLevel.ANOMALOUS, 0.99f, "97.2%", 88)
    )
    val audits = listOf(
        AuditEvent("EV-9001", "09:42:11", "Governor-Prime", "Constitutional Validate", "INFO", "0x9f3a...c1d2", true),
        AuditEvent("EV-9002", "09:41:03", "Guardian-ZeroTrust", "Anomaly Detected", "WARN", "0x4b12...8a9f", true),
        AuditEvent("EV-9003", "09:38:22", "Auditor-Constitutional", "Immutable Commit", "INFO", "0xab11...0033", true),
        AuditEvent("EV-9004", "09:35:10", "Executor-Gotong", "Rollback Executed", "CRITICAL", "0x00ff...991a", false),
        AuditEvent("EV-9005", "09:30:55", "Orchestrator", "Federation Sync", "INFO", "0x77c2...1122", true),
        AuditEvent("EV-9006", "09:28:00", "Planner-Nusantara", "Proposal MUS-041 Validated", "INFO", "0x1234...abcd", true)
    )
    val layers = listOf(
        ArchitectureLayer(1, "CIVILIZATION LAYER", "Sovereign AI Operating Civilization", listOf("Governance Engine", "Constitutional AI", "Federation Trust"), Color(0xFFC9A86A), "SOVEREIGN"),
        ArchitectureLayer(2, "FEDERATION LAYER", "Distributed Consensus & Trust Propagation", listOf("PBFT Ledger", "Zero-Trust Mesh", "Quantum KEM"), Color(0xFF1EB5A6), "ACTIVE"),
        ArchitectureLayer(3, "ORCHESTRATION LAYER", "Multi-Agent Swarm & Event Bus", listOf("Swarm Topology", "Event Bus", "Policy Engine"), Color(0xFF7BA7C9), "SCALING"),
        ArchitectureLayer(4, "KERNEL LAYER", "Immutable Core & Self-Healing Fabric", listOf("Kernel Guard", "Replay-Safe Store", "Healing Fabric"), Color(0xFFE63946), "SECURED")
    )
}
