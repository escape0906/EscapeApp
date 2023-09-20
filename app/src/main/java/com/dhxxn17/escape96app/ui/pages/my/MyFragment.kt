package com.dhxxn17.escape96app.ui.pages.my

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dhxxn17.escape96app.R
import com.dhxxn17.escape96app.databinding.FragmentMyBinding
import com.dhxxn17.escape96app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFragment : BaseFragment<FragmentMyBinding>(R.layout.fragment_my) {
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMyBinding {
        return FragmentMyBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
    }
}