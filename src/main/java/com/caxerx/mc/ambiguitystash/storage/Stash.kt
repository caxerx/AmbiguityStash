package com.caxerx.mc.ambiguitystash.storage

import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.*

/**
 * Created by caxerx on 2017/6/5.
 */
class Stash(ownerUniqueId: UUID) {
    lateinit var stashContent: ArrayList<ItemStack>

    init {
        stashContent = null!!//TODO: LOAD CONTENT
    }

    fun save() {}
    fun getPage(page: Int): Inventory {
        return null!! //TODO: CHANGE
    }
}