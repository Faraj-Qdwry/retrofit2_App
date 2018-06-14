package com.example.all_the_way_up.retrofit2


import io.reactivex.Observable
import retrofit2.http.GET

interface callingApiInterface {
    @get:GET("users")
    val usres : Observable<List<dataclass>>
}