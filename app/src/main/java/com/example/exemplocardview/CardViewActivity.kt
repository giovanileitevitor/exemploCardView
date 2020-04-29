package com.example.exemplocardview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.exemplocardview.componenteCardView.ExpandCardView
import kotlinx.android.synthetic.main.activity_cardview_expand.*

class CardViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cardview_expand)

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

        expandcardview1.onClickButton {
            Toast.makeText(applicationContext, "TESTE", Toast.LENGTH_LONG).show()
        }

//        expandcardview1.onClickLikeButton{
//            Toast.makeText(applicationContext, "Clicou em Like", Toast.LENGTH_SHORT).show()
//        }
//
//        expandcardview1.onClickUnlikeButton{
//            Toast.makeText(applicationContext, "Clicou em Unlike", Toast.LENGTH_SHORT).show()
//        }

    }
}
