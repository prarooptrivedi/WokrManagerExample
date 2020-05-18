package com.example.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class FilteringWorkers(context: Context,params:WorkerParameters):Worker(context,params) {

    override fun doWork(): Result {
        try {

            for (i in 0 ..3000) {

                Log.i("MUTAG", "Filtering $i")
            }



            return Result.success()
        }
        catch (e:Exception){
            return Result.failure()
        }
    }
}