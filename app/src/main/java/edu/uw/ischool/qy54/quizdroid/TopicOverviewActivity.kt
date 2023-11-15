package edu.uw.ischool.qy54.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TopicOverviewActivity : AppCompatActivity() {

    private var selectedTopicKey: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_overview)

        selectedTopicKey = intent.getStringExtra("selectedTopic")
        val selectedTopic = selectedTopicKey?.let { QuizApp.repository.getTopicByName(it) }

        val tvTopicName: TextView = findViewById(R.id.tvTopicName)
        val tvTopicDescription: TextView = findViewById(R.id.tvTopicDescription)
        val tvTotalQuestions: TextView = findViewById(R.id.tvTotalQuestions)
        val btnBegin: Button = findViewById(R.id.btnBegin)

        // Set the text views and button based on the selected topic
        tvTopicName.text = selectedTopic?.title
        tvTopicDescription.text = selectedTopic?.shortDescription
        tvTotalQuestions.text = getString(R.string.total_questions_text, selectedTopic?.quizzes?.size ?: 0)

        btnBegin.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            intent.putExtra("selectedTopic", selectedTopicKey)
            intent.putExtra("questionIndex", 0)
            intent.putExtra("correctCount", 0)
            startActivity(intent)
            finish()
        }
    }
}
