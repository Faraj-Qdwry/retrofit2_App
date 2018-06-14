package com.example.all_the_way_up.retrofit2.Retrofit


import com.example.all_the_way_up.retrofit2.data.User
import com.example.all_the_way_up.retrofit2.data.repos
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface callingApiInterface {
    @get:GET("users")
    val usres : Observable<List<User>>

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<repos>>
}