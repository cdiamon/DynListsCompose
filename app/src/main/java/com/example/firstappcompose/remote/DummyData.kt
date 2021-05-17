package com.example.firstappcompose.remote

import com.example.firstappcompose.model.currencies.assets.Cryptocoin
import com.example.firstappcompose.model.currencies.Fiat
import com.example.firstappcompose.model.currencies.assets.Metal
import com.example.firstappcompose.model.wallets.CryptocoinWallet
import com.example.firstappcompose.model.wallets.FiatWallet
import com.example.firstappcompose.model.wallets.MetalWallet
import java.math.BigDecimal

class DummyData {

    val cryptocoins: List<Cryptocoin> = listOf(
        Cryptocoin(
            name = "Bitcoin",
            symbol = "BTC",
            id = "1",
            price = BigDecimal(9000.0),
            logo = "https://iconarchive.com/download/i109479/cjdowner/cryptocurrency-flat/Bitcoin-Plus-XBC.ico"
        ),
        Cryptocoin(
            name = "Bitpanda Ecosystem Token",
            symbol = "BEST",
            id = "2",
            price = BigDecimal(0.03),
            logo = "https://styles.redditmedia.com/t5_7spwq/styles/communityIcon_embgsij7apv61.png"
        ),
        Cryptocoin(
            name = "Ripple",
            symbol = "XRP",
            id = "3",
            price = BigDecimal(0.2119),
            logo = "https://icons.iconarchive.com/icons/cjdowner/cryptocurrency-flat/1024/Ripple-XRP-icon.png"
        )
    )
    val dummyCryptoWalletList = listOf(
        CryptocoinWallet(
            id = "1",
            name = "Test BTC Wallet",
            balance = BigDecimal(133.7),
            isDefault = false,
            cryptocoinId = "1",
            deleted = false
        ),
        CryptocoinWallet(
            id = "2",
            name = "Test BTC Wallet 2",
            balance = BigDecimal(0),
            isDefault = false,
            cryptocoinId = "1",
            deleted = true
        ),
        CryptocoinWallet(
            id = "3",
            name = "Test BEST Wallet",
            balance = BigDecimal(1032.23),
            isDefault = false,
            cryptocoinId = "2",
            deleted = false
        ),
        CryptocoinWallet(
            id = "4",
            name = "Test Ripple Wallet",
            balance = BigDecimal(2304.04),
            isDefault = false,
            cryptocoinId = "3",
            deleted = false
        )
    )


    val fiats: List<Fiat> = listOf(
        Fiat(
            name = "Euro",
            symbol = "EUR",
            id = "1",
            logo = "https://iconarchive.com/download/i109537/cjdowner/cryptocurrency-flat/Euro-EUR.ico"
        ),
        Fiat(
            name = "Swiss Franc",
            symbol = "CHF",
            id = "2",
            logo = "https://img.icons8.com/ios/452/swiss-franc.png"
        )
    )
    val dummyEURWalletList = listOf(
        FiatWallet(
            id = "1",
            name = "EUR Wallet",
            fiatId = "1",
            balance = BigDecimal(400.0),
            isDefault = false,
            deleted = false
        ),
        FiatWallet(
            id = "2",
            name = "CHF Wallet",
            fiatId = "2",
            balance = BigDecimal(0),
            isDefault = false,
            deleted = false
        )
    )


    val metals: List<Metal> = listOf(
        Metal(
            name = "Gold",
            symbol = "XAU",
            id = "4",
            price = BigDecimal(45.14),
            logo = "https://iknowfirst.com/wp-content/uploads/2020/12/goldsmall.jpg"
        ),
        Metal(
            name = "Palladium",
            symbol = "XPD",
            id = "5",
            price = BigDecimal(70.40),
            logo = "https://cdn2.iconfinder.com/data/icons/periodic-elements-transition-metals/532/46-Palladium-512.png"
        )
    )
    val dummyMetalWalletList = listOf(
        MetalWallet(
            id = "1",
            name = "Gold Wallet 1",
            balance = BigDecimal(133.729),
            isDefault = true,
            metalId = "4",
            deleted = false
        ),
        MetalWallet(
            id = "2",
            name = "Gold Wallet 2",
            balance = BigDecimal(2043.4340),
            isDefault = false,
            metalId = "4",
            deleted = false
        ),
        MetalWallet(
            id = "2",
            name = "Test Palladium Wallet",
            balance = BigDecimal(200.0),
            isDefault = false,
            metalId = "5",
            deleted = false
        )
    )
}
