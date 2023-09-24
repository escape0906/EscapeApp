package com.dhxxn17.escape96app.ui.pages.home

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dhxxn17.escape96app.R
import com.dhxxn17.escape96app.data.Theme
import com.dhxxn17.escape96app.databinding.FragmentHomeBinding
import com.dhxxn17.escape96app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val adapter = ThemeAdapter()

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
        }



        observeData()


    }

    private fun observeData() {
        //TODO : viewModel에서 observe data

        val list = mutableListOf<Theme>(
            Theme("세렌디피티(SERENDIPITY)", "넥스트에디션 건대보네르관", "https://next-edition.s3.amazonaws.com/theme/title_image_url/%EC%84%B8%EB%A0%8C%EB%94%94%ED%94%BC%ED%8B%B0(SERENDIPITY)/poster_theme__PNG%E1%84%91%E1%85%A1%E1%84%8B%E1%85%B5%E1%86%AF%E1%84%87%E1%85%A9%E1%86%AB_%EC%84%B8%EB%A0%8C%EB%94%94%ED%94%BC%ED%8B%B0(SERENDIPITY).jpg"),
            Theme("SOUL CHASER - 실종", "넥스트에디션 건대점", "https://next-edition.s3.amazonaws.com/theme/title_image_url/SOUL%20CHASER%20-%20%EC%8B%A4%EC%A2%85/poster_theme__%E1%84%89%E1%85%B5%E1%86%AF%E1%84%8C%E1%85%A9%E1%86%BC_%E1%84%91%E1%85%A9%E1%84%89%E1%85%B3%E1%84%90%E1%85%A5_SOUL%20CHASER%20-%20%EC%8B%A4%EC%A2%85.jpg"),
            Theme("데.코.연 (데이트코스 연구회)", "넥스트에디션 잠실점", "https://next-edition.s3.amazonaws.com/theme/title_image_url/%EB%8D%B0.%EC%BD%94.%EC%97%B0.(%EB%8D%B0%EC%9D%B4%ED%8A%B8%20%EC%BD%94%EC%8A%A4%20%EC%97%B0%EA%B5%AC%ED%9A%8C)/poster_theme__%E1%84%83%E1%85%A6%E1%84%8F%E1%85%A9%E1%84%8B%E1%85%A7%E1%86%AB_%E1%84%91%E1%85%A9%E1%84%89%E1%85%B3%E1%84%90%E1%85%A5_%E1%84%8C%E1%85%A5%E1%84%8B%E1%85%AD%E1%86%BC%E1%84%85%E1%85%A3%E1%86%BC__%EB%8D%B0.%EC%BD%94.%EC%97%B0.(%EB%8D%B0%EC%9D%B4%ED%8A%B8%20%EC%BD%94%EC%8A%A4%20%EC%97%B0%EA%B5%AC%ED%9A%8C).jpg"),
            Theme("락페스티벌", "넥스트에디션 잠실점", "https://next-edition.s3.amazonaws.com/theme/title_image_url/%EB%9D%BD%ED%8E%98%EC%8A%A4%ED%8B%B0%EB%B2%8C/poster_theme__%E1%84%85%E1%85%A1%E1%86%A8%E1%84%91%E1%85%A6%E1%84%89%E1%85%B3%E1%84%90%E1%85%B5%E1%84%87%E1%85%A5%E1%86%AF_%E1%84%91%E1%85%A9%E1%84%89%E1%85%B3%E1%84%90%E1%85%A5_%E1%84%8C%E1%85%A5%E1%84%8B%E1%85%AD%E1%86%BC%E1%84%85%E1%85%A7%E1%86%BC__%EB%9D%BD%ED%8E%98%EC%8A%A4%ED%8B%B0%EB%B2%8C.jpg"),
            Theme("SOS", "넥스트에디션 강남5호점", "https://next-edition.s3.amazonaws.com/theme/title_image_url/SOS/poster_theme__SOS_%E1%84%91%E1%85%A9%E1%84%89%E1%85%B3%E1%84%90%E1%85%A5_%E1%84%89%E1%85%AE%E1%84%8C%E1%85%A5%E1%86%BC_%E1%84%8C%E1%85%A5%E1%84%8B%E1%85%AD%E1%86%BC%E1%84%85%E1%85%A3%E1%86%BC__SOS.jpg"),
            Theme("극", "넥스트에디션 신림점", "https://next-edition.s3.amazonaws.com/theme/title_image_url/%EA%B7%B9/poster_theme__%E1%84%80%E1%85%B3%E1%86%A8%E1%84%91%E1%85%A9%E1%84%89%E1%85%B3%E1%84%90%E1%85%A5_%EA%B7%B9.jpg"),
            Theme("그래도 피망은 먹기 싫단 말이에욧", "넥스트에디션 강남5호점", "https://next-edition.s3.amazonaws.com/theme/title_image_url/%EA%B7%B8%EB%9E%98%EB%8F%84%20%ED%94%BC%EB%A7%9D%EC%9D%80%20%EB%A8%B9%EA%B8%B0%20%EC%8B%AB%EB%8B%A8%20%EB%A7%90%EC%9D%B4%EC%97%90%EC%9A%A7/poster_theme__%E1%84%91%E1%85%B5%E1%84%86%E1%85%A1%E1%86%BC_%E1%84%91%E1%85%A9%E1%84%89%E1%85%B3%E1%84%90%E1%85%A5_%E1%84%89%E1%85%AE%E1%84%8C%E1%85%A5%E1%86%BC_%E1%84%8C%E1%85%A5%E1%84%8B%E1%85%AD%E1%86%BC%E1%84%85%E1%85%A3%E1%86%BC__%EA%B7%B8%EB%9E%98%EB%8F%84%20%ED%94%BC%EB%A7%9D%EC%9D%80%20%EB%A8%B9%EA%B8%B0%20%EC%8B%AB%EB%8B%A8%20%EB%A7%90%EC%9D%B4%EC%97%90%EC%9A%A7.jpg"),
            Theme("작은 악마들", "넥스트에디션 잠실점", "https://next-edition.s3.amazonaws.com/theme/title_image_url/%EC%9E%91%EC%9D%80%20%EC%95%85%EB%A7%88%EB%93%A4/poster_theme__%E1%84%8C%E1%85%A1%E1%86%A8%E1%84%8B%E1%85%B3%E1%86%AB_%E1%84%8B%E1%85%A1%E1%86%A8%E1%84%86%E1%85%A1%E1%84%83%E1%85%B3%E1%86%AF_%E1%84%8C%E1%85%A5%E1%84%8B%E1%85%AD%E1%86%BC%E1%84%85%E1%85%A3%E1%86%BC__%EC%9E%91%EC%9D%80%20%EC%95%85%EB%A7%88%EB%93%A4.jpg"),
            Theme("카페라떼", "넥스트에디션 잠실점", "https://next-edition.s3.amazonaws.com/theme/title_image_url/%EC%B9%B4%ED%8E%98%EB%9D%BC%EB%96%BC/poster_theme__%E1%84%8F%E1%85%A1%E1%84%91%E1%85%A6%E1%84%85%E1%85%A1%E1%84%84%E1%85%A6_%E1%84%8C%E1%85%A5%E1%84%8B%E1%85%AD%E1%86%BC%E1%84%85%E1%85%A3%E1%86%BC__%EC%B9%B4%ED%8E%98%EB%9D%BC%EB%96%BC.jpg"),
            Theme("크리쳐 - 신인류의 탄생", "넥스트에디션 강남3호점", "https://next-edition.s3.amazonaws.com/theme/title_image_url/%ED%81%AC%EB%A6%AC%EC%B3%90%20-%20%EC%8B%A0%EC%9D%B8%EB%A5%98%EC%9D%98%20%ED%83%84%EC%83%9D/poster_theme__%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%84%8E%E1%85%A7_%E1%84%91%E1%85%A9%E1%84%89%E1%85%B3%E1%84%90%E1%85%A5_%ED%81%AC%EB%A6%AC%EC%B3%90%20-%20%EC%8B%A0%EC%9D%B8%EB%A5%98%EC%9D%98%20%ED%83%84%EC%83%9D.jpg"),
            Theme("트와이스 어데이 (TWICE A DAY)", "넥스트에디션 강남신논현점", "https://next-edition.s3.amazonaws.com/theme/title_image_url/%ED%8A%B8%EC%99%80%EC%9D%B4%EC%8A%A4%20%EC%96%B4%20%EB%8D%B0%EC%9D%B4%20(TWICE%20A%20DAY)/poster_theme__%E1%84%90%E1%85%B3%E1%84%8B%E1%85%AA%E1%84%89%E1%85%B3%E1%84%8B%E1%85%A5%E1%84%83%E1%85%A6%E1%84%8B%E1%85%B5_%E1%84%91%E1%85%A9%E1%84%89%E1%85%B3%E1%84%90%E1%85%A5_%E1%84%8C%E1%85%A5%E1%84%8B%E1%85%AD%E1%86%BC%E1%84%85%E1%85%A3%E1%86%BC__%ED%8A%B8%EC%99%80%EC%9D%B4%EC%8A%A4%20%EC%96%B4%20%EB%8D%B0%EC%9D%B4%20(TWICE%20A%20DAY).jpg"),
            Theme("BANK RUPT", "넥스트에디션 강남5호점", "https://next-edition.s3.amazonaws.com/theme/title_image_url/BANK%20RUPT/poster_theme__%E1%84%87%E1%85%A2%E1%86%BC%E1%84%8F%E1%85%B3%E1%84%85%E1%85%A5%E1%86%B8_%E1%84%91%E1%85%A9%E1%84%89%E1%85%B3%E1%84%90%E1%85%A5_%E1%84%8C%E1%85%A5%E1%84%8B%E1%85%AD%E1%86%BC%E1%84%85%E1%85%A3%E1%86%BC__BANK%20RUPT.jpg"),

        )
        adapter.updateData(list)

    }

}