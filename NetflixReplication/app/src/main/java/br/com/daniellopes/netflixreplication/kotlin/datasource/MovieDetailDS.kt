package br.com.daniellopes.netflixreplication.kotlin.datasource

import br.com.daniellopes.netflixreplication.kotlin.Movie
import br.com.daniellopes.netflixreplication.kotlin.datasource.retrofit.EndPointRetrofit
import br.com.daniellopes.netflixreplication.kotlin.datasource.retrofit.NetflixAPI
import br.com.daniellopes.netflixreplication.kotlin.interfaces.MovieDetailC
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailDS {
    fun findMovie(callback: MovieDetailC, id: Int) {
        NetflixAPI.retrofit().create(EndPointRetrofit::class.java)
                .getMovies(id).enqueue(object : Callback<Movie> {
                    override fun onFailure(call: Call<Movie>, t: Throwable) {
                        t.message?.let { error ->
                            callback.onError(error)
                        }
                    }

                    override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                        if (response.isSuccessful) {
                            response.body()?.let { movie ->
                                callback.onSuccess(movie)
                            }
                        }
                        callback.onComplete()
                    }

                })
    }
}