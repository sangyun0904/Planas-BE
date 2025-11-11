package com.sykim.planas.common

import org.springframework.stereotype.Component

@Component
class ItemColorRegistry {
    private val colors = listOf(
        ItemColor("blue", "bg-blue-100 border-blue-200", "text-blue-500"),
        ItemColor("green", "bg-green-100 border-green-200", "text-green-500"),
        ItemColor("yellow", "bg-yellow-100 border-yellow-200", "text-yellow-500"),
        ItemColor("purple", "bg-purple-100 border-purple-200", "text-purple-500"),
        ItemColor("orange", "bg-orange-100 border-orange-200", "text-purple-500"),
        ItemColor("pink", "bg-pink-100 border-pink-200", "text-purple-500")
    )

    fun getAllColorList() = colors
    fun getColorByName(name: String): ItemColor {
        colors.forEach { itemColor ->
            if (itemColor.name == name) return itemColor
        }
        throw RuntimeException()
    }
    fun getColorIdByName(name: String): Int {
        for (i in colors.indices) {
            if (colors[i].name == name) return i
        }
        throw RuntimeException()
    }
    fun getColorById(id: Int): ItemColor {
        return colors.get(id)
    }
}