package iglo.bootcamp.assessmentNewsApi.activity.detail_article

import android.os.Bundle
import androidx.activity.viewModels
import iglo.bootcamp.assessmentNewsApi.R
import iglo.bootcamp.assessmentNewsApi.databinding.DetailArticleLayoutBinding
import iglo.bootcamp.assessmentNewsApi.view_model.DetailArticleViewModel
import iglo.bootcamp.common.base.BaseActivity

class DetailArticleActivity : BaseActivity<DetailArticleLayoutBinding, DetailArticleViewModel>() {
    override val layoutResourceId: Int = R.layout.detail_article_layout
    override val vm: DetailArticleViewModel by viewModels { vmFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "News"
        val link = intent.getStringExtra("EXTRA_LINK_ARTICLE")
        binding.webView.loadUrl(link.orEmpty())
    }

}