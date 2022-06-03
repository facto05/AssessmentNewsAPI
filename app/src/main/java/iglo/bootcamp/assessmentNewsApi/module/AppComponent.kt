package iglo.bootcamp.assessmentNewsApi.module

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import iglo.bootcamp.assessmentNewsApi.NewsApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ViewModelProviderFactoryModule::class])
abstract class AppComponent(): AndroidInjector<NewsApplication> {

    @Component.Builder
    abstract class Builder{

        @BindsInstance
        abstract fun app(application: DaggerApplication): Builder
        abstract fun build(): AppComponent
    }
}