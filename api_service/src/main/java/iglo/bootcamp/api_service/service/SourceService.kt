package iglo.bootcamp.api_service.service

import iglo.bootcamp.api_service.Constants
import iglo.bootcamp.common.entity.source.SourceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SourceService {
    @GET("top-headlines/sources")
    suspend fun getSource(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): Response<SourceResponse>
}