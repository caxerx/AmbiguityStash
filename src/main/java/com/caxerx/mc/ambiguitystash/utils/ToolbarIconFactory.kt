package com.caxerx.mc.ambiguitystash.utils

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

/**
 * Created by caxerx on 2017/6/6.
 */
class ToolbarIconFactory {
    fun isToolbarIcon(item: ItemStack): Boolean {
        return item.type != Material.AIR && NBTItemFactory(item).hasKey("AmbiguityStash")
    }

    fun getIconType(item: ItemStack): String {
        return NBTItemFactory(item).getString("AmbiguityStash")
    }

    fun append(iconName: String, item: ItemStack): ItemStack {
        return NBTItemFactory(item).setString("AmbiguityStash", iconName).create()
    }
}