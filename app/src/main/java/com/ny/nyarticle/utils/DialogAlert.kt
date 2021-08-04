package com.ny.nyarticle.utils

import android.app.Activity
import android.app.AlertDialog

class DialogAlert {

    fun showErrorDialog(context: Activity, title: String, message: String, btn_Postive: String) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton(
            btn_Postive
        ) { arg0, arg1 ->
            arg0.dismiss()
            context.finish()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

}