package io.marketwatch.marketwatchadminv1.data.model

import io.marketwatch.marketwatchadminv1.domain.model.ActiveTipsItem
import io.marketwatch.marketwatchadminv1.domain.model.CreateTipsRequest

data class ActiveTipsDTOItem(
    val buyprice: String,
    val create_date: String,
    val duration: String,
    val id: String,
    val stoploss: String,
    var storename: String,
    val target1: String,
    val target2: String,
    val target3: String,
    val type: String
)

fun ActiveTipsDTOItem.toDomain(): ActiveTipsItem {
    return ActiveTipsItem(storename=this.storename,buyprice=this.buyprice,type=this.type, create_date = this.create_date, duration = this.duration, stoploss = this.stoploss, target1 = this.target1, target2 = this.target2, target3 = this.target3, id = this.id);
}

