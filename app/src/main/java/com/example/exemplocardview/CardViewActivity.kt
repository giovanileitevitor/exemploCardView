package com.example.exemplocardview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_card_question.*

class CardViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_question)

        initCardView(savedInstanceState)
        initListeners()
    }

    private fun initCardView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            expandcardview2.header = "Title or Header"
            expandcardview2.question = "Input your question here"
            expandcardview2.remarks = "Add your remarks here"
        }
    }

    private fun initListeners() {

        btn_startCard.setOnClickListener {
            expandcardview2.isCardOpen = true
            //0 -> empty ou reset
            //1 -> Like ativo
            //2 -> Unlike ativo
            expandcardview2.status = 0
            expandcardview2.header = "Title or Header"
            expandcardview2.question = "Input your question here"
            expandcardview2.remarks = "Add your remarks here"
        }

        btn_closeCard.setOnClickListener {
            expandcardview2.isCardOpen = false
        }

        btn_resetCard.setOnClickListener {

        }
    }
}
