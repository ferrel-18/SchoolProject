package com.example.schoolproject

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity() ,View.OnClickListener{
    private var mCurrentPosition:Int=1
    private var mQuestionsList:ArrayList<Questions>?=null
    private var mSelectedOptionPosition:Int=0
    private var mCorrectAnswer:Int =0
    private var mUsername: String? = null


    lateinit var progressBar: ProgressBar
    lateinit var tv_progress:TextView
    lateinit var tv_question:TextView
    lateinit var tv_option_one:TextView
    lateinit var tv_option_two:TextView
    lateinit var tv_option_three:TextView
    lateinit var tv_option_four:TextView
    lateinit var btn_submit:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUsername= intent.getStringExtra(Constants.USERNAME)

        mQuestionsList =Constants.getQuestions()

        setQusetions()

        tv_option_one.setOnClickListener { this }
        tv_option_two.setOnClickListener { this }
        tv_option_three.setOnClickListener { this }
        tv_option_four.setOnClickListener { this }
        btn_submit.setOnClickListener { this }



    }
    private fun setQusetions(){

         mCurrentPosition=1
        val question=mQuestionsList!![mCurrentPosition-1]

        defaultOptionsView()

        if (mCurrentPosition==mQuestionsList!!.size){
            btn_submit.text="FINISH"
        }else{
            btn_submit.text="SUBMIT"
        }

        progressBar=findViewById(R.id.progress_bar)
        tv_progress=findViewById(R.id.tv_progress)
        tv_question=findViewById(R.id.tv_question)
        tv_option_one=findViewById(R.id.tv_option_one)
        tv_option_two=findViewById(R.id.tv_option_two)
        tv_option_three=findViewById(R.id.tv_option_three)
        btn_submit=findViewById(R.id.btn_Submit)
        tv_option_four=findViewById(R.id.tv_option_four)

        progressBar.progress=mCurrentPosition
        tv_progress.text="$mCurrentPosition" + "/" + progressBar.max
        tv_question.text=question!!.question
        tv_option_one.text=question.optionOne
        tv_option_two.text=question.optionTwo
        tv_option_three.text=question.optionThree
        tv_option_four.text=question.optionFour

    }
    private  fun defaultOptionsView(){
        val options =ArrayList<TextView>()
        options.add(0,tv_option_one)
        options.add(1,tv_option_two)
        options.add(2,tv_option_three)
        options.add(3,tv_option_four)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface= Typeface.DEFAULT
            option.background =ContextCompat.getDrawable(
                this,R.drawable.default_option_border
            )

        }
    }
    override fun onClick(v:View){
        when(v?.id){
            R.id.tv_option_one -> {
                selectedOptionview(tv_option_one, 1)
            }
            R.id.tv_option_two -> {
                selectedOptionview(tv_option_two,2)
            }
            R.id.tv_option_three -> {
                selectedOptionview(tv_option_three,3)
            }
            R.id.tv_option_four -> {
                selectedOptionview(tv_option_four,4)
            }
            R.id.btn_Submit ->{
                if (mSelectedOptionPosition==0){
                    mCurrentPosition++

                    when{
                        mCurrentPosition<=mQuestionsList!!.size -> {
                            setQusetions()
                        }else ->{
                           val intent=Intent(this,ResultActivity::class.java)
                        intent.putExtra(Constants.USERNAME,mUsername)
                        startActivity(intent)
                        }
                    }
                }else{
                    val question =mQuestionsList?.get(mCurrentPosition-1)
                    if (question!!.correctAnswer !=mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition,R.drawable.wrong_option_border)
                    }else{
                        mCorrectAnswer++
                    }
                    answerView(question.correctAnswer,R.drawable.correct_option_border)

                    if (mCurrentPosition==mQuestionsList!!.size){
                        btn_submit.text ="FINISH"
                    }else{
                        btn_submit.text="GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition=0
                }

            }
        }
    }
    private  fun answerView(answer:Int,drawableView:Int){
        when(answer){
            1->{
                tv_option_one.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            2->{
                tv_option_two.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            3->{
                tv_option_three.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            4->{
                tv_option_four.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
        }
    }
    private fun selectedOptionview(tv:TextView,selectedOptionNumber:Int){
        defaultOptionsView()
        mSelectedOptionPosition=selectedOptionNumber

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background =ContextCompat.getDrawable(
            this,R.drawable.selector_option_border
        )


    }
}