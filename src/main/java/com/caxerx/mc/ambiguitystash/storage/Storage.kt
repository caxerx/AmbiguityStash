package com.caxerx.mc.ambiguitystash.storage

import com.caxerx.mc.ambiguitystash.api.StashItemList

/**
 * Created by caxerx on 2017/6/5.
 */
interface Storage {
    fun loadPlayer(user: String): StashItemList
    fun savePlayer(user: String): Boolean
}