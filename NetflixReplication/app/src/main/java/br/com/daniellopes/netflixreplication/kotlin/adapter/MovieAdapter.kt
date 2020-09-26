package br.com.daniellopes.netflixreplication.kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.daniellopes.netflixreplication.R
import br.com.daniellopes.netflixreplication.kotlin.Movie
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list_movie.view.*

class MovieAdapter(private val movies: List<Movie>,
                   private val context: Context,
                   private val listener: ((Movie) -> Unit)?) :
        RecyclerView.Adapter<MovieAdapter.MovieHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder =
            MovieHolder(LayoutInflater.from(context)
                    .inflate(R.layout.item_list_movie, parent, false), listener)

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) =
            holder.bind(movies[position])

    inner class MovieHolder(itemView: View, private val onclick: ((Movie) -> Unit)?) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) =
                with(itemView) {
                    Glide.with(context)
                            .load(movie.coverUrl)
                            .placeholder(R.drawable.background_bg)
                            .into(image_view_cover)
                    image_view_cover.setOnClickListener {
                        onclick?.invoke(movie)
                    }
                }
    }
}