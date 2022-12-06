package io.marketwatch.marketwatchadminv1.presenter.ui.utils

sealed class UiState<out T>{
    object Loading: UiState<Nothing>()
    class Success<T>(var data:T):UiState<T>()
    class Failed<T>(var msg:String):UiState<T>()
}
