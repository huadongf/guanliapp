package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Studentadapter(private val context: Context, private val kapianList: ArrayList<User>) : RecyclerView.Adapter<Studentadapter.ViewHolder>() {
    private lateinit var  onItemClickListener: OnItemClickListener
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val equationName: TextView = view.findViewById(R.id.stuu)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.xuesheng_item, parent, false)
        val holder=ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            onItemClickListener.onItemClick(position)
        }
        holder.itemView.setOnLongClickListener {
            val position = holder.adapterPosition
                val userdao = AppDatabase.getDatabase(context as MainActivity).userDao()
                userdao.deleteUserbyidd(kapianList[position].idd)
                kapianList.remove(kapianList[position])
            notifyItemRemoved(position)
            false
        }
        return holder
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.equationName.text = kapianList[position].Name
    }
    override fun getItemCount() = kapianList.size
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}

