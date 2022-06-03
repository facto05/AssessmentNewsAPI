package iglo.bootcamp.assessmentNewsApi.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import iglo.bootcamp.api_service.usecase.CategoryUseCase
import iglo.bootcamp.common.base.BaseViewModel
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application, val categoryUseCase: CategoryUseCase) :
    BaseViewModel(application) {
    val data = MutableLiveData<List<String>>()
    init {
        viewModelScope.launch {
            categoryUseCase.invoke().collect{
                data.postValue(it)
            }
        }
    }
}