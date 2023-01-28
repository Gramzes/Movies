package com.gramzin.movies.model

data class Movie(val id: Long, val name: String, var alternativeName: String, val year: String,
            val movieLength: String, val countries: List<String>, val genres: List<String>,
            val rating: String, val posterPreviewURL: String? ){
}