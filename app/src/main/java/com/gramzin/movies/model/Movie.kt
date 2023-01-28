package com.gramzin.movies.model

class Movie(val id: Long, val name: String, val alternativeName: String, val year: Int,
            val movieLength: Int, val countries: List<String>, val typeNumber: Int, val genres: List<String>,
            val description: String, val rating: Int, val posterFullUrl: String?,
            val posterPreviewURL: String? ){
    var type: String? = null
}