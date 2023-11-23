package com.dhxxn17.escape96app.ui.pages.theme

import android.content.Context
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
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

    private val viewModel by viewModels<ThemeViewModel>()
    private val adapter = ThemeAdapter()
    private lateinit var callback: OnBackPressedCallback
    private var isBackPressed = false

    override fun onAttach(context: Context) {
        super.onAttach(context)

        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressClicked()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

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
            if(etSearch.text.isEmpty()) {
                emptyText.text = "테마 이름을 검색해보세요 !"
            }

            backBtn.setOnClickListener {
                onBackPressClicked()
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
                if (it.isEmpty()) {
                    requireDataBinding().themeList.visibility = View.GONE
                    requireDataBinding().emptyLayout.visibility = View.VISIBLE
                    requireDataBinding().emptyText.text = "검색결과가 없습니다"
                } else {
                    requireDataBinding().themeList.visibility = View.VISIBLE
                    requireDataBinding().emptyLayout.visibility = View.GONE
                    adapter.updateData(it)
                }
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

    private fun onBackPressClicked() {
        isBackPressed = true
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        if (isBackPressed) {
            with(viewModel) {
                resultList.removeObservers(viewLifecycleOwner)
                searchQuery.removeObservers(viewLifecycleOwner)
                totalCount.removeObservers(viewLifecycleOwner)
            }
        }
        super.onDestroyView()
    }
}