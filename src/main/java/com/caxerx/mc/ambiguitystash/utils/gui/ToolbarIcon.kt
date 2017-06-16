package com.caxerx.mc.ambiguitystash.utils.gui

import com.caxerx.mc.ambiguitystash.api.StashSession
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

/**
 * Created by caxerx on 2017/6/16.
 */
interface ToolbarIcon {
    val type: String
    fun getItemStack(session: StashSession): ItemStack
    fun onClick(e: InventoryClickEvent)
}