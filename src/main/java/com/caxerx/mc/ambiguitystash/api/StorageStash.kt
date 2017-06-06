package com.caxerx.mc.ambiguitystash.api

import com.caxerx.mc.ambiguitystash.storage.Stash
import com.google.gson.Gson
import org.bukkit.inventory.ItemStack

class StorageStash : ArrayList<StashItem> {
    var maxPage: Int
    val pageSize: Int

    constructor(stash: Stash) {
        stash.stashContent.forEach { item ->
            add(StashItem(item))
        }
        this.maxPage = stash.maxPage
        this.pageSize = stash.pageSize
    }

    companion object {
        //val airItem: StashItem = StashItem(ItemStack(Material.AIR))
        val gson = Gson()
    }

    val itemStackList: List<ItemStack>
        get() {
            val itemStackList = ArrayList<ItemStack>()
            forEach { rawItem -> itemStackList.add(rawItem.minecraftItemStack) }
            return itemStackList
        }

    override fun clone(): StorageStash {
        return super.clone() as StorageStash
    }

    override fun toString(): String {
        return gson.toJson(this)
    }
}