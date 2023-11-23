package com.dhxxn17.escape96app.ui.pages.theme

import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.dhxxn17.escape96app.R
import com.dhxxn17.escape96app.databinding.FragmentThemeBinding
import com.dhxxn17.escape96app.ui.base.BaseFragment
import com.dhxxn17.escape96app.ui.pages.home.ThemeAdapter
import com.dhxxn17.escape96app.ui.util.toVisibility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThemeFragment :BaseFragment<FragmentThemeBinding>(R.layout.fragment_theme) {

    private val viewModel by activityViewModels<ThemeViewModel>()
    private val adapter = ThemeAdapter()

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentThemeBinding {
        return FragmentThemeBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        with(requireDataBinding()) {
            backBtn.setOnClickListener {
                findNavController().popBackStack()
            }

            swipeRefreshLayout.setOnRefreshListener {
                viewModel.requestSearchTheme(viewModel.searchQuery.value ?: "")
                swipeRefreshLayout.isRefreshing = false
            }

            themeList.adapter = adapter
            adapter.apply { onClick = this@ThemeFragment::goToDetail }

            etSearch.setOnEditorActionListener { _, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE
                    || event.keyCode == KeyEvent.KEYCODE_ENTER
                    && event.action == KeyEvent.ACTION_DOWN) {
                    viewModel.requestSearchTheme(etSearch.text.toString())
                    return@setOnEditorActionListener true
                }
                false
            }

            if (viewModel.entireProgressVisible.value == false) {
                if (!themeList.canScrollVertically(1)) {
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
        requireView().findNavController().navigate( ThemeFragmentDirections.actionThemeFragmentToDetailFragment(themeId))
    }
}