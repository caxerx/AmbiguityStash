package com.caxerx.mc.ambiguitystash.utils.gui

import com.caxerx.mc.ambiguitystash.api.StashSession
import org.bukkit.inventory.ItemStack

/**
 * Created by caxerx on 2017/6/16.
 */
interface StashToolbar {
    fun getToolbar(session: StashSession): Array<ItemStack>
    val type: String
}