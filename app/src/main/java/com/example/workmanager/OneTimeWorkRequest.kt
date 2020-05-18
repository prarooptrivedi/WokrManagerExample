package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.NetworkType
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
        //When assign long task like vedio Upload or other large size file upload then battery charge mandatory so use
        //this code
        val constraints=Constraints.Builder()
            .setRequiresCharging(true)
                //set Check Internet Condition
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()


        val workManager=WorkManager.getInstance(applicationContext)
       val upLoadWorkRequest=OneTimeWorkRequest.Builder(UploadWorker::class.java)
           .setConstraints(constraints)
           .build()
        workManager.enqueue(upLoadWorkRequest)
        workManager.getWorkInfoByIdLiveData(upLoadWorkRequest.id)
            .observe(this, Observer {
                btn_message.text=it.state.name
            })
    }
}
