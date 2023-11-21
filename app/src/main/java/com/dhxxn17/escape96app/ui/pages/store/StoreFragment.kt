package com.dhxxn17.escape96app.ui.pages.store

import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.dhxxn17.escape96app.R
import com.dhxxn17.escape96app.databinding.FragmentStoreBinding
import com.dhxxn17.escape96app.ui.base.BaseFragment
import com.dhxxn17.escape96app.ui.pages.home.HomeFragmentDirections
import com.dhxxn17.escape96app.ui.pages.home.ThemeAdapter
import com.dhxxn17.escape96app.ui.util.toVisibility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreFragment : BaseFragment<FragmentStoreBinding>(R.layout.fragment_store) {

    private val viewModel by activityViewModels<StoreViewModel>()
    private val adapter = ThemeAdapter()

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentStoreBinding {
        return FragmentStoreBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        with(requireDataBinding()){
            backBtn.setOnClickListener {
                findNavController().popBackStack()
            }

            swipeRefreshLayout.setOnRefreshListener {
                viewModel.requestSearchStore(viewModel.searchQuery.value ?: "")
                swipeRefreshLayout.isRefreshing = false
            }

            storeList.adapter = adapter
            adapter.apply { onClick = this@StoreFragment::goToDetail }

            etSearch.setOnEditorActionListener { _, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE
                    || event.keyCode == KeyEvent.KEYCODE_ENTER
                    && event.action == KeyEvent.ACTION_DOWN) {
                    viewModel.requestSearchStore(etSearch.text.toString())
                    return@setOnEditorActionListener true
                }
                false
            }

            if (viewModel.entireProgressVisible.value == false) {
                if (!storeList.canScrollVertically(1)) {
                    viewModel.loadMore()
                }
            }
        }

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

    private fun goToDetail(themeId: Int) {
        requireView().findNavController().navigate( StoreFragmentDirections.actionStoreFragmentToDetailFragment(themeId))
    }
}