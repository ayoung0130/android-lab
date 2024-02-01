package com.android.my_application.ui.search

import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.android.base.BaseFragment
import com.android.base.ViewState
import com.android.my_application.databinding.FragmentSearchBinding
import com.android.my_application.ext.showToast
import com.android.my_application.network.response.ImageResult
import com.android.my_application.ui.MainViewModel
import com.android.my_application.ui.MainViewState
import com.android.my_application.ui.adapter.SearchAdapter

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate),
    ViewState {
    private val mainViewModel by activityViewModels<MainViewModel>()

    override val viewModel by viewModels<SearchViewModel>(
        factoryProducer = { SearchViewModelFactory() }
    )

    private val searchAdapter = SearchAdapter(viewModel::toggleBookmark)

    override fun initUi() {
        binding.viewModel = viewModel
        binding.rvSearch.adapter = searchAdapter
        mainViewModel.viewStateLiveData.observe(viewLifecycleOwner, ::onChangedViewState)
    }

    override fun onChangedViewState(viewState: ViewState) {
        when (viewState) {
            is SearchViewState.GetImageList -> {
                binding.rvSearch.isVisible = true
                binding.tvNoResult.isVisible = false
                updateImages(viewState.imageList)
            }

            is SearchViewState.ShowToast -> {
                showToast(message = viewState.message)
            }

            SearchViewState.EmptyResult -> {
                binding.rvSearch.isVisible = false
                binding.tvNoResult.isVisible = true
            }

            is SearchViewState.ToggleBookmark -> {
                searchAdapter.toggleBookmark(viewState.item)
            }

            is MainViewState.DeleteBookmark -> {
                searchAdapter.deleteBookmark(viewState.item)
            }
        }
    }

    private fun updateImages(images: List<ImageResult>) {
        searchAdapter.clearItems()
        searchAdapter.addItems(images)
    }

}