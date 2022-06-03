package iglo.bootcamp.assessmentNewsApi.module

import dagger.Module
import dagger.Provides
import iglo.bootcamp.api_service.service.ArticleService
import iglo.bootcamp.api_service.service.SourceService
import iglo.bootcamp.api_service.usecase.ArticleUseCase
import iglo.bootcamp.api_service.usecase.CategoryUseCase
import iglo.bootcamp.api_service.usecase.SourceUseCase
import iglo.bootcamp.assessmentNewsApi.module.ApiServiceModule

@Module(includes = [ApiServiceModule::class])
class UseCaseModule {
    @Provides
    fun provideCategoryUseCase() = CategoryUseCase()

    @Provides
    fun provideSourceUseCase(sourceService: SourceService) = SourceUseCase(sourceService)

    @Provides
    fun provideArticleUseCase(articleService: ArticleService) = ArticleUseCase(articleService)
}