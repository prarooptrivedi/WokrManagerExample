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

class UploadWorker(context: Context,params:WorkerParameters):Worker(context,params) {
    companion object{
        const val KEY_Worker="key_worker"
    }
    override fun doWork(): Result {
        try {
            val count=inputData.getInt(OneTimeWorkRequest.KEY_COUNT_VALUE,0)

            for (i in 0 until count) {

                Log.i("MUTAG", "Uploadin $i")
            }
            //Recive Return Data
            val time=SimpleDateFormat("dd/mm/yyyy hh:mm:ss")
            val currentDate=time.format(Date())
            val outPutData=Data.Builder()
                .putString(KEY_Worker,currentDate)
                .build()


            return Result.success(outPutData)
        }
        catch (e:Exception){
            return Result.failure()
        }
    }
}