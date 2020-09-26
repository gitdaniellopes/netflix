package br.com.daniellopes.netflixreplication.kotlin.datasource.retrofit

import br.com.daniellopes.netflixreplication.kotlin.datasource.retrofit.EndPointRetrofit.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetflixAPI {
    companion object {
        fun retrofit(): Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}