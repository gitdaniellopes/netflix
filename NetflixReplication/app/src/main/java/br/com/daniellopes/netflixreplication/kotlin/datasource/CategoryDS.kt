package br.com.daniellopes.netflixreplication.kotlin.datasource

import br.com.daniellopes.netflixreplication.kotlin.Categories
import br.com.daniellopes.netflixreplication.kotlin.datasource.retrofit.EndPointRetrofit
import br.com.daniellopes.netflixreplication.kotlin.datasource.retrofit.NetflixAPI
import br.com.daniellopes.netflixreplication.kotlin.interfaces.CategoryC
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryDS {
    fun find(callBack: CategoryC) {
        NetflixAPI.retrofit().create(EndPointRetrofit::class.java)
                .getCategories().enqueue(object : Callback<Categories> {
                    override fun onFailure(call: Call<Categories>, t: Throwable) {
                        t.message?.let { error ->
                            callBack.onError(error)
                        }
                    }

                    override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                        if (response.isSuccessful) {
                            response.body()?.let { categories ->
                                callBack.onSuccess(categories)
                            }
                        }
                        callBack.onComplete()
                    }

                })
    }
}