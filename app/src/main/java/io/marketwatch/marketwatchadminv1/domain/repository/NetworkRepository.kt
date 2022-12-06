package io.marketwatch.marketwatchadminv1.domain.repository

import io.marketwatch.marketwatchadminv1.data.model.ActiveTipsDTO
import io.marketwatch.marketwatchadminv1.data.model.ActiveTipsDTOItem
import io.marketwatch.marketwatchadminv1.domain.model.ActiveTipsItem
import io.marketwatch.marketwatchadminv1.domain.model.CreateTipsRequest
import io.marketwatch.marketwatchadminv1.domain.model.Respo
import retrofit2.Call
import retrofit2.Response

interface NetworkRepository {
   suspend fun getActiveTips():List<ActiveTipsDTOItem>

   suspend fun postActiveTips(createTipsRequest: CreateTipsRequest):Response<Respo>

   suspend fun DeleteActiveTipsById(id:Int):Response<Respo>

   suspend fun getActiveTips1():Response<ActiveTipsDTO>


}