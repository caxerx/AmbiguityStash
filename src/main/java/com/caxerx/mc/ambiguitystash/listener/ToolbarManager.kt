package com.caxerx.mc.ambiguitystash.listener

import com.caxerx.mc.ambiguitystash.api.StashItem
import com.caxerx.mc.ambiguitystash.utils.ToolbarIconFactory
import com.caxerx.mc.ambiguitystash.utils.gui.NextPageIcon
import com.caxerx.mc.ambiguitystash.utils.gui.PlaceholderIcon
import com.caxerx.mc.ambiguitystash.utils.gui.PreviousPageIcon
import com.caxerx.mc.ambiguitystash.utils.gui.ToolbarIcon
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.inventory.ItemStack

/**
 * Created by caxerx on 2017/6/6.
 */
class ToolbarManager : Listener {
    val actionList: HashMap<String, ToolbarIcon> = HashMap()

    init {
        instance = this
    }


    fun registerToolbarIcon(name: String, icon: ToolbarIcon) {
        actionList.put(name, icon)
    }

    companion object {
        lateinit var instance: ToolbarManager
        val toolbarIconFactory = ToolbarIconFactory()
    }

    @EventHandler
    fun onToolbarIconClick(e: InventoryClickEvent) {
        if (e.currentItem != null) {
            if (toolbarIconFactory.isToolbarIcon(e.currentItem)) {
                e.isCancelled = true
                if (actionList.containsKey(ToolbarIconFactory().getIconType(e.currentItem))) {
                    actionList[ToolbarIconFactory().getIconType(e.currentItem)]!!.onClick(e)
                }
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