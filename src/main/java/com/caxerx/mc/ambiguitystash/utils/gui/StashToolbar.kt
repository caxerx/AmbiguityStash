package com.caxerx.mc.ambiguitystash.utils.gui

import com.caxerx.mc.ambiguitystash.api.StashItem
import com.caxerx.mc.ambiguitystash.utils.ToolbarIconFactory
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

/**
 * Created by caxerx on 2017/6/6.
 */
class StashToolbar {
    val iconMap: Map<String, StashItem> = mapOf(Pair("placeholder", StashItem(ItemStack(Material.STAINED_GLASS_PANE, 1, 5))), Pair("previousPage", StashItem(ItemStack(Material.STAINED_GLASS_PANE, 1, 2))), Pair("nextPage", StashItem(ItemStack(Material.STAINED_GLASS_PANE, 1, 2))), Pair("sortPage", StashItem(ItemStack(Material.LEVER))), Pair("sortStash", StashItem(ItemStack(Material.REDSTONE_TORCH_ON))))
    val format: List<String> = listOf("previousPage", "placeholder", "placeholder", "sortPage", "placeholder", "sortPage", "placeholder", "placeholder", "nextPage")
    fun getToolbar(prevButton: Boolean, nextButton: Boolean): Array<ItemStack> {
        val toolbar: ArrayList<ItemStack> = ArrayList()
        format.forEach { item ->
            if (item.equals("previousPage", true) && prevButton.not()) {
                toolbar.add(ToolbarIconFactory().append(item, iconMap["placeholder"]!!.minecraftItemStack))
            } else if (item.equals("nextPage", true) && nextButton.not()) {
                toolbar.add(ToolbarIconFactory().append(item, iconMap["placeholder"]!!.minecraftItemStack))
            } else {
                toolbar.add(ToolbarIconFactory().append(item, iconMap[item]!!.minecraftItemStack))
            }
        }
        return toolbar.toTypedArray()
    }
}
