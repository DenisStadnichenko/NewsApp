package so.notion.news.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import so.notion.news.data.repository.NewsRepository
import so.notion.news.data.response.Article

interface TopNewsUseCase {
    fun getTop(): Flow<List<Article>>
    fun searchTop(query: String): Flow<List<Article>>
}

class TopNewsUseCaseImpl(
    private val newsRepository: NewsRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : TopNewsUseCase {
    override fun getTop(): Flow<List<Article>> {
        return newsRepository.getTop().flowOn(dispatcher)
    }

    override fun searchTop(query: String): Flow<List<Article>> {
        return newsRepository.searchTopByQuery(query).flowOn(dispatcher)
    }
}