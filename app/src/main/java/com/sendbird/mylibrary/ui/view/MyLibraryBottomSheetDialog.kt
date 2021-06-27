package com.sendbird.mylibrary.ui.view

import android.app.Activity
import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sendbird.mylibrary.R
import com.sendbird.mylibrary.databinding.ViewBottomSheetBinding

class DemoBottomSheetDialog(val context: Context) {

    val binding: ViewBottomSheetBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_bottom_sheet, null, false)
    private val dialog: BottomSheetDialog by lazy { BottomSheetDialog(context) }

    //  dialog create
    fun create(): BottomSheetDialog {
        return dialog.apply {
            setContentView(binding.root)
        }
    }

    fun addTextView(title: String, funcClick: () -> Unit) {
        val tv_new: TextView = TextView(context).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, context.resources.getDimensionPixelSize(R.dimen.custom_bottom_sheet_content_height))
            setPadding(
                context.resources.getDimensionPixelSize(R.dimen.custom_bottom_sheet_content_padding_start),
                context.resources.getDimensionPixelSize(R.dimen.custom_bottom_sheet_content_padding_top),
                0,
                0
            )

            text = title
            setTextSize(TypedValue.COMPLEX_UNIT_PX, context.resources.getDimension(R.dimen.font_size_14))
            setTextColor(context.getColor(R.color.black))

            isClickable = true
            isFocusable = true

            setOnClickListener {
                funcClick.invoke()
                dismiss()
            }
        }
        binding.llDialogView.addView(tv_new)
    }

    private fun dismiss() {
        dialog.dismiss()
    }
}

fun Activity.showBottomSheetDialog(func: DemoBottomSheetDialog.() -> Unit) =
    DemoBottomSheetDialog(this).apply {
        func()
    }.create().show()