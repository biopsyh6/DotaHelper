package com.example.dotahelperproject.pickerpage

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.example.application.HeroPickerUseCases.firebase.AppFirebaseHeroPickerRepository
import com.example.domain.abstractions.hero.HeroPickerRepository
import com.example.domain.entities.HeroPicker
import com.example.domain.entities.PickerListView
import com.example.dotahelperproject.R
import com.example.dotahelperproject.adapters.ListViewAdapter
import com.example.dotahelperproject.databinding.ActivityPickerpageBinding

class PickerpageActivity : AppCompatActivity() {

    private lateinit var heroPickerRepository : HeroPickerRepository
//    private lateinit var apiService: MyApiService
    private lateinit var binding: ActivityPickerpageBinding
    private var newHeroMap: Map<Int, HeroPicker> = mapOf()
//    private lateinit var heroPickerDataFetcher: HeroPickerDataFetcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickerpageBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        heroPickerRepository = AppFirebaseHeroPickerRepository()
        val spinner = binding.pickerSpinner
        heroPickerRepository.getHeroes().observe(this, Observer { heroes ->
            val heroNames = heroes.map { it.name }

            val adapter = ArrayAdapter(this, R.layout.spinner_item_white, heroNames)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        })

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedHeroName = parent?.getItemAtPosition(position) as String
                heroPickerRepository.getHeroByName(selectedHeroName).observe(this@PickerpageActivity, Observer { selectedHero ->
                    selectedHero?.let {
                        val badAgainstHeroesLiveData = it.bad_against.map { heroName ->
                            heroPickerRepository.getHeroByName(heroName)
                        }
//                        val badAgainstListData = mutableListOf<PickerListView>()
                        val badAgainstListData = MediatorLiveData<MutableList<PickerListView>>().apply { value = mutableListOf() }
                        binding.frameLayoutProgressDetail.visibility = View.VISIBLE
                        badAgainstHeroesLiveData.forEach { heroLiveData ->
                            badAgainstListData.addSource(heroLiveData) { hero ->
                                hero?.let {
                                    badAgainstListData.value?.add(PickerListView(it.img, it.name))
                                    badAgainstListData.value = badAgainstListData.value // Trigger an update
                                }

                            }

                        }
                        badAgainstListData.observe(this@PickerpageActivity, Observer { list ->
                            val listViewAdapter = ListViewAdapter(this@PickerpageActivity, list)
                            binding.badListView.adapter = listViewAdapter


                            if (list.size == it.bad_against.size) {
                                binding.frameLayoutProgressDetail.visibility = View.GONE
                            }
                        })
                    }
                })
                heroPickerRepository.getHeroByName(selectedHeroName).observe(this@PickerpageActivity, Observer { selectedHero ->
                    selectedHero?.let {
                        val goodAgainstHeroesLiveData = it.good_against.map { heroName ->
                            heroPickerRepository.getHeroByName(heroName)
                        }
                        val goodAgainstListData = MediatorLiveData<MutableList<PickerListView>>().apply { value = mutableListOf() }
                        binding.frameLayoutProgressDetail.visibility = View.VISIBLE
                        goodAgainstHeroesLiveData.forEach { heroLiveData ->
                            goodAgainstListData.addSource(heroLiveData) { hero ->
                                hero?.let {
                                    goodAgainstListData.value?.add(PickerListView(it.img, it.name))
                                    goodAgainstListData.value = goodAgainstListData.value // Trigger an update
                                }

                            }

                        }
                        goodAgainstListData.observe(this@PickerpageActivity, Observer { list ->
                            val listViewAdapter = ListViewAdapter(this@PickerpageActivity, list)
                            binding.goodListView.adapter = listViewAdapter


                            if (list.size == it.good_against.size) {
                                binding.frameLayoutProgressDetail.visibility = View.GONE
                            }
                        })
                    }
                })

//                val badAgainstListData = mutableListOf<PickerListView>()
//
//                badAgainstHeroesLiveData.forEach { heroLiveData ->
//                    heroLiveData.observe(this@PickerpageActivity, Observer { hero ->
//                        hero?.let {
//                            badAgainstListData.add(PickerListView(it.img, it.name))
//                        }
//                    })
//                }
//
//                val goodAgainstHeroesLiveData = selectedHero.good_against.map { heroName ->
//                    heroPickerRepository.getHeroByName(heroName)
//                }
//
//                val goodAgainstListData = mutableListOf<PickerListView>()
//
//                goodAgainstHeroesLiveData.forEach { heroLiveData ->
//                    heroLiveData.observe(this@PickerpageActivity, Observer { hero ->
//                        hero?.let {
//                            goodAgainstListData.add(PickerListView(it.img, it.name))
//                        }
//                    })
//                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://dota-counter-picker.onrender.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        apiService = retrofit.create(MyApiService::class.java)
//        heroPickerDataFetcher = HeroPickerDataFetcher(apiService)
    }
}