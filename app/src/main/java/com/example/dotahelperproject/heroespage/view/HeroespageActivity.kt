package com.example.dotahelperproject.heroespage.view

import android.icu.number.NumberFormatter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dotahelperproject.R
import com.example.dotahelperproject.databinding.ActivityHeroespageBinding
import com.example.dotahelperproject.heroespage.presenter.IHeroespagePresenter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.dotahelperproject.NumberAdapter


class HeroespageActivity : AppCompatActivity(), IHeroespageView {
    private lateinit var binding: ActivityHeroespageBinding
    private lateinit var iHeroespagePresenter: IHeroespagePresenter

    private lateinit var adapter: NumberAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private val tabNames: Array<Int> = arrayOf(
        R.drawable.hero_strength,
        R.drawable.hero_agility,
        R.drawable.hero_intelligence,
        R.drawable.hero_universal
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroespageBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        adapter = NumberAdapter(this)
        viewPager = findViewById(R.id.heroes_pager)
        viewPager.adapter = adapter
        tabLayout = findViewById(R.id.heroes_tab_layout)

        TabLayoutMediator(tabLayout, viewPager) {tab, position ->
            tab.setIcon(tabNames[position])
        }.attach()
    }
}