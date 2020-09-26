package br.com.daniellopes.netflixreplication.kotlin.interfaces

import br.com.daniellopes.netflixreplication.kotlin.Movie

interface MovieDetailC {
    fun onSuccess(movie: Movie)

    fun onError(message: String)

    fun onComplete()
}