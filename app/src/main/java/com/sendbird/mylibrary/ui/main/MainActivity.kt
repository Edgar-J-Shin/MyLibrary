package com.sendbird.mylibrary.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayoutMediator
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

        setupViewPager()
    }

    private fun observeViewModel() {

    }

    private fun setupViewPager() {
        // View Pager
        with(binding) {
            // View Pager
            vpMain.adapter = SectionsPagerAdapter(this@MainActivity)

            // Tab Layout
            TabLayoutMediator(tlMainTabs, vpMain) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
    }

    companion object {
        private val TAB_TITLES = arrayOf(
            R.string.tab_title_1,
            R.string.tab_title_2,
            R.string.tab_title_3
        )
    }
}