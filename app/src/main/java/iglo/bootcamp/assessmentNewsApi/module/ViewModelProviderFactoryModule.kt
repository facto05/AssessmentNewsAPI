package iglo.bootcamp.assessmentNewsApi.module

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.DaggerApplication
import dagger.multibindings.IntoMap
import iglo.bootcamp.assessmentNewsApi.activity.category.CategoryActivity
import iglo.bootcamp.assessmentNewsApi.activity.detail_article.DetailArticleActivity
import iglo.bootcamp.assessmentNewsApi.activity.list_article.ListArticleActivity
import iglo.bootcamp.assessmentNewsApi.activity.source.SourceActivity
import iglo.bootcamp.assessmentNewsApi.view_model.ArticleViewModel
import iglo.bootcamp.assessmentNewsApi.view_model.CategoryViewModel
import iglo.bootcamp.assessmentNewsApi.view_model.DetailArticleViewModel
import iglo.bootcamp.assessmentNewsApi.view_model.SourceViewModel
import iglo.bootcamp.common.di.ViewModelKey
import iglo.bootcamp.common.di.ViewModelProviderFactory

@Module(includes = [ViewModelModule::class])
abstract class ViewModelProviderFactoryModule {

    @Binds
    abstract fun provideContext(application: DaggerApplication) : Context

    @Binds
    abstract fun provideViewModelProviderFactory(factory: ViewModelProviderFactory):
            ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    abstract fun bindCategoryViewModel(categoryViewModel: CategoryViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SourceViewModel::class)
    abstract fun bindSourceViewModel(sourceViewModel: SourceViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArticleViewModel::class)
    abstract fun bindArticleViewModel(articleViewModel: ArticleViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailArticleViewModel::class)
    abstract fun bindDetailArticleViewModel(detailArticleViewModel: DetailArticleViewModel) : ViewModel

    @ContributesAndroidInjector
    abstract fun contributeCategoryActivity() : CategoryActivity

    @ContributesAndroidInjector
    abstract fun contributeSourceActivity() : SourceActivity

    @ContributesAndroidInjector
    abstract fun contributeListArticleActivity() : ListArticleActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailArticleActivity() : DetailArticleActivity
}