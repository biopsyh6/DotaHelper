package com.example.dotahelperproject.itemspage.view.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.application.ItemUseCases.firebase.AppFirebaseItemRepository
import com.example.domain.entities.Item
import com.example.dotahelperproject.R
import com.example.dotahelperproject.itemspage.view.ItemspageActivity
import com.squareup.picasso.Picasso

class ItemFragment : Fragment() {
    private lateinit var gridLayout: GridLayout
    private var itemRepository = AppFirebaseItemRepository()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item, container, false)
        gridLayout = view.findViewById(R.id.grid_layout_item)

        val shop = "shop"
        itemRepository.getItemsByTypeName(shop).
        observe(viewLifecycleOwner, { shopItems ->
            populateGridLayout(shopItems)
        })
//        itemRepository.getItems().observe(viewLifecycleOwner, {items ->
//            populateGridLayout(items)
//        })
        return view
    }

    private fun populateGridLayout(items: List<Item>){

        gridLayout.removeAllViews()

        for (item in items) {
            val itemView = LayoutInflater.from(context).inflate(R.layout.item_item_grid, gridLayout,
                false)
            val imageView: ImageView = itemView.findViewById(R.id.item_image)
            Picasso.get().load(item.imageUrl).into(imageView)

            itemView.setOnClickListener {
                (activity as? ItemspageActivity)?.openItemDetailFragment(item)
            }
            gridLayout.addView(itemView)
        }

    }

}