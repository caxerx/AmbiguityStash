package com.caxerx.mc.ambiguitystash.utils.gui

import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

/**
 * Created by caxerx on 2017/6/6.
 */
class StashParser(stashGui: Inventory) {
    var stashPageContent: Array<ItemStack> = stashGui.contents.copyOfRange(0, stashGui.contents.size - 9)
}