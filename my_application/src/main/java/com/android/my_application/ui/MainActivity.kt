package com.android.my_application.ui

import androidx.activity.viewModels
import com.android.base.BaseActivity
import com.android.base.ViewState
import com.android.my_application.R
import com.android.my_application.databinding.ActivityMainBinding
import com.android.my_application.ext.showToast
import com.android.my_application.ui.adapter.ViewPagerAdapter
import com.android.my_application.ui.bookmark.BookmarkFragment
import com.android.my_application.ui.search.SearchFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : BaseActivity<ActivityMainBinding>(layoutResId = R.layout.activity_main) {

    override val viewModel by viewModels<MainViewModel>()

    private val tabLayoutMediator =
        TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.text = resources.getStringArray(R.array.tab_title)[position]
            tab.icon = resources.obtainTypedArray(R.array.tab_icon).getDrawable(position)
        }

    override fun initUi() {
        val fragments = listOf(SearchFragment(), BookmarkFragment())
        binding.viewpager.adapter = ViewPagerAdapter(fragments, supportFragmentManager, lifecycle)
        binding.viewpager.offscreenPageLimit = fragments.size
        TabLayoutMediator(binding.tabLayout, binding.viewpager, tabLayoutMediator).attach()
    }

    override fun onChangedViewState(viewState: ViewState) {
        when (viewState) {
            is MainViewState.DeleteBookmark -> {
                showToast(message = "즐겨찾기가 삭제되었습니다.")
            }
        }
    }
}