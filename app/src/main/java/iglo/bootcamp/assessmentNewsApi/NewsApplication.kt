package iglo.bootcamp.assessmentNewsApi

import android.util.Log
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import iglo.bootcamp.assessmentNewsApi.module.DaggerAppComponent

class NewsApplication : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            Log.e("", "", e)
            t.join()
        }
    }
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().app(this).build()
}