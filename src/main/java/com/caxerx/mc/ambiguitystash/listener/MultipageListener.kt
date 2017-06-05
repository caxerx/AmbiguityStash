package com.caxerx.mc.ambiguitystash.listener

import com.caxerx.mc.ambiguitystash.api.StashPlayerManager
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent

/**
 * Created by caxerx on 2017/6/5.
 */
class MultipageListener(val playerManager: StashPlayerManager) : Listener {
    @EventHandler
    fun onInventoryClose(e: InventoryCloseEvent) {
        val stashPlayer = playerManager.getPlayer(e.player as Player)
        if (stashPlayer.session != null && stashPlayer.session!!.currentPage != -1) {
            stashPlayer.session!!.saveCurrentPage()
            if (stashPlayer.session!!.nextPage != -1) {
                e.view.topInventory.equals(stashPlayer.session!!.currentPageInventory)
                stashPlayer.session!!.stash.save()
                stashPlayer.session = null
            }
        }
    }

    @EventHandler
    fun onInventoryOpen(e: InventoryOpenEvent) {
        val stashPlayer = playerManager.getPlayer(e.player as Player)
        if (stashPlayer.session != null && stashPlayer.session!!.nextPage != -1) {
            e.view.topInventory.equals(stashPlayer.session!!.nextPageInventory)
            stashPlayer.session!!.currentPage = stashPlayer.session!!.nextPage
            stashPlayer.session!!.currentPageInventory = stashPlayer.session!!.nextPageInventory
            stashPlayer.session!!.nextPageInventory = null
            stashPlayer.session!!.nextPage = -1
        }
    }
}