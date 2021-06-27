package com.sendbird.mylibrary.core.binding

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * activity binding
 */
fun <T : ViewDataBinding> Activity.binding(
    @LayoutRes layoutResId: Int,
    bindingComponent: DataBindingComponent? = null
): T = DataBindingUtil.setContentView(this, layoutResId, bindingComponent)

/**
 * fragment binding
 */
fun <T : ViewDataBinding> Fragment.binding(
    @LayoutRes layoutResId: Int,
    inflater: LayoutInflater,
    viewGroup: ViewGroup?,
    bindingComponent: DataBindingComponent? = null
): T = DataBindingUtil.inflate(inflater, layoutResId, viewGroup, false, bindingComponent)

/**
 * viewGroup binding (viewHolder)
 */
fun <T : ViewDataBinding> ViewGroup.binding(
    @LayoutRes layoutResId: Int,
    bindingComponent: DataBindingComponent? = null
): T = DataBindingUtil.inflate(LayoutInflater.from(context), layoutResId, this, false, bindingComponent)

inline fun <T : ViewDataBinding> T.initBinding(initBlock: T.() -> Unit) = run(initBlock)