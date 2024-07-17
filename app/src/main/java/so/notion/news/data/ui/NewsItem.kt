package so.notion.news.data.ui

import so.notion.news.data.response.Source


data class NewsItem(
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
) {


}