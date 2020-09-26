package br.com.daniellopes.netflixreplication.kotlin.datasource.retrofit

import br.com.daniellopes.netflixreplication.kotlin.Categories
import br.com.daniellopes.netflixreplication.kotlin.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EndPointRetrofit {

    companion object {
        const val BASE_URL = "https://tiagoaguiar.co/api/netflix/"
    }

    @GET("home")
    fun getCategories(): Call<Categories>

    @GET("{id}")
    fun getMovies(@Path("id") id: Int): Call<Movie>
}