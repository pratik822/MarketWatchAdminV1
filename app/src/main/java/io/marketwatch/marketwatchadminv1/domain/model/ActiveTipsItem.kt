package io.marketwatch.marketwatchadminv1.domain.model

import android.os.Parcel
import android.os.Parcelable
import io.marketwatch.marketwatchadminv1.data.model.toDomain
import java.io.Serializable


data class ActiveTipsItem(
     val buyprice: String?,
     val create_date: String?,
     val duration: String?,
     val id: String?,
     val stoploss: String?,
     var storename: String?,
     val target1: String?,
     val target2: String?,
     val target3: String?,
     val type: String?
):Parcelable {
     constructor(parcel: Parcel) : this(
          parcel.readString(),
          parcel.readString(),
          parcel.readString(),
          parcel.readString(),
          parcel.readString(),
          parcel.readString(),
          parcel.readString(),
          parcel.readString(),
          parcel.readString(),
          parcel.readString()
     ) {
     }

     override fun writeToParcel(parcel: Parcel, flags: Int) {
          parcel.writeString(buyprice)
          parcel.writeString(create_date)
          parcel.writeString(duration)
          parcel.writeString(id)
          parcel.writeString(stoploss)
          parcel.writeString(storename)
          parcel.writeString(target1)
          parcel.writeString(target2)
          parcel.writeString(target3)
          parcel.writeString(type)
     }

     override fun describeContents(): Int {
          return 0
     }

     companion object CREATOR : Parcelable.Creator<ActiveTipsItem> {
          override fun createFromParcel(parcel: Parcel): ActiveTipsItem {
               return ActiveTipsItem(parcel)
          }

          override fun newArray(size: Int): Array<ActiveTipsItem?> {
               return arrayOfNulls(size)
          }
     }
     private fun ActiveTipsItem.toTipsRequestDomain(): CreateTipsRequest {
          return CreateTipsRequest(storename=this.storename,buyprice=this.buyprice,type=this.type, create_date = this.create_date, duration = this.duration, stoploss = this.stoploss, target1 = this.target1, target2 = this.target2, target3 = this.target3);

     }

}
