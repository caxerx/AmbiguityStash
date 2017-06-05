package com.caxerx.mc.ambiguitystash.api

import com.comphenix.protocol.utility.MinecraftReflection
import com.comphenix.protocol.wrappers.nbt.NbtBase
import com.comphenix.protocol.wrappers.nbt.NbtFactory
import com.comphenix.protocol.wrappers.nbt.NbtWrapper
import com.comphenix.protocol.wrappers.nbt.io.NbtTextSerializer
import org.bukkit.inventory.ItemStack

/**
 * Created by caxerx on 2017/4/16.
 */
class StashItem(itemStack: org.bukkit.inventory.ItemStack) {
    private val serializeItem: MutableMap<String, Any> = itemStack.serialize()

    companion object {
        val gson = com.google.gson.Gson()
    }

    init {
        if (itemStack.hasItemMeta()) {
            val nbtTag = NbtFactory.asCompound(NbtFactory.fromItemTag(itemStack))
            serializeItem.put("meta", NbtTextSerializer().serialize<Map<String, NbtBase<*>>>(nbtTag))
        }
    }

    val minecraftItemStack: org.bukkit.inventory.ItemStack
        get() {
            val meta: NbtWrapper<Any>
            val serializedItem: HashMap<String, Any> = HashMap(serializeItem)
            if (serializeItem.containsKey("meta")) {
                meta = NbtTextSerializer().deserialize<Any>(serializedItem["meta"] as String)
                serializedItem.remove("meta")
                var deserializedItemStack: ItemStack = ItemStack.deserialize(serializedItem)
                deserializedItemStack = MinecraftReflection.getBukkitItemStack(deserializedItemStack)
                NbtFactory.setItemTag(deserializedItemStack, NbtFactory.asCompound(meta))
                return deserializedItemStack
            } else {
                return ItemStack.deserialize(serializedItem)
            }
        }

    override fun toString(): String {
        return gson.toJson(this)
    }
}
