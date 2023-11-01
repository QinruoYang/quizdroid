package edu.uw.ischool.qy54.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.addCallback
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

        val correctAnswerIndex = question?.correctAnswerIndex ?: -1
        val correctAnswer = if (question != null && correctAnswerIndex >= 0) {
            question.options[correctAnswerIndex]
        } else {
            null
        }

        val selectedOptionIndex = when (selectedOption) {
            R.id.radioButton1 -> 0
            R.id.radioButton2 -> 1
            R.id.radioButton3 -> 2
            R.id.radioButton4 -> 3
            else -> -1
        }

        tvYourAnswer.text = getString(R.string.your_answer, question?.options?.get(selectedOptionIndex))
        tvCorrectAnswer.text = getString(R.string.correct_answer, correctAnswer)
        tvScore.text = getString(R.string.score_count, correctCount, questionIndex + 1)

        if ((questionIndex + 1) < (TopicManager.topics[selectedTopicKey]?.questions?.size ?: 0)) {
            btnNextOrFinish.text = getString(R.string.btn_next)
            btnNextOrFinish.setOnClickListener {
                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra("selectedTopic", selectedTopicKey)
                intent.putExtra("questionIndex", questionIndex + 1)
                intent.putExtra("correctCount", correctCount)
                startActivity(intent)
                finish()
            }
        } else {
            btnNextOrFinish.text = getString(R.string.btn_finish)
            btnNextOrFinish.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }


        handleBackPressedLogic()
    }

    private fun handleBackPressedLogic() {
        onBackPressedDispatcher.addCallback(this) {
            val intent = Intent(this@AnswerActivity, QuestionActivity::class.java)
            intent.putExtra("selectedTopic", selectedTopicKey)
            intent.putExtra("questionIndex", questionIndex)
            intent.putExtra("correctCount", correctCount)
            startActivity(intent)
            finish()
        }
    }
}
