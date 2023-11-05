package edu.uw.ischool.qy54.quizdroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity

class QuestionActivity : AppCompatActivity() {

    private var selectedTopicKey: String? = null
    private var questionIndex: Int = 0
    private var correctCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        selectedTopicKey = intent.getStringExtra("selectedTopic")
        questionIndex = intent.getIntExtra("questionIndex", 0)
        correctCount = intent.getIntExtra("correctCount", 0)

        val topic = selectedTopicKey?.let { QuizApp.repository.getTopicByName(it) }
        val quiz = topic?.quizzes?.get(questionIndex)

        val tvQuestion: TextView = findViewById(R.id.tvQuestion)
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        val radioButton1: RadioButton = findViewById(R.id.radioButton1)
        val radioButton2: RadioButton = findViewById(R.id.radioButton2)
        val radioButton3: RadioButton = findViewById(R.id.radioButton3)
        val radioButton4: RadioButton = findViewById(R.id.radioButton4)
        val btnSubmit: Button = findViewById(R.id.btnSubmit)

        tvQuestion.text = quiz?.text
        radioButton1.text = quiz?.answers?.getOrNull(0)
        radioButton2.text = quiz?.answers?.getOrNull(1)
        radioButton3.text = quiz?.answers?.getOrNull(2)
        radioButton4.text = quiz?.answers?.getOrNull(3)

        radioGroup.setOnCheckedChangeListener { _, _ ->
            btnSubmit.visibility = View.VISIBLE
        }

        btnSubmit.setOnClickListener {
            val selectedOptionIndex = when (radioGroup.checkedRadioButtonId) {
                R.id.radioButton1 -> 0
                R.id.radioButton2 -> 1
                R.id.radioButton3 -> 2
                R.id.radioButton4 -> 3
                else -> -1
            }

            val intent = Intent(this, AnswerActivity::class.java).apply {
                putExtra("selectedTopic", selectedTopicKey)
                putExtra("questionIndex", questionIndex)
                putExtra("selectedOption", selectedOptionIndex)
                putExtra("correctCount", if (quiz?.correctAnswerIndex == selectedOptionIndex) correctCount + 1 else correctCount)
            }
            startActivity(intent)
            finish()
        }

        handleBackPressedLogic()
    }

    private fun handleBackPressedLogic() {
        onBackPressedDispatcher.addCallback(this) {
            if (questionIndex > 0) {
                val previousQuestionIndex = questionIndex - 1
                val intent = Intent(this@QuestionActivity, QuestionActivity::class.java).apply {
                    putExtra("selectedTopic", selectedTopicKey)
                    putExtra("questionIndex", previousQuestionIndex)
                    putExtra("correctCount", correctCount)
                    // Still need to check whether the previous question was answered correctly or not
                }
                startActivity(intent)
                finish()
            } else {
                finish()
            }
        }
    }
}