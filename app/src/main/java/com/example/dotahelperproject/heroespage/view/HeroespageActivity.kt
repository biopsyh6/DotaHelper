package com.example.dotahelperproject.heroespage.view

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.domain.entities.Hero
import com.example.domain.entities.Skill
import com.example.dotahelperproject.R
import com.example.dotahelperproject.adapters.NumberAdapter
import com.example.dotahelperproject.databinding.ActivityHeroespageBinding
import com.example.dotahelperproject.heroespage.presenter.IHeroespagePresenter
import com.example.dotahelperproject.heroespage.view.screens.StrengthFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


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
        binding.heroesTabLayout.tabIconTint = null
        binding.heroesTabLayout.tabMode = TabLayout.MODE_FIXED
        binding.heroesTabLayout.setBackgroundColor(Color.parseColor("#201E28"))
        val iconColor = ContextCompat.getColor(this, R.color.white)
        binding.heroesTabLayout.setSelectedTabIndicatorColor(iconColor)


        TabLayoutMediator(tabLayout, viewPager) {tab, position ->
            tab.setIcon(tabNames[position])
        }.attach()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, StrengthFragment())
                .commit()
        }
    }
    fun openHeroDetailFragment(hero: Hero, skills: List<Skill>) {
//        Log.d("HeroClick", "Opening hero details")
//        val fragment = HeroDetailFragment.newInstance(hero)
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container, fragment)
//            .addToBackStack(null)
//            .commit()
        val intent = HeroDetailActivity.createIntent(this, hero, skills)
        startActivity(intent)
    }
}