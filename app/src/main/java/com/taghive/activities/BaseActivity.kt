package com.taghive.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.taghive.R

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    var dialog: Dialog? = null

    fun showLoader(context: Activity, message: String) {
        try {
            dialog = Dialog(context)
            dialog!!.setContentView(R.layout.dialog_progress)
            dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            (dialog!!.findViewById(R.id.dialog_progress_txt_message) as TextView).text =
                message
            dialog!!.setCancelable(false)
            dialog!!.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun dismissLoader() {
        try {
            dialog!!.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showKeyboard(activity: Activity) {
        val inputMethodManager: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        inputMethodManager.toggleSoftInputFromWindow(
            view.applicationWindowToken,
            InputMethodManager.SHOW_IMPLICIT, 0
        )
    }
}