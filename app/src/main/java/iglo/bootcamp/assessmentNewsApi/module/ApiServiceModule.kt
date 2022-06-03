package iglo.bootcamp.assessmentNewsApi.module

import android.content.Context
import dagger.Module
import dagger.Provides
import iglo.bootcamp.api_service.RetrofitClient
import iglo.bootcamp.api_service.service.ArticleService
import iglo.bootcamp.api_service.service.SourceService
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
class ApiServiceModule {

    @Provides
    @Singleton
    fun provideRetrofitClient(context: Context) = RetrofitClient.getClient(context)

    @Provides
    @Singleton
    fun provideSourceService(retrofit: Retrofit) = retrofit.create(SourceService::class.java)

    @Provides
    @Singleton
    fun provideArticleService(retrofit: Retrofit) = retrofit.create(ArticleService::class.java)
}