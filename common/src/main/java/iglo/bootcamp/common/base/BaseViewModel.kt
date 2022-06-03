package iglo.bootcamp.common.base

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import iglo.bootcamp.common.ext.SingleLiveEvent

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    val navigationEvent = SingleLiveEvent<Intent>()
    open fun handleNavigationParams(it: Intent){}
}