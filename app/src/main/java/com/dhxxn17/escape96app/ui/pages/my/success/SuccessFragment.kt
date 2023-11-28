package com.dhxxn17.escape96app.ui.pages.my.success

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.dhxxn17.escape96app.R
import com.dhxxn17.escape96app.databinding.FragmentSuccessBinding
import com.dhxxn17.escape96app.ui.base.BaseFragment
import com.dhxxn17.escape96app.ui.pages.home.ThemeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuccessFragment : BaseFragment<FragmentSuccessBinding>(R.layout.fragment_success) {

    private val viewModel by viewModels<SuccessViewModel>()
    private val adapter = ThemeAdapter()

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSuccessBinding {
        return FragmentSuccessBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        with(requireDataBinding()) {
            successList.adapter = adapter
            adapter.apply { onClick = this@SuccessFragment::goToDetail }

            swipeLayout.setOnRefreshListener {
                viewModel.getAllSuccessList()
                swipeLayout.isRefreshing = false
            }
        }

        observeData()
    }

    private fun observeData() {
        with(viewModel) {
            successList.observe(viewLifecycleOwner) {
                adapter.updateData(it)
            }
        }
    }

    private fun goToDetail(themeId: Int) {

    }
}