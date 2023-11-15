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

        val topic = selectedTopicKey?.let { QuizApp.repository.getTopicByName(it) }
        val quiz = topic?.quizzes?.get(questionIndex)

        val correctAnswerIndex = quiz?.correctAnswerIndex?.minus(1) ?: -1
        val correctAnswer = quiz?.answers?.getOrNull(correctAnswerIndex)

        tvYourAnswer.text = getString(R.string.your_answer, quiz?.answers?.get(selectedOption))
        tvCorrectAnswer.text = getString(R.string.correct_answer, correctAnswer)
        tvScore.text = getString(R.string.score_count, correctCount, questionIndex + 1)

        // Define the text and action for the button based on whether there are more questions left
        if ((questionIndex + 1) < (topic?.quizzes?.size ?: 0)) {
            btnNextOrFinish.text = getString(R.string.btn_next)
            btnNextOrFinish.setOnClickListener {
                val intent = Intent(this, QuestionActivity::class.java).apply {
                    putExtra("selectedTopic", selectedTopicKey)
                    putExtra("questionIndex", questionIndex + 1)
                    putExtra("correctCount", correctCount)
                }
                startActivity(intent)
                finish()
            }
        } else {
            btnNextOrFinish.text = getString(R.string.btn_finish)
            btnNextOrFinish.setOnClickListener {
                finish()
            }
        }

        handleBackPressedLogic()
    }

    private fun handleBackPressedLogic() {
        onBackPressedDispatcher.addCallback(this) {
            val intent = Intent(this@AnswerActivity, QuestionActivity::class.java).apply {
                putExtra("selectedTopic", selectedTopicKey)
                putExtra("questionIndex", questionIndex)
                putExtra("correctCount", correctCount)
            }
            startActivity(intent)
            finish()
        }
    }
}

