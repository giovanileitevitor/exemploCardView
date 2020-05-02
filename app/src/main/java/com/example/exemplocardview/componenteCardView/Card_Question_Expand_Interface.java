package com.example.exemplocardview.componenteCardView;

import android.graphics.drawable.Drawable;

interface Card_Question_Expand_Interface {

    void openCard();

    void closeCard();

    boolean isCardOpen();

    void setTextHeader(String text);

    void setTextButton(String text);

    void setIconHeader(Drawable drawable);

    void setValuesBlock1(String text, String value);

    void startAccessibility();

    void setContentDescriptionValuePrimaryBlock1(String text);

}
