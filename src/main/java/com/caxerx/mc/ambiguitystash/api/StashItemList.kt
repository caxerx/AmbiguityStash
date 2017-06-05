import com.caxerx.mc.ambiguitystash.api.StashItem
import com.google.gson.Gson
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.util.*

class StashItemList : ArrayList<StashItem> {
    companion object {
        val airItem: StashItem = StashItem(ItemStack(Material.AIR))
        val gson = Gson()
    }

    constructor(content: Array<ItemStack?>) : super() {
        content.forEach { rawItem -> if (rawItem == null) airItem else add(StashItem(rawItem)) }
    }

    constructor(itemList: StashItemList) : super(itemList) {}

    constructor(itemList: List<ItemStack?>) : super() {
        itemList.forEach { rawItem -> if (rawItem == null) airItem else add(StashItem(rawItem)) }
    }

    constructor() : super() {}

    val itemStackList: List<ItemStack>
        get() {
            val itemStackList = ArrayList<ItemStack>()
            forEach { rawItem -> itemStackList.add(rawItem.minecraftItemStack) }
            return itemStackList
        }

    override fun clone(): StashItemList {
        return super.clone() as StashItemList
    }

    override fun toString(): String {
        return gson.toJson(this)
    }
}