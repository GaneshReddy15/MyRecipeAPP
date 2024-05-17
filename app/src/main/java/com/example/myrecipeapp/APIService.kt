// Declare the package name for the Kotlin file
package com.example.myrecipeapp

// Import the Retrofit library for making HTTP requests
import retrofit2.Retrofit
// Import the GsonConverterFactory for converting JSON responses into Kotlin objects
import retrofit2.converter.gson.GsonConverterFactory
// Import the GET annotation to specify the HTTP request method
import retrofit2.http.GET

// Create a Retrofit instance with a base URL for making API requests
private val retrofit = Retrofit.Builder()
    // Specify the base URL for the API requests
    .baseUrl("https://www.themealdb.com/api/json/v1/1/") // Make sure to include the protocol (https://)
    // Add a converter factory to handle JSON response parsing using Gson
    .addConverterFactory(GsonConverterFactory.create())
    // Build the Retrofit instance
    .build()

// Create an instance of the API service interface using the Retrofit instance
val recipeService = retrofit.create(APIService::class.java)

// Define an interface to represent the API endpoints and their corresponding methods
interface APIService {
    // Define a suspend function to retrieve categories from the API
    @GET("categories.php")
    // Specify the return type as CategoriesResponse, indicating the expected response type
    suspend fun getCategories(): CategoriesResponse
}
