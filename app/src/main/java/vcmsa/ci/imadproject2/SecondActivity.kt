package za.co.varsitycollege.imadproject2_st10470447


import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import vcmsa.ci.imadproject2.R


class SecondActivity : AppCompatActivity() {
/

    // List of questions with the correct answer true/false
    private val questions = listOf(
        Question( " south africa is still in apartheid" false),
        Question("cyril ramaphosa is the current president of south africa"true),
        Question("the cradle of humankind is in south africa"true),
        Question("south africa entered into democracy in 1994"true),
        Question("the capital of south africa is durban"false)
    )
    private val allCorrectAnswers: List<String> = questions.filter { it.answer }.map { it.text }
    private val correctAnswerList = mutableListOf<String>()

    //Data class for a question
    data class Question(val text:String,val answer: Boolean)


    private var currentQuestionIndex = 0
    private val totalQuestions = questions.size

    private var score = 0

    private lateinit var questionTextView: TextView
    private lateinit var answerTextView: TextView
    private lateinit var truebutton:Button
    private lateinit var falsebutton:Button
    private lateinit var nextPageButton: Button
    private lateinit var questionCounter: TextView





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge() // remove if not used
        setContentView(R.layout.activity_second)

        supportActionBar?.title = "Second Page"


        // initialize views properly
        questionTextView = findViewById<TextView>(R.id.textViewBox)
        truebutton = findViewById<Button>(R.id.truebutton)
        falsebutton = findViewById<Button>(R.id.falsebutton)
        nextPageButton= findViewById<Button>(R.id.nextPageButton)
        answerTextView = findViewById<TextView>(R.id.answerTextView)
        questionCounter = findViewById<TextView>(R.id.questionCounter)


        //display the first question
        displayquestion(currentQuestionIndex)

        //Button logic
        truebutton.setOnClickListener {
            checkAnswer(true) }

        //Next Question
        nextPageButton.setOnClickListener {
            if (currentQuestionIndex < totalQuestions - 1) {
                currentQuestionIndex++
                displayquestion(currentQuestionIndex)
            } else {
                Toast.makeText(this,"Quiz Finished", Toast.LENGTH_SHORT).show()
                val intent = intent(this, ResultActivity::class.java)
                intent.putExtra("SCORE",score)//send correcr score
                intent.putExtra("TOTAL",totalQuestions) //should be 5
                intent.putStringArrayListExtra("CORRECT_ANSWERS",ArrayList(allCorrectAnswers)) //All Answers List
                startActivity(intent)
                finish() //closes quiz screen
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets -> val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

@SuppressLint("SetTextI18n")
private fun displayquestion(index: Int) {
    val currentQuestion = questions[index]
    questionText.text = currentQuestion.text
    questionCounter.text ="${index + 1} / $totalQuestions"
    answerTextView.text = "" // clear previous answer result
}

@SuppressLint("SetTextI18n")
private fun checkAnswer(userAnswer: Boolean) {
    val correctAnswer = questions[currentquestionIndex].answer
    if (userAnswer == correctAnswer) {
        score++ // Count all correct answers
        Toast.makeText(this,"Score: $score", Toast.LENGTH_SHORT).show()
        correctAnswerList.add(questions[curentQuestionIndex].text) // add this
        Toast.makeText(this,"Correct!",Toast.LENGTH_SHORT).show()
        answerTextView.text ="Correct!"
    } else {
        Toast.makeText(this,"Incorrect!",Toast.LENGTH_SHORT).show()
        answerTextView.text = "Incorrect!"
    }
}