package br.com.daniellopes.netflixreplication.kotlin.util

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import br.com.daniellopes.netflixreplication.R

class LoadingDialog(
        private val context: Context, private var dialog: Dialog) {

    fun startLoadingDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setView(LayoutInflater
                .from(context)
                .inflate(R.layout.custom_dialog, null))
        builder.setCancelable(true)
        dialog = builder.create()
        dialog.show()
    }

    fun dismissDialog() {
        dialog.dismiss()
    }
}