package iglo.bootcamp.assessmentNewsApi.view_model

import android.app.Application
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import iglo.bootcamp.api_service.usecase.SourceUseCase
import iglo.bootcamp.common.base.BaseViewModel
import iglo.bootcamp.common.entity.base_response.AppResponse
import iglo.bootcamp.common.entity.source.Source
import kotlinx.coroutines.launch

class SourceViewModel(
    application: Application,
    private val sourceUseCase: SourceUseCase)
    : BaseViewModel(application) {
    val data = MutableLiveData<AppResponse<List<Source>>>()
    var searchText = ""
    var listSearch = listOf<Source>()

    fun getSource(category : String){
        viewModelScope.launch {
            sourceUseCase.invoke(category).collect{
                data.postValue(it)
            }
        }
    }
    fun filterSource() {
        data.value?.data?.let { data ->
            listSearch = data.filter { source ->
                source.name.contains(searchText.orEmpty(), true)
            }
        }
    }
}