package so.notion.news.screens.top

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import so.notion.news.common.mvi.MVI
import so.notion.news.common.mvi.mvi
import so.notion.news.data.ui.NewsItem
import so.notion.news.domain.usecase.TopNewsUseCase

private fun initialUiState(): TopNewsContract.UiState = TopNewsContract.UiState(
    isLoading = true,
    news = emptyList()
)

class TopNewsViewModel(
    private val topNewsUseCase: TopNewsUseCase
) : ViewModel(),
    MVI<TopNewsContract.UiState, TopNewsContract.UiAction, TopNewsContract.SideEffect> by mvi(
        initialUiState()
    ) {

    init {
        getTop()
    }

    override fun onAction(uiAction: TopNewsContract.UiAction) {
        when (uiAction) {
            TopNewsContract.UiAction.OnBackPressed -> sendOnBackPressedEffect()
            is TopNewsContract.UiAction.OnQueryChanged -> {
                searchByQuery(uiAction.query)
            }
        }
    }

    private fun sendOnBackPressedEffect() {
        viewModelScope.emitSideEffect(TopNewsContract.SideEffect.OnBackPressed)
    }

    private fun searchByQuery(query: String) {
        if (query.isEmpty()) {
            getTop()
        } else {
            topNewsUseCase.searchTop(query)
                .catch {
                    Log.e("TAG", "ERROR:${it.message}")
                }.map {
                    it.map { article ->
                        NewsItem(
                            author = article.author,
                            source = article.source,
                            title = article.title,
                            description = article.description,
                            publishedAt = article.publishedAt,
                            url = article.url,
                            urlToImage = article.urlToImage,
                            content = article.content
                        )
                    }
                }
                .onEach {
                    updateUiState {
                        copy(news = it)
                    }
                }.stateIn(
                    scope = viewModelScope,
                    initialValue = emptyList(),
                    started = SharingStarted.WhileSubscribed(1_000)
                ).launchIn(viewModelScope)
        }
    }

    private fun getTop() {
        topNewsUseCase.getTop()
            .catch {
                Log.e("TAG", "ERROR:${it.message}")
            }.map {
                it.map { article ->
                    NewsItem(
                        author = article.author,
                        source = article.source,
                        title = article.title,
                        description = article.description,
                        publishedAt = article.publishedAt,
                        url = article.url,
                        urlToImage = article.urlToImage,
                        content = article.content
                    )
                }
            }.onEach {
                updateUiState {
                    copy(news = it)
                }
            }.launchIn(viewModelScope)
    }
}