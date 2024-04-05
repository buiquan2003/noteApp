package com.example.noteapp.utils

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.noteapp.R
import com.example.noteapp.data.model.ErrorType
import com.example.noteapp.databinding.CustomDialogBinding

object Dialog {

    class CustomDialog(private val context: Context) {

        fun show(title: String, content: String) {
            val inflater = LayoutInflater.from(context)
            val binding = CustomDialogBinding.inflate(inflater)
            binding.textTitle.text = title
            binding.btnButton.text = content

            val dialog = android.app.AlertDialog.Builder(context)
                .setView(binding.root)
                .setCancelable(true)
                .create()

            binding.btnButtonClose.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }
    }

    // Usage in Fragment
    fun Fragment.showCustomDialog(title: String, content: String) {
        val customDialog = CustomDialog(requireContext())
        customDialog.show(title, content)
    }

    // Usage in Activity
    fun Activity.showCustomDialog(title: String, content: String) {
        val customDialog = CustomDialog(this)
        customDialog.show(title, content)
    }

    // dialog2

    fun errorDiaLog(act: AppCompatActivity, title_res: String, mes: String, listener: DialogInterface.OnClickListener?= null) {
        val title = act.layoutInflater.inflate(R.layout.custom_dialog,null)
        title.findViewById<TextView>(R.id.textTitle).setText(title_res)
        val dialog = AlertDialog.Builder(act)
            .setCustomTitle(title)
            .setMessage(mes)
            .setPositiveButton(R.string.btn_Ok, listener)
//            { dialogInterface: DialogInterface, i: Int ->
//                listener?.onClick(dialogInterface, i) // Gọi listener nếu có
//                dialogInterface.dismiss() // Ẩn dialog khi người dùng bấm "OK"
//            }
            .setCancelable(false)
            .show()

        dialog?.apply {
            findViewById<TextView>(R.id.textTitle)?.let {
                it.textSize = 12f
            }

            findViewById<Button>(R.id.btn_button)?.let {
                it.textSize = 12f
            }
        }



    }


    fun showDiaLog(
        activity: AppCompatActivity,
        listener: DialogInterface.OnClickListener? = null,
        errorType: ErrorType
    ) {
          errorDiaLog(
              activity,
              "",
              getErrorMessage(activity,errorType),
              listener

          )

    }


   fun getErrorMessage(
       activity: AppCompatActivity,
       errorType: ErrorType
   ): String {
       return when(errorType) {
           ErrorType.ErrorToken -> R.string.app_name.toString()
           else -> ""
       }

   }

}