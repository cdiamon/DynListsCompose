package com.example.firstappcompose.remote

import com.example.firstappcompose.model.CurrencyType
import com.example.firstappcompose.model.CurrencyWallet
import com.example.firstappcompose.model.currencies.assets.Cryptocoin
import com.example.firstappcompose.model.currencies.Currency
import com.example.firstappcompose.model.currencies.Fiat
import com.example.firstappcompose.model.currencies.assets.Asset
import com.example.firstappcompose.model.currencies.assets.Metal
import com.example.firstappcompose.model.wallets.CryptocoinWallet
import com.example.firstappcompose.model.wallets.FiatWallet
import com.example.firstappcompose.model.wallets.MetalWallet
import com.example.firstappcompose.model.wallets.Wallet
import java.math.BigDecimal

class DummyWebService(private val dummyData: DummyData) {

    fun getCryptoWallets(): List<CryptocoinWallet> {
        return dummyData.dummyCryptoWalletList
    }

    fun getMetalWallets(): List<MetalWallet> {
        return dummyData.dummyMetalWalletList
    }

    fun getFiatWallets(): List<FiatWallet> {
        return dummyData.dummyEURWalletList
    }

    fun getAllWallets(): List<Wallet> {
        return mutableListOf<Wallet>().apply {
            addAll(getCryptoWallets())
            addAll(getMetalWallets())
            addAll(getFiatWallets())
        }
    }

    fun getCryptocoins(): List<Cryptocoin> {
        return dummyData.cryptocoins
    }

    fun getMetals(): List<Metal> {
        return dummyData.metals
    }

    fun getFiats(): List<Fiat> {
        return dummyData.fiats
    }

    fun getAllCurrencies(): List<Currency> {
        return mutableListOf<Currency>().apply {
            addAll(getMetals())
            addAll(getCryptocoins())
            addAll(getFiats())
        }
    }

    fun getCurrencyWallets(): List<CurrencyWallet> {
        val allCurrencies = getAllCurrencies()

        return getAllWallets()
            .mapNotNull { wallet ->
                val currency = allCurrencies
                    .filter {
                        when (wallet) {
                            is MetalWallet -> it is Metal
                            is FiatWallet -> it is Fiat
                            else -> it is Cryptocoin
                        }
                    }
                    .find { it.id == wallet.getCurrencyId() }
                if (currency == null || wallet.deleted) null
                else {
                    CurrencyWallet(
                        icon = currency.logo,
                        symbol = currency.symbol,
                        balance = wallet.balance.toStringWithPrecision(currency.precision),
                        price = if (currency is Asset) {
                            currency.price.toStringWithPrecision(currency.precision)
                        } else "N/A",
                        type = getCurrencyType(currency)
                    )
                }
            }
    }

    private fun getCurrencyType(currency: Currency): CurrencyType {
        return when (currency) {
            is Fiat -> CurrencyType.Fiat
            is Metal -> CurrencyType.Metal
            else -> CurrencyType.Crypto
        }
    }

    private fun BigDecimal.toStringWithPrecision(precision: Int): String {
        return setScale(precision, BigDecimal.ROUND_HALF_EVEN)
            .stripTrailingZeros()
            .toPlainString()
    }
}
