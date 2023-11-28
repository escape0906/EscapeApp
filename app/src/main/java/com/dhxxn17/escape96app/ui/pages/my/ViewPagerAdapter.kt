package com.dhxxn17.escape96app.ui.pages.my

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dhxxn17.escape96app.ui.pages.my.like.LikeFragment
import com.dhxxn17.escape96app.ui.pages.my.success.SuccessFragment

class ViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> LikeFragment()
            else -> SuccessFragment()
        }
    }
}