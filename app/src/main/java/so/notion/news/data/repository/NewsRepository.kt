package so.notion.news.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import so.notion.news.data.datasource.RemoteDataSource
import so.notion.news.data.response.Article

interface NewsRepository {
    fun getTop(): Flow<List<Article>>
    fun searchTopByQuery(query: String): Flow<List<Article>>
}

class NewsRepositoryImp(
    private val remoteDataSource: RemoteDataSource
) : NewsRepository {
    override fun getTop(): Flow<List<Article>> {
        return flow {
            val response = runCatching {
                remoteDataSource.getTopNews()
            }


            val items = arrayListOf<Article>()
            for (i in 0 until 66) {
                items.add(
                    Article(
                        source = null,
                        author = "a:$i",
                        title = "Test",
                        description = "",
                        url = "",
                        urlToImage = "https://www.cnet.com/a/img/resize/cd5c7277200e6d4704da2970311e28bcb003b8c1/hub/2024/07/10/b203bae9-b961-41eb-ba82-a0a201c05d09/gettyimages-2150280190.jpg?auto=webp&fit=crop&height=675&width=1200",
                        publishedAt = "2024-06-27T01:38:00Z",
                        content = ""
                    )
                )
            }


            when {
                response.isSuccess -> emit(response.getOrNull()?.articles ?: emptyList())
                response.isFailure -> error("Failed get top news")
            }

            // emit(items)
        }
    }

    override fun searchTopByQuery(query: String): Flow<List<Article>> {
        return flow {

            val response = runCatching {
                remoteDataSource.searchTopByQuery(query)
            }

            when {
                response.isSuccess -> emit(response.getOrNull()?.articles ?: emptyList())
                response.isFailure -> error("Failed search news")
            }
        }
    }
}