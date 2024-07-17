package so.notion.news.di

import org.koin.dsl.module
import so.notion.news.domain.usecase.TopNewsUseCase
import so.notion.news.domain.usecase.TopNewsUseCaseImpl

val domainModule = module {

    factory<TopNewsUseCase> {
        TopNewsUseCaseImpl(
            newsRepository = get()
        )
    }
}