package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private val results = ArrayList<User>()
    private lateinit var adapter: Studentadapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent= Intent(this,AddActivity::class.java)
        val lay = GridLayoutManager(this, 1)
        recc.layoutManager = lay
        adapter = Studentadapter(this, results)
        recc.adapter = adapter
        adapter.setOnItemClickListener(object : Studentadapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                intent.putExtra(AddActivity.IDD,results[position].idd)
                startActivity(intent)
            }
        })
        fab.setOnClickListener{
            val o:Long=0
            intent.putExtra(AddActivity.IDD,o)
            startActivity(intent)
        }
            val userdao = AppDatabase.getDatabase(this).userDao()

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, pos: Int, id: Long) {
                val tv = view as TextView
                tv.textSize = 20f //设置大小
                tv.gravity = Gravity.CENTER_HORIZONTAL //设置居中
                val a = resources.getStringArray(R.array.nandu)
                when {
                    a[pos] == "按学号排序" -> {
                            results.clear()
                            for (user in userdao.orid())
                                results.add(user)

                        adapter.notifyDataSetChanged()
                    }
                    a[pos] == "按姓名排序" -> {
                            results.clear()
                            for (user in userdao.orname())
                                results.add(user)
                        adapter.notifyDataSetChanged()
                    }
                    a[pos] == "按成绩排序" -> {
                            results.clear()
                            for (user in userdao.orgrade())
                                results.add(user)
                        adapter.notifyDataSetChanged()
                    }
                    a[pos] == "按籍贯排序" -> {
                            results.clear()
                            for (user in userdao.orhome())
                                results.add(user)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }
    override fun onStart() {
        super.onStart()
                val userdao = AppDatabase.getDatabase(this).userDao()
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, pos: Int, id: Long) {
                val tv = view as TextView
                tv.textSize = 20f //设置大小
                tv.gravity = Gravity.CENTER_HORIZONTAL //设置居中
                val a = resources.getStringArray(R.array.nandu)
                when {
                    a[pos] == "按学号排序" -> {
                            results.clear()
                            for (user in userdao.orid())
                                results.add(user)

                        adapter.notifyDataSetChanged()
                    }
                    a[pos] == "按姓名排序" -> {
                            results.clear()
                            for (user in userdao.orname())
                                results.add(user)

                        adapter.notifyDataSetChanged()
                    }
                    a[pos] == "按成绩排序" -> {
                            results.clear()
                            for (user in userdao.orgrade())
                                results.add(user)
                        adapter.notifyDataSetChanged()
                    }
                    a[pos] == "按籍贯排序" -> {
                            results.clear()
                            for (user in userdao.orhome())
                                results.add(user)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}