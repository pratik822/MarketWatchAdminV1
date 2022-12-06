package io.marketwatch.marketwatchadminv1.presenter.ui

import io.marketwatch.marketwatchadminv1.domain.model.ActiveTipsItem

data class ActiveTipsState(var isLoading:Boolean=false, var data:List<ActiveTipsItem>?=null, val error: String = "")

