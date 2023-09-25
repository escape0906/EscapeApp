package com.dhxxn17.escape96app.ui.pages.search

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dhxxn17.escape96app.R
import com.dhxxn17.escape96app.databinding.FragmentSearchBinding
import com.dhxxn17.escape96app.ui.base.BaseFragment
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        val flexboxLayoutManager = FlexboxLayoutManager(requireContext()).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            alignItems = AlignItems.STRETCH
        }

        requireDataBinding().searchRecentList.run {
            layoutManager = flexboxLayoutManager
//            adapter = growthNoteTagAdapter
            setHasFixedSize(false)
        }
    }

}