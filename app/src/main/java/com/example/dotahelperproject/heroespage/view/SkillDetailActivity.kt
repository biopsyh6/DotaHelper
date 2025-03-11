package com.example.dotahelperproject.heroespage.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.domain.entities.Skill
import com.example.dotahelperproject.R
import com.squareup.picasso.Picasso

class SkillDetailActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_SKILL = "extra_skill"

        fun createIntent(context: Context, skill: Skill): Intent {
            return Intent(context, SkillDetailActivity::class.java).apply {
                putExtra(EXTRA_SKILL, skill)
            }
        }
    }
    private lateinit var skill:Skill

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_skill_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        skill = intent.getParcelableExtra(EXTRA_SKILL)!!
        findViewById<TextView>(R.id.skill_name).text = skill.skillName
        findViewById<TextView>(R.id.skill_description).text = skill.description
        val skillImageView = findViewById<ImageView>(R.id.skill_image)
        Picasso.get().load(skill.imageUrl).into(skillImageView)

        var statsList = ArrayList<String>()
        statsList.add("Damage Type: ${skill.damageType}")
        for (spValue in skill.specialValues) {
            if (spValue.values.singleOrNull() != 0.0f && spValue.heading.isNotEmpty()){
                statsList.add("${spValue.heading} ${spValue.values}")
            } else if (spValue.values.singleOrNull() != 0.0f && spValue.name.isNotEmpty()) {
                statsList.add("${spValue.name} ${spValue.values}")
            }
        }
        statsList.add("Shard Description: ${skill.shardDescription}")
        statsList.add("Scepter Description: ${skill.scepterDescription}")
        findViewById<ListView>(R.id.skill_stats).adapter =
            ArrayAdapter(this, R.layout.my_list_item, statsList)
    }
}