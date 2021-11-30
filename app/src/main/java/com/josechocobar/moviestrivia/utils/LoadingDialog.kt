package com.josechocobar.moviestrivia.utils

import android.app.Activity
import android.app.Dialog
import androidx.appcompat.app.AlertDialog
import com.josechocobar.moviestrivia.R

class LoadingDialog (myactivity: Activity){
    var activity: Activity = myactivity
    var dialog: Dialog?=null

    fun startLoadingDialog(){
        val builder : AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.loading, null))
        builder.setCancelable(true)
        dialog = builder.create()
        dialog?.show()

    }
    fun dissmissDialog(){
        dialog?.dismiss()

    }


}