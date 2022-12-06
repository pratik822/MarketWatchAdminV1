package io.marketwatch.marketwatchadminv1.presenter.ui.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import io.marketwatch.marketwatchadminv1.R
import io.marketwatch.marketwatchadminv1.data.model.ActiveTipsDTOItem
import io.marketwatch.marketwatchadminv1.databinding.ActivityEditActiveTipsBinding
import io.marketwatch.marketwatchadminv1.domain.model.ActiveTipsItem
import io.marketwatch.marketwatchadminv1.domain.model.CreateTipsRequest

@AndroidEntryPoint
class Edit_Active_Tips : AppCompatActivity() {
    var binding: ActivityEditActiveTipsBinding? = null
    private var extras: Intent? = null
    val marketWatchListViewModel:MarketWatchListViewModel by viewModels();
    var activeTipsItem:ActiveTipsItem?=null;
    var createTipsRequest:CreateTipsRequest?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViews()
        init()

        lifecycleScope.launchWhenCreated {
            marketWatchListViewModel.createTipsStateFlow.collect{
                println("myedited"+it.data)
            }
        }
        binding?.btnEdit?.setOnClickListener {
            // API CALL

            if(createTipsRequest!=null){
                println("mytips"+createTipsRequest)
                marketWatchListViewModel.postActiveTips(createTipsRequest!!)
            }

        }



    }

    fun bindViews() {
        binding = ActivityEditActiveTipsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

    }
    fun init() {
        extras = this.intent
        if (extras != null) {
            activeTipsItem = extras?.getParcelableExtra("name")
            println("myname" + activeTipsItem.toString())
            binding?.edtStockName?.setText(activeTipsItem?.storename)
            binding?.edtStoploss?.setText(activeTipsItem?.stoploss)
            binding?.edtBuyPrice?.setText(activeTipsItem?.buyprice)
            binding?.edtTarget1?.setText(activeTipsItem?.target1)
            binding?.edtTarget2?.setText(activeTipsItem?.target2)
            binding?.edtTarget3?.setText(activeTipsItem?.target3)

            createTipsRequest= CreateTipsRequest(activeTipsItem?.buyprice,activeTipsItem?.create_date,activeTipsItem?.duration
            ,activeTipsItem?.stoploss,activeTipsItem?.storename,activeTipsItem?.target1,activeTipsItem?.target2,
            activeTipsItem?.target3,activeTipsItem?.type)
            binding?.radioGroup?.setOnCheckedChangeListener { radioGroup, i ->

                when (i) {
                    R.id.radio_buy -> {
                        Toast.makeText(this, "Buy", Toast.LENGTH_LONG).show()
                    }
                    R.id.radio_sell -> {
                        Toast.makeText(this, "Sell", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}


