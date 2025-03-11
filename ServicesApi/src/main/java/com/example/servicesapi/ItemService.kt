package com.example.servicesapi

import com.example.application.dbInitializer.DbFirebaseInitializer
import com.example.domain.abstractions.item.ItemRepository
import com.example.domain.entities.Attributes
import com.example.domain.entities.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray

class ItemService(
    private val itemRepository: ItemRepository
) {
    fun parseItemData(data: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val itemsArray = JSONArray(data)
            val itemsList = mutableListOf<Item>()

            for (i in 0 until itemsArray.length()) {
                val itemObject = itemsArray.getJSONObject(i)
//                val attributesArray = itemObject.getJSONArray("attributes")
                val attributesList = if (itemObject.has("attributes") && !itemObject.isNull("attributes")) {

                    val attributesArray = itemObject.getJSONArray("attributes")
                    val tempAttributesList = mutableListOf<Attributes>()
                    for (j in 0 until attributesArray.length()) {
                        val attributeObject = attributesArray.getJSONObject(j)
                        val attributes = Attributes(
                            value = attributeObject.getString("value"),
                            label = attributeObject.getString("label")
                        )
                        tempAttributesList.add(attributes)
                    }
                    tempAttributesList
                } else {
                    mutableListOf()
                }

                val item = Item(
                    name = itemObject.getString("name"),
                    description = itemObject.getString("description"),
                    imageUrl = itemObject.getString("imageUrl"),
                    price = itemObject.getString("price"),
                    attributes = attributesList,
                    type = itemObject.getString("type")
                )
                itemsList.add(item)
            }
            DbFirebaseInitializer.itemInitialize(itemsList)
        }
    }
}