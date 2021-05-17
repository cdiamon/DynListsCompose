package com.example.firstappcompose.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.firstappcompose.model.CurrencyWallet
import com.example.firstappcompose.ui.list.ListUiState

class PreviewService {

    private val dummyWebService by lazy { DummyWebService(DummyData()) }

    private val _currencyWalletsData = MutableLiveData<List<CurrencyWallet>>()
    private val currencyWalletsData: LiveData<List<CurrencyWallet>> = _currencyWalletsData

    private val _currentFilterName = MutableLiveData<String>()
    val currentFilterName: LiveData<String> = _currentFilterName

    fun getCurrencyWallets(): ListUiState {
        if (currencyWalletsData.value == null) {
            _currencyWalletsData.value = dummyWebService.getCurrencyWallets()
            _currentFilterName.value = "defaultFilter!"
        }
        return ListUiState(
            filterName = currentFilterName,
            initialWallets = currencyWalletsData
        )
    }
}
