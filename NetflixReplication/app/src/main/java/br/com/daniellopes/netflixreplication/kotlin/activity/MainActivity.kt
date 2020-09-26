package br.com.daniellopes.netflixreplication.kotlin.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.daniellopes.netflixreplication.R
import br.com.daniellopes.netflixreplication.kotlin.Category
import br.com.daniellopes.netflixreplication.kotlin.adapter.MainAdapter
import br.com.daniellopes.netflixreplication.kotlin.datasource.CategoryDS
import br.com.daniellopes.netflixreplication.kotlin.presentation.CategoryP
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val mainAdapter by lazy {
        MainAdapter(categories(), this)
    }
    private val recycle by lazy {
        recycle_view_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configRecycle()

        val categorySource = CategoryDS()
        val presenter = CategoryP(this, categorySource)

        presenter.requestAll()
    }

    private fun configRecycle() {
        with(recycle) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)

        }
    }

    private fun categories(): ArrayList<Category> = arrayListOf()


    fun showCategory(categories: MutableList<Category>) {
        mainAdapter.addCategories(categories)
        mainAdapter.notifyDataSetChanged()
    }

    fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showProgressBar() {
        progress_recycle.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progress_recycle.visibility = View.GONE
    }
}