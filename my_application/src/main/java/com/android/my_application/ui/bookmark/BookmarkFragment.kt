package com.android.my_application.ui.bookmark

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.android.base.BaseFragment
import com.android.base.ViewState
import com.android.my_application.databinding.FragmentBookmarkBinding
import com.android.my_application.ui.MainViewModel
import com.android.my_application.ui.adapter.BookmarkAdapter

class BookmarkFragment : BaseFragment<FragmentBookmarkBinding>(FragmentBookmarkBinding::inflate) {
    private val mainViewModel by activityViewModels<MainViewModel>()

    override val viewModel by viewModels<BookmarkViewModel>(
        factoryProducer = { BookmarkViewModelFactory() }
    )

    private val bookmarkAdapter = BookmarkAdapter {
        viewModel.deleteBookmark(it)
        mainViewModel.deleteBookmark(it)
    }

    override fun initUi() {
        binding.rvBookmark.adapter = bookmarkAdapter
    }

    override fun onChangedViewState(viewState: ViewState) {
        when (viewState) {
            is BookmarkViewState.GetBookmarkList -> {
                bookmarkAdapter.addAll(viewState.items)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getBookmarkList()
    }
}