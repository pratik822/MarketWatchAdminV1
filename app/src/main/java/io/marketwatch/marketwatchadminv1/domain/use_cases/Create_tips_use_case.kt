package io.marketwatch.marketwatchadminv1.domain.use_cases

import io.marketwatch.marketwatchadminv1.domain.model.CreateTipsRequest
import io.marketwatch.marketwatchadminv1.domain.model.Respo
import io.marketwatch.marketwatchadminv1.domain.repository.NetworkRepository
import io.marketwatch.marketwatchadminv1.presenter.ui.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Create_tips_use_case @Inject constructor(var repository: NetworkRepository) {
    fun execute(activeTipsItem: CreateTipsRequest): Flow<UiState<Respo>> = flow {
        emit(UiState.Loading)
        emit(UiState.Success(repository.postActiveTips(activeTipsItem).body()!!))
    }
}