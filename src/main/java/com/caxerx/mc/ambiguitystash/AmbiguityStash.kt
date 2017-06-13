package com.caxerx.mc.ambiguitystash

import com.caxerx.mc.ambiguitystash.api.StashSession
import com.caxerx.mc.ambiguitystash.listener.StashManager
import com.caxerx.mc.ambiguitystash.listener.ToolbarIconManager
import com.caxerx.mc.ambiguitystash.storage.JsonStorage
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

/**
 * Created by caxerx on 2017/6/6.
 */
class AmbiguityStash : JavaPlugin() {
    val stashManager = StashManager(this)
    val storage = JsonStorage(this)
    override fun onEnable() {
        StashSession.plugin = this
        server.pluginManager.registerEvents(stashManager, this)
        server.pluginManager.registerEvents(ToolbarIconManager(), this)
    }

    override fun onCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<out String>?): Boolean {
        if (label.equals("pi")) {
            if (sender is Player) {
                try {
                    val stashPlayer = stashManager.getPlayer(sender)
                    if (stashPlayer.stashSession != null) {
                        stashPlayer.stashSession!!.openAfterUnlocked(1)
                    } else {
                        stashPlayer.createStorageStashSession(storage).openAfterUnlocked(1)
                    }
                } catch(e: Exception) {
                    e.printStackTrace()
                }
            }
            return true
        }
        return false
    }

    fun initStorage() {

    }

    fun initConfig() {

    }

    fun initCommand() {

    }

    fun initCache() {

    }
}