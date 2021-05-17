package com.example.firstappcompose.model

import androidx.compose.runtime.Immutable

@Immutable
data class CurrencyWallet(
    val icon: String,
    val symbol: String,
    val balance: String,
    val price: String,
    val type: CurrencyType
)

enum class CurrencyType {
    Fiat, Metal, Crypto
}
