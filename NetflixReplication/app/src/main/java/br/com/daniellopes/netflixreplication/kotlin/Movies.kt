package br.com.daniellopes.netflixreplication.kotlin

import com.google.gson.annotations.SerializedName

data class Categories(@SerializedName("category")
                      val categories: List<Category>)

data class Category(val id: Int = 0,
                    val title: String = "",
                    @SerializedName("movie") val movies: List<Movie> = arrayListOf())


data class Movie(val id: Int = 0,
                 @SerializedName("cover_url")
                 val coverUrl: String = "",
                 val title: String = "",
                 val desc: String = "",
                 val cast: String = "",
                 @SerializedName("movie") val movieSimilar: List<MovieSimilar> = arrayListOf())

data class MovieSimilar(val id: Int = 0,
                        @SerializedName("cover_url") val coverUrl: String)