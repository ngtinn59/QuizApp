package com.example.quizapp

object Constants {


    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        // 1
        val q1 = Question(
            1,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_argentina,
            "Argentina",
            "Australia",
            "Armenia",
            "Austria",
            1
        )
        questionsList.add(q1)


        val q2 = Question(
            2,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_brazil,
            "Argentina",
            "Brazil",
            "Australia",
            "Austria",
             2
        )
        questionsList.add(q2)



        return questionsList
    }
}