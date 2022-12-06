package io.marketwatch.marketwatchadminv1.data

import io.marketwatch.marketwatchadminv1.data.model.ActiveTipsDTO
import io.marketwatch.marketwatchadminv1.data.model.ActiveTipsDTOItem
import io.marketwatch.marketwatchadminv1.data.network.ApiService
import io.marketwatch.marketwatchadminv1.domain.model.ActiveTipsItem
import io.marketwatch.marketwatchadminv1.domain.model.CreateTipsRequest
import io.marketwatch.marketwatchadminv1.domain.model.Respo
import io.marketwatch.marketwatchadminv1.domain.repository.NetworkRepository
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(val apiService: ApiService):NetworkRepository {
    override suspend fun getActiveTips(): List<ActiveTipsDTOItem> {
       var data=apiService.getActiveTips().body();
        return data!!
    }

    override suspend fun getActiveTips1(): Response<ActiveTipsDTO> {
        var data=apiService.getActiveTips()
        return data
    }

    override suspend fun postActiveTips(createTipsRequest: CreateTipsRequest): Response<Respo> {
        var data=apiService.createTips(createTipsRequest)
        return data
    }

    override suspend fun DeleteActiveTipsById(id: Int): Response<Respo> {
       return apiService.deleteTip(id)
    }


}