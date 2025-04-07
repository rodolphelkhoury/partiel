package com.example.partiel

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
import com.example.partiel.components.InputTextField
import com.example.partiel.viewModels.WelcomePageViewModel


@Composable
fun WelcomePage(
    viewModel: WelcomePageViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Add Your First name and last name",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(30.dp))

        InputTextField(
            value = viewModel.firstName,
            onValueChange = viewModel::onFirstNameChange,
            label = "First Name"
        )

        Spacer(modifier = Modifier.height(5.dp))

        InputTextField(
            value = viewModel.lastName,
            onValueChange = viewModel::onLastNameChange,
            label = "Last Name"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            // Handle click logic here, e.g., navigate to next page
        }) {
            Text("Continue")
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}
