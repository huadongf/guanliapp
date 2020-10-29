package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    companion object {
        const val IDD = "yi"
    }
    private val fromAlbum=2
    private lateinit var ur:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)
        icimagine.setOnClickListener {
            val intent=Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type="image/*"
            startActivityForResult(intent,fromAlbum)
        }
        ur=""
        val o:Long=0
        val ok=intent.getLongExtra(IDD, o)
            val userdao = AppDatabase.getDatabase(this).userDao()
            if (ok != o)
                for (user in userdao.chazhao(ok)) {
                    idtext.setText(user.id)
                    nametext.setText(user.Name)
                    gendertext.setText(user.gender)
                    hometext.setText(user.hometown)
                    gradetext.setText(user.grade.toString())
                    Glide.with(this).load(Uri.parse(user.urii)).into(icimagine)
                }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val intent = Intent(this, MainActivity::class.java)
        button.setOnClickListener{
            if(idtext.text.toString().isEmpty() || nametext.text.toString().isEmpty() || gendertext.text.toString()
                    .isEmpty() || hometext.text.toString().isEmpty() || gradetext.text.toString()
                    .isEmpty()||ur.isEmpty()
            )
                Toast.makeText(this,"请完整输入信息!", Toast.LENGTH_SHORT).show()
            else if(ok == o)
            {

                    val ne = User(
                        idtext.text.toString(),
                        nametext.text.toString(),
                        gendertext.text.toString(),
                        hometext.text.toString(),
                        gradetext.text.toString().toLong(),
                        ur
                    )
                    userdao.insertUser(ne)
                    intent.putExtra("ONE", ne)
                    startActivity(intent)
            }
            else
            {
                    val ne = User(
                        idtext.text.toString(),
                        nametext.text.toString(),
                        gendertext.text.toString(),
                        hometext.text.toString(),
                        gradetext.text.toString().toLong(),
                        ur
                    )
                    ne.idd = ok
                    userdao.updateUser(ne)
                    intent.putExtra("ONE", ne)
                    startActivity(intent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode)
        {
            fromAlbum ->{
                if (resultCode == Activity.RESULT_OK && data != null) {
                    data.data?.let { uri ->
                        ur=uri.toString()
                        Glide.with(this).load(uri).into(icimagine)
                    }
                }
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}