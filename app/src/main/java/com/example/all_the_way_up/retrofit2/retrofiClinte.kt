package com.example.all_the_way_up.retrofit2

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object retrofiClinte {
    private var myRetroIstance: Retrofit? = null

    val instances : Retrofit
            get() {
                if (myRetroIstance==null){
                    myRetroIstance = Retrofit.Builder()
                            .baseUrl("https://api.github.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build()
                }
                return myRetroIstance!!
            }


}