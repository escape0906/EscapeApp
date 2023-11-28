package com.dhxxn17.escape96app.ui.pages.detail

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        viewModel.getIsLikeNSuccess(themeId)
        observeData()
    }

    private fun observeData() {
        with(viewModel) {
            theme.observe(viewLifecycleOwner) {
                setDetailInfoUI(it)
            }

            isLiked.observe(viewLifecycleOwner) {
                with(requireDataBinding().likeBtn) {
                    if (it) {
                        setTextColor(resources.getColor(R.color.white))
                        backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black))
//                        setCompoundDrawablesWithIntrinsicBounds(resources.getDrawable(R.drawable.ic_check), null, null, null)
                    } else {
                        setTextColor(resources.getColor(R.color.black))
                        backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.light_gray_05))
//                        setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
                    }
                }

            }

            isSuccessed.observe(viewLifecycleOwner) {
                with(requireDataBinding().successBtn) {
                    if (it) {
                        setTextColor(resources.getColor(R.color.white))
                        backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black))
//                        setCompoundDrawablesWithIntrinsicBounds(resources.getDrawable(R.drawable.ic_check), null, null, null)
                    } else {
                        setTextColor(resources.getColor(R.color.white))
                        backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.test))
//                        setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
                    }
                }

            }

            message.observe(viewLifecycleOwner) {
                 showToast(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(requireDataBinding()) {
            backBtn.setOnClickListener {
                findNavController().popBackStack()
            }
            backBtn.bringToFront()

            likeBtn.setOnClickListener {
                if (viewModel.isLiked.value == true) {
                    viewModel.deleteLikeTheme()
                } else {
                    viewModel.addLikeTheme()
                }
            }

            successBtn.setOnClickListener {
                if (viewModel.isSuccessed.value == true) {
                    viewModel.deleteSuccessTheme()
                } else {
                    viewModel.addSuccessTheme()
                }
            }
        }
    }

    private fun setDetailInfoUI(theme: Theme) {
        with(requireDataBinding()) {
            detailThemeStore.text = theme.store
            detailThemeTitle.text = theme.title
            Glide.with(requireContext())
                .load(theme.thumbnail)
                .centerCrop()
                .into(detailThemeImg)

            // 추천인원 없으면 미노출
            if (theme.recommendedPeople.isNotEmpty()) {
                detailThemePeople.text = theme.recommendedPeople
            } else {
                detailThemePeople.visibility = View.GONE
                detailThemePeopleText.visibility = View.GONE
            }

            // 장르 없으면 미노출
            if (theme.genre.trim().isEmpty()) {
                detailThemeGenre.visibility = View.GONE
                detailThemeGenreText.visibility = View.GONE
            } else {
                detailThemeGenre.visibility = View.VISIBLE
                detailThemeGenreText.visibility = View.VISIBLE
                detailThemeGenre.text = theme.genre
            }

            // 플레이시간 없으면 미노출
            if (theme.playTime != 0) {
                detailThemePlaytime.visibility = View.GONE
                detailThemePlaytimeText.visibility = View.GONE
            } else {
                detailThemePlaytime.text = "${theme.playTime}분"
                detailThemePlaytime.visibility = View.VISIBLE
                detailThemePlaytimeText.visibility = View.VISIBLE
            }

            // TODO 같은 매장 다른 테마

        }
    }

}