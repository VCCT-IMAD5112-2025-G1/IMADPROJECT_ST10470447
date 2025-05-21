package za.co.varsitycollege.imadproject2_st10470447


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


//private var Nothing?.text: Any
//    get() {set(value) {}}


class SecondActivity : AppCompatActivity() {


    // List of questions with the correct answer true/false
    private val questions = listOf(
        Question( "South Africa is still in apartheid",false),
        Question("Cyril Ramaphosa is the current president of south africa",true),
        Question("The cradle of humankind is in south africa",true),
        Question("South Africa entered into democracy in 1994",true),
        Question("The capital of south africa is durban",false)
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
    private lateinit var trueButton:Button
    private lateinit var falseButton:Button
    private lateinit var nextPageButton: Button
    private lateinit var questionCounter: TextView





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge() // remove if not used
        setContentView(R.layout.activity_second)

        supportActionBar?.title = "Second Page"


        // initialize views properly
        questionTextView = findViewById<TextView>(R.id.textViewBox)
        trueButton = findViewById<Button>(R.id.trueButton)
        falseButton = findViewById<Button>(R.id.falseButton)
        nextPageButton = findViewById<Button>(R.id.nextPageButton)
        answerTextView = findViewById<TextView>(R.id.answerTextView)
        questionCounter = findViewById<TextView>(R.id.questionCounter)


        //display the first question
        displayquestion(currentQuestionIndex)

        //Button logic
        trueButton.setOnClickListener {
            checkAnswer(true) }

        falseButton.setOnClickListener {
            checkAnswer(false) }

        //Next Question
        nextPageButton.setOnClickListener {
            if (currentQuestionIndex < totalQuestions - 1) {
                currentQuestionIndex++
                displayquestion(currentQuestionIndex)
            } else {
                Toast.makeText(this, "Quiz Finished", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("SCORE", score) //send correct score
                intent.putExtra("TOTAL", totalQuestions) //should be 5
                intent.putStringArrayListExtra(
                    "CORRECT_ANSWERS",
                    ArrayList(allCorrectAnswers)
                ) //All Answers List
                startActivity(intent)
                finish() //closes quiz screen

            }
        }

        }
        @SuppressLint("SetTextI18n")
        fun displayquestion(index: Int) {
            val currentQuestion = questions[index]
            questionTextView.text = currentQuestion.text
            questionCounter.text ="${index + 1} / $totalQuestions"
            answerTextView.text = "" // clear previous answer result
        }

        @SuppressLint("SetTextI18n")
         fun checkAnswer(userAnswer: Boolean) {
            val correctAnswer = questions[currentQuestionIndex].answer
            if (userAnswer == correctAnswer) {
                score++ // Count all correct answers
                Toast.makeText(this,"Score: $score", Toast.LENGTH_SHORT).show()
                correctAnswerList.add(questions[currentQuestionIndex].text) // add this
                Toast.makeText(this,"Correct!",Toast.LENGTH_SHORT).show()
                answerTextView.text ="Correct!"
            } else {
                Toast.makeText(this,"Incorrect!",Toast.LENGTH_SHORT).show()
                answerTextView.text = "Incorrect!"
            }
        }
    }


