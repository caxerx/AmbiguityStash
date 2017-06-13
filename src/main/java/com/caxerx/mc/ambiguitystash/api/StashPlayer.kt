package com.caxerx.mc.ambiguitystash.api

import com.caxerx.mc.ambiguitystash.storage.Storage
import org.bukkit.entity.Player

/**
 * Created by caxerx on 2017/6/5.
 */
class StashPlayer {
    val player: Player
    var stashSession: StashSession? = null

    constructor(player: Player) {
        this.player = player
    }

    fun createStorageStashSession(storage: Storage): StashSession {
        stashSession = StashSession(this, storage)
        return stashSession!!
    }


}