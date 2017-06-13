package com.caxerx.mc.ambiguitystash.utils.gui

import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

/**
 * Created by caxerx on 2017/6/6.
 */
class StashDrawer(val toolbar: StashToolbar) {
    fun createInventory(size: Int, content: Array<ItemStack>, prev: Boolean, next: Boolean): Inventory {
        var inventory = Bukkit.createInventory(null, size + 9)
        content.forEachIndexed() { i, item ->
            inventory.setItem(i, item)
        }
        toolbar.getToolbar(prev, next).forEachIndexed { i, item ->
            inventory.setItem(size + i, item)
        }
        return inventory
    }
}


