package com.caxerx.mc.ambiguitystash.storage

import com.caxerx.mc.ambiguitystash.api.StorageStash
import java.util.*

/**
 * Created by caxerx on 2017/6/5.
 */
interface Storage {
    fun loadPlayer(user: UUID): StorageStash
    fun savePlayer(user: UUID, content: StorageStash)
}