package br.com.daniellopes.netflixreplication.kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.daniellopes.netflixreplication.R
import br.com.daniellopes.netflixreplication.kotlin.MovieSimilar
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie_similar.view.*

class MovieSimilarAdapter(private val movies: MutableList<MovieSimilar>,
                          private val context: Context) : RecyclerView.Adapter<MovieSimilarAdapter.MovieSimilarHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSimilarHolder =
            MovieSimilarHolder(LayoutInflater.from(context)
                    .inflate(R.layout.item_movie_similar, parent, false))

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieSimilarHolder, position: Int) =
            holder.bind(movies[position])

    fun addMovies(movies: List<MovieSimilar>) {
        this.movies.clear()
        this.movies.addAll(movies)
    }

    inner class MovieSimilarHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieSimilar) {
            with(itemView) {
                Glide.with(context)
                        .load(movie.coverUrl)
                        .placeholder(R.drawable.background_bg)
                        .into(image_view_cover_similar)
            }
        }
    }
}