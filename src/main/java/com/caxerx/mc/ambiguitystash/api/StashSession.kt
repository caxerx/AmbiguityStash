package com.caxerx.mc.ambiguitystash.api

import com.caxerx.mc.ambiguitystash.storage.Stash
import com.caxerx.mc.ambiguitystash.storage.Storage
import com.caxerx.mc.ambiguitystash.utils.gui.StashDrawer
import com.caxerx.mc.ambiguitystash.utils.gui.StashParser
import com.caxerx.mc.ambiguitystash.utils.gui.StashToolbar
import org.bukkit.inventory.Inventory
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable
import java.lang.Thread.sleep
import java.util.concurrent.CompletableFuture.runAsync

/**
 * Created by caxerx on 2017/6/5.
 */
class StashSession(val player: StashPlayer, val storage: Storage) : BukkitRunnable() {
    var currentPageInventory: Inventory? = null
    var nextPageInventory: Inventory? = null
    var currentPage: Int = -1
    var nextPage: Int = -1
    var maxPage: Int = -1
    lateinit var stash: Stash
    var locked: Boolean = true

    companion object {
        lateinit var plugin: Plugin
        val stashDrawer: StashDrawer = StashDrawer(StashToolbar())
    }

    init {
        this.runTaskLaterAsynchronously(plugin, 2400)
        runAsync {
            try {
                this.stash = Stash(storage.loadPlayer(player.player.uniqueId))
                maxPage = stash.maxPage
                this.locked = false
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun run() {
        player.stashSession = null
    }


    fun saveCurrentPage() {
        stash.savePage(currentPage, StashParser(currentPageInventory!!).stashPageContent)
    }

    fun openAfterUnlocked(page: Int) {
        runAsync {
            if (locked) player.player.sendMessage("MESSAGE_INVENTORY_LOCKED_PLEASE_WAIT")
            while (locked) {
                sleep(1000)
                player.player.sendMessage("DEBUG_PAGE_LOCKING_WAIT")
            }
            player.player.sendMessage("DEBUG_PAGE_UNLOCKED")
            try {
                openPage(page)
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun openPage(page: Int) {
        if (page in 1..maxPage) {
            currentPage = page
            currentPageInventory = stashDrawer.createInventory(stash.pageSize * 9, stash.getPage(page), page != 1, page != maxPage)
            player.player.openInventory(currentPageInventory)
        } else {
            throw Exception("Illegal Page")
        }
    }


    fun openNextPage() {
        if (currentPage < maxPage) {
            nextPage = currentPage + 1
            openPage(nextPage)
        } else {
            throw Exception("Illegal Next Page")
        }
    }

    fun openPreviousPage() {
        if (currentPage > 1) {
            nextPage = currentPage - 1
            openPage(nextPage)
        } else {
            throw Exception("Illegal Previous Page")
        }
    }

}