package io.marketwatch.marketwatchadminv1.domain.use_cases

import io.marketwatch.marketwatchadminv1.domain.model.Respo
import io.marketwatch.marketwatchadminv1.domain.repository.NetworkRepository
import io.marketwatch.marketwatchadminv1.presenter.ui.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Delete_ActiveTips_Use_Case @Inject constructor( var repository: NetworkRepository) {

    fun execute(id:Int):Flow<UiState<Respo>> = flow{
        emit(UiState.Loading)
        var data=repository.DeleteActiveTipsById(id);
         emit(UiState.Success(data.body()!!))
    }

}