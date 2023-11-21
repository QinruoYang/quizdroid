package edu.uw.ischool.qy54.quizdroid

import android.app.Application
import android.content.Context
import java.util.concurrent.TimeUnit
import android.util.Log
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager


class QuizApp : Application() {
    companion object {
        lateinit var repository: TopicRepository
            private set
    }

    override fun onCreate() {
        super.onCreate()
        repository = InMemoryTopicRepository(this)

        Log.i("QuizApp", "fileDir=$filesDir" )
        Log.d("QuizApp", "QuizApp is running!")
    }

}