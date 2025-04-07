package com.example.partiel.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class WelcomePageViewModel : ViewModel() {
    var firstName by mutableStateOf("")

    var lastName by mutableStateOf("")

    fun onFirstNameChange(newValue: String) {
        firstName = newValue
    }

    fun onLastNameChange(newValue: String) {
        lastName = newValue
    }
}
