package com.dhxxn17.escape96app.ui.pages.my.like

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.dhxxn17.escape96app.R
import com.dhxxn17.escape96app.databinding.FragmentLikeBinding
import com.dhxxn17.escape96app.ui.base.BaseFragment
import com.dhxxn17.escape96app.ui.pages.home.ThemeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LikeFragment : BaseFragment<FragmentLikeBinding>(R.layout.fragment_like) {

    private val viewModel by viewModels<LikeViewModel>()
    private val adapter = ThemeAdapter()

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLikeBinding {
        return FragmentLikeBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        with(requireDataBinding()) {
            likeList.adapter = adapter
            adapter.apply { onClick = this@LikeFragment::goToDetail }

            swipeLayout.setOnRefreshListener {
                viewModel.getAllLikeList()
                swipeLayout.isRefreshing = false
            }
        }

        observeData()
    }

    private fun observeData() {
        with(viewModel) {
            likeList.observe(viewLifecycleOwner) {
                adapter.updateData(it)
            }
        }
    }

    private fun goToDetail(themeId: Int) {

    }
}