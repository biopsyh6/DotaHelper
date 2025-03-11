package com.example.dotahelperproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.domain.entities.ListItem
import com.example.dotahelperproject.R
import com.example.dotahelperproject.heroespage.view.SkillDetailActivity
import com.squareup.picasso.Picasso

class ExpandableAdapter(var context: Context, var header:MutableList<String>, var childitem: MutableList<MutableList<ListItem>>)
    :BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return header.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return childitem[groupPosition].size
    }

    override fun getGroup(groupPosition: Int): String {
        return header[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): ListItem {
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

        if (listItem.skill != null) {
            //if it is skill
            val skill = listItem.skill
            Picasso.get().load(skill?.imageUrl).into(childItemImage)
            childItemText.text = listItem.description
            childItemImage.visibility = View.VISIBLE

            cView.setOnClickListener {
                val intent = SkillDetailActivity.createIntent(context, skill!!)
                context.startActivity(intent)
            }
        } else if (listItem.role != null) {
            //if it is role
            val role = listItem.role
            Picasso.get().load(role?.imageUrl).into(childItemImage)
            childItemText.text = listItem.role!!.name
            childItemImage.visibility = View.VISIBLE
            cView.setOnClickListener(null)
        } else {
            //just a string
            childItemImage.visibility = View.GONE
            childItemText.text = listItem.description
            cView.setOnClickListener(null)
        }
        return cView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}