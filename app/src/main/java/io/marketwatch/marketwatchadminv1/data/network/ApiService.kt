package io.marketwatch.marketwatchadminv1.data.network

import io.marketwatch.marketwatchadminv1.data.model.ActiveTipsDTO
import io.marketwatch.marketwatchadminv1.data.model.ActiveTipsDTOItem
import io.marketwatch.marketwatchadminv1.domain.model.ActiveTipsItem
import io.marketwatch.marketwatchadminv1.domain.model.CreateTipsRequest
import io.marketwatch.marketwatchadminv1.domain.model.Respo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

const val BASE_URL="http://new247.in/"

interface ApiService {
    @GET("/marketwatch-server/stockmarket/getcreateData.php")
    suspend fun getActiveTips():Response<ActiveTipsDTO>

    @Headers("Accept: application/json")
    @POST("/marketwatch-server/stockmarket/createData.php")
    suspend fun createTips(@Body createTipsRequest: CreateTipsRequest):Response<Respo>


    @POST("/marketwatch-server/stockmarket/deletecreateData.php")
    suspend fun deleteTip(@Field("id") id:Int):Response<Respo>
}