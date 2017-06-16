package com.caxerx.mc.ambiguitystash.storage

import com.caxerx.mc.ambiguitystash.AmbiguityStash
import com.google.gson.Gson
import org.apache.commons.io.FileUtils
import java.io.File
import java.util.*

/**
 * Created by caxerx on 2017/6/11.
 */
class JsonStorage(val plugin: AmbiguityStash) : Storage {
    override fun loadPlayer(user: UUID): StorageStash {
        val playerFile = File(plugin.dataFolder.path + File.separator + "userstorage" + File.separator + user + ".json")
        if (playerFile.exists()) {
            return Gson().fromJson(FileUtils.readFileToString(playerFile), StorageStash::class.javaObjectType)
        }
        return StorageStash(Stash(arrayOf(), plugin.configManager.getDefaultInventoryPage(), plugin.configManager.getDefaultInventorySize()))
    }

    override fun savePlayer(user: UUID, content: StorageStash) {
        FileUtils.writeStringToFile(File(plugin.dataFolder.path + File.separator + "userstorage" + File.separator + user + ".json"), Gson().toJson(content, StorageStash::class.javaObjectType))
    }
}

