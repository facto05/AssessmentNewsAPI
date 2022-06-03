package iglo.bootcamp.assessmentNewsApi.module

import dagger.Module
import dagger.Provides
import dagger.android.DaggerApplication
import iglo.bootcamp.api_service.usecase.ArticleUseCase
import iglo.bootcamp.api_service.usecase.CategoryUseCase
import iglo.bootcamp.api_service.usecase.SourceUseCase
import iglo.bootcamp.assessmentNewsApi.module.UseCaseModule
import iglo.bootcamp.assessmentNewsApi.view_model.ArticleViewModel
import iglo.bootcamp.assessmentNewsApi.view_model.CategoryViewModel
import iglo.bootcamp.assessmentNewsApi.view_model.DetailArticleViewModel
import iglo.bootcamp.assessmentNewsApi.view_model.SourceViewModel

@Module(includes = [UseCaseModule::class])
class ViewModelModule {
    @Provides
    fun provideCategoryViewModel(application: DaggerApplication, categoryUseCase: CategoryUseCase) =
        CategoryViewModel(application, categoryUseCase)

    @Provides
    fun provideSourceViewModel(application: DaggerApplication, sourceUseCase: SourceUseCase) =
        SourceViewModel(application, sourceUseCase)

    @Provides
    fun provideArticleViewModel(application: DaggerApplication, articleUseCase: ArticleUseCase) =
        ArticleViewModel(application, articleUseCase)

    @Provides
    fun provideDetailArticleViewModel(application: DaggerApplication) =
        DetailArticleViewModel(application)
}