package br.com.daniellopes.netflixreplication.kotlin.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.daniellopes.netflixreplication.R
import br.com.daniellopes.netflixreplication.kotlin.Category
import br.com.daniellopes.netflixreplication.kotlin.activity.MovieActivity
import kotlinx.android.synthetic.main.item_category.view.*

class MainAdapter(val categories: MutableList<Category>,
                  private val context: Context) : RecyclerView.Adapter<MainAdapter.CategoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder =
            CategoryHolder(LayoutInflater.from(context)
                    .inflate(R.layout.item_category, parent, false))

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) =
            holder.bind(categories[position])

    fun addCategories(categories: List<Category>) {
        this.categories.clear()
        this.categories.addAll(categories)
    }

    inner class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category) =
                with(itemView) {
                    text_view_category.text = category.title
                    recycle_view_movie.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                    recycle_view_movie.adapter = MovieAdapter(category.movies, context) { movie ->
                        if (movie.id > 3) {
                            Toast.makeText(context,
                                    "não foi implementado esa funcionalidade",
                                    Toast.LENGTH_LONG).show()
                        } else {
                            val intent = Intent(context, MovieActivity::class.java)
                            intent.putExtra("id", movie.id)
                            context.startActivity(intent)
                        }

                    }
                }
    }
}