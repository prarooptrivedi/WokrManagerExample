package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Filter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class PeriodicWorkRequest : AppCompatActivity() {




    companion object{
        const val KEY_COUNT_VALUE="key_count"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_startworkRequest.setOnClickListener{
            periodicWorkRequest()
        }
    }
    private fun periodicWorkRequest(){
        val periodicWorkRequest=PeriodicWorkRequest.
            Builder(DownloadingWorkers::class.java,16,TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(applicationContext).enqueue(periodicWorkRequest)

    }
}
