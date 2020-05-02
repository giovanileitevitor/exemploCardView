package com.example.exemplocardview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_card_question_expand.*

class CardViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_question_expand)

        initCardView()
    }

    private fun initCardView(){
        expandcardview1.setTextHeader("Pneus")
        expandcardview1.setValuesBlock1("Pneus", "Os pneus de seu caminhao estão adequadamente calibrados?")
        //expandcardview1.setTextButton("Like")
        //expandcardview1.setTextLikeButton("Like")
        //expandcardview1.setTextUnlikeButton("UnLike")
        expandcardview1.onClickHeader { bool ->
            if (bool) {
                expandcardview1.closeCard()

            } else {
                expandcardview1.openCard()
            }
        }
        expandcardview1.startAccessibility()


        expandcardview1.onUnlikeClickButton{
            Toast.makeText(applicationContext, "Clicou em Like", Toast.LENGTH_SHORT).show()
        }

        expandcardview1.onLikeClickButton{
            Toast.makeText(applicationContext, "Clicou em Unlike", Toast.LENGTH_SHORT).show()
        }

    }
}
