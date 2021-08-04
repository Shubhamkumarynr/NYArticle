package com.ny.nyarticle.network

import com.ny.nyarticle.model.DataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {
    /**
     * ApiInterface
     * @desc - Interface for network request method
     * */
    @GET
    fun getListData(@Url url: String): Call<DataModel>


}