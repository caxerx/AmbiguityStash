package com.caxerx.mc.ambiguitystash.utils.gui

import com.caxerx.mc.ambiguitystash.api.StashSession
import com.caxerx.mc.ambiguitystash.listener.StashManager
import com.caxerx.mc.ambiguitystash.listener.ToolbarManager
import com.caxerx.mc.ambiguitystash.utils.ToolbarIconFactory
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

/**
 * Created by caxerx on 2017/6/16.
 */
class NextPageIcon(var material: Material, var data: Short?, var displayName: String?, var lore: ArrayList<String>?) : ToolbarIcon {
    override val type: String = "nextPage"

    override fun getItemStack(session: StashSession): ItemStack {
        if (session.nextPage == session.maxPage) {
            return ToolbarManager.instance.actionList["placeholder"]!!.getItemStack(session)
        }
        var item = ItemStack(material, 1, data ?: 0)
        var meta = item.itemMeta
        if (lore != null) {
            lore!!.forEachIndexed { i, item ->
                lore!![i] = ChatColor.translateAlternateColorCodes('&', item.replace("{page}", (session.nextPage + 1).toString()))
            }
            meta.lore = lore
        }
        if (displayName != null) meta.displayName = ChatColor.translateAlternateColorCodes('&', displayName).replace("{page}", (session.nextPage + 1).toString())
        item.itemMeta = meta
        return ToolbarIconFactory().append(type, item)
    }

    override fun onClick(e: InventoryClickEvent) {
        StashManager.instance.getPlayer(e.whoClicked as Player).stashSession!!.openNextPage()
    }
}
