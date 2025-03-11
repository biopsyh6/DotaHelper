package com.example.servicesapi.DataFetchers

import com.example.servicesapi.ItemService

class ItemDataFetcher(
    private val itemService: ItemService
) {
    fun fetchItemDataFromJson(json: String) {
        itemService.parseItemData(json)
    }
}