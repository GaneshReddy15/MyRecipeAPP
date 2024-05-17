package com.example.myrecipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

// Define a Composable function named RecipeScreen, which represents the UI screen for displaying recipes
@Composable
fun RecipeScreen(modifier: Modifier = Modifier) {
    // Obtain an instance of the MainViewModel using the viewModel() function from Jetpack Compose
    val recipeViewModel: MainViewModel = viewModel()
    // Observe the categoriesState LiveData object from the MainViewModel to get the current state
    val viewState by recipeViewModel.categoriesState

    // Display different UI components based on the current state of the viewstate
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            // If the viewstate indicates that data is loading, display a circular progress indicator
            viewState.loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            // If an error occurred while fetching data, display an error message
            viewState.error != null -> {
                Text(text = "Error Occurred")
            }
            // If data is available, display the CategoryScreen with the list of categories
            else -> {
                CategoryScreen(categories = viewState.list)
            }
        }
    }
}

// Define a Composable function named CategoryScreen, which represents the UI screen for displaying categories
@Composable
fun CategoryScreen(categories: List<Category>) {
    // Display a LazyVerticalGrid layout to show categories in a grid layout
    LazyVerticalGrid(
        // Specify the number of columns in the grid layout
        GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        // Iterate over the list of categories and display each category using the CategoryItem Composable
        items(categories) { category ->
            CategoryItem(category = category)
        }
    }
}

// Define a Composable function named CategoryItem, which represents an individual category item in the grid
@Composable
fun CategoryItem(category: Category) {
    // Display an Image for the category thumbnail using the rememberAsyncImagePainter function
    Image(
        painter = rememberAsyncImagePainter(category.strCategoryThumb),
        contentDescription = null,
        // Set the modifier to fill the available space and maintain aspect ratio
        modifier = Modifier.fillMaxSize().aspectRatio(1f)
    )
    // Display the category name below the thumbnail image
    Text(
        text = category.strCategory,
        color = Color.Black,
        // Set the text style to bold
        style = TextStyle(fontWeight = FontWeight.Bold),
        // Add padding to the top of the text
        modifier = Modifier.padding(top = 4.dp)
    )
}
