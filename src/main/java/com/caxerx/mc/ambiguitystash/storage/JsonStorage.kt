package com.caxerx.mc.ambiguitystash.storage

import com.google.gson.Gson
import org.apache.commons.io.FileUtils
import org.bukkit.plugin.Plugin
import java.io.File
import java.util.*

/**
 * Created by caxerx on 2017/6/11.
 */
class JsonStorage(val plugin: Plugin) : Storage {
    override fun loadPlayer(user: UUID): StorageStash {
        val playerFile = File(plugin.dataFolder.path + File.separator + user)
        if (playerFile.exists().not()) {
            return StorageStash(Stash(arrayOf(), 1, 5))
        }
        return Gson().fromJson(FileUtils.readFileToString(playerFile), StorageStash::class.javaObjectType)
    }

    override fun savePlayer(user: UUID, content: StorageStash) {
        FileUtils.writeStringToFile(File(plugin.dataFolder.path + File.separator + user), Gson().toJson(content, StorageStash::class.javaObjectType))
    }
}

