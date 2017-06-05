package com.caxerx.mc.ambiguitystash.utils

import com.comphenix.protocol.utility.MinecraftReflection
import com.comphenix.protocol.wrappers.nbt.NbtCompound
import com.comphenix.protocol.wrappers.nbt.NbtFactory
import org.bukkit.inventory.ItemStack

class NBTItemFactory(item: ItemStack) {

    private val craftItem: ItemStack = MinecraftReflection.getBukkitItemStack(item.clone())
    private val nbtCompound: NbtCompound = NbtFactory.asCompound(NbtFactory.fromItemTag(craftItem))

    fun create(): ItemStack {
        NbtFactory.setItemTag(craftItem, nbtCompound)
        return craftItem
    }

    fun setString(key: String, value: String): NBTItemFactory {
        nbtCompound.put(key, value)
        return this
    }

    fun setInteger(key: String, value: Int): NBTItemFactory {
        nbtCompound.put(key, value)
        return this
    }

    fun setDouble(key: String, value: Double): NBTItemFactory {
        nbtCompound.put(key, value)
        return this
    }


    fun getString(key: String): String {
        return nbtCompound.getString(key)
    }

    fun getInteger(key: String): Int? {
        return nbtCompound.getInteger(key)
    }

    fun getDouble(key: String): Double {
        return nbtCompound.getDouble(key)
    }

    fun hasKey(key: String): Boolean {
        return nbtCompound.containsKey(key)
    }

}