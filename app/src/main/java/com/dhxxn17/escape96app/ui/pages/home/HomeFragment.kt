package com.dhxxn17.escape96app.ui.pages.home

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.dhxxn17.escape96app.R
import com.dhxxn17.escape96app.data.Theme
import com.dhxxn17.escape96app.databinding.FragmentHomeBinding
import com.dhxxn17.escape96app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val adapter = ThemeAdapter()
    private val viewModel by activityViewModels<HomeViewModel>()

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
            homeThemeList.adapter = adapter
            adapter.apply { onClick = this@HomeFragment::goToDetail }
        }

        observeData()

    }

    private fun observeData() {
        with(viewModel) {
            themeList.observe(viewLifecycleOwner) {
                adapter.updateData(it)
            }
        }

    }

    private fun goToDetail(theme: Theme) {
        val item = arrayListOf<Theme>(theme)
       requireView().findNavController().navigate( HomeFragmentDirections.actionHomeToDetailFragment(item.toTypedArray()))
    }

}