package com.example.workmanager

import android.content.Context
import android.util.Log
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception

class UploadWorker(context: Context,params:WorkerParameters):Worker(context,params) {

    override fun doWork(): Result {
        try {


            for (i in 0..600) {
                Log.i("MUTAG", "Uploadin $i")
            }
            return Result.success()
        }
        catch (e:Exception){
            return Result.failure()
        }
    }
}