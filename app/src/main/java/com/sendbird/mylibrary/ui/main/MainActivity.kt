package com.sendbird.mylibrary.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.sendbird.mylibrary.R
import com.sendbird.mylibrary.core.base.BaseActivity
import com.sendbird.mylibrary.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding {
            lifecycleOwner = this@MainActivity
            viewModel = this@MainActivity.viewModel
        }

        observeViewModel()
    }

    private fun observeViewModel() {

    }
}