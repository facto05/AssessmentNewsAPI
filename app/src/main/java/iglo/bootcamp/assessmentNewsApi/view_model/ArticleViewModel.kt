package iglo.bootcamp.assessmentNewsApi.view_model

import android.app.Application
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import iglo.bootcamp.api_service.usecase.ArticleUseCase
import iglo.bootcamp.common.base.BaseViewModel
import iglo.bootcamp.common.entity.article.Article
import kotlinx.coroutines.launch

class ArticleViewModel(
    application: Application,
    val articleUseCase: ArticleUseCase) :
    BaseViewModel(application) {
    val data = MutableLiveData<PagingData<Article>>()
    var searchText = ""

    fun getArticle(source:String,q : String = ""){
        viewModelScope.launch {
            articleUseCase.invoke(sources = source, q = q).collect{
                data.postValue(it)
            }
        }
    }
}