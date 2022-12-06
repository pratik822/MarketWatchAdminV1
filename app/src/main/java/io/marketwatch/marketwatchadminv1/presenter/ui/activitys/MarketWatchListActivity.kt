package io.marketwatch.marketwatchadminv1.presenter.ui.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.marketwatch.marketwatchadminv1.databinding.ActivityMarketWatchListBinding
import io.marketwatch.marketwatchadminv1.domain.model.ActiveTipsItem
import io.marketwatch.marketwatchadminv1.presenter.ui.adapters.rv_adapter
import io.marketwatch.marketwatchadminv1.presenter.ui.utils.UiState
import io.marketwatch.marketwatchadminv1.presenter.ui.utils.iItemselectListner

@AndroidEntryPoint
class MarketWatchListActivity : AppCompatActivity(),iItemselectListner {
     var binding:ActivityMarketWatchListBinding?=null;
     private val viewModel:MarketWatchListViewModel by viewModels();
     private var adapter:rv_adapter?=null;
     private var activeTipsList:List<ActiveTipsItem>?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter=rv_adapter(this)
        initBindings();
        observable();

    }

   fun initBindings(){
       binding = ActivityMarketWatchListBinding.inflate(layoutInflater)
       setContentView(binding!!.root)

       binding?.apply {
           val layoutManager = LinearLayoutManager(applicationContext)
           rvList.layoutManager = layoutManager
           rvList.adapter=adapter;


       }
   }

    fun observable(){

        viewModel.getMarketWatchList1();
        viewModel.marketWatchListViewModel.observe(this, Observer {
        when(it){
           is UiState.Loading->{
               println("mylivedata"+"Loading")
            }
            is UiState.Success ->{
                println("mylivedata"+it.data)
            }

            is UiState.Failed ->{
                println("mylivedata"+"Faild")
            }
        }

        })

        viewModel.getMarketWatchList()
        lifecycleScope.launchWhenCreated {
            viewModel.marketWatchListStateFlow.collect {
                if (it.isLoading) {
                    println("MyDatass" + "Loading")
                }
                if (it.data != null) {
                    println("MyDatass" + it.data)
                    activeTipsList=it.data
                    adapter?.differ?.submitList(it.data)
                }
            }
        }
    }

    private fun submitList(){
        lifecycleScope.launchWhenCreated {
            viewModel.marketWatchListStateFlow.collect{
                adapter?.differ?.submitList(it.data)
                adapter?.notifyDataSetChanged()
                println("mylatest"+it)
            }
        }
    }

    override fun onitemselect(type:String,index: Int) {
        if(type=="edit"){
            Intent(this,Edit_Active_Tips::class.java).apply {
                putExtra("name",activeTipsList?.get(index))
               // startActivity(this)
                submitList()
                adapter?.filter?.filter("Gle")
                //adapter?.notifyDataSetChanged()
            }
        }else{
            submitList()
            Handler(Looper.myLooper()!!).postDelayed(Runnable {
                adapter?.filter?.filter("")
            },500)


//            activeTipsList?.get(index)?.id?.toInt()?.let {
//                viewModel.deleteActiveTipsById(it) }
        }

       Toast.makeText(this,"Toast"+type+" "+index+"",Toast.LENGTH_LONG).show()
    }
}