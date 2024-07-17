package so.notion.news.screens.top.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import so.notion.news.R
import so.notion.news.screens.top.TopNewsContract
import so.notion.news.ui.theme.ImagePreviewColor
import so.notion.news.ui.theme.MainTextColor
import so.notion.news.ui.theme.SecondaryTextColor
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun LIst(modifier: Modifier, uiState: TopNewsContract.UiState) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())

    ) {
        uiState.news.forEach { newsItem ->

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(width = 1.dp, color = Color.White)
                    .background(
                        Color.White.copy(alpha = 0.46f)
                    )

            ) {

                Row(
                    modifier = Modifier.padding(16.dp),
                ) {

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                    ) {


                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(ImagePreviewColor)
                        ) {


                            Image(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .padding(8.dp)
                                    .size(40.dp),
                                painter = painterResource(id = R.drawable.ic_preview),
                                contentDescription = "preview"
                            )

                        }

                        AsyncImage(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .size(40.dp),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(newsItem.urlToImage)
                                .build(),
                            contentDescription = "",
                            contentScale = ContentScale.Crop
                        )
                    }

                    Column(modifier = Modifier.padding(start = 12.dp, end = 12.dp)) {
                        Text(
                            text = newsItem.title ?: "",
                            style = TextStyle(
                                color = MainTextColor,
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                                fontFamily = FontFamily(
                                    Font(R.font.poppins_medium)
                                )
                            ),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            text = getTime(newsItem.publishedAt ?: ""), style = TextStyle(
                                color = SecondaryTextColor,
                                fontSize = 12.sp,
                                lineHeight = 24.sp,
                                fontFamily = FontFamily(
                                    Font(R.font.poppins_regular)
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}


private fun getTime(string: String): String {
    val formatter = DateTimeFormatter.ISO_DATE_TIME
    val dateTime = LocalDateTime.parse(string, formatter)

    val outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val formattedDate = dateTime.format(outputFormatter)
    return formattedDate
}