package com.gdtech.smarttransportationcity.api

import com.gdtech.smarttransportationcity.model.CctvData
import retrofit2.Response
import retrofit2.http.GET

interface CctvApi {

    @GET("db.json")
    suspend fun getListCctv(): Response<CctvData>

}