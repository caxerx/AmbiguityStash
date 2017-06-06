package com.caxerx.mc.ambiguitystash.api

import com.caxerx.mc.ambiguitystash.storage.Stash
import com.caxerx.mc.ambiguitystash.storage.Storage
import com.caxerx.mc.ambiguitystash.utils.gui.StashDrawer
import com.caxerx.mc.ambiguitystash.utils.gui.StashParser
import org.bukkit.inventory.Inventory
import java.util.concurrent.CompletableFuture.runAsync

/**
 * Created by caxerx on 2017/6/5.
 */
class StashSession {
    val player: StashPlayer
    var currentPageInventory: Inventory? = null
    var nextPageInventory: Inventory? = null
    var currentPage: Int = -1
    var nextPage: Int = -1
    var maxPage: Int
    lateinit var stash: Stash
    val storage: Storage
    var locked: Boolean = true

    constructor(player: StashPlayer, storage: Storage) {
        this.player = player
        this.storage = storage
        maxPage = 0 // TODO: NEED CHANGE
        runAsync {
            this.stash = Stash(storage.loadPlayer(player.player.uniqueId))
            this.locked = false
        }
    }

    companion object {
        val stashDrawer: StashDrawer = StashDrawer(null!!, null!!) //TODO: NEED CHANGE
    }

    fun saveCurrentPage() {
        var currentPageContent = StashParser(currentPageInventory!!).stashPageContent

    }

    fun resetNextPage() {
        nextPage = -1
    }

    fun openAfterUnlocked() {
        runAsync {
            while (this.locked.not()) {
                player.player.openInventory(stashDrawer.createInventory(stash.getPage(1)))
            }
        }
    }

    fun openPage(page: Int) {
        if (locked.not()) {
            if (page in 1..maxPage) {
                player.player.openInventory(stashDrawer.createInventory(stash.getPage(page)))
            } else {
                throw Exception("Illegal Page")
            }
        } else {
            throw Exception("Inventory Lock")
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