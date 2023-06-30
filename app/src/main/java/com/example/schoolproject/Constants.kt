package com.example.schoolproject

object Constants {
    const val USERNAME:String="username"
    fun getQuestions():ArrayList<Questions>{
        val questionList = ArrayList<Questions>()

        val que1=Questions(
            1,
            "How old are you?",
            "10-20",
            "21-40",
            "41-60",
            "80 and above",
            0
        )
        questionList.add(que1)

        val que2=Questions(
            2,
            "What is your gender?",
            "Male",
            "Female",
            "Other",
            "Prefer not to say",
            0
        )
        questionList.add(que2)

        val que3=Questions(
            3,
            "Where did you get information about our app?",
            "Friends/Family",
            "Social Media Platforms",
            "Through my own means",
            "Other recommendations",
            0
        )
        questionList.add(que3)

        return questionList
    }

    }



