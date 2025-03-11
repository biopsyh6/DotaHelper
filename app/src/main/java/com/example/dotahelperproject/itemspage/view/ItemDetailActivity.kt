package com.example.dotahelperproject.itemspage.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.application.ItemUseCases.firebase.AppFirebaseItemRepository
import com.example.domain.entities.Item
import com.example.domain.entities.ListItems
import com.example.dotahelperproject.R
import com.example.dotahelperproject.adapters.ExpandableItemAdapter
import com.example.dotahelperproject.databinding.ActivityItemDetailBinding
import com.squareup.picasso.Picasso

class ItemDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemDetailBinding
    private lateinit var expandableListView: ExpandableListView
    val header: MutableList<String> = ArrayList()
    val childItem: MutableList<MutableList<ListItems>> = ArrayList()
    companion object {
        private const val EXTRA_ITEM = "extra_item"

        fun createIntent(context: Context, item: Item): Intent {
            return Intent(context, ItemDetailActivity::class.java).apply {
                putExtra(EXTRA_ITEM, item)
            }
        }
    }
    private lateinit var item: Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val itemRepository = AppFirebaseItemRepository()
        item = intent.getParcelableExtra(EXTRA_ITEM)!!
        val itemImageView = findViewById<ImageView>(R.id.image_item)
        Picasso.get().load(item.imageUrl).into(itemImageView)
        val itemNameTextView = findViewById<TextView>(R.id.item_name)
        itemNameTextView.text = item.name

        updateUI()

    }

    private fun updateUI() {
        expandableListView = findViewById(R.id.expandaleListViewItem)
        setupData()
        expandableListView.setAdapter(ExpandableItemAdapter(this@ItemDetailActivity, header, childItem))
    }

    private fun setupData() {
        header.clear()
        childItem.clear()

        header.add("About Item")
        header.add("Price")
        header.add("Attributes")

        val infoAboutItem = mutableListOf(
            ListItems(null, null, item.description, null)
        )
        childItem.add(infoAboutItem)

        val priceItem = mutableListOf(
            ListItems(null, item.price, null, null)
        )
        childItem.add(priceItem)

        val attributesList = item.attributes.map {
            ListItems(null, null, null, "${it.label}: ${it.value}")
        }.toMutableList()
        childItem.add(attributesList)
    }
}