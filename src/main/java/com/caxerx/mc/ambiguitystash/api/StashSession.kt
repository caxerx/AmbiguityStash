package com.caxerx.mc.ambiguitystash.api

import com.caxerx.mc.ambiguitystash.AmbiguityStash
import com.caxerx.mc.ambiguitystash.event.StashChangeEvent
import com.caxerx.mc.ambiguitystash.storage.Stash
import com.caxerx.mc.ambiguitystash.storage.Storage
import com.caxerx.mc.ambiguitystash.utils.gui.StashDrawer
import com.caxerx.mc.ambiguitystash.utils.gui.StashParser
import org.bukkit.inventory.Inventory
import java.lang.Thread.sleep
import java.util.concurrent.CompletableFuture.runAsync

/**
 * Created by caxerx on 2017/6/5.
 */
class StashSession(val player: StashPlayer, val storage: Storage) {
    var currentPageInventory: Inventory? = null
    var nextPageInventory: Inventory? = null
    var currentPage: Int = -1
    var nextPage: Int = -1
    var maxPage: Int = -1
    lateinit var stash: Stash
    var locked: Boolean = true
        set(locked) {
            if (currentPageInventory != null && locked) {
                player.player.closeInventory()
            }
            field = locked
        }


    companion object {
        lateinit var stashDrawer: StashDrawer
    }

    init {
        runAsync {
            try {
                this.stash = Stash(storage.loadPlayer(player.player.uniqueId))
                this.maxPage = stash.maxPage
                this.locked = false
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun saveCurrentPage() {
        val origin = stash.getPage(currentPage)
        val new = StashParser(currentPageInventory!!).stashPageContent
        if (origin.equals(new).not()) {
            val event = StashChangeEvent(player, origin, new)
            AmbiguityStash.instance.server.pluginManager.callEvent(event)
            if (event.isCancelled.not()) {
                stash.savePage(currentPage, event.newContent)
            }
        }
    }

    fun openAfterUnlocked(page: Int) {
        runAsync {
            while (locked) {
                sleep(1000)
            }
            try {
                openPage(page)
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun openPage(page: Int) {
        if (page in 1..maxPage) {
            nextPage = page
            nextPageInventory = stashDrawer.createInventory(this, page, stash.pageSize * 9, stash.getPage(page))
            player.player.openInventory(nextPageInventory)
        } else {
            throw Exception("Illegal Page")
        }
    }


    fun openNextPage() {
        if (currentPage < maxPage) {
            openPage(currentPage + 1)
        } else {
            throw Exception("Illegal Next Page")
        }
    }

    fun openPreviousPage() {
        if (currentPage > 1) {
            openPage(currentPage - 1)
        } else {
            throw Exception("Illegal Previous Page")
        }
    }

}