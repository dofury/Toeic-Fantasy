package com.example.toeicfantasy.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.material.icons.filled.EMobiledata
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.toeicfantasy.R
import com.example.toeicfantasy.data.Mission
import com.example.toeicfantasy.data.Stage1
import com.example.toeicfantasy.ui.theme.whiteGray
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MissionScreen(){
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        var selectedTabIndex by remember { mutableIntStateOf(0) }
        Column {
            TabRow(selectedTabIndex = selectedTabIndex) {
                MissionTab("채용",false) {
                    selectedTabIndex = 0
                }

                MissionTab("규칙,법률",false) {
                    selectedTabIndex = 1
                }

                MissionTab("일반사무(1)",false) {
                    selectedTabIndex = 2
                }
            }

            when(selectedTabIndex) {
                0 -> {
                    MissionContents()
                }
                1 -> {
                    Text("규칙")
                }
            }
        }

    }
}

@Composable
fun MissionContents(){
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(id = R.drawable.test),
            contentDescription = "company")

        MissionContent(Stage1.C101)
        MissionContent(Stage1.C102)
        MissionContent(Stage1.C103)
    }
}
@Composable
fun MissionContent(mission: Mission){
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(mission.name)
        Box(modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)) {
            Divider(
                color = Color.Gray,
                thickness = 1.dp
            )
        }

        MissionProgressBar(mission)
        Row {
            Card {
                
            }
        }
        
    }
    Divider(
        color = Color.Gray,
        thickness = 1.dp
    )
}


@Composable
fun MissionProgressBar(mission: Mission){
    var currentProgress by remember { mutableFloatStateOf(0f) }
    var loading by remember { mutableStateOf(false)}
    val scope = rememberCoroutineScope()// Create Coroutine Scope
    var count by remember { mutableIntStateOf(0)}

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        if(loading){
            LinearProgressIndicator(
                progress = currentProgress,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                IconTextBox("Point",Icons.Filled.Paid,mission.point)
                Spacer(Modifier.height(4.dp))
                IconTextBox("Exp",Icons.Filled.EMobiledata,mission.exp)
            }
            Button(
                modifier =Modifier
                    .height(IntrinsicSize.Min),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = whiteGray, contentColor = Color.Blue),
                onClick = {
                loading = true
                scope.launch {
                    loadProgress {progress->
                        currentProgress = progress
                    }
                    loading = false
                    count++

                }
            },
                enabled = !loading){
                Text("Run")
            }

        }



        Text(count.toString())

    }
}

@Composable
private fun IconTextBox(name: String, image: ImageVector, value: Int) {
    Row(
        modifier = Modifier
            .width(64.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(whiteGray)
            .padding(4.dp)
    ) {
        Icon(
            imageVector = image,
            contentDescription = name
        )
        Text(value.toString())
    }
}

suspend fun loadProgress(updateProgress: (Float) -> Unit){
    for (i in 1..100) {
        updateProgress(i.toFloat() / 100)
        delay(5)
    }
}
@Composable
fun MissionTab(name: String,selected: Boolean,onClick: () -> Unit){
    Tab(selected, onClick) {
        Column(
            Modifier
                .padding(10.dp)
                .height(50.dp)
        ) {
            Box(
                Modifier
                    .size(10.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(
                        color = if (selected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.background
                    )
            )
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
@Composable
fun MissionsTab(name: String,selected: Boolean,onClick: () -> Unit){
    Tab(selected, onClick) {
        Column(
            Modifier
                .padding(10.dp)
                .height(50.dp)
        ) {
            Box(
                Modifier
                    .size(10.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(
                        color = if (selected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.background
                    )
            )
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}