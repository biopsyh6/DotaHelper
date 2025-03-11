package com.example.dotahelperproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.domain.entities.ListItems
import com.example.dotahelperproject.R
import com.squareup.picasso.Picasso

class ExpandableItemAdapter(var context: Context, var header:MutableList<String>, var childitem: MutableList<MutableList<ListItems>>)
    : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return header.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return childitem[groupPosition].size
    }

    override fun getGroup(groupPosition: Int): String {
        return header[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): ListItems {
        return childitem[groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val cView = convertView?: LayoutInflater.from(context).inflate(R.layout.list_group, parent, false)
        val itemChild = cView.findViewById<TextView>(R.id.groupHeader)
        itemChild.text = getGroup(groupPosition)
        return cView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var cView = convertView?: LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        val childItemText = cView.findViewById<TextView>(R.id.childitem)
        val childItemImage = cView.findViewById<ImageView>(R.id.childImage)

        val listItem = getChild(groupPosition, childPosition)

        if (listItem.item != null) {
            //if it is item
            val item = listItem.item
            Picasso.get().load(item?.imageUrl).into(childItemImage)
            childItemText.text = listItem.description
            childItemImage.visibility = View.VISIBLE

        } else if (listItem.price != null) {
            //if it is price
            val price = listItem.price
            Picasso.get().load("https://dota2.ru/img/icons/gold.png").into(childItemImage)
            childItemText.text = listItem.price
            childItemImage.visibility = View.VISIBLE
            cView.setOnClickListener(null)
        } else if (listItem.description != null) {
            //just a string
            childItemImage.visibility = View.GONE
            childItemText.text = listItem.description
            cView.setOnClickListener(null)
        } else if (listItem.attribute != null) {
            childItemImage.visibility = View.GONE
            childItemText.text = listItem.attribute
            cView.setOnClickListener(null)
        }
        return cView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}