package com.caxerx.mc.ambiguitystash.utils

import com.caxerx.mc.ambiguitystash.AmbiguityStash
import com.caxerx.mc.ambiguitystash.listener.ToolbarManager
import com.caxerx.mc.ambiguitystash.utils.gui.*
import org.apache.commons.io.FileUtils
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.configuration.file.FileConfiguration
import java.io.File

/**
 * Created by caxerx on 2017/6/16.
 */
class ConfigManager {
    val config: FileConfiguration = AmbiguityStash.instance.config

    init {
        val file = File(AmbiguityStash.instance.dataFolder.toString() + File.separator.toString() + "config.yml")
        if (file.exists().not()) {
            FileUtils.copyInputStreamToFile(AmbiguityStash.instance.getResource("config.yml"), file)
        }
        config.options().copyDefaults(true)
        AmbiguityStash.instance.saveConfig()
    }

    fun registerIcon() {
        ToolbarManager.instance.registerToolbarIcon("previousPage", PreviousPageIcon(Material.getMaterial(config.getString("icon-style.previous-page.material")), config.getInt("icon-style.previous-page.data").toShort(), config.getString("icon-style.previous-page.name"), ArrayList(config.getStringList("icon-style.previous-page.lore"))))
        ToolbarManager.instance.registerToolbarIcon("nextPage", NextPageIcon(Material.getMaterial(config.getString("icon-style.next-page.material")), config.getInt("icon-style.next-page.data").toShort(), config.getString("icon-style.next-page.name"), ArrayList(config.getStringList("icon-style.next-page.lore"))))
        ToolbarManager.instance.registerToolbarIcon("placeholder", PlaceholderIcon(Material.getMaterial(config.getString("icon-style.placeholder.material")), config.getInt("icon-style.placeholder.data").toShort(), config.getString("icon-style.placeholder.name"), ArrayList(config.getStringList("icon-style.placeholder.lore"))))
    }

    fun getToolbarFormat(): DefaultStashToolbar {
        var toolbar: ArrayList<ToolbarIcon> = arrayListOf()
        for (i in 0..8) {
            toolbar.add(ToolbarManager.instance.actionList["placeholder"]!!)
        }

        config.getConfigurationSection("toolbar-format.stash").getKeys(false).forEach { i ->
            toolbar[Integer.parseInt(i)] = ToolbarManager.instance.actionList[config.getString("toolbar-format.stash." + i)]!!
        }
        return DefaultStashToolbar(toolbar)
    }

    fun getStashTitle(): String {
        return ChatColor.translateAlternateColorCodes('&', config.getString("title.stash-page"))
    }

    fun getDefaultInventorySize(): Int {
        return config.getInt("default-inventory-size")
    }

    fun getDefaultInventoryPage(): Int {
        return config.getInt("default-inventory-page")
    }
}

/*
toolbar-format:
  stash:
    0: previousPage
    8: nextPage
 */