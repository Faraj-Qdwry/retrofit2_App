package com.example.all_the_way_up.retrofit2.DataSource

import com.example.all_the_way_up.retrofit2.data.User
import com.example.all_the_way_up.retrofit2.data.repos
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource  : DataSource{

    var retrofit = retrofiClinte.instances

    override fun listRepos(user: String): Call<ArrayList<repos>> {
        return retrofit.listRepos("user")
    }

    override fun getUsres(): Observable<ArrayList<User>> {
        return retrofit.getUsres()
    }

}

