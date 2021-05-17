package com.example.firstappcompose.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firstappcompose.model.CurrencyType
import com.example.firstappcompose.model.CurrencyWallet
import com.example.firstappcompose.remote.DummyData
import com.example.firstappcompose.remote.DummyWebService
import com.example.firstappcompose.ui.list.MainViewModel.SortTypes.*

/**
 * Used to communicate between screens.
 */
class MainViewModel : ViewModel() {

    private val dummyData by lazy { DummyData() }
    private val dummyWebService by lazy { DummyWebService(dummyData) }
    private var sortType = Default

    private val _currencyWalletsData = MutableLiveData<List<CurrencyWallet>>()
    private val currencyWalletsData: LiveData<List<CurrencyWallet>> = _currencyWalletsData

    private val _currentFilterName = MutableLiveData<String>()
    val currentFilterName: LiveData<String> = _currentFilterName

    fun getCurrencyWallets(): ListUiState {
        if (currencyWalletsData.value == null) {
            _currencyWalletsData.value = dummyWebService.getCurrencyWallets()
            _currentFilterName.value = ""
        }
        return ListUiState(
            filterName = currentFilterName,
            initialWallets = currencyWalletsData
        )
    }

    fun swapCurrWalletsFilter() {
        when (sortType) {
            Default -> {
                _currencyWalletsData.value =
                    dummyWebService.getCurrencyWallets().sortedBy { it.price }
                sortType = Balance
                _currentFilterName.value = " (Balance)"
            }
            Balance -> {
                _currencyWalletsData.value =
                    dummyWebService.getCurrencyWallets().filter { it.type == CurrencyType.Fiat }
                sortType = Fiat
                _currentFilterName.value = " (Fiat)"
            }
            Fiat -> {
                _currencyWalletsData.value =
                    dummyWebService.getCurrencyWallets().filter { it.type == CurrencyType.Crypto }
                sortType = Cryptocoins
                _currentFilterName.value = " (Cryptocoins)"
            }
            Cryptocoins -> {
                _currencyWalletsData.value =
                    dummyWebService.getCurrencyWallets().filter { it.type == CurrencyType.Metal }
                sortType = Metals
                _currentFilterName.value = " (Metals)"
            }
            Metals -> {
                _currencyWalletsData.value =
                    dummyWebService.getCurrencyWallets().sortedBy { it.price }
                sortType = Balance
                _currentFilterName.value = " (Balance)"
            }
        }
    }

    enum class SortTypes {
        Default, Fiat, Cryptocoins, Metals, Balance
    }
}
