package com.example.exemplocardview.componenteCardView

internal interface CardQuestionInterface {
    fun openCard()
    fun closeCard()
    fun isCardOpen(): Boolean
    fun isCardClose(): Boolean
    fun textHeader(text : String?)
    fun textQuestion(text: String?)
    fun textRemarks(text: String?)
    fun setLikeIconActivated()
    fun setLikeIconDeactivated()
    fun setUnlikeIconActivated()
    fun setUnlikeIconDeactivated()
    fun isLikeSet(): Boolean
    fun isUnlikeSet(): Boolean
    fun resetComponent()
}