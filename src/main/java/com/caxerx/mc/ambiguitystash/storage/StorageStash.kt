package com.caxerx.mc.ambiguitystash.storage

import com.caxerx.mc.ambiguitystash.api.StashItem
import com.google.gson.Gson
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class StorageStash {
    var maxPage: Int
    val pageSize: Int
    val content: ArrayList<StashItem> = ArrayList()

    constructor(stash: Stash) {
        stash.stashContent.forEach { item ->
            if (item == null) {
                content.add(airItem)
            } else {
                content.add(StashItem(item))
            }
        }
        this.maxPage = stash.maxPage
        this.pageSize = stash.pageSize
    }

    companion object {
        val airItem: StashItem = StashItem(ItemStack(Material.AIR))
        val gson = Gson()
    }

    val itemStackList: List<ItemStack>
        get() {
            val itemStackList = ArrayList<ItemStack>()
            content.forEach { rawItem -> itemStackList.add(rawItem.minecraftItemStack) }
            return itemStackList
        }

    override fun toString(): String {
        return gson.toJson(this)
    }
}