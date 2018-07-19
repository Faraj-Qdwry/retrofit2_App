package com.example.all_the_way_up.retrofit2.DataSource

import com.example.all_the_way_up.retrofit2.data.User
import com.example.all_the_way_up.retrofit2.data.Repos
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DataSource{
    @GET("users")
    fun getUsres() : Observable<ArrayList<User>>

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<ArrayList<Repos>>
}
