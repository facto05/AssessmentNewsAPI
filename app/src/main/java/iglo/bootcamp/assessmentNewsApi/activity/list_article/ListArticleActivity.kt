package iglo.bootcamp.assessmentNewsApi.activity.list_article

import android.os.Bundle
import androidx.activity.viewModels
import iglo.bootcamp.assessmentNewsApi.R
import iglo.bootcamp.assessmentNewsApi.databinding.ListArticleLayoutBinding
import iglo.bootcamp.assessmentNewsApi.view_model.ArticleViewModel
import iglo.bootcamp.common.base.BaseActivity

class ListArticleActivity : BaseActivity<ListArticleLayoutBinding, ArticleViewModel>() {
    override val layoutResourceId: Int= R.layout.list_article_layout
    override val vm: ArticleViewModel by viewModels { vmFactory }
    val adapter = ListArticlePagingAdapter(this)
    val loadStateAdapter = ListArticlePagingStateAdapter(this,::retry)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        observeLiveData()
    }
}