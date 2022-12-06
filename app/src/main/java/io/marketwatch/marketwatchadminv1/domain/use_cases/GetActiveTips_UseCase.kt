package io.marketwatch.marketwatchadminv1.domain.use_cases

import io.marketwatch.marketwatchadminv1.data.model.toDomain
import io.marketwatch.marketwatchadminv1.domain.model.ActiveTipsItem
import io.marketwatch.marketwatchadminv1.domain.repository.NetworkRepository
import io.marketwatch.marketwatchadminv1.presenter.ui.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetActiveTips_UseCase @Inject constructor(var networkRepository: NetworkRepository) {
    fun execute():Flow<UiState<List<ActiveTipsItem>>> = flow{
         emit(UiState.Loading)
         emit(UiState.Success(networkRepository.getActiveTips().map { it.toDomain() }));

    }
   suspend fun execute1():List<ActiveTipsItem>{
       var result:List<ActiveTipsItem>?= null
       if(networkRepository.getActiveTips1().isSuccessful){
            result =  networkRepository.getActiveTips1().body()?.map { it.toDomain() }
       }else{
           result= emptyList();
       }
       return result!!;
    }
}