package com.caxerx.mc.ambiguitystash.utils.gui

import com.caxerx.mc.ambiguitystash.api.StashSession
import com.caxerx.mc.ambiguitystash.utils.ToolbarIconFactory
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

/**
 * Created by caxerx on 2017/6/16.
 */
class PlaceholderIcon(var material: Material, var data: Short?, var displayName: String?, var lore: ArrayList<String>?) : ToolbarIcon {
    override val type: String = "placeholder"

    override fun getItemStack(session: StashSession): ItemStack {
        var item = ItemStack(material, 1, data ?: 0)
        var meta = item.itemMeta
        if (lore != null) {
            lore!!.forEachIndexed { i, item ->
                lore!![i] = ChatColor.translateAlternateColorCodes('&', item)
            }
            meta.lore = lore
        }
        if (displayName != null) meta.displayName = ChatColor.translateAlternateColorCodes('&', displayName)
        item.itemMeta = meta
        return ToolbarIconFactory().append(type, item)
    }

    override fun onClick(e: InventoryClickEvent) {
        //placeholder do nothing
    }
}