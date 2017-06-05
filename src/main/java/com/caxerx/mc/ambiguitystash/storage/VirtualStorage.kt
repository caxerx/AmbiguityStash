package com.caxerx.mc.ambiguitystash.storage

import com.caxerx.mc.ambiguitystash.api.StashItemList

/**
 * Created by caxerx on 2017/6/6.
 */
class VirtualStorage : Storage {
    override fun loadPlayer(user: String): StashItemList {
        throw Exception("Loaded a virtual storage stash")
    }

    override fun savePlayer(user: String): Boolean {
        return true
    }
}