package io.marketwatch.marketwatchadminv1.domain.model

import android.os.Parcel
import android.os.Parcelable

data class CreateTipsRequest(
    val buyprice: String?,
    val create_date: String?,
    val duration: String?,
    val stoploss: String?,
    var storename: String?,
    val target1: String?,
    val target2: String?,
    val target3: String?,
    val type: String?
)
