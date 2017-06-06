package com.caxerx.mc.ambiguitystash.api

import com.caxerx.mc.ambiguitystash.storage.Stash
import com.caxerx.mc.ambiguitystash.storage.Storage
import com.caxerx.mc.ambiguitystash.utils.gui.StashParser
import org.bukkit.inventory.Inventory

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
    val stash: Stash
    val storage: Storage

    constructor(player: StashPlayer, stash: Stash, storage: Storage) {
        this.player = player
        this.stash = stash
        this.storage = storage
        maxPage = 0 // TODO: NEED CHANGE
    }

    fun saveCurrentPage() {
        var currentPageContent = StashParser(currentPageInventory!!).stashPageContent

    }

    fun resetNextPage() {
        nextPage = -1
    }

    fun openPage(page: Int) {
        if (page > 0 || page <= maxPage) {
            player.player.openInventory(stash.getPage(page))
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