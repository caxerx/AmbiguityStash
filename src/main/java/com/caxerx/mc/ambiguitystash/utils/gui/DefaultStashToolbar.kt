package com.caxerx.mc.ambiguitystash.utils.gui

import com.caxerx.mc.ambiguitystash.api.StashSession
import org.bukkit.inventory.ItemStack

/**
 * Created by caxerx on 2017/6/6.
 */
class DefaultStashToolbar(var toolbarFormat: List<ToolbarIcon>) : StashToolbar {
    override val type: String = "default"
    override fun getToolbar(session: StashSession): Array<ItemStack> {
        val toolbar: ArrayList<ItemStack> = ArrayList()
        toolbarFormat.forEach { item ->
            toolbar.add(item.getItemStack(session))
        }
        return toolbar.toTypedArray()
    }
}
