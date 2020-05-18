package com.example.workmanager

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*
/*Created BY Praroop*/
class MainActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btn_startworkRequest.setOnClickListener{
            val intent=Intent(this,OneTimeWorkRequest::class.java)
            startActivity(intent)
        }
    }
}