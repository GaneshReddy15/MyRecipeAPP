// Declare the package name for the Kotlin file
package com.example.myrecipeapp

// Define a data class named "Category" to represent a single category
data class Category(
    // Declare a property to store the category ID
    val idCategory: String,
    // Declare a property to store the category name
    val strCategory: String,
    // Declare a property to store the URL of the category thumbnail image
    val strCategoryThumb: String,
    // Declare a property to store the description of the category
    val strCategoryDescription: String
)

// Define a data class named "CategoriesResponse" to represent the response from the API endpoint
data class CategoriesResponse(
// Declare a property to store a list of categories
    val categories: List<Category>
)
