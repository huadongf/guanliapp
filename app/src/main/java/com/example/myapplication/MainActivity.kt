package com.example.myapplication
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
        val intent= Intent(this, AddActivity::class.java)
        val lay = GridLayoutManager(this, 1)
        recc.layoutManager = lay
        adapter = Studentadapter(this, results)
        recc.adapter = adapter
        adapter.setOnItemClickListener(object : Studentadapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                intent.putExtra(AddActivity.IDD, results[position].idd)
                startActivity(intent)
            }
        })
        fab.setOnClickListener{
            val o:Long=0
            intent.putExtra(AddActivity.IDD, o)
            startActivity(intent)
        }
            val userdao = AppDatabase.getDatabase(this).userDao()
        thread {
            for (user in userdao.orid())
                results.add(user)
        }
        searchtext.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
                //text  输入框中改变后的字符串信息
                //start 输入框中改变后的字符串的起始位置
                //before 输入框中改变前的字符串的位置 默认为0
                //count 输入框中改变后的一共输入字符串的数量
                //    textView1.setText("输入后字符串 [ $text ] 起始光标 [ $start ] 输入数量 [ $count ]")
            }

            override fun beforeTextChanged(text: CharSequence, start: Int, count: Int, after: Int) {
                //text  输入框中改变前的字符串信息
                //start 输入框中改变前的字符串的起始位置
                //count 输入框中改变前后的字符串改变数量一般为0
                //after 输入框中改变后的字符串与起始位置的偏移量
                //  println(text.toString())
                //   textView0.setText("输入前字符串 [ $text ]起始光标 [ $start ]结束偏移量  [$after ]")
            }

            override fun afterTextChanged(edit: Editable) {
                //edit  输入结束呈现在输入框中的信息
                //  textView2.setText("输入结束后的内容为 [$edit ] 即将显示在屏幕上")
                results.clear()
                val bb = edit.toString()
                for (user in userdao.chaxun(bb))
                    results.add(user)
                adapter.notifyDataSetChanged()
            }
        })
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, pos: Int, id: Long) {
                val tv = view as TextView
                tv.textSize = 20f //设置大小
                tv.gravity = Gravity.CENTER_HORIZONTAL //设置居中
                val a = resources.getStringArray(R.array.nandu)
                when {
                    a[pos] == "按学号排序" -> {
                        results.sortWith { o1, o2 ->
                            o1.id.compareTo(o2.id)
                        }
                        adapter.notifyDataSetChanged()
                    }
                    a[pos] == "按姓名排序" -> {
                        results.sortWith { o1, o2 ->
                            o1.Name.compareTo(o2.Name)
                        }
                        adapter.notifyDataSetChanged()
                    }
                    a[pos] == "按成绩排序" -> {
                        results.sortWith { o1, o2 ->
                            o1.grade.compareTo(o2.grade)
                        }
                        adapter.notifyDataSetChanged()
                    }
                    a[pos] == "按籍贯排序" -> {
                        results.sortWith { o1, o2 ->
                            o1.hometown.compareTo(o2.hometown)
                        }
                        adapter.notifyDataSetChanged()
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }
    override fun onStart() {
        super.onStart()
        AppDatabase.getDatabase(this).userDao()
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, pos: Int, id: Long) {
                val tv = view as TextView
                tv.textSize = 20f //设置大小
                tv.gravity = Gravity.CENTER_HORIZONTAL //设置居中
                val a = resources.getStringArray(R.array.nandu)
                when {
                    a[pos] == "按学号排序" -> {
                        results.sortWith { o1, o2 ->
                            o1.id.compareTo(o2.id)
                        }
                        adapter.notifyDataSetChanged()
                    }
                    a[pos] == "按姓名排序" -> {
                        results.sortWith { o1, o2 ->
                            o1.Name.compareTo(o2.Name)
                        }
                        adapter.notifyDataSetChanged()
                    }
                    a[pos] == "按成绩排序" -> {
                        results.sortWith { o1, o2 ->
                            o1.grade.compareTo(o2.grade)
                        }
                        adapter.notifyDataSetChanged()
                    }
                    a[pos] == "按籍贯排序" -> {
                        results.sortWith { o1, o2 ->
                            o1.hometown.compareTo(o2.hometown)
                        }
                        adapter.notifyDataSetChanged()
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}