package com.dhxxn17.escape96app.ui.pages.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dhxxn17.escape96app.R
import com.dhxxn17.escape96app.databinding.FragmentSearchBinding
import com.dhxxn17.escape96app.ui.base.BaseFragment
import com.dhxxn17.escape96app.ui.pages.home.HomeFragmentDirections
import com.dhxxn17.escape96app.ui.pages.home.ThemeAdapter
import com.dhxxn17.escape96app.ui.toVisibility
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val args: SearchFragmentArgs by navArgs()
    private val viewModel by activityViewModels<SearchViewModel>()
    private val adapter = ThemeAdapter()
    private var query = ""

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        val input = args.input
        query = input
        requireDataBinding().etSearch.setText(query)
        viewModel.requestSearch(input)
        observeData()
    }

    private fun observeData() {
        with(viewModel) {
            resultList.observe(viewLifecycleOwner) {
                adapter.updateData(it)
            }

            errorMessage.observe(viewLifecycleOwner) {
                showToast(it)
            }

            entireProgressVisible.observe(viewLifecycleOwner) {
                requireDataBinding().searchProgressBar.visibility = it.toVisibility()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(requireDataBinding()) {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }

            swipeRefreshLayout.setOnRefreshListener {
                viewModel.requestSearch(viewModel.searchQuery.value ?: "")
                swipeRefreshLayout.isRefreshing = false
            }

            searchResultList.adapter = adapter
            adapter.apply { onClick = this@SearchFragment::goToDetail }

        }
    }

    private fun goToDetail(themeId: Int) {
        requireView().findNavController().navigate(SearchFragmentDirections.actionSearchToDetailFragment(themeId))
    }

}