package edu.uw.ischool.qy54.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class AnswerActivity : AppCompatActivity() {

    private var selectedTopicKey: String? = null
    private var questionIndex: Int = 0
    private var correctCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        selectedTopicKey = intent.getStringExtra("selectedTopic")
        questionIndex = intent.getIntExtra("questionIndex", 0)
        val selectedOption = intent.getIntExtra("selectedOption", -1)
        correctCount = intent.getIntExtra("correctCount", 0)

        val tvYourAnswer: TextView = findViewById(R.id.tvYourAnswer)
        val tvCorrectAnswer: TextView = findViewById(R.id.tvCorrectAnswer)
        val tvScore: TextView = findViewById(R.id.tvScore)
        val btnNextOrFinish: Button = findViewById(R.id.btnNextOrFinish)

        val question = TopicManager.topics[selectedTopicKey]?.questions?.get(questionIndex)
        val correctAnswer = question?.options?.get(question?.correctAnswerIndex ?: -1)

        when (selectedOption) {
            R.id.radioButton1 -> tvYourAnswer.text = "Your answer: ${question?.options?.get(0)}"
            R.id.radioButton2 -> tvYourAnswer.text = "Your answer: ${question?.options?.get(1)}"
            R.id.radioButton3 -> tvYourAnswer.text = "Your answer: ${question?.options?.get(2)}"
            R.id.radioButton4 -> tvYourAnswer.text = "Your answer: ${question?.options?.get(3)}"
        }

        tvCorrectAnswer.text = "Correct answer: $correctAnswer"
        tvScore.text = "You have $correctCount out of ${questionIndex + 1} correct"

        if (questionIndex + 1 < TopicManager.topics[selectedTopicKey]?.questions?.size ?: 0) {
            btnNextOrFinish.text = "Next"
            btnNextOrFinish.setOnClickListener {
                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra("selectedTopic", selectedTopicKey)
                intent.putExtra("questionIndex", questionIndex + 1)
                intent.putExtra("correctCount", correctCount)
                startActivity(intent)
                finish()
            }
        } else {
            btnNextOrFinish.text = "Finish"
            btnNextOrFinish.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onBackPressed() {
            val intent = Intent(this, QuestionActivity::class.java)
            intent.putExtra("selectedTopic", selectedTopicKey)
            intent.putExtra("questionIndex", questionIndex)
            intent.putExtra("correctCount", correctCount)
            startActivity(intent)
            finish()
    }

}
