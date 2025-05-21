package za.co.varsitycollege.imadproject2_st10470447

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        val score = intent.getIntExtra("SCORE",0) // change for all correct answers
        val total = intent. getIntExtra("TOTAL",5) // DEFAULT TO 5
        val correctAnswers = intent.getStringArrayListExtra("CORRECT_ANSWERS") ?: arrayListOf()


        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        val feedbackTextView = findViewById<TextView>(R.id.feedbackTextView)
        val reviewButton = findViewById<Button>(R.id.reviewButton)

        resultTextView.text = "You Got $score Out Of $total Correct"

        //Feedback based on performance
        val feedback = when {
            score == total -> "Great work! You are a BrainTeaser pro"
            score >= total/ 2 ->"Good Try , Keep Learning"
            else -> "Try Again Next Time"
        }
        feedbackTextView.text = feedback

        reviewButton.setOnClickListener {
            val message =correctAnswers.joinToString("\n. ", prefix = ".")


            AlertDialog.Builder(this)
                .setTitle("All Correct Answers")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}