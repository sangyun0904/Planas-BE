package com.sykim.planas.common.converter

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames


@Converter
class StringListConverter: AttributeConverter<List<String>, String> {

    override fun convertToDatabaseColumn(stringList: List<String>): String {
        return java.lang.String.join(",", stringList)
    }

    override fun convertToEntityAttribute(string: String): List<String> {
        return  string.split(",")
    }

}