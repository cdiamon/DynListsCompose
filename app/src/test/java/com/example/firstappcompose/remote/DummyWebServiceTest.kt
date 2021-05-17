package com.example.firstappcompose.remote

import com.example.firstappcompose.model.currencies.Fiat
import com.example.firstappcompose.model.currencies.assets.Cryptocoin
import com.example.firstappcompose.model.currencies.assets.Metal
import com.example.firstappcompose.model.wallets.CryptocoinWallet
import com.example.firstappcompose.model.wallets.FiatWallet
import com.example.firstappcompose.model.wallets.MetalWallet
import junit.framework.TestCase
import org.junit.Test

class DummyWebServiceTest : TestCase() {

    lateinit var dummyWebService: DummyWebService
    lateinit var dummyData: DummyData

    override fun setUp() {
        super.setUp()

        dummyData = DummyData()
        dummyWebService = DummyWebService(dummyData)
    }

    @Test
    fun testGetCryptoWallets() {
        assertEquals(dummyWebService.getCryptoWallets().size, 4)

        assertEquals(dummyWebService.getCryptoWallets().filter { !it.deleted }.size, 3)
    }

    @Test
    fun testGetAllWallets() {
        val allWallets = dummyWebService.getAllWallets()

        assertEquals(allWallets.isEmpty(), false)

        assertNotNull(allWallets.find { it is CryptocoinWallet })
        assertNotNull(allWallets.find { it is FiatWallet })
        assertNotNull(allWallets.find { it is MetalWallet })
    }

    @Test
    fun testGetAllCurrencies() {
        val allWallets = dummyWebService.getAllCurrencies()

        assertEquals(allWallets.isEmpty(), false)

        assertNotNull(allWallets.find { it is Fiat })
        assertNotNull(allWallets.find { it is Metal })
        assertNotNull(allWallets.find { it is Cryptocoin })
    }

    @Test
    fun testGetCurrencyWallets() {
        assertEquals(dummyWebService.getCurrencyWallets().isEmpty(), false)
    }
}
