package com.example.schoolproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    lateinit var tv_name:TextView
    lateinit var btn_getstarted:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        tv_name=findViewById(R.id.tv_name)
        btn_getstarted=findViewById(R.id.btn_Finish)
        btn_getstarted.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }


        val username =intent.getStringExtra(Constants.USERNAME)
        tv_name.text=username
    }
}