package com.sykim.planas.common

import org.springframework.stereotype.Component

@Component
class ItemColorRegistry {
    private val colors = listOf(
        ItemColor(
            "blue",
            "#3B82F6",
            "#EFF6FF",
            "#1E40AF"
        ),
        ItemColor(
            "green",
            "#10B981",
            "#ECFDF5",
            "#065F46"
        ),
        ItemColor(
            "yellow",
            "#F59E0B",
            "#FFFBEB",
            "#92400E"
        ),
        ItemColor(
            "red",
            "#EF4444",
            "#FEF2F2",
            "#991B1B"
        ),
        ItemColor(
            "purple",
            "#8B5CF6",
            "#F5F3FF",
            "#5B21B6"
        ),
        ItemColor(
            "orange",
            "#F97316",
            "#FFF7ED",
            "#C2410C"
        ),
        ItemColor(
            "cyan",
            "#06B6D4",
            "#ECFEFF",
            "#0E7490"
        ),
        ItemColor(
            "lime",
            "#84CC16",
            "#F7FEE7",
            "#365314"
        ),
        ItemColor(
            "pink",
            "#EC4899",
            "#FDF2F8",
            "#BE185D"
        ),
        ItemColor(
            "indigo",
            "#6366F1",
            "#EEF2FF",
            "#3730A3"
        ),
        ItemColor(
            "teal",
            "#14B8A6",
            "#F0FDFA",
            "#134E4A"
        ),
        ItemColor(
            "slate",
            "#64748B",
            "#F8FAFC",
            "#334155")
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