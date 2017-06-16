package com.caxerx.mc.ambiguitystash.api

import com.caxerx.mc.ambiguitystash.AmbiguityStash
import com.caxerx.mc.ambiguitystash.listener.StashManager
import com.caxerx.mc.ambiguitystash.storage.StorageStash
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * Created by caxerx on 2017/6/16.
 */
class StashApi {
    companion object {
        lateinit var instance: StashApi
    }

    init {
        instance = this
    }

    fun addItem(player: Player, toAdd: ItemStack): Boolean {
        val stashPlayer = StashManager.instance.getPlayer(player)
        if (stashPlayer.stashSession != null) {
            stashPlayer.createStorageStashSession(AmbiguityStash.instance.storage)
        }
        stashPlayer.stashSession!!.locked = true
        val stashContent = stashPlayer.stashSession!!.stash.stashContent
        stashContent.forEachIndexed { i, item ->
            if (item.isSimilar(toAdd) && (item.amount + toAdd.amount) <= item.type.maxStackSize) {
                stashContent[i].amount = item.amount + toAdd.amount
                stashPlayer.stashSession!!.storage.savePlayer(player.uniqueId, StorageStash(stashPlayer.stashSession!!.stash))
                stashPlayer.stashSession!!.locked = false
                return true
            }
        }

        stashContent.forEachIndexed { i, item ->
            if (item == null || item.type == Material.AIR) {
                stashContent[i] = item
                stashPlayer.stashSession!!.storage.savePlayer(player.uniqueId, StorageStash(stashPlayer.stashSession!!.stash))
                stashPlayer.stashSession!!.locked = false
                return true
            }
        }

        stashPlayer.stashSession!!.locked = false
        return false
    }

    fun setMaxPage(player: Player, page: Int) {
        val stashPlayer = StashManager.instance.getPlayer(player)
        if (stashPlayer.stashSession != null) {
            stashPlayer.createStorageStashSession(AmbiguityStash.instance.storage)
        }
        stashPlayer.stashSession!!.locked = true
        stashPlayer.stashSession!!.stash.maxPage = page
        stashPlayer.stashSession!!.storage.savePlayer(player.uniqueId, StorageStash(stashPlayer.stashSession!!.stash))
        stashPlayer.stashSession!!.locked = false
    }

    fun setPageSize(player: Player, pagesize: Int) {
        val stashPlayer = StashManager.instance.getPlayer(player)
        if (stashPlayer.stashSession != null) {
            stashPlayer.createStorageStashSession(AmbiguityStash.instance.storage)
        }
        stashPlayer.stashSession!!.locked = true
        stashPlayer.stashSession!!.stash.maxPage = pagesize
        stashPlayer.stashSession!!.storage.savePlayer(player.uniqueId, StorageStash(stashPlayer.stashSession!!.stash))
        stashPlayer.stashSession!!.locked = false
    }
}