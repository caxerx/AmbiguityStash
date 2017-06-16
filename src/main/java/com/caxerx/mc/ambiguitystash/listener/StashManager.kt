package com.caxerx.mc.ambiguitystash.listener

import com.caxerx.mc.ambiguitystash.api.StashPlayer
import com.caxerx.mc.ambiguitystash.storage.StorageStash
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.Plugin
import java.util.concurrent.CompletableFuture.runAsync

/**
 * Created by caxerx on 2017/6/5.
 */
class StashManager(val plugin: Plugin) : Listener {
    private val players: HashMap<Player, StashPlayer> = HashMap()

    init {
        instance = this
    }

    companion object {
        lateinit var instance: StashManager
    }

    fun getPlayer(player: Player): StashPlayer {
        return players.getOrPut(player) { StashPlayer(player) }
    }

    @EventHandler
    fun onInventoryClose(e: InventoryCloseEvent) {
        val stashPlayer = getPlayer(e.player as Player)
        if (stashPlayer.stashSession != null && stashPlayer.stashSession!!.currentPage != -1 && e.view.topInventory.equals(stashPlayer.stashSession!!.currentPageInventory)) {
            stashPlayer.stashSession!!.saveCurrentPage()
            if (stashPlayer.stashSession!!.nextPage == -1) {
                stashPlayer.stashSession!!.currentPage = -1
                stashPlayer.stashSession!!.currentPageInventory = null
                runAsync {
                    try {
                        stashPlayer.stashSession!!.storage.savePlayer(stashPlayer.player.uniqueId, StorageStash(stashPlayer.stashSession!!.stash))
                    } catch(e: Exception) {
                        e.printStackTrace()
                    }

                }
            }
        }
    }

    @EventHandler
    fun onInventoryOpen(e: InventoryOpenEvent) {
        val stashPlayer = getPlayer(e.player as Player)
        if (stashPlayer.stashSession != null && stashPlayer.stashSession!!.nextPage != -1) {
            e.view.topInventory.equals(stashPlayer.stashSession!!.nextPageInventory)
            stashPlayer.stashSession!!.currentPage = stashPlayer.stashSession!!.nextPage
            stashPlayer.stashSession!!.currentPageInventory = stashPlayer.stashSession!!.nextPageInventory
            stashPlayer.stashSession!!.nextPageInventory = null
            stashPlayer.stashSession!!.nextPage = -1
        }
    }

    @EventHandler
    fun onPlayerQuit(e: PlayerQuitEvent) {
        players.remove(e.player)
    }
}