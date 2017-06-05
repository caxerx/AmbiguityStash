package com.caxerx.mc.ambiguitystash.api

import com.caxerx.mc.ambiguitystash.api.StashSession
import com.caxerx.mc.ambiguitystash.storage.Stash
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

    fun createStashSession(stash: Stash): StashSession {
        session = StashSession(this, stash)
        return session!!
    }

}