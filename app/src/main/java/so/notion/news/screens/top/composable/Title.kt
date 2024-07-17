package so.notion.news.screens.top.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import so.notion.news.R
import so.notion.news.ui.theme.MainTextColor

@Composable
fun Title(modifier: Modifier) {
    Text(
        modifier = modifier,
        text = "News", style = TextStyle(
            color = MainTextColor,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            fontFamily = FontFamily(
                Font(R.font.poppins_medium)
            )
        )
    )
}