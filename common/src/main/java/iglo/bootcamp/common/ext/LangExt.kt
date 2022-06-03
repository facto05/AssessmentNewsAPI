package iglo.bootcamp.common.ext

import android.view.View
import androidx.annotation.MainThread
import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import java.util.concurrent.atomic.AtomicBoolean

suspend fun <T> Flow<T>.collectCatching(flowCollector: FlowCollector<T>, err: (Exception) -> Unit) {
    try {
        collect(flowCollector)
    } catch (e: Exception) {
        err(e)
    }
}
fun <T> singleLiveEventOf() = SingleLiveEvent<T>()
fun <T> mutableLiveDataOf() = MutableLiveData<T>()

class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val mPending = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, Observer<T> { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    override fun setValue(@Nullable t: T?) {
        mPending.set(true)
        super.setValue(t)
    }

    override fun postValue(value: T) {
        mPending.set(true)
        super.postValue(value)
    }

    @MainThread
    fun call() {
        value = null
    }
}