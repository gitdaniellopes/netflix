package br.com.daniellopes.netflixreplication.kotlin.presentation

import br.com.daniellopes.netflixreplication.kotlin.Categories
import br.com.daniellopes.netflixreplication.kotlin.Category
import br.com.daniellopes.netflixreplication.kotlin.activity.MainActivity
import br.com.daniellopes.netflixreplication.kotlin.datasource.CategoryDS
import br.com.daniellopes.netflixreplication.kotlin.interfaces.CategoryC

class CategoryP(
        val view: MainActivity,
        val datasource: CategoryDS
) : CategoryC {

    fun requestAll() {
        this.view.showProgressBar()
        datasource.find(this)
    }

    override fun onSuccess(categories: Categories) {
        val categoriesList = arrayListOf<Category>()
        for (category in categories.categories) {
            categoriesList.add(Category(category.id, category.title, category.movies))
        }
        this.view.showCategory(categoriesList)
    }

    override fun onError(message: String) {
        this.view.showFailure(message)
    }

    override fun onComplete() {
        this.view.hideProgressBar()
    }
}