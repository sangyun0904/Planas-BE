package com.sykim.planas.common

import jakarta.persistence.*

@Entity
@Table
data class ItemColor (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var name: String,
    var bgColor: String,
    var textColor: String
)