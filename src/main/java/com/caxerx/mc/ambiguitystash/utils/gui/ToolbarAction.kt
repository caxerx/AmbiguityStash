package com.caxerx.mc.ambiguitystash.utils.gui

import org.bukkit.event.inventory.InventoryClickEvent

/**
 * Created by caxerx on 2017/6/6.
 */
interface ToolbarAction {
    fun execute(e: InventoryClickEvent)
}