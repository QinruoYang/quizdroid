package edu.uw.ischool.qy54.quizdroid

import android.app.Application
import android.util.Log

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