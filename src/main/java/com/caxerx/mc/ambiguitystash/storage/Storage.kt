package com.caxerx.mc.ambiguitystash.storage

import com.caxerx.mc.ambiguitystash.api.StashItemList
import org.bukkit.inventory.ItemStack
import java.util.*

/**
 * Created by caxerx on 2017/6/5.
 */
interface Storage {
    fun loadPlayer(user: String): Stash
    fun loadPlayerRaw(user: String): StashItemList
    fun savePlayer(user: UUID, content: List<ItemStack>): Boolean
}