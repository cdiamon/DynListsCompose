package com.example.firstappcompose.model.currencies

interface Currency {
    val precision: Int
    val name: String
    val symbol: String
    val id: String
    val logo: String
}
