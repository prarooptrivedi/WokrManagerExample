package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Filter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*
import androidx.work.OneTimeWorkRequest
import kotlinx.android.synthetic.main.activity_main.*

class OneTimeWorkRequestAnotherOne : AppCompatActivity() {




    companion object{
        const val KEY_COUNT_VALUE="key_count"
    }
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
            //For Subbmit Data
        val data=Data.Builder()
            .putInt(KEY_COUNT_VALUE,125)
            .build()

        val workManager=WorkManager.getInstance(applicationContext)

        val upLoadWorkRequest=OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        val filtringRequest=OneTimeWorkRequest.Builder(FilteringWorkers::class.java)
            .build()

        val comressingWorkers=OneTimeWorkRequest.Builder(CompressingWorker::class.java)
            .build()

        val downloadingWorkers=OneTimeWorkRequest.Builder(DownloadingWorkers::class.java)
            .build()

        val paralleworks= mutableListOf<OneTimeWorkRequest>()
        paralleworks.add(downloadingWorkers)
        paralleworks.add(filtringRequest)

        workManager.beginWith(paralleworks)
            .then(comressingWorkers)
            .then(upLoadWorkRequest)
            .enqueue()
        workManager.getWorkInfoByIdLiveData(upLoadWorkRequest.id)
            .observe(this, Observer {
                btn_message.text=it.state.name
                if (it.state.isFinished){
                    val data=it.outputData
                    val message=data.getString(UploadWorker.KEY_Worker)
                    Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
                }
            })

    }
}
