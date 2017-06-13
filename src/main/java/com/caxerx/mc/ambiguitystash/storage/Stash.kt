package com.caxerx.mc.ambiguitystash.storage

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

/**
 * Created by caxerx on 2017/6/5.
 */
class Stash(var stashContent: Array<ItemStack>, var maxPage: Int, val pageSize: Int) {
    constructor(storageStash: StorageStash) : this(storageStash.itemStackList.toTypedArray(), storageStash.maxPage, storageStash.pageSize)

    init {
        var stashContent = arrayListOf(*stashContent)
        if (stashContent.size < pageSize * 9 * maxPage) {
            for (i in IntRange((stashContent.size), (pageSize * 9 * maxPage) - 1)) {
                stashContent.add(ItemStack(Material.AIR))
            }
            this.stashContent = stashContent.toTypedArray()
        }
    }

    fun getPage(page: Int): Array<ItemStack> {
        //first (page-1)*(pageSize*9)
        //last (page-1)*(pageSize*9)+(pageSize*9)-1
        if (page in 1..maxPage) {
            return stashContent.copyOfRange((page - 1) * (pageSize * 9), (page - 1) * (pageSize * 9) + (pageSize * 9) - 1)
        } else {
            throw Exception("IllegalPage")
        }
    }

    fun savePage(page: Int, content: Array<ItemStack>) {
        if (content.size < pageSize * 9) {
            for (i in IntRange((content.size), (pageSize * 9) - 1)) {
                content[i] = ItemStack(Material.AIR)
            }
        }
        content.forEachIndexed { i, item ->
            if (i <= pageSize * 9) {
                stashContent[(page - 1) * (pageSize * 9) + i] = item
            }
        }
    }
}