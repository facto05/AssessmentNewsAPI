package iglo.bootcamp.api_service.service

import iglo.bootcamp.api_service.Constants
import iglo.bootcamp.common.entity.article.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {
    @GET("everything")
    suspend fun getArticleService(@Query("sources") sources: String,
                                  @Query("q") q: String,
                                  @Query("page") page: Int = 1,
                                  @Query("pageSize") pageSize: Int,
                                  @Query("apiKey") apiKey: String = Constants.API_KEY
    ): Response<ArticleResponse>
}