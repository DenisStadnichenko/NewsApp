package so.notion.news.screens.top

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import so.notion.news.R
import so.notion.news.screens.top.composable.Indicator
import so.notion.news.screens.top.composable.LIst
import so.notion.news.screens.top.composable.MySearchBar
import so.notion.news.screens.top.composable.Title
import so.notion.news.ui.theme.MainBackground


@Composable
fun TopNewsScreen(
    uiState: TopNewsContract.UiState,
    onAction: (TopNewsContract.UiAction) -> Unit
) {

    Box(
        modifier = Modifier
            .background(MainBackground)
            .fillMaxSize(),
        contentAlignment = Alignment.CenterEnd
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_top_main),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(244.dp)
                .height(417.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Title(modifier = Modifier.padding(16.dp))
            MySearchBar(uiState, onAction)
            Indicator(modifier = Modifier.padding(16.dp), uiState)
            LIst(modifier = Modifier, uiState = uiState)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TopNewsLoadingPreview() {
    TopNewsScreen(
        onAction = {},
        uiState = TopNewsContract.UiState(isLoading = true, news = emptyList())
    )
}