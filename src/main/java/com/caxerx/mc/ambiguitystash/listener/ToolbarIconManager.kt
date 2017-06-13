package com.caxerx.mc.ambiguitystash.listener

import com.caxerx.mc.ambiguitystash.utils.ToolbarIconFactory
import com.caxerx.mc.ambiguitystash.utils.gui.ToolbarAction
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryDragEvent

/**
 * Created by caxerx on 2017/6/6.
 */
class ToolbarIconManager : Listener {
    val actionList: HashMap<String, ToolbarAction> = HashMap()
    fun registerToolbarAction(icon: String, action: ToolbarAction) {
        actionList.put(icon, action)
    }

    companion object {
        val toolbarIconFactory = ToolbarIconFactory()
    }

    @EventHandler
    fun onToolbarIconClick(e: InventoryClickEvent) {
        if (e.currentItem != null) {
            if (toolbarIconFactory.isToolbarIcon(e.currentItem)) {
                e.isCancelled = true
            }
        }
    }

    @EventHandler
    fun onToolbarIconDrag(e: InventoryDragEvent) {
        if (e.cursor != null) {
            if (toolbarIconFactory.isToolbarIcon(e.cursor)) {
                e.isCancelled = true
            }
        }
    }
}