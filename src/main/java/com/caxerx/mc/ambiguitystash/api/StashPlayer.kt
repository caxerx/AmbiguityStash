package com.caxerx.mc.ambiguitystash.api

import com.caxerx.mc.ambiguitystash.storage.Storage
import org.bukkit.entity.Player

/**
 * Created by caxerx on 2017/6/5.
 */
class StashPlayer {
    val player: Player
    var session: StashSession? = null

    constructor(player: Player) {
        this.player = player
    }

    fun getStashSession(): StashSession? {
        return session
    }

    fun createStorageStashSession(storage: Storage): StashSession {
        session = StashSession(this, storage)// TODO: loadStorage
        return session!!
    }

}