package com.example.exemplocardview.componenteCardView;

interface CardQuestionInterface {

    void openCard();

    void closeCard();

    boolean isCardOpen();

    boolean isCardClose();

    void setTextHeader(String text_title_header);

    String getTextHeader();

    void setTextQuestion(String text_question);

    String getTextQuestion();

    void setTextRemarks(String text_remarks);

    String getTextRemarks();

    void setLikeIconActivated();

    void setLikeIconDeactivated();

    void setUnlikeIconActivated();

    void setUnlikeIconDeactivated();

    boolean isLikeSet();

    boolean isUnlikeSet();

    void resetComponent();
}