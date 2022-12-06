package io.marketwatch.marketwatchadminv1.presenter.ui.activitys

import android.content.Context
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.blue
import androidx.databinding.BindingAdapter
import io.marketwatch.marketwatchadminv1.domain.model.ActiveTipsItem

class AdapterBinding {

    @BindingAdapter("textColorCode")
    fun TextViewColorCodeBinding(context: Context,textView: TextView, item:ActiveTipsItem){
        if(item.buyprice?.toInt()!!>=200){
            textView.setTextColor(ContextCompat.getColor(context,android.R.color.black))
        }else{
            textView.setTextColor(ContextCompat.getColor(context,android.R.color.holo_blue_bright))
        }

    }
}