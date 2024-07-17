package so.notion.news.screens.top.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import so.notion.news.R
import so.notion.news.screens.top.TopNewsContract
import so.notion.news.ui.theme.MainTextColor

@Composable
fun Indicator(modifier: Modifier, uiState: TopNewsContract.UiState) {
    Text(
        modifier = modifier,
        text = "${uiState.news.size} news",
        style = TextStyle(
            color = MainTextColor,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            fontFamily = FontFamily(
                Font(R.font.poppins_medium)
            )
        )
    )
}