package com.sendbird.mylibrary.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.sendbird.mylibrary.core.binding.binding
import com.sendbird.mylibrary.core.binding.initBinding

abstract class BaseFragment<T : ViewDataBinding> constructor(
    @LayoutRes private val layoutResId: Int
) : Fragment() {

    private var _binding: T? = null
    protected val binding: T
        get() = checkNotNull(_binding) {
            "Fragment $this binding cannot be accessed before onCreateView() or after onDestroyView()"
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = binding(layoutResId, inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.unbind()
        _binding = null
    }

    protected inline fun initBinding(initBlock: T.() -> Unit) = binding.initBinding(initBlock)
}