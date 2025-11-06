package com.sykim.planas.common

import org.springframework.stereotype.Component

@Component
class ItemColorRegistry {
    private val colors = listOf(
        ItemColor("blue", "bg-blue-500", "text-blue-500"),
        ItemColor("green", "bg-green-500", "text-green-500"),
        ItemColor("yellow", "bg-yellow-500", "text-yellow-500"),
        ItemColor("purple", "bg-purple-500", "text-purple-500")
    )

    fun getAllColorList() = colors
    fun getColorByName(name: String): ItemColor {
        colors.forEach { itemColor ->
            if (itemColor.name == name) return itemColor
        }
        throw RuntimeException()
    }
}