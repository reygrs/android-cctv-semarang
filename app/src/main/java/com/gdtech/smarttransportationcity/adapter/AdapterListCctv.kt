package com.gdtech.smarttransportationcity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gdtech.smarttransportationcity.R
import com.gdtech.smarttransportationcity.model.Data
import kotlinx.android.synthetic.main.item_list_cctv.view.*

class AdapterListCctv constructor(
    private val listCctv: List<Data>,
    private val listener: ListenerAdapterListCctv
) : RecyclerView.Adapter<AdapterListCctv.ViewHolderItemCctv>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItemCctv {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_cctv, parent, false)
        return ViewHolderItemCctv(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderItemCctv, position: Int) {
        val data = listCctv[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listCctv.size
    }

    inner class ViewHolderItemCctv constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.linear_layout_container_item_list_cctv.setOnClickListener {
                listener.onClickItem(listCctv[adapterPosition])
            }
        }

        fun bind(data: Data) {
            itemView.text_view_name_item_list_cctv.text = data.lokasi
            //itemView.text_view_url_item_list_cctv.text = data.url
        }

    }

    interface ListenerAdapterListCctv {

        fun onClickItem(data: Data)

    }

}