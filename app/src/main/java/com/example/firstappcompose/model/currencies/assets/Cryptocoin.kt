package com.example.firstappcompose.model.currencies.assets

import java.math.BigDecimal

data class Cryptocoin(
    override val precision: Int = 4,
    override val name: String = "",
    override val symbol: String = "",
    override val id: String = "",
    override val logo: String,
    override val price: BigDecimal = BigDecimal.ZERO,
) : Asset
