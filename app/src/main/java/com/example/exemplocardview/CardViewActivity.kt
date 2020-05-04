package com.example.exemplocardview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_card_question.*

class CardViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_question)

        initCardView()
        initListeners()
    }

    private fun initCardView(){
        expandcardview1.closeCard()
        expandcardview1.textHeader = "Informe o Titulo"
        expandcardview1.textQuestion = "Informe a pergunta"
        expandcardview1.textRemarks = "Informe as observaçoes"
    }

    private fun initListeners(){
        //Método para controle do card (aberto ou fechado)
        expandcardview1.onClickHeader { bool ->
            if (bool) {
                expandcardview1.closeCard()

            } else {
                expandcardview1.openCard()
            }
        }

        //Método para tratar o click do botao Unlike
        expandcardview1.onUnlikeClickButton{
            expandcardview1.setUnlikeIconActivated()
            expandcardview1.setLikeIconDeactivated()
            Toast.makeText(applicationContext, "Clicou em UnLike", Toast.LENGTH_SHORT).show()
        }

        //Método para tratar o click do botao Like
        expandcardview1.onLikeClickButton{
            expandcardview1.setLikeIconActivated()
            expandcardview1.setUnlikeIconDeactivated()
            Toast.makeText(applicationContext, "Clicou em Like", Toast.LENGTH_SHORT).show()
        }

        //Método para recuperar o texto de Remarks
        btn_printRemarks.setOnClickListener {
            if(expandcardview1.textRemarks.isNullOrEmpty()){
                Toast.makeText(applicationContext, "Remarks not added", Toast.LENGTH_SHORT).show()
            }else{
                txt_output.text = "${expandcardview1.textHeader + expandcardview1.textQuestion + expandcardview1.textRemarks}"
            }

            if(expandcardview1.isLikeSet || expandcardview1.isUnlikeSet){
                txt_output2.text = "${expandcardview1.isLikeSet.toString() + expandcardview1.isUnlikeSet.toString()}"
            }else{
                txt_output2.text = "${expandcardview1.isLikeSet.toString() + expandcardview1.isUnlikeSet.toString()}"
            }
        }

        btn_resetCard.setOnClickListener{
            expandcardview1.resetComponent()
        }

        btn_startCard.setOnClickListener{
            expandcardview1.closeCard()
            expandcardview1.textHeader = "Informe o Titulo"
            expandcardview1.textQuestion = "Informe a pergunta"
            expandcardview1.textRemarks = "Informe as observaçoes"
        }
    }

}
