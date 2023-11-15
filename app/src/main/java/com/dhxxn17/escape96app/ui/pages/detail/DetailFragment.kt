package com.dhxxn17.escape96app.ui.pages.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
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
    private val viewModel by activityViewModels<DetailViewModel>()
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        val themeId = args.value
        viewModel.getThemeDetail(themeId)
        observeData()
    }

    private fun observeData() {
        with(viewModel) {
            theme.observe(viewLifecycleOwner) {
                setUi(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(requireDataBinding()) {
            backBtn.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setUi(theme: Theme) {
        with(requireDataBinding()) {
            detailThemeStore.text = theme.store
            detailThemeTitle.text = theme.title
            Glide.with(requireContext())
                .load(theme.thumbnail)
                .fitCenter()
                .into(detailThemeImg)
            detailThemeImg.clipToOutline = true
            detailThemePlaytime.text = "${theme.playTime}분"
            detailThemeGenre.text = theme.genre

            if (theme.recommendedPeople.isNotEmpty()) {
                detailThemePeople.text = theme.recommendedPeople
            } else {
                detailThemePeople.isVisible = false
                detailThemePeopleText.isVisible = false
            }

        }
    }

}