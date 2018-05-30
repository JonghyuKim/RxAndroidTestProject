package com.spacemonster.rxandroidtestproject

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.simplelist_item.view.*


class SimpleViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun init(viewData: SimpleData){
        itemView.iv_image.setImageDrawable(viewData.imgDrawable)
        itemView.tv_name.setText(viewData.name)
        itemView.clicks().subscribe {
            Toast.makeText(itemView.context, "click : ${viewData.name}", Toast.LENGTH_SHORT).show()
        }
    }
}