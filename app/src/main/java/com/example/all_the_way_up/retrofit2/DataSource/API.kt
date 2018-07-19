package com.example.all_the_way_up.retrofit2.DataSource

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object retrofiClinte{
    private var myRetroIstance: DataSource? = null

    val instances : DataSource
        get() {
            if (myRetroIstance ==null){
                myRetroIstance = Retrofit.Builder()
                        .baseUrl("https://api.github.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
                        .create(DataSource::class.java)
            }
            return myRetroIstance!!
        }
}

class AndroidDisposable {
    private var compositeDisposable: CompositeDisposable? = null

    fun add(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable?.add(disposable)
    }

    fun dispose(){
        compositeDisposable?.dispose()
    }
}
