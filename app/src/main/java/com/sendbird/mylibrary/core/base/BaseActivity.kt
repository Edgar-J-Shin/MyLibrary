package com.sendbird.mylibrary.core.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.sendbird.mylibrary.core.binding.binding
import com.sendbird.mylibrary.core.binding.initBinding
import com.sendbird.mylibrary.ui.view.CustomLoadingDialog

abstract class BaseActivity<T : ViewDataBinding> constructor(
    @LayoutRes private val layoutResId: Int
) : AppCompatActivity() {
    private var _binding: T? = null
    protected val binding: T
        get() = checkNotNull(_binding) {
            "AppCompatActivity $this binding cannot be accessed before onCreate() or after onDestroy()"
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = binding(layoutResId)
    }

    private val dialogCustom: CustomLoadingDialog by lazy { CustomLoadingDialog(this) }
    protected fun showLoadingView() {
        dialogCustom.show()
    }

    protected fun hideLoadingView() {
        dialogCustom.hide()
    }

    protected fun showErrorDialog(throwable: Throwable) {
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(throwable.message)
            .setNegativeButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding?.unbind()
        _binding = null
    }

    protected inline fun initBinding(initBlock: T.() -> Unit) = binding.initBinding(initBlock)
}