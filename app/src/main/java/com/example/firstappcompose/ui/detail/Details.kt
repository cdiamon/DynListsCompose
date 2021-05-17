
package com.example.firstappcompose.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.firstappcompose.ui.theme.WalletTheme
import com.example.firstappcompose.R
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun DetailsScreen(userData: String) {

    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    UserInfoFields(userData, this@BoxWithConstraints.maxHeight)
                }
            }
        }
    }
}

@Composable
private fun UserInfoFields(userData: String, containerHeight: Dp) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))

        NameAndPosition(userData)
    }
}

@Composable
private fun NameAndPosition(
    userData: String
) {
    Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp)) {
        Name(
        )
        Spacer(modifier = Modifier.height(8.dp))
        Position(
            userData,
            modifier = Modifier
                .padding(bottom = 20.dp)
        )
    }
}

@Composable
private fun Name(modifier: Modifier = Modifier) {
    Text(
        text = "Currency's current price in EUR",
        modifier = modifier,
        style = MaterialTheme.typography.subtitle1
    )
}

@Composable
private fun Position(userData: String, modifier: Modifier = Modifier) {
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(
            text = userData,
            modifier = modifier,
            style = MaterialTheme.typography.h6
        )
    }
}

@Composable
fun ProfileError() {
    Text(stringResource(R.string.profile_error))
}


@Preview(widthDp = 640, heightDp = 360)
@Composable
fun ConvPreviewLandscapeMeDefault() {
    ProvideWindowInsets(consumeWindowInsets = false) {
        WalletTheme {
            DetailsScreen("4204.50")
        }
    }
}

@Preview(widthDp = 360, heightDp = 480)
@Composable
fun ConvPreviewPortraitMeDefault() {
    ProvideWindowInsets(consumeWindowInsets = false) {
        WalletTheme {
            DetailsScreen("4204.50")
        }
    }
}

@Preview(widthDp = 360, heightDp = 480)
@Composable
fun ConvPreviewPortraitOtherDefault() {
    ProvideWindowInsets(consumeWindowInsets = false) {
        WalletTheme {
            DetailsScreen("4204.50")
        }
    }
}
