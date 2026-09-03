package com.agon.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agon.app.data.TaskColumn
import com.agon.app.ui.theme.*
import com.agon.app.viewmodel.MusgoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GotongScreen(vm: MusgoViewModel){
    var showAdd by remember { mutableStateOf(false) }
    var newTaskTitle by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(title = { Column { Text("GOTONG-ROYONG BOARD", fontWeight = FontWeight.Black, fontSize = 14.sp, letterSpacing = 1.sp); Text("Eksekusi kolektif setelah mufakat", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant) } }, navigationIcon = { Box(Modifier.padding(start = 12.dp).size(36.dp).clip(CircleShape).background(SovereignTeal), contentAlignment = Alignment.Center){ Icon(Icons.Filled.Groups, null, tint = Color.White) } })
        },
        floatingActionButton = { ExtendedFloatingActionButton(onClick = { showAdd = true }, containerColor = SovereignTealDark, contentColor = Color.White, icon = { Icon(Icons.Filled.AddTask, null) }, text = { Text("Tugas Baru", fontWeight = FontWeight.Bold) }) }
    ){ padding ->
        Column(Modifier.fillMaxSize().padding(padding)){
            // summary bar
            Row(Modifier.padding(horizontal = 16.dp, vertical = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)){
                SummaryChip("Backlog ${vm.tasks.count{it.column==TaskColumn.BACKLOG}}", SovereignAmber, Modifier.weight(1f))
                SummaryChip("Gotong ${vm.tasks.count{it.column==TaskColumn.GOTONG}}", SovereignTeal, Modifier.weight(1f))
                SummaryChip("Selesai ${vm.tasks.count{it.column==TaskColumn.SELESAI}}", SovereignEmerald, Modifier.weight(1f))
            }
            Row(Modifier.fillMaxSize().padding(horizontal = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)){
                TaskColumnUi("BACKLOG", TaskColumn.BACKLOG, SovereignAmber, vm, Modifier.weight(1f))
                TaskColumnUi("GOTONG", TaskColumn.GOTONG, SovereignTeal, vm, Modifier.weight(1f))
                TaskColumnUi("SELESAI", TaskColumn.SELESAI, SovereignEmerald, vm, Modifier.weight(1f))
            }
        }
    }
    if(showAdd){
        AlertDialog(onDismissRequest = { showAdd=false }, title = { Text("Tugas Gotong-Royong Baru", fontWeight = FontWeight.Bold) }, text = { Column{ Text("Founder Dhani, tugas akan masuk Backlog lalu dapat digotong bersama agent swarm.", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant); Spacer(Modifier.height(12.dp)); OutlinedTextField(value = newTaskTitle, onValueChange = { newTaskTitle = it }, label = { Text("Judul Tugas") }, placeholder = { Text("Contoh: Validasi PBFT Ledger") }, modifier = Modifier.fillMaxWidth()) } }, confirmButton = { Button(onClick = { if(newTaskTitle.isNotBlank()){ vm.addTask(newTaskTitle); newTaskTitle=""; showAdd=false } }, colors = ButtonDefaults.buttonColors(containerColor = SovereignTealDark)){ Text("Tambah") } }, dismissButton = { TextButton(onClick = { showAdd=false }){ Text("Batal") } })
    }
}

@Composable
private fun SummaryChip(text: String, color: Color, modifier: Modifier = Modifier){
    Surface(modifier = modifier, shape = RoundedCornerShape(10.dp), color = color.copy(0.14f)) { Text(text, Modifier.padding(vertical = 8.dp).wrapContentWidth(Alignment.CenterHorizontally), fontSize = 11.sp, fontWeight = FontWeight.Black, color = color) }
}

@Composable
private fun TaskColumnUi(title: String, column: TaskColumn, accent: Color, vm: MusgoViewModel, modifier: Modifier = Modifier){
    val tasks = vm.tasks.filter { it.column == column }
    Column(modifier = modifier.fillMaxHeight().clip(RoundedCornerShape(16.dp)).background(MaterialTheme.colorScheme.surfaceVariant.copy(0.5f)).padding(8.dp)){
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(4.dp)){
            Box(Modifier.size(8.dp).clip(CircleShape).background(accent))
            Spacer(Modifier.width(6.dp))
            Text(title, fontWeight = FontWeight.Black, fontSize = 11.sp, letterSpacing = 0.8.sp, color = MaterialTheme.colorScheme.onSurface)
            Spacer(Modifier.weight(1f))
            Surface(color = accent.copy(0.14f), shape = CircleShape){ Text("${tasks.size}", Modifier.padding(horizontal = 8.dp, vertical = 2.dp), fontSize = 11.sp, fontWeight = FontWeight.Black, color = accent) }
        }
        HorizontalDivider(Modifier.padding(vertical = 6.dp), color = MaterialTheme.colorScheme.outlineVariant)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxSize()){
            items(tasks, key = { it.id }){ t ->
                Card(shape = RoundedCornerShape(14.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), elevation = CardDefaults.cardElevation(1.dp)) {
                    Column(Modifier.padding(12.dp)){
                        Row(verticalAlignment = Alignment.CenterVertically){
                            Surface(color = when(t.priority){
                                "CRITICAL" -> SovereignCrimson.copy(0.14f)
                                "HIGH" -> SovereignAmber.copy(0.14f)
                                else -> SovereignTeal.copy(0.14f)
                            }, shape = RoundedCornerShape(6.dp)){ Text(t.priority, Modifier.padding(horizontal = 6.dp, vertical = 2.dp), fontSize = 9.sp, fontWeight = FontWeight.Black, color = when(t.priority){ "CRITICAL" -> SovereignCrimson; "HIGH" -> SovereignAmber; else -> SovereignTeal }) }
                            Spacer(Modifier.weight(1f))
                            Text(t.id, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        Spacer(Modifier.height(8.dp))
                        Text(t.title, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface, lineHeight = 15.sp)
                        Spacer(Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically){
                            Box(Modifier.size(20.dp).clip(CircleShape).background(SovereignNavy), contentAlignment = Alignment.Center){ Text(t.assignee.take(1), color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold) }
                            Spacer(Modifier.width(6.dp))
                            Column{
                                Text(t.assignee, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                                Text(t.domain, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                        Spacer(Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)){
                            if(column != TaskColumn.BACKLOG){
                                OutlinedButton(onClick = { vm.moveTask(t.id, if(column==TaskColumn.GOTONG) TaskColumn.BACKLOG else TaskColumn.GOTONG) }, shape = RoundedCornerShape(8.dp), contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp), modifier = Modifier.weight(1f)) { Icon(Icons.Filled.ArrowBack, null, Modifier.size(12.dp)); Spacer(Modifier.width(4.dp)); Text(if(column==TaskColumn.SELESAI) "Ulang" else "Backlog", fontSize = 10.sp) }
                            }
                            if(column != TaskColumn.SELESAI){
                                Button(onClick = { vm.moveTask(t.id, if(column==TaskColumn.BACKLOG) TaskColumn.GOTONG else TaskColumn.SELESAI) }, shape = RoundedCornerShape(8.dp), colors = ButtonDefaults.buttonColors(containerColor = accent), contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp), modifier = Modifier.weight(1f)) { Text(if(column==TaskColumn.BACKLOG) "Gotong" else "Selesai", fontSize = 10.sp, fontWeight = FontWeight.Bold); Spacer(Modifier.width(4.dp)); Icon(Icons.Filled.ArrowForward, null, Modifier.size(12.dp)) }
                            } else {
                                Surface(color = SovereignEmerald.copy(0.14f), shape = RoundedCornerShape(8.dp), modifier = Modifier.weight(1f)){ Row(Modifier.padding(vertical = 6.dp).fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){ Icon(Icons.Filled.CheckCircle, null, Modifier.size(14.dp), tint = SovereignEmerald); Spacer(Modifier.width(4.dp)); Text("Selesai", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = SovereignEmerald) } }
                            }
                        }
                    }
                }
            }
            if(tasks.isEmpty()){
                item { Box(Modifier.fillMaxWidth().padding(vertical = 24.dp), contentAlignment = Alignment.Center){ Text("Kosong", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant) } }
            }
        }
    }
}
