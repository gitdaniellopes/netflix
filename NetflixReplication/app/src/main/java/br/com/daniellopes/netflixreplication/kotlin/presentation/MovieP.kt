package br.com.daniellopes.netflixreplication.kotlin.presentation

import br.com.daniellopes.netflixreplication.kotlin.Movie
import br.com.daniellopes.netflixreplication.kotlin.activity.MovieActivity
import br.com.daniellopes.netflixreplication.kotlin.datasource.MovieDetailDS
import br.com.daniellopes.netflixreplication.kotlin.interfaces.MovieDetailC

class MovieP(
        private val view: MovieActivity,
        private val datasource: MovieDetailDS
) : MovieDetailC {

    fun getMovie(id: Int) {
        this.view.showProgressBar()
        datasource.findMovie(this, id)
    }

    override fun onSuccess(movie: Movie) {
       this.view.showMovieDetail(movie)
    }

    override fun onError(message: String) {
       this.view.showFailure(message)
    }

    override fun onComplete() {
       this.view.hideProgressBar()
    }
}