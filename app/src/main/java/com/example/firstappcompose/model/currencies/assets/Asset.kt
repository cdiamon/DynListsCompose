package com.example.firstappcompose.model.currencies.assets

import com.example.firstappcompose.model.currencies.Currency
import java.math.BigDecimal

interface Asset: Currency {
    val price: BigDecimal
}