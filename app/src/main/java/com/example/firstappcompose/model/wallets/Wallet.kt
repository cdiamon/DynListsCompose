package com.example.firstappcompose.model.wallets

import java.math.BigDecimal

interface Wallet {
    val id: String
    val isDefault: Boolean
    val balance: BigDecimal
    val deleted: Boolean
    val name: String

    fun getCurrencyId(): String
}