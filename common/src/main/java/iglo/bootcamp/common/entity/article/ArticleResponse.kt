package iglo.bootcamp.common.entity.article

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)