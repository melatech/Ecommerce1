package com.melatech.ecommerce1.fragments.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.melatech.ecommerce1.R
import com.melatech.ecommerce1.adapters.HomeViewPagerAdapter
import com.melatech.ecommerce1.databinding.FragmentHomeBinding
import com.melatech.ecommerce1.fragments.categories.AccessoryFragment
import com.melatech.ecommerce1.fragments.categories.ChairFragment
import com.melatech.ecommerce1.fragments.categories.CupboardFragment
import com.melatech.ecommerce1.fragments.categories.FurnitureFragment
import com.melatech.ecommerce1.fragments.categories.MainCategoryFragment
import com.melatech.ecommerce1.fragments.categories.TableFragment

class HomeFragment: Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoriesFragments = arrayListOf<Fragment>(
            MainCategoryFragment(),
            ChairFragment(),
            CupboardFragment(),
            TableFragment(),
            AccessoryFragment(),
            FurnitureFragment()
        )

        val viewPager2Adapter = HomeViewPagerAdapter(categoriesFragments, childFragmentManager, lifecycle)
        binding.viewPagerHome.adapter = viewPager2Adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPagerHome){ tab, position ->
            when(position){
                0 -> tab.text = "Main"
                1 -> tab.text = "Chair"
                2 -> tab.text = "Cupboard"
                3 -> tab.text = "Table"
                4 -> tab.text = "Accessory"
                5 -> tab.text = "Furniture"
            }
        }.attach()
    }
}