package com.melatech.ecommerce1.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.melatech.ecommerce1.R
import com.melatech.ecommerce1.databinding.ActivityShopppingBinding

class ShoppingActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityShopppingBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navController = findNavController(R.id.shoppingHostFragment)
        binding.bottomNavigation.setupWithNavController(navController)
    }
}