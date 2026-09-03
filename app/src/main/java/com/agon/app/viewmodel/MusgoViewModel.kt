package com.agon.app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.agon.app.data.*

class MusgoViewModel : ViewModel() {
    val proposals = mutableStateListOf<Proposal>().apply { addAll(MusgoRepository.proposals) }
    val tasks = mutableStateListOf<GotongTask>().apply { addAll(MusgoRepository.tasks) }
    val agents = mutableStateListOf<AgentNode>().apply { addAll(MusgoRepository.agents) }
    val audits = mutableStateListOf<AuditEvent>().apply { addAll(MusgoRepository.audits) }

    var selectedDomain by mutableStateOf<ProposalDomain?>(null)
    var selectedStatus by mutableStateOf<ProposalStatus?>(null)
    var auditFilter by mutableStateOf<String?>(null)
    var searchAudit by mutableStateOf("")

    fun vote(proposalId: String, type: String) {
        val idx = proposals.indexOfFirst { it.id == proposalId }
        if (idx == -1) return
        val p = proposals[idx]
        val updated = when(type){
            "FOR" -> p.copy(votesFor = p.votesFor + 1, quorum = (p.quorum + 0.06f).coerceAtMost(1f))
            "AGAINST" -> p.copy(votesAgainst = p.votesAgainst + 1)
            else -> p.copy(abstain = p.abstain + 1)
        }
        // Auto promote if quorum high
        val final = if(updated.quorum >= 0.85f && updated.status == ProposalStatus.MUSYAWARAH) updated.copy(status = ProposalStatus.KONSENSUS) else updated
        proposals[idx] = final
        audits.add(0, AuditEvent("EV-${(9000..9999).random()}", "09:44:02", "Founder Dhani", "Vote $type pada ${p.id}", "INFO", "0x" + (1000..9999).random().toString()+"..."+"${(1000..9999).random()}", true))
    }

    fun moveTask(taskId: String, newColumn: TaskColumn){
        val idx = tasks.indexOfFirst { it.id == taskId }
        if(idx!=-1) tasks[idx] = tasks[idx].copy(column = newColumn)
    }

    fun toggleAgent(id: String){
        val idx = agents.indexOfFirst { it.id == id }
        if(idx==-1) return
        val a = agents[idx]
        val nextState = when(a.state){
            AgentState.ISOLATED -> AgentState.HEALING
            AgentState.HEALING -> AgentState.ACTIVE
            AgentState.ACTIVE -> AgentState.ISOLATED
            AgentState.VALIDATING -> AgentState.ACTIVE
        }
        val nextTrust = if(nextState==AgentState.ISOLATED) TrustLevel.ANOMALOUS else if(nextState==AgentState.ACTIVE) TrustLevel.VERIFIED else a.trust
        agents[idx] = a.copy(state = nextState, trust = nextTrust)
        audits.add(0, AuditEvent("EV-${(9000..9999).random()}", "09:45:11", "Guardian-ZeroTrust", "Agent ${a.name} -> $nextState", if(nextState==AgentState.ISOLATED) "CRITICAL" else "INFO", "0x" + (1000..9999).random().toString()+"...heal", true))
    }

    fun addProposal(title: String, domain: ProposalDomain){
        val newId = "MUS-2026-0${46 + proposals.size}"
        proposals.add(0, Proposal(newId, title, domain, ProposalStatus.MUSYAWARAH, 0.12f, 1, 0, 0, "Founder Dhani", "Medium", "Civilization", "03 Sep 2026 09:45", "Inisiatif sovereign baru dari Founder untuk ekosistem MUSGO-OS 2in1."))
    }

    fun addTask(title: String){
        tasks.add(GotongTask("GT-0${tasks.size+1}", title, TaskColumn.BACKLOG, "Founder Dhani", "MEDIUM", "Gotong"))
    }

    fun filteredProposals(): List<Proposal> {
        return proposals.filter { p ->
            (selectedDomain==null || p.domain==selectedDomain) && (selectedStatus==null || p.status==selectedStatus)
        }
    }
}
