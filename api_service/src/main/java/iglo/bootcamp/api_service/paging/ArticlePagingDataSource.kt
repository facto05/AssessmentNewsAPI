package iglo.bootcamp.api_service.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import iglo.bootcamp.api_service.service.ArticleService
import iglo.bootcamp.common.entity.article.Article

class ArticlePagingDataSource(
    private val articleService: ArticleService,
    private val sources: String,
    private val q: String
) : PagingSource<Int, Article>(){
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        val pageSize = 20
        try {

            val result = articleService.getArticleService(
                sources = sources,
                q = q,
                page = page,
                pageSize = pageSize
            )
            if(!result.isSuccessful){
                return LoadResult.Error(Exception("invalid data error"))
            }
            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (
                result.body()?.articles?.isEmpty() == true ||
                result.body()?.let { page * pageSize >= it.totalResults } == true
            ) {
                null
            } else {
                page + 1
            }
            return result.body()?.let {
                LoadResult.Page(
                    data = it.articles,
                    prevKey,
                    nextKey
                )
            } ?: LoadResult.Error(Exception("invalid data error"))
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}

//PagingSource
//Pager
//PagingData
object ArticlePager {
    fun createPager(
        articleService: ArticleService,
        pageSize: Int,
        sources: String,
        q: String
    ): Pager<Int, Article> = Pager(
        config = PagingConfig(pageSize),
        pagingSourceFactory = {
            ArticlePagingDataSource(
                articleService,
                sources,
                q)
        }
    )
}