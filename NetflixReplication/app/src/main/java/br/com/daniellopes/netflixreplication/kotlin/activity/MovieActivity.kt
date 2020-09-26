package br.com.daniellopes.netflixreplication.kotlin.activity

import android.app.Dialog
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import br.com.daniellopes.netflixreplication.R
import br.com.daniellopes.netflixreplication.kotlin.Movie
import br.com.daniellopes.netflixreplication.kotlin.MovieSimilar
import br.com.daniellopes.netflixreplication.kotlin.adapter.MovieSimilarAdapter
import br.com.daniellopes.netflixreplication.kotlin.datasource.MovieDetailDS
import br.com.daniellopes.netflixreplication.kotlin.presentation.MovieP
import br.com.daniellopes.netflixreplication.kotlin.util.LoadingDialog
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie.*

class MovieActivity : AppCompatActivity() {

    private var id: Int = 0
    private val adapterMovie by lazy {
        MovieSimilarAdapter(movies(), this)
    }

    private val recycle by lazy {
        recycle_view_similar
    }

    private val loadingDialog by lazy {
        LoadingDialog(this, Dialog(this))
    }

    companion object {
        const val KEY_ID = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        setSupportActionBar(toolbar)

        configToolbar()
        getMovieSend()
        configRecycle()

        val movieDetailDS = MovieDetailDS()
        val presenter = MovieP(this, movieDetailDS)

        presenter.getMovie(id)

    }

    private fun movies(): ArrayList<MovieSimilar> = arrayListOf()

    private fun configRecycle() {
        with(recycle) {
            adapter = adapterMovie
            layoutManager = GridLayoutManager(this@MovieActivity, 3)
        }
    }

    private fun getMovieSend() {
        intent.extras?.let {
            id = it.getInt(KEY_ID)
        }
    }

    private fun configToolbar() {
        supportActionBar?.let { toolbar ->
            toolbar.setDisplayHomeAsUpEnabled(true)
            toolbar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_white_24)
            toolbar.title = null
        }
    }

    fun showMovieDetail(movie: Movie) {
        text_view_movie_title.text = movie.title
        text_view_movie_desc.text = movie.desc
        text_view_movie_cast.text = movie.cast
        Glide.with(this).load(movie.coverUrl).into(image_view_movie_cover)
        showMoviesSimilar(movie.movieSimilar)
    }

    private fun showMoviesSimilar(movies: List<MovieSimilar>) {
        adapterMovie.addMovies(movies)
        adapterMovie.notifyDataSetChanged()
    }

    fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showProgressBar() {
        loadingDialog.startLoadingDialog()
    }

    fun hideProgressBar() {
        loadingDialog.dismissDialog()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}