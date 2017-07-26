package com.shushper.instagirl

import android.app.Application
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class App : Application() {

    lateinit var api: Api

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
                .baseUrl("http://instagirl.getsandbox.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        api = retrofit.create(Api::class.java)
    }

}