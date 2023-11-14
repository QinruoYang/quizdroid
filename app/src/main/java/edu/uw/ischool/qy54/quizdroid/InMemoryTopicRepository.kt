package edu.uw.ischool.qy54.quizdroid

import android.content.Context
import android.util.Log
import org.json.JSONArray
import java.io.File


class InMemoryTopicRepository(private val context: Context) : TopicRepository {

    private val topics = mutableListOf<Topic>()

    override fun getAllTopics(): List<Topic> = topics

    override fun getTopicByName(name: String): Topic? = topics.find { it.title == name }

    override fun addTopic(topic: Topic) {
        topics.add(topic)
    }

    override suspend fun fetchTopics() {
        val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        val url = sharedPreferences.getString("URL", "defaultURL")
        val downloadFrequency = sharedPreferences.getInt("DownloadFrequency", 60)
        val file = File(context.externalCacheDir, "questions.json")
        if (file.exists()) {
            val jsonString = file.readText(Charsets.UTF_8)
            val jsonArray = JSONArray(jsonString)

            val newTopics = mutableListOf<Topic>()
            for (i in 0 until jsonArray.length()) {
                val jsonTopic = jsonArray.getJSONObject(i)
                val title = jsonTopic.getString("title")
                val shortDescription = jsonTopic.getString("desc")
                val longDescription = "" // Assuming long description is not in your JSON

                val jsonQuestions = jsonTopic.getJSONArray("questions")
                val quizzes = mutableListOf<Quiz>()

                for (j in 0 until jsonQuestions.length()) {
                    val jsonQuestion = jsonQuestions.getJSONObject(j)
                    val text = jsonQuestion.getString("text")
                    val answerIndex = jsonQuestion.getInt("answer")
                    val answers = mutableListOf<String>()

                    val jsonAnswers = jsonQuestion.getJSONArray("answers")
                    for (k in 0 until jsonAnswers.length()) {
                        answers.add(jsonAnswers.getString(k))
                    }

                    quizzes.add(Quiz(text, answers, answerIndex))
                }

                newTopics.add(Topic(title, shortDescription, longDescription, quizzes))
            }

            topics.clear()
            topics.addAll(newTopics)
        } else {
            Log.e("InMemoryTopicRepository", "Error: questions.json file not found in externalCacheDir")
        }
    }
}