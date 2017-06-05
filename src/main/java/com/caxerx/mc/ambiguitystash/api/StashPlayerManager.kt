package com.caxerx.mc.ambiguitystash.api

import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

/**
 * Created by caxerx on 2017/6/5.
 */
class StashPlayerManager(val plugin: Plugin) {
    private val players: HashMap<Player, StashPlayer> = HashMap()

    fun getPlayer(player: Player): StashPlayer {
        return players.getOrPut(player) { StashPlayer(player) }
    }

}