package com.caxerx.mc.ambiguitystash.utils.gui

import com.caxerx.mc.ambiguitystash.api.StashSession
import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

/**
 * Created by caxerx on 2017/6/6.
 */
class StashDrawer(val title: String, val toolbar: DefaultStashToolbar) {
    fun createInventory(session: StashSession, page: Int, size: Int, content: Array<ItemStack>): Inventory {
        var inventory = Bukkit.createInventory(null, size + 9, title.replace("{page}", page.toString()).replace("{maxpage}", session.maxPage.toString()))
        content.forEachIndexed { i, item ->
            inventory.setItem(i, item)
        }

        toolbar.getToolbar(session).forEachIndexed { i, item ->
            inventory.setItem(size + i, item)
        }
        return inventory
    }
}


