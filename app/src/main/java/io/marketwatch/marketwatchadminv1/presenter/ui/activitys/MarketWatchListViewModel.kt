package io.marketwatch.marketwatchadminv1.presenter.ui.activitys

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.marketwatch.marketwatchadminv1.domain.model.ActiveTipsItem
import io.marketwatch.marketwatchadminv1.domain.model.CreateTipsRequest
import io.marketwatch.marketwatchadminv1.domain.use_cases.Create_tips_use_case
import io.marketwatch.marketwatchadminv1.domain.use_cases.Delete_ActiveTips_Use_Case
import io.marketwatch.marketwatchadminv1.domain.use_cases.GetActiveTips_UseCase
import io.marketwatch.marketwatchadminv1.presenter.ui.ActiveTipsState
import io.marketwatch.marketwatchadminv1.presenter.ui.CreateActiveTipsState
import io.marketwatch.marketwatchadminv1.presenter.ui.utils.UiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarketWatchListViewModel @Inject constructor(var getactivetipsUsecase: GetActiveTips_UseCase,var createTipsUseCase: Create_tips_use_case, var deleteActivetipsUseCase: Delete_ActiveTips_Use_Case):ViewModel() {
   private var _marketwatchListStateFLow = MutableStateFlow(ActiveTipsState())
    private var _createTipsStateFlow= MutableStateFlow(CreateActiveTipsState())
    private var _deleteActiveTipsById= MutableStateFlow(CreateActiveTipsState())


    var marketWatchListStateFlow:StateFlow<ActiveTipsState> = _marketwatchListStateFLow
    var createTipsStateFlow:StateFlow<CreateActiveTipsState> = _createTipsStateFlow
    var deleteActiveTipsById:StateFlow<CreateActiveTipsState> = _deleteActiveTipsById

    private var marketwatchFilterListStateFlow = MutableStateFlow(ActiveTipsState())

    private var _marketWatchListViewModel=MutableLiveData<UiState<List<ActiveTipsItem>>>();
    val marketWatchListViewModel:LiveData<UiState<List<ActiveTipsItem>>>
    get() = _marketWatchListViewModel

    fun getMarketWatchList1(){
        viewModelScope.launch {
            _marketWatchListViewModel.value=UiState.Loading
             var result=getactivetipsUsecase.execute1()
            if(result!=null && result.size>0){
                _marketWatchListViewModel.value=UiState.Success(result)
            }else{
                _marketWatchListViewModel.value=UiState.Failed("error")
            }


        }

    }

    fun getMarketWatchList(){
        getactivetipsUsecase.execute().onEach {
            when(it){
                is UiState.Loading->{
                    _marketwatchListStateFLow.value= ActiveTipsState(true)
                }
                is UiState.Success->{
                    _marketwatchListStateFLow.value=ActiveTipsState(data = it.data )
                }
                is UiState.Failed->{
                    _marketwatchListStateFLow.value=ActiveTipsState(error = "error")
                }
            }
        }.launchIn(viewModelScope)
    }



    fun postActiveTips(createTipsRequest: CreateTipsRequest){
         createTipsUseCase.execute(createTipsRequest).onEach {
             when(it){
                 UiState.Loading->{
                     _createTipsStateFlow.value= CreateActiveTipsState(isLoading = true)
                 }
                is UiState.Success->{
                     _createTipsStateFlow.value= CreateActiveTipsState(data = it.data)
                 }
             }
         }.launchIn(viewModelScope)
    }


    fun deleteActiveTipsById(id:Int){
         deleteActivetipsUseCase.execute(id).onEach {
             when(it){
                 is UiState.Loading->{
                     _deleteActiveTipsById.value= CreateActiveTipsState(isLoading = true)
                 }
                 is UiState.Success->{
                     _deleteActiveTipsById.value=CreateActiveTipsState(data = it.data!!)
                 }
             }
         }.launchIn(viewModelScope)
    }
}