package com.example.dotahelperproject.itemspage.view

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.domain.entities.Item
import com.example.dotahelperproject.R
import com.example.dotahelperproject.adapters.NumberItemAdapter
import com.example.dotahelperproject.databinding.ActivityItemspageBinding
import com.example.dotahelperproject.itemspage.view.screens.ItemFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ItemspageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemspageBinding
    private lateinit var adapter: NumberItemAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private val tabNames: Array<Int> = arrayOf(
        R.drawable.teleport_icon,
        R.drawable.stick_icon
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemspageBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        adapter = NumberItemAdapter(this)
        viewPager = findViewById(R.id.items_pager)
        viewPager.adapter = adapter
        tabLayout = findViewById(R.id.items_tab_layout)
        binding.itemsTabLayout.tabIconTint = ColorStateList.valueOf(Color.WHITE)
        binding.itemsTabLayout.tabMode = TabLayout.MODE_FIXED
        binding.itemsTabLayout.setBackgroundColor(Color.parseColor("#201E28"))
        val iconColor = ContextCompat.getColor(this, R.color.white)
        binding.itemsTabLayout.setSelectedTabIndicatorColor(iconColor)

        TabLayoutMediator(tabLayout, viewPager) {tab, position ->
            tab.setIcon(tabNames[position])
        }.attach()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ItemFragment())
                .commit()
        }
    }

    fun openItemDetailFragment(item: Item) {
        val intent = ItemDetailActivity.createIntent(this, item)
        startActivity(intent)
    }
}