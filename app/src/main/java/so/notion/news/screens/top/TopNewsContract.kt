package so.notion.news.screens.top

import so.notion.news.data.ui.NewsItem

interface TopNewsContract {

    data class UiState(
        val isLoading: Boolean,
        val news: List<NewsItem>
    )

    sealed interface UiAction {
        data object OnBackPressed : UiAction
        data class OnQueryChanged(val query: String) : UiAction
    }

    sealed interface SideEffect {
        data object OnBackPressed : SideEffect
    }
}