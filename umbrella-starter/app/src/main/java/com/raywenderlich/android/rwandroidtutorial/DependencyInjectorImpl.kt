package com.raywenderlich.android.rwandroidtutorial


//, it’s just considered good practice
// in case you ever wanted to swap in a different implementation in the future.
class DependencyInjectorImpl : DependencyInjector{

    override fun weatherRepository(): WeatherRepository {
        return WeatherRepositoryImpl()
    }
}