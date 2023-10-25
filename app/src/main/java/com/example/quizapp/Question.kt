package com.example.quizapp

data class Question(
    val id: Int,
    val questionString: String,
    val image: Int,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswers: Int,
)
