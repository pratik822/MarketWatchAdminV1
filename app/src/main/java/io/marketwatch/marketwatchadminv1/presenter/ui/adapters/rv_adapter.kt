package io.marketwatch.marketwatchadminv1.presenter.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.marketwatch.marketwatchadminv1.databinding.ListItemBinding
import io.marketwatch.marketwatchadminv1.domain.model.ActiveTipsItem
import io.marketwatch.marketwatchadminv1.presenter.ui.utils.iItemselectListner

class rv_adapter(var iItemselectListners:iItemselectListner):Filterable,RecyclerView.Adapter<rv_ViewHolder>() {
    val differ= AsyncListDiffer(this,rv_ViewHolder.DiffCallBack);


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rv_ViewHolder {
       var binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return rv_ViewHolder(binding);
    }


    override fun onBindViewHolder(holder: rv_ViewHolder, position: Int) {
            holder.binding.item=differ.currentList[position]
       // holder.Bind(differ.currentList[position])

//        holder.binding.ivEdit.setOnClickListener {
//            iItemselectListners.onitemselect("edit",position)
//        }
//
//        holder.binding.ivDelete.setOnClickListener {
//            iItemselectListners.onitemselect("close",position)
//        }
    }

    override fun getItemCount(): Int {
        println("differsize"+differ.currentList.size)
        return differ.currentList.size
    }

    override fun getFilter(): Filter {
       return customFilter(differ)
    }



    class customFilter(var differ: AsyncListDiffer<ActiveTipsItem>) :Filter(){

        override fun performFiltering(p0: CharSequence?): FilterResults {
            var list= mutableListOf<ActiveTipsItem>();
            if(p0!=null && p0.length>0){
                println("myresult---"+list.size)
                for (item in differ.currentList) {
                    if(item.storename?.lowercase()?.contains(p0.toString().lowercase()) == true){
                        list.add(item)
                    }
                }
            }else{
                println("myresult--"+list.size)
                list.clear()
                list.addAll(differ.currentList)
            }
            val results = FilterResults()
             results.values = list
            println("myresult"+list.size)
             return results
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            differ.submitList(p1?.values as List<ActiveTipsItem>)
         }

    }

}


class rv_ViewHolder(val binding:ListItemBinding):RecyclerView.ViewHolder(binding.root){
//    fun Bind(activeTipsItem: ActiveTipsItem){
//        binding.tvStockName.setText(activeTipsItem.storename)
//        binding.tvBuyPrice.setText(activeTipsItem.buyprice)
//        binding.tvDate.setText(activeTipsItem.create_date)
//        binding.tvTarget1.setText(activeTipsItem.target1)
//        binding.tvTarget2.setText(activeTipsItem.target2)
//        binding.tvTarget3.setText(activeTipsItem.target3)
//        binding.tvStoploss.setText(activeTipsItem.stoploss)
//
//    }

   companion object DiffCallBack:DiffUtil.ItemCallback<ActiveTipsItem>(){
        override fun areItemsTheSame(oldItem: ActiveTipsItem, newItem: ActiveTipsItem): Boolean {
         return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: ActiveTipsItem, newItem: ActiveTipsItem): Boolean {
          return oldItem == newItem
        }

    }

}