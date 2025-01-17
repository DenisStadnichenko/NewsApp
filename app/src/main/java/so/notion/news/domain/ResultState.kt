package so.notion.news.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface ResultState<out T> {
    data class Success<T>(val data: T) : ResultState<T>
    data class Error(val exception: Throwable? = null) : ResultState<Nothing>
    data object Loading : ResultState<Nothing>
}

fun <T> Flow<T>.asResultState(): Flow<ResultState<T>> {
    return this
        .map<T, ResultState<T>> {
            ResultState.Success(it)
        }
        .onStart {
            emit(ResultState.Loading)
        }
        .catch { emit(ResultState.Error(it)) }
}
