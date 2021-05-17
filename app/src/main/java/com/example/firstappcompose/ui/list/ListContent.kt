package com.example.firstappcompose.ui.list

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstappcompose.ui.theme.WalletTheme
import com.example.firstappcompose.ui.utils.elevatedSurface
import com.example.firstappcompose.model.CurrencyWallet
import com.example.firstappcompose.remote.PreviewService
import com.example.firstappcompose.ui.utils.NetworkImage
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.toPaddingValues
import kotlinx.coroutines.launch

/**
 * Entry point for a currency wallets screen.
 *
 * @param uiState [ListUiState] that contains wallets to display
 * @param navigateToDetails User action when navigation to details is requested
 * @param modifier [Modifier] to apply to this layout node
 */
@Composable
fun ListContent(
    uiState: ListUiState,
    navigateToDetails: (String) -> Unit,
    swapFilters: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberLazyListState()

    Surface(modifier = modifier) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(Modifier.fillMaxSize()) {
                Wallets(
                    wallets = uiState,
                    navigateToDetails = navigateToDetails,
                    swapFilters = swapFilters,
                    modifier = Modifier.weight(1f),
                    scrollState = scrollState
                )
            }
        }
    }
}

@Composable
fun Wallets(
    wallets: ListUiState,
    navigateToDetails: (String) -> Unit,
    swapFilters: () -> Unit,
    scrollState: LazyListState,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    Box(modifier = modifier) {

        val walletList = wallets.wallets.observeAsState()

        LazyColumn(
            state = scrollState,
            // Add content padding so that the content can be scrolled (y-axis)
            // below the status bar + app bar
            contentPadding = LocalWindowInsets.current.statusBars.toPaddingValues(
                additionalTop = 16.dp,
                additionalBottom = 112.dp
            ),
            modifier = Modifier
                .fillMaxSize()
        ) {
            for (index in walletList.value!!.indices) {
                val prevSymbol = walletList.value!!.getOrNull(index - 1)?.symbol
                val nextSymbol = walletList.value!!.getOrNull(index + 1)?.symbol
                val content = walletList.value!![index]
                val isFirstRowOfSymbol = prevSymbol != content.symbol
                val isLastRowOfSymbol = nextSymbol != content.symbol

                item {
                    Wallet(
                        onWalletClick = { name -> navigateToDetails(name) },
                        wallet = content,
                        isFirstRowOfSymbol = isFirstRowOfSymbol,
                        isLastRowOfSymbol = isLastRowOfSymbol
                    )
                }
            }
        }

        SwitchFilterBottom(
            filterText = wallets.filterName,
            onClicked = {
                scope.launch {
                    swapFilters.invoke()
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun Wallet(
    onWalletClick: (String) -> Unit,
    wallet: CurrencyWallet,
    isFirstRowOfSymbol: Boolean,
    isLastRowOfSymbol: Boolean
) {
    val spaceBetweenAuthors = if (isLastRowOfSymbol) Modifier.padding(top = 8.dp) else Modifier
    Row(
        modifier = spaceBetweenAuthors
            .clickable(onClick = { onWalletClick(wallet.price) })
    ) {
        if (isFirstRowOfSymbol) {
            NetworkImage(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(42.dp)
                    .border(1.dp, MaterialTheme.colors.secondary, CircleShape)
                    .border(3.dp, MaterialTheme.colors.surface, CircleShape)
                    .clip(CircleShape)
                    .align(Alignment.Top),
                url = wallet.icon,
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        } else {
            Spacer(modifier = Modifier.width(74.dp))
        }
        NameAndSymbolItem(
            wallet = wallet,
            isFirstRowOfSymbol = isFirstRowOfSymbol,
            isLastRowOfSymbol = isLastRowOfSymbol,
            modifier = Modifier
                .padding(end = 16.dp)
                .weight(1f)
        )
    }
}

@Composable
fun NameAndSymbolItem(
    wallet: CurrencyWallet,
    isFirstRowOfSymbol: Boolean,
    isLastRowOfSymbol: Boolean,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        if (isFirstRowOfSymbol) {
            SymbolItem(wallet)
        }
        BalanceBubble(wallet)
        if (isFirstRowOfSymbol) {
            Spacer(modifier = Modifier.height(4.dp))
        }
        if (isLastRowOfSymbol) {
            Spacer(modifier = Modifier.height(4.dp))
            Divider(thickness = 0.5.dp)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun SymbolItem(msg: CurrencyWallet) {
    Row(modifier = Modifier.semantics(mergeDescendants = true) {}) {
        Text(
            text = msg.symbol,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .alignBy(LastBaseline)
                .paddingFrom(LastBaseline, after = 8.dp)
        )
    }
}

private val LastChatBubbleShape = RoundedCornerShape(0.dp, 8.dp, 8.dp, 8.dp)

@Composable
fun BalanceBubble(
    wallet: CurrencyWallet
) {

    val backgroundBubbleColor =
        if (MaterialTheme.colors.isLight) {
            Color(0xFFF5F5F5)
        } else {
            MaterialTheme.colors.elevatedSurface(2.dp)
        }

    val bubbleShape = LastChatBubbleShape
    Column {
        Surface(color = backgroundBubbleColor, shape = bubbleShape) {
            Text(
                text = wallet.balance,
                style = MaterialTheme.typography.h6.copy(
                    color = LocalContentColor.current,
                    fontStyle = FontStyle.Normal
                ),
                modifier = Modifier.padding(12.dp),
            )
        }
    }
}

@Preview
@Composable
fun WalletsPreview() {
    WalletTheme {
        ListContent(
            uiState = PreviewService().getCurrencyWallets(),
            navigateToDetails = {},
            swapFilters = {}
        )
    }
}
