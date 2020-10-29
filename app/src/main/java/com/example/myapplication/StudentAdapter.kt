package com.example.myapplication

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Studentadapter(private val context: Context, private val kapianList: ArrayList<User>) : RecyclerView.Adapter<Studentadapter.ViewHolder>() {
    private lateinit var  onItemClickListener: OnItemClickListener
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val equationName: TextView = view.findViewById(R.id.stuu)
        val tu:View=view.findViewById(R.id.tupian)
        val equationhome: TextView = view.findViewById(R.id.stuuhome)
        val equationgender: TextView = view.findViewById(R.id.stugender)
        val equationid: TextView = view.findViewById(R.id.stuuid)
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
            AlertDialog.Builder(context).apply {
                setTitle("删除")
                setMessage("确定删除这个学生的信息?")
                setCancelable(false)
                setPositiveButton("删除") { dialog,which ->
                    userdao.deleteUserbyidd(kapianList[position].idd)
                    kapianList.remove(kapianList[position])
                    notifyItemRemoved(position)
                }
                setNegativeButton("取消")
                { dialog,which -> }
                show()
            }

            false
        }
        return holder
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.equationName.text = kapianList[position].Name
        holder.equationhome.text = kapianList[position].hometown
        holder.equationgender.text = kapianList[position].gender
        holder.equationid.text = kapianList[position].id
        Glide.with(context).load(Uri.parse(kapianList[position].urii)).into(holder.tu as ImageView)
    }
    override fun getItemCount() = kapianList.size
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}

