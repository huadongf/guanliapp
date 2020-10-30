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
class MainActivity : AppCompatActivity() {
    private val results = ArrayList<User>()
    private lateinit var adapter: Studentadapter
    private var ok:Int=0
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
        results.clear()
        for (user in userdao.orid())
            results.add(user)
        adapter.notifyDataSetChanged()
        searchtext.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
            }
            override fun beforeTextChanged(text: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun afterTextChanged(edit: Editable) {
                results.clear()
                val bb = edit.toString()
                for (user in userdao.chaxun(bb))
                    results.add(user)
                results.sortWith { o1, o2 ->
                    when(ok) {
                        1 -> o1.id.compareTo(o2.id)
                        2 -> o1.Name.compareTo(o2.Name)
                        3 -> o1.grade.compareTo(o2.grade)
                        4 -> o1.hometown.compareTo(o2.hometown)
                        else -> o1.idd.compareTo(o2.idd)
                    }
                }
                adapter.notifyDataSetChanged()
            }
        })
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, pos: Int, id: Long) {
                val tv = view as TextView
                tv.textSize = 20f //设置大小
                tv.gravity = Gravity.CENTER_HORIZONTAL //设置居中
                val a = resources.getStringArray(R.array.nandu)
                results.sortWith { o1, o2 ->
                    when(a[pos]) {
                        "按学号排序" -> {
                            ok=1
                            o1.id.compareTo(o2.id)
                        }
                        "按姓名排序" -> {
                            ok=2
                            o1.Name.compareTo(o2.Name)
                        }
                        "按成绩排序" -> {
                            ok=3
                            o1.grade.compareTo(o2.grade)
                        }
                        "按籍贯排序" -> {
                            ok=4
                            o1.hometown.compareTo(o2.hometown)
                        }
                        else -> o1.idd.compareTo(o2.idd)
                    }
                }
                adapter.notifyDataSetChanged()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }
    override fun onStart() {
        super.onStart()
        val userdao=AppDatabase.getDatabase(this).userDao()
            results.clear()
            for (user in userdao.orid())
                results.add(user)
        results.sortWith { o1, o2 ->
            when(ok) {
                1 -> o1.id.compareTo(o2.id)
                2 -> o1.Name.compareTo(o2.Name)
                3 -> o1.grade.compareTo(o2.grade)
                4 -> o1.hometown.compareTo(o2.hometown)
                else -> o1.idd.compareTo(o2.idd)
            }
        }
        adapter.notifyDataSetChanged()
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, pos: Int, id: Long) {
                val tv = view as TextView
                tv.textSize = 20f //设置大小
                tv.gravity = Gravity.CENTER_HORIZONTAL //设置居中
                val a = resources.getStringArray(R.array.nandu)
                results.sortWith { o1, o2 ->
                    when(a[pos]) {
                        "按学号排序" -> {
                            ok=1
                            o1.id.compareTo(o2.id)
                        }
                        "按姓名排序" -> {
                            ok=2
                            o1.Name.compareTo(o2.Name)
                        }
                        "按成绩排序" -> {
                            ok=3
                            o1.grade.compareTo(o2.grade)
                        }
                        "按籍贯排序" -> {
                            ok=4
                            o1.hometown.compareTo(o2.hometown)
                        }
                        else -> o1.idd.compareTo(o2.idd)
                    }
                }
                adapter.notifyDataSetChanged()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}