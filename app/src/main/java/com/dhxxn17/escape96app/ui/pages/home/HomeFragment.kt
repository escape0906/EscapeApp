package com.dhxxn17.escape96app.ui.pages.home

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dhxxn17.escape96app.R
import com.dhxxn17.escape96app.data.Theme
import com.dhxxn17.escape96app.databinding.FragmentHomeBinding
import com.dhxxn17.escape96app.ui.base.BaseFragment
import com.dhxxn17.escape96app.ui.toVisibility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val adapter = ThemeAdapter()
    private val viewModel by activityViewModels<HomeViewModel>()
    private var isTop = true

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {

        with(requireDataBinding()) {
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.getAllThemeList()
                swipeRefreshLayout.isRefreshing = false
            }

            homeThemeList.adapter = adapter
            adapter.apply { onClick = this@HomeFragment::goToDetail }

            scrollTopButton.setOnClickListener {
                homeThemeList.smoothScrollToPosition(0)
                scrollView.smoothScrollTo(0, 0)
            }

            scrollView.setOnScrollChangeListener { view, i, i2, i3, i4 ->
                if (!scrollView.canScrollVertically(-1)
                    && i == RecyclerView.SCROLL_STATE_IDLE) {
                    scrollTopButton.visibility = View.GONE
                    isTop = true
                } else {
                    if (isTop) {
                        scrollTopButton.visibility = View.VISIBLE
                        isTop = false
                    }
                }

                if (!viewModel.entireProgressVisible.value!!) {
                    if (!scrollView.canScrollVertically(1)) {
                        viewModel.loadMore()
                    }
                }
            }
        }

        observeData()

    }

    private fun observeData() {
        with(viewModel) {
            themeList.observe(viewLifecycleOwner) {
                adapter.updateData(it)
            }

            errorMessage.observe(viewLifecycleOwner) {
                showToast(it)
            }

            entireProgressVisible.observe(viewLifecycleOwner) {
                requireDataBinding().homeProgressBar.visibility = it.toVisibility()
            }
        }

    }

    private fun goToDetail(themeId: Int) {
       requireView().findNavController().navigate( HomeFragmentDirections.actionHomeToDetailFragment(themeId))
    }

}