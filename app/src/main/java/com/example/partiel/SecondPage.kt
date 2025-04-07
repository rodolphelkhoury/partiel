package com.example.partiel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondPage(
    firstName: String,
    lastName: String,
    navController: NavController
) {
    val timePickerState = rememberTimePickerState(
        initialHour = 1,
        initialMinute = 0,
        is24Hour = false
    )

    val selectedHour = if (timePickerState.hour == 0) 12 else timePickerState.hour % 12


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Hello $firstName $lastName!, Pick an number",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(30.dp))

        TimePicker(
            state = timePickerState,
            colors = TimePickerDefaults.colors()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            navController.navigate("play/${firstName}/${lastName}/$selectedHour")
        }) {
            Text("Play")
        }


        Spacer(modifier = Modifier.height(20.dp))
    }
}
