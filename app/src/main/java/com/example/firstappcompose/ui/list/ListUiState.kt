package com.example.firstappcompose.ui.list

import androidx.lifecycle.LiveData
import com.example.firstappcompose.model.CurrencyWallet

class ListUiState(
    val filterName: LiveData<String>,
    initialWallets: LiveData<List<CurrencyWallet>>
) {

    val wallets: LiveData<List<CurrencyWallet>> = initialWallets
}
