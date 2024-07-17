package so.notion.news.screens.top.composable

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.jakhongirmadaminov.glassmorphiccomposables.GlassmorphicColumn
import dev.jakhongirmadaminov.glassmorphiccomposables.Place
import dev.shreyaspatil.capturable.Capturable
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.withContext
import so.notion.news.R
import so.notion.news.data.ui.NewsItem

const val BLURRED_BG_KEY = "BLURRED_BG_KEY"
const val BLUR_RADIUS = 50

@Composable
fun Test(modifier: Modifier, list: List<NewsItem>) {
    val screenWidthDp = LocalConfiguration.current.screenWidthDp
    val cardWidthDp = screenWidthDp
    var capturedBitmap by remember { mutableStateOf<Bitmap?>(null) }

    val scrollState = rememberScrollState()
    Box(modifier = modifier) {
        val captureController = rememberCaptureController()

        Capturable(
            modifier = Modifier.fillMaxSize(),
            controller = captureController,
            onCaptured = { bitmap, error ->
                capturedBitmap = bitmap?.asAndroidBitmap()
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_autumn),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        LaunchedEffect(key1 = true, block = {
            withContext(Main) {
                if (capturedBitmap == null) captureController.capture()
            }
        })

        val childMeasures = remember { list.map { Place() } }

        capturedBitmap?.let {
            GlassmorphicColumn(
                modifier = Modifier.padding(16.dp),
                scrollState = scrollState,
                childMeasures = childMeasures.toImmutableList(),
                targetBitmap = it.asImageBitmap(),
                dividerSpace = 30,
                blurRadius = BLUR_RADIUS,
                drawOnTop = { path ->
                    val strokeColor = Color(0x80ffffff)
                    drawPath(
                        path = path,
                        color = strokeColor,
                        style = Stroke(1f),
                    )
                },
                content = {
                    list.forEachIndexed { index, it ->
                        Box(
                            modifier = Modifier
                                .onGloballyPositioned { pos ->
                                    childMeasures[index].apply {
                                        sizeX = pos.size.width
                                        sizeY = pos.size.height
                                        offsetX = pos.positionInParent().x
                                        offsetY = pos.positionInParent().y
                                    }
                                }
                                .width(cardWidthDp.dp)
                                .padding(16.dp)
                        ) {
                            Text(
                                "Item ${it.author}",
                                color = Color.Black
                            )
                        }
                    }
                },
                isAlreadyBlurred = true,

                )
        }
    }
}