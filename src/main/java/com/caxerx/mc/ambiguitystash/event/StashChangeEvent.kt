package com.caxerx.mc.ambiguitystash.event

import com.caxerx.mc.ambiguitystash.api.StashPlayer
import org.bukkit.event.Cancellable
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerEvent
import org.bukkit.inventory.ItemStack

/**
 * Created by caxerx on 2017/6/16.
 */
class StashChangeEvent(val stashPlayer: StashPlayer, val originContent: Array<ItemStack>, var newContent: Array<ItemStack>) : PlayerEvent(stashPlayer.player), Cancellable {
    private var cancelled: Boolean = false
    val myhandlers = HandlerList()

    override fun getHandlers(): HandlerList {
        return myhandlers
    }

    override fun setCancelled(p0: Boolean) {
        cancelled = p0
    }

    override fun isCancelled(): Boolean {
        return cancelled
    }
}