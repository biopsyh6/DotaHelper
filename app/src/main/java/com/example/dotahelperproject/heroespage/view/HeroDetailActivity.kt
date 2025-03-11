package com.example.dotahelperproject.heroespage.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.application.RoleUseCases.firebase.AppFirebaseRoleRepository
import com.example.application.SkillUseCases.firebase.AppFirebaseSkillRepository
import com.example.domain.entities.Hero
import com.example.domain.entities.ListItem
import com.example.domain.entities.Role
import com.example.domain.entities.Skill
import com.example.dotahelperproject.R
import com.example.dotahelperproject.adapters.ExpandableAdapter
import com.example.dotahelperproject.databinding.ActivityHeroDetailBinding

class HeroDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHeroDetailBinding
    private lateinit var expandableListView: ExpandableListView
    private lateinit var videoView: VideoView
    val header: MutableList<String> = ArrayList()
    val childItem: MutableList<MutableList<ListItem>> = ArrayList()
    companion object {
        private const val EXTRA_HERO = "extra_hero"
        private const val EXTRA_SKILLS = "extra_skills"

        fun createIntent(context: Context, hero: Hero): Intent {
            return Intent(context, HeroDetailActivity::class.java).apply {
                putExtra(EXTRA_HERO, hero)
            }
        }

    }
    private lateinit var hero: Hero
    private lateinit var skills: List<Skill>
    private lateinit var roles: List<Role>

    override fun onResume() {
        super.onResume()
        videoView.start() // повторное воспроизведение
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroDetailBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.hero_name)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val roleRepository = AppFirebaseRoleRepository()
        val skillRepository = AppFirebaseSkillRepository()
        hero = intent.getParcelableExtra(EXTRA_HERO)!!
//        skills = intent.getParcelableArrayListExtra(EXTRA_SKILLS)!!

//        val heroImageView = findViewById<ImageView>(R.id.image_hero)
//        Picasso.get().load(hero.imageUrl).into(heroImageView)
        val heroNameTextView = findViewById<TextView>(R.id.hero_name)
        heroNameTextView.text = hero.name
        videoView = binding.heroVideoView
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)

        val onlineUri = Uri.parse("https://cdn.akamai.steamstatic.com/apps/dota2/videos/dota_react/heroes/renders/${hero.nameForUrl}.webm")
//        videoView.setMediaController(mediaController)
        videoView.setVideoURI(onlineUri)
        videoView.requestFocus()
        videoView.start()
        videoView.setOnCompletionListener {
            videoView.start() // повторное воспроизведение
        }
        binding.frameLayoutProgressDetail.visibility = View.VISIBLE
        roleRepository.getRolesByRoleIds(hero.roleIds).observe(this) { roles ->
//            binding.frameLayoutProgressDetail.visibility = View.GONE
            this.roles = roles
            updateUI()

        }
        skillRepository.getSkillsByHeroName(hero.name).observe(this) { skills ->
            binding.frameLayoutProgressDetail.visibility = View.GONE
            this.skills = skills
            updateUI()
        }


    }

    private fun updateUI() {
        if (::roles.isInitialized && ::skills.isInitialized) {
            expandableListView = findViewById(R.id.expandaleListView)
            setupData()
            expandableListView.setAdapter(ExpandableAdapter(this@HeroDetailActivity, header, childItem))

        }
    }

    private fun setupData() {
        header.clear()
        childItem.clear()

        header.add("About Hero")
        header.add("Skills")
        header.add("Roles")
        header.add("Characteristics")

        val infoAboutHero = mutableListOf(
            ListItem(null, null, hero.description)
        )
        childItem.add(infoAboutHero)

        val skillList = mutableListOf<Skill>()
        for (skill in skills) {
            skillList.add(skill)
        }
        val skillItems = skillList.map { ListItem(it, null, it.skillName) }.toMutableList()
        childItem.add(skillItems)

        val rolesList = mutableListOf<Role>()
        for (role in roles) {
            rolesList.add(role)
        }
        val roleItems = rolesList.map { ListItem(null, it, it.name) }.toMutableList()
        childItem.add(roleItems)

        val characteristicsList = ArrayList<String>()
        characteristicsList.add("Health: ${hero.health}")
        characteristicsList.add("Mana: ${hero.mana}")
        characteristicsList.add("Strength: ${hero.strength}")
        characteristicsList.add("Agility: ${hero.agility}")
        characteristicsList.add("Intelligence: ${hero.intelligence}")
        characteristicsList.add("Damage: ${hero.damage}")
        characteristicsList.add("Armor: ${hero.armor}")
        characteristicsList.add("Speed: ${hero.speed}")
        characteristicsList.add("Attack Range: ${hero.attackRange}")
        characteristicsList.add("Attack Speed: ${hero.attackSpeed}")
        val characteristicsItems = characteristicsList.map { ListItem(null, null, it) }.toMutableList()
        childItem.add(characteristicsItems)
    }
}