package com.caxerx.mc.ambiguitystash

import com.caxerx.mc.ambiguitystash.api.StashSession
import com.caxerx.mc.ambiguitystash.listener.StashManager
import com.caxerx.mc.ambiguitystash.listener.ToolbarManager
import com.caxerx.mc.ambiguitystash.storage.JsonStorage
import com.caxerx.mc.ambiguitystash.storage.Storage
import com.caxerx.mc.ambiguitystash.utils.ConfigManager
import com.caxerx.mc.ambiguitystash.utils.gui.StashDrawer
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

/**
 * Created by caxerx on 2017/6/6.
 */
class AmbiguityStash : JavaPlugin() {
    val stashManager = StashManager(this)
    lateinit var configManager: ConfigManager
    lateinit var storage: Storage

    companion object {
        lateinit var instance: AmbiguityStash
    }

    override fun onEnable() {
        instance = this
        initStorage()
        initEvent()
        initConfig()
        initCommand()
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
        storage = JsonStorage(this)
    }

    fun initConfig() {
        configManager = ConfigManager()
        configManager.registerIcon()
        StashSession.stashDrawer = StashDrawer(configManager.getStashTitle(), configManager.getToolbarFormat())
    }

    fun initEvent() {
        server.pluginManager.registerEvents(ToolbarManager(), this)
        server.pluginManager.registerEvents(stashManager, this)
    }

    fun initCommand() {

    }

}