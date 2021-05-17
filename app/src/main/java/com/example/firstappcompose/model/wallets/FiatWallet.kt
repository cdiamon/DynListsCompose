package com.example.firstappcompose.model.wallets

import java.math.BigDecimal

data class FiatWallet(

    override val id: String = "",
    val fiatId: String = "",
    override val isDefault: Boolean = false,
    override val balance: BigDecimal = BigDecimal.ZERO,
    override val deleted: Boolean = false,
    override val name: String = ""
): Wallet {

    //todo implement me
    fun reduceBalance(amount: Double) {
    }

    //todo implement me
    fun addBalance(amount: Double) {
    }

    override fun getCurrencyId(): String {
        return fiatId
    }
}
