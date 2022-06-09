package iglo.bootcamp.api_service.usecase

import iglo.bootcamp.api_service.paging.ArticlePager
import iglo.bootcamp.api_service.service.ArticleService

class ArticleUseCase(private val articleService: ArticleService) {
    operator fun invoke(sources: String, q: String) =
        ArticlePager.createPager(articleService, 20, sources, q).flow
}