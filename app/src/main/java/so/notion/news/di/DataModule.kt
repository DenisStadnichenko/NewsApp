package so.notion.news.di

import org.koin.dsl.module
import so.notion.news.data.repository.NewsRepository
import so.notion.news.data.repository.NewsRepositoryImp

val dataModule = module {

    single<NewsRepository> {
        NewsRepositoryImp(remoteDataSource = get())
    }
}