package com.sykim.planas.common

import jakarta.persistence.*

data class ItemColor (
    var name: String,
    var color: String,
    var bgColor: String,
    var textColor: String
)

