package com.example.firstappcompose.model.currencies

data class Fiat(
    override val precision: Int = 2,
    override val name: String = "",
    override val symbol: String = "",
    override val id: String = "",
    override val logo: String,
) : Currency
