package com.example.dotahelperproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.dotahelperproject.databinding.ActivityMainBinding
import com.example.dotahelperproject.entities.Attribute
import com.example.dotahelperproject.entities.Hero
import com.example.dotahelperproject.entities.Rune
import com.example.dotahelperproject.login.view.LoginActivity
import com.example.dotahelperproject.mainpage.view.MainpageActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    companion object {
        lateinit var database: MainDb
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("email")

        database = MainDb.getDb(this)

        val db = MainDb.getDb(this)
        if(FirebaseAuth.getInstance().currentUser ==null){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        else{
            val intent = Intent(this, MainpageActivity::class.java)
            startActivity(intent)
        }
        val linkToAuth: TextView = findViewById(R.id.back)
        linkToAuth.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        val strength = Attribute(null, "Strength", R.drawable.hero_strength)
        val agility = Attribute(null, "Agility", R.drawable.hero_agility)
        val intelligence = Attribute(null, "Intelligence", R.drawable.hero_intelligence)
        val universal = Attribute(null, "Universal", R.drawable.hero_universal)

        val bounty_rune = Rune(null, "Bounty Rune", "Дает дополнительное золото.",
            R.drawable.rune_of_bounty_model)
        val water_rune = Rune(null, "Water Rune",
            "Мгновенно восстанавливает 40 здоровья и 80 маны.", R.drawable.rune_of_water_model)
        val damage_rune = Rune(null, "Amplify Damage Rune",
            "Увеличивает базовый урон на 80% и даёт +15% к урону от заклинаний.",
            R.drawable.rune_of_amplify_damage_model)
        val arcane_rune = Rune(null, "Arcane Rune",
            "Уменьшает время перезарядки способностей на 30% и расход маны на них на 30%.",
            R.drawable.rune_of_arcane_model)
        val wisdom_rune = Rune(null, "Wisdom Rune", "Даёт дополнительный опыт.",
            R.drawable.rune_of_wisdom_model)
        val haste_rune = Rune(null, "Haste Rune",
            "Увеличивает скорость передвижения до максимума.", R.drawable.rune_of_haste_model)
        val illusion_rune = Rune(null, "Illusion Rune",
            "Создает 2 иллюзии вашего героя, наносящие 35% урона. Иллюзии ближнего боя получают 200% урона, дальнего — 300%. Иллюзии пропадут через 75 секунд.",
            R.drawable.rune_of_illusion_model)
        val invisibility_rune = Rune(null, "Invisibility Rune",
            "Вы станете невидимым на 45 секунд. Руна прекращает свое действие при атаке или при использовании умения или предмета.",
            R.drawable.rune_of_invisibility_model)
        val regeneration_rune = Rune(null, "Regeneration Rune",
            "Восстанавливает здоровье и ману до максимума.", R.drawable.rune_of_regeneration_model)


        Thread{
            db.openHelper.writableDatabase.execSQL("DELETE FROM sqlite_sequence WHERE name='attributes'")
            db.clearAllTables()

            database.runeDao().insert(bounty_rune)
            database.runeDao().insert(water_rune)
            database.runeDao().insert(damage_rune)
            database.runeDao().insert(arcane_rune)
            database.runeDao().insert(wisdom_rune)
            database.runeDao().insert(haste_rune)
            database.runeDao().insert(illusion_rune)
            database.runeDao().insert(invisibility_rune)
            database.runeDao().insert(regeneration_rune)

//            val attributeCount = db.getDao().getAttributeCountByName(strength.name)
//            if(attributeCount == 0){
//                db.getDao().insertAttribute(strength)
//            }

//            db.getDao().deleteStrengthAttribute()
        }.start()


    }
}