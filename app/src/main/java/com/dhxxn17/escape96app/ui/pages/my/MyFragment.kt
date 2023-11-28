package com.dhxxn17.escape96app.ui.pages.my

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dhxxn17.escape96app.R
import com.dhxxn17.escape96app.databinding.FragmentMyBinding
import com.dhxxn17.escape96app.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

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
        with(requireDataBinding()) {
            backBtn.setOnClickListener {
                findNavController().popBackStack()
            }

            tabLayout.addTab(
                tabLayout.newTab().setText("찜")
            )
            tabLayout.addTab(
                tabLayout.newTab().setText("완료")
            )
            val tabTitleList = arrayListOf<String>("찜", "완료")

            myViewPager.adapter = ViewPagerAdapter(this@MyFragment)
            TabLayoutMediator(tabLayout, myViewPager) { tab, position ->
                tab.text = tabTitleList[position]
            }.attach()
            userIcon.backgroundTintList = ColorStateList.valueOf(getRandomColor())
        }
    }

    private fun getRandomColor(): Int {
        val random = Random.Default
        val r = random.nextInt(256)
        val g = random.nextInt(256)
        val b = random.nextInt(256)
        return Color.rgb(r, g, b)
    }
}