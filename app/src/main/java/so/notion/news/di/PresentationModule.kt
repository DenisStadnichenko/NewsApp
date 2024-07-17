package so.notion.news.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import so.notion.news.screens.top.TopNewsViewModel

val presentationModule = module {
    viewModel { TopNewsViewModel(topNewsUseCase = get()) }
}