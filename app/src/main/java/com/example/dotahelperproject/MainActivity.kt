package com.example.dotahelperproject

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dotahelperproject.databinding.ActivityMainBinding
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

        ///////////////////////////////////////DELETING
//        var root: DatabaseReference = FirebaseDatabase.getInstance("https://dotahelperproject-default-rtdb.europe-west1.firebasedatabase.app/").getReference()
//        root.setValue(null)

//////////////////////////////////////////////////////////



//        val attributeRepository = AppFirebaseAttributeRepository()
//        val roleRepository = AppFirebaseRoleRepository()
//        val heroRepository = AppFirebaseHeroRepository()
//        var heroService = HeroService(attributeRepository, roleRepository, heroRepository)
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://www.dota2.com/")
//            .addConverterFactory(ScalarsConverterFactory.create())
//            .build()
//        val apiService = retrofit.create(MyApiService::class.java)
//
//        val fetcher = HeroDataFetcher(heroService, apiService)
//        DbFirebaseInitializer.initializeAttributesAndRoles()
//        for(id in 1..138){
//            fetcher.fetchHeroData(id)
//        }




//        val service = retrofit.create(MyApiService::class.java)
//        val call = service.fetchHeroData("english", 102)
//        call.enqueue(object : Callback<String> {
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                if(response.isSuccessful){
//                    val json = response.body()
//                    if (json != null) {
//                        heroService.parseHeroData(json)
//                    }
//                }
//                else {
//                    Log.d("Error", "${response.code()}")
//                }
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                Log.d("Error", "${t.message}")
//            }
//        })


/////////////////////////////////////////////////////////////////




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


        val bounty_rune = com.example.domain.entities.Rune(
            null, "Bounty Rune", "Дает дополнительное золото.",
            R.drawable.rune_of_bounty_model
        )
        val water_rune = com.example.domain.entities.Rune(
            null, "Water Rune",
            "Мгновенно восстанавливает 40 здоровья и 80 маны.", R.drawable.rune_of_water_model
        )
//        val runeRepository = AppFirebaseRuneRepository()
//        runeRepository.saveRune(bounty_rune)
//        runeRepository.saveRune(water_rune)


//        val database = FirebaseDatabase.getInstance("https://dotahelperproject-default-rtdb.europe-west1.firebasedatabase.app/")
//        val reference = database.getReference("runes")
//
//        val runeid = reference.push().key
//        val runeReference = reference.child(runeid.toString())
//
//        runeReference.setValue(bounty_rune).addOnSuccessListener {
//            Toast.makeText(this, "Успешно", Toast.LENGTH_LONG).show()
//        }


//        val dbInitializer = DbInitializer(database)
//        dbInitializer.initialize()
        Thread{
//            db.openHelper.writableDatabase.execSQL("DELETE FROM sqlite_sequence WHERE name='attributes'")
//            db.clearAllTables()
//
//            database.runeDao().insert(bounty_rune)
//            database.runeDao().insert(water_rune)
//            database.runeDao().insert(damage_rune)
//            database.runeDao().insert(arcane_rune)
//            database.runeDao().insert(wisdom_rune)
//            database.runeDao().insert(haste_rune)
//            database.runeDao().insert(illusion_rune)
//            database.runeDao().insert(invisibility_rune)
//            database.runeDao().insert(regeneration_rune)

//            val attributeCount = db.getDao().getAttributeCountByName(strength.name)
//            if(attributeCount == 0){
//                db.getDao().insertAttribute(strength)
//            }

//            db.getDao().deleteStrengthAttribute()
        }.start()


    }
}