package com.melatech.ecommerce1.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.melatech.ecommerce1.fragments.categories.FurnitureFragment

class HomeViewPagerAdapter(
    val fragments: List<Fragment>,
    fm: FragmentManager,
    lifeCycle: Lifecycle
): FragmentStateAdapter(fm, lifeCycle) {
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}