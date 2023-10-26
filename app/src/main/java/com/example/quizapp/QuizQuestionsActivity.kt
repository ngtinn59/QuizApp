package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

import android.widget.TextView





class QuizQuestionsActivity : AppCompatActivity() {

    private var currentPosition:Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var correctAnswers:  Int = 0
    private var progressBar: ProgressBar? = null
    private var tvProgressBar: TextView? = null
    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null
    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null
    private var btnSubmit: Button? = null
    private var selectedOption:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        progressBar = findViewById(R.id.progressBar)!!
        tvProgressBar = findViewById(R.id.tvProgress)
        tvQuestion = findViewById(R.id.tvQuestion)
        ivImage = findViewById(R.id.ivImage)
        tvOptionOne = findViewById(R.id.optionOne)
        tvOptionTwo = findViewById(R.id.optionTwo)
        tvOptionThree = findViewById(R.id.optionThree)
        tvOptionFour = findViewById(R.id.optionFour)
        btnSubmit = findViewById(R.id.btnSubmit)
        mQuestionsList = Constants.getQuestions()


        progressBar!!.max = mQuestionsList?.size!!
        setQuestion()

        tvOptionOne?.setOnClickListener {
            setSelectedOption(it, 1)
        }

        tvOptionTwo?.setOnClickListener {
            setSelectedOption(it, 2)
        }

        tvOptionThree?.setOnClickListener {
            setSelectedOption(it, 3)
        }

        tvOptionFour?.setOnClickListener {
            setSelectedOption(it, 4)
        }

        btnSubmit?.setOnClickListener {
            onSubmit()
        }
    }

    private fun setQuestion() {
        defaultOptionView()
        progressBar?.progress = currentPosition
        var question:Question = mQuestionsList!![currentPosition-1]
        tvProgressBar?.text = "$currentPosition/${progressBar?.max}"

        tvQuestion?.text = question.questionString
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour
        ivImage?.setImageResource(question.image)

        if (currentPosition == mQuestionsList!!.size){
            btnSubmit?.text = "FINISH"
        }
        else{
            btnSubmit?.text = "SUBMIT"
        }

    }

    private fun defaultOptionView() {
        val options = ArrayList<TextView>()
            tvOptionOne?.let { options.add(it) }
            tvOptionTwo?.let { options.add(it) }
            tvOptionThree?.let { options.add(it) }
            tvOptionFour?.let { options.add(it) }


        for (option in options){
            option.typeface = Typeface.DEFAULT
            option.setBackgroundResource(R.drawable.tv_border_default)
            option.setTextColor(Color.parseColor("#7a8089"))

        }
    }

    private fun onSubmit() {
        if (selectedOption==0){
            currentPosition++
            if (currentPosition<= mQuestionsList!!.size){
                setQuestion()
            }
            else{
                val username = intent.getStringExtra("username");
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("username", username)
                intent.putExtra("correctAnswers", correctAnswers)
                startActivity(intent)
            }
        }
        else{
            val question = mQuestionsList?.get(currentPosition-1)
            if(question?.correctAnswers!=selectedOption){
                answerView(selectedOption, R.drawable.wrong_option_border_bg)
            }
            else{
                correctAnswers++;

            }


            answerView(question!!.correctAnswers, R.drawable.correct_option_border_bg)

            if(currentPosition==mQuestionsList!!.size){
                btnSubmit?.text = "FINISH"
            }
            else{
                btnSubmit?.text = "GO TO THE NEXT QUESTION"
            }
            selectedOption = 0
        }
    }



    private fun setSelectedOption(view: View, num: Int){
        defaultOptionView()
        (view as TextView).setBackgroundColor(Color.YELLOW)
        selectedOption = num
    }
    private fun answerView(answer: Int, drawableView: Int ) {
        when(answer) {
            1 -> {
                tvOptionOne?.background = ContextCompat.getDrawable(this,drawableView)
            }
            2 -> {
                tvOptionTwo?.background = ContextCompat.getDrawable(this,drawableView)
            }
            3 -> {
                tvOptionThree?.background = ContextCompat.getDrawable(this,drawableView)
            }
            4 -> {
                tvOptionFour?.background = ContextCompat.getDrawable(this,drawableView)
            }
        }
    }
}