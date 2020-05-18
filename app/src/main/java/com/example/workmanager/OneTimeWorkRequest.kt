package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*

class OneTimeWorkRequest : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_startworkRequest.setOnClickListener{
            setOneTimeWorkRequest()
        }
    }
    private fun setOneTimeWorkRequest(){

        val workManager=WorkManager.getInstance(applicationContext)
       val upLoadWorkRequest=OneTimeWorkRequest.Builder(UploadWorker::class.java)
           .build()
        workManager.enqueue(upLoadWorkRequest)
        workManager.getWorkInfoByIdLiveData(upLoadWorkRequest.id)
            .observe(this, Observer {
                btn_message.text=it.state.name
            })
    }
}
