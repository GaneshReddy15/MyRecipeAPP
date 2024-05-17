package com.example.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

// Define a ViewModel class named MainViewModel, which is responsible for managing UI-related data for the Recipe app
class MainViewModel: ViewModel() {
    // Define a mutable state to hold the current state of categories data
    private val _categorieState = mutableStateOf(RecipeState())
    // Define a State object to expose the categories state to observers
    val categoriesState: State<RecipeState> = _categorieState

    // Initialize the ViewModel and fetch categories data upon initialization
    init {
        fetchCategories()
    }

    // Define a private function to fetch categories data from the API
    private fun fetchCategories() {
        // Launch a coroutine in the viewModelScope to perform the asynchronous API call
        viewModelScope.launch {
            try {
                // Call the getCategories function from the recipeService to fetch categories
                val response = recipeService.getCategories()
                // Update the categoriesState with the fetched data and mark loading as false
                _categorieState.value = _categorieState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                // If an exception occurs during the API call, update categoriesState with the error message
                _categorieState.value = _categorieState.value.copy(
                    loading = false,
                    error = "Error fetching Categories ${e.message}"
                )
            }
        }
    }

    // Define a data class named RecipeState to represent the state of categories data
    data class RecipeState(
        // Flag indicating whether data is currently being loaded
        val loading: Boolean = true,
        // List of categories fetched from the API
        val list: List<Category> = emptyList(),
        // Error message if an error occurs during data fetching
        val error: String? = null
    )
}
