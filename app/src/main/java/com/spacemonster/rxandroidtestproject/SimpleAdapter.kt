package com.spacemonster.rxandroidtestproject

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class SimpleAdapter : RecyclerView.Adapter<SimpleViewHolder>(){

    private val dataList = ArrayList<SimpleData>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SimpleViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val defaultView = inflater.inflate(R.layout.simplelist_item, parent, false)

        return when(viewType){
            1 ->  SimpleViewHolder(inflater.inflate(R.layout.simplelist_item, parent, false))
            else -> SimpleViewHolder(defaultView)
        }
    }

    override fun onBindViewHolder(holder: SimpleViewHolder?, position: Int) {
        dataList?.run{
            holder?.init(this[position])
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addData(data: SimpleData){
        dataList.add(data)
        notifyItemInserted(dataList.size)
    }

    fun addDataAll(datas: List<SimpleData>){
        val startIndex = dataList.size
        dataList.addAll(dataList)
        notifyItemRangeInserted(startIndex, datas.size)
    }

    fun removeData(data: SimpleData){
        val position = dataList.indexOf(data)
        if(position != -1){
            dataList.remove(data)
            notifyItemRemoved(position)
        }
    }

}