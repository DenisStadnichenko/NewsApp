package so.notion.news.screens.top.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import so.notion.news.R
import so.notion.news.screens.top.TopNewsContract
import so.notion.news.ui.theme.SearchTextColor

@Composable
fun MySearchBar(
    uiState: TopNewsContract.UiState,
    onAction: (TopNewsContract.UiAction) -> Unit
) {

    var searchInput by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            shape = CircleShape,
            value = searchInput,
            onValueChange = { newValue ->
                searchInput = newValue
                onAction(TopNewsContract.UiAction.OnQueryChanged(newValue))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 16.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White,
                disabledIndicatorColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
            ),
            placeholder = {
                Text(
                    "Search", style = TextStyle(
                        color = SearchTextColor,
                        fontSize = 17.sp,
                        lineHeight = 22.sp,
                        fontFamily = FontFamily(
                            Font(R.font.sf_regular)
                        )
                    )
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
            ),
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.ic_search_glyph), null)
            },
            trailingIcon = {
                if (searchInput.isNotEmpty()) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier
                            .size(22.dp)
                            .clickable {
                                searchInput = ""
                                onAction(TopNewsContract.UiAction.OnQueryChanged(""))
                            },
                        tint = SearchTextColor
                    )
                }
            },
            keyboardActions = KeyboardActions(onSearch = {
                keyboardController?.hide()
            }),
            textStyle = TextStyle(
                color = SearchTextColor,
                fontSize = 17.sp,
                lineHeight = 22.sp,
                fontFamily = FontFamily(Font(R.font.sf_regular))
            )
        )
    }
}