package com.caxerx.mc.ambiguitystash.storage

import com.caxerx.mc.ambiguitystash.api.StashItemList
import org.bukkit.inventory.ItemStack
import java.util.*

/**
 * Created by caxerx on 2017/6/6.
 */
class VirtualStorage : Storage {
    override fun savePlayer(user: UUID, content: List<ItemStack>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadPlayerRaw(user: String): StashItemList {
        throw Exception("Loaded raw at virtual storage stash")
    }

    override fun loadPlayer(user: String): Stash {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}