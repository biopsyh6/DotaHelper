package com.example.dotahelperproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.domain.entities.PickerListView
import com.example.dotahelperproject.R
import com.squareup.picasso.Picasso

class ListViewAdapter(val context: Context, val dataList: List<PickerListView>): BaseAdapter() {
    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItem(position: Int): PickerListView {
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var myConvertView = convertView
        if (myConvertView == null) {
            myConvertView = LayoutInflater.from(context).inflate(R.layout.list_item_view, parent, false)
        }

        val currentItem = getItem(position)

        val imageItem = myConvertView?.findViewById<ImageView>(R.id.list_item_image)
        val titleItem = myConvertView?.findViewById<TextView>(R.id.list_item_title)

        Picasso.get().load(currentItem.image).into(imageItem)
        titleItem?.text = currentItem.title

        return myConvertView!!
    }

}