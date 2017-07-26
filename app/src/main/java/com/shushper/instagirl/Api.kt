package com.shushper.instagirl

import io.reactivex.Observable
import retrofit2.http.GET

interface Api {

    @GET("/girls")
    fun girls(): Observable<List<Girl>>
}