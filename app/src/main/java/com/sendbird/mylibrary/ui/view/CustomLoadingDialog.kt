package com.sendbird.mylibrary.ui.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.sendbird.mylibrary.R

class CustomLoadingDialog constructor(context: Context) : Dialog(context) {

    init {
        setCanceledOnTouchOutside(false)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.view_loading)
    }
}