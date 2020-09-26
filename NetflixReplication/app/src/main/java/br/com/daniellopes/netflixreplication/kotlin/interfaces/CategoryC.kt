package br.com.daniellopes.netflixreplication.kotlin.interfaces

import br.com.daniellopes.netflixreplication.kotlin.Categories

interface CategoryC {
    fun onSuccess(categories: Categories)

    fun onError(message: String)

    fun onComplete()
}