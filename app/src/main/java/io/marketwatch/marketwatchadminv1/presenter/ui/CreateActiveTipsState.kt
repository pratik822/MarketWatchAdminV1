package io.marketwatch.marketwatchadminv1.presenter.ui

import io.marketwatch.marketwatchadminv1.domain.model.ActiveTipsItem
import io.marketwatch.marketwatchadminv1.domain.model.Respo
import retrofit2.Call
import retrofit2.Response

data class CreateActiveTipsState(var isLoading:Boolean=false, var data:Respo?=null, val error: String = "")

