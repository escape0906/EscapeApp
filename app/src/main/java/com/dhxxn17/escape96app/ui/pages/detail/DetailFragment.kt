package com.dhxxn17.escape96app.ui.pages.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dhxxn17.escape96app.R
import com.dhxxn17.escape96app.data.Theme
import com.dhxxn17.escape96app.databinding.FragmentDetailBinding
import com.dhxxn17.escape96app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    private val args : DetailFragmentArgs by navArgs()
    private var data: Theme? = null
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        val theme = args.value.toCollection(ArrayList())[0]
        setUi(theme)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("data", args.value[0])
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            data = savedInstanceState.getParcelable<Theme>("data")
        }
    }

    override fun onStart() {
        super.onStart()
        data?.let { setUi(it) }
    }

    private fun setUi(theme: Theme) {
        with(requireDataBinding()) {
            detailThemeStore.text = theme.location
            detailThemeTitle.text = theme.title
            Glide.with(requireContext())
                .load(theme.thumbnail)
                .fitCenter()
                .into(detailThemeImg)
            detailThemeImg.clipToOutline = true

            backBtn.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

}