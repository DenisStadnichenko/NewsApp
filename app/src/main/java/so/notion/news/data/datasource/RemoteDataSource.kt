package so.notion.news.data.datasource

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import so.notion.news.BuildConfig
import so.notion.news.data.response.TopNewsResponse

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getTopNews() = apiService.getTopNews()
    suspend fun searchTopByQuery(query: String) = apiService.searchTopByQuery(query)
}

class ApiService(private val httpClient: HttpClient) {

    private val apiKey = "3e13e602acc944c3bd8709b95887e5d0"
    private val top = "top-headlines?"
    private val searchTop = "everything?"

    //https://newsapi.org/v2/top-headlines?country=us&apiKey=c154bfea308d49f79d842ef07126c8b8
    suspend fun getTopNews(): TopNewsResponse =
        httpClient.get(BuildConfig.BASE_URL + top + "country=us" + "&apiKey=" + apiKey)
            .body<TopNewsResponse>()

    //https://newsapi.org/v2/everything?q=<QUERY_FROM_TEXT_FIELD>&apiKey=<YOUR_API_KEY>
    suspend fun searchTopByQuery(query: String): TopNewsResponse =
        httpClient.get(BuildConfig.BASE_URL + searchTop + "q=$query" + "&apiKey=" + apiKey)
            .body<TopNewsResponse>()
}