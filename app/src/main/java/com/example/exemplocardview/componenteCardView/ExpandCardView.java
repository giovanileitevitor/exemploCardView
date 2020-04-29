package com.example.exemplocardview.componenteCardView;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.exemplocardview.R;


public class ExpandCardView extends LinearLayout implements ExpandCardViewInterface {

    public static final int DURATION = 300;
    public static final int ROTATION_90 = 90;
    public static final int ROTATION_270 = 270;
    private boolean isCardOpen = true;

    private ConstraintLayout mLayoutHeader;
    private ImageView mIconImageHeader, mArrowImageheader;
    private TextView mTextHeader;
    private LinearLayout mLayoutBody, mLayoutBodyBlock1, mLayoutBodyBlock2, mLayoutBodyBlocks;
    private TextView mPrimaryTextBody;
    private TextView mPrimaryValueBody;
    private TextView mButton;

    private String mTitleHeader, mTextBlock1, mValueBlock1, mTextBlock2, mValueBlock2, mNameTextButton;

    private OnClickHeaderListener onHeaderClickListener;
    private OnClickButtonListener onButtonClickListener;

    public ExpandCardView(Context context) {
        super(context);
        this.init(context);
    }

    public ExpandCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context);
    }

    public ExpandCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(context);
    }

    public void onClickHeader(OnClickHeaderListener onHeaderClickListener) {
        this.onHeaderClickListener = onHeaderClickListener;
    }

    public void onClickButton(OnClickButtonListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    public String getTitleHeader() {
        return mTitleHeader;
    }

    public void setTitleHeader(String mTitleHeader) {
        this.mTitleHeader = mTitleHeader;
    }

    public String getTextBlock1() {
        return mTextBlock1;
    }

    private void setTextBlock1(String mTextBlock1) {
        this.mTextBlock1 = mTextBlock1;
    }

    public String getValueBlock1() {
        return mValueBlock1;
    }

    private void setValueBlock1(String mValueBlock1) {
        this.mValueBlock1 = mValueBlock1;
    }

    public String getTextBlock2() {
        return mTextBlock2;
    }

    private void setTextBlock2(String mTextBlock2) {
        this.mTextBlock2 = mTextBlock2;
    }

    public String getValueBlock2() {
        return mValueBlock2;
    }

    private void setValueBlock2(String mValueBlock2) {
        this.mValueBlock2 = mValueBlock2;
    }

    public String getNameTextButton() {
        return mNameTextButton;
    }

    private void setNameTextButton(String mNameTextButton) {
        this.mNameTextButton = mNameTextButton;
    }

    private void init(Context context) {
        View cardRoot = View.inflate(context, R.layout.widget_card_view_expand, this);

        mLayoutHeader = cardRoot.findViewById(R.id.layout_header);
        mIconImageHeader = cardRoot.findViewById(R.id.icon_image_header);
        mArrowImageheader = cardRoot.findViewById(R.id.arrow_image_header);
        mTextHeader = cardRoot.findViewById(R.id.text_header);
        mLayoutBody = cardRoot.findViewById(R.id.layout_body);
        mLayoutBodyBlocks = cardRoot.findViewById(R.id.layout_body_blocks);
        mLayoutBodyBlock1 = cardRoot.findViewById(R.id.layout_body_block1);
        mLayoutBodyBlock2 = cardRoot.findViewById(R.id.layout_body_block2);
        mPrimaryTextBody = cardRoot.findViewById(R.id.primary_text_body);
        mPrimaryValueBody = cardRoot.findViewById(R.id.primary_value_body);
        mButton = cardRoot.findViewById(R.id.text_button);

        setClicks();
    }

    private void setClicks() {
        mLayoutHeader.setOnClickListener(getOnClickLayoutHeader());
        mButton.setOnClickListener(getOnClickButton());
    }

    private OnClickListener getOnClickButton() {
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onButtonClickListener != null) {
                    onButtonClickListener.onClick();
                }
            }
        };
    }

    private OnClickListener getOnClickLayoutHeader() {
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onHeaderClickListener != null) {
                    onHeaderClickListener.onClick(isCardOpen());
                }
            }
        };
    }

    @Override
    public void openCard() {
        isCardOpen = true;
        mLayoutHeader.setContentDescription("Recolher " + getTitleHeader());
        AnimationCardViewExpand.expand(mLayoutBody);
        openAnim();
    }

    private void openAnim() {
        ObjectAnimator.ofFloat(mArrowImageheader, View.ROTATION, ROTATION_270)
                .setDuration(DURATION).start();
    }

    @Override
    public void closeCard() {
        isCardOpen = false;
        mLayoutHeader.setContentDescription("Expandir " + getTitleHeader());
        AnimationCardViewExpand.collapse(mLayoutBody);
        closeAnim();
    }

    private void closeAnim() {
        ObjectAnimator.ofFloat(mArrowImageheader, View.ROTATION, ROTATION_90)
                .setDuration(DURATION).start();
    }

    @Override
    public boolean isCardOpen() {
        return isCardOpen;
    }

    @Override
    public void setTextHeader(String text) {
        if (TextUtils.isEmpty(text)) {
            mTextHeader.setVisibility(GONE);
        } else {
            setTitleHeader(text);
            mTextHeader.setText(getTitleHeader());
        }
    }

    @Override
    public void setTextButton(String text) {
        setNameTextButton(text);
        mButton.setText(text);
    }

    @Override
    public void setIconHeader(Drawable drawable) {
        mIconImageHeader.setImageDrawable(drawable);
    }

    @Override
    public void setValuesBlock1(String text, String value) {

        if (TextUtils.isEmpty(text) && TextUtils.isEmpty(value)) {
            mLayoutBodyBlock1.setVisibility(GONE);
        } else {
            mLayoutBodyBlock1.setVisibility(VISIBLE);
            setTextPrimaryBlock1(text);
            setValuePrimaryBlock1(value);
        }
    }

    @Override
    public void setValuesBlock2(String text, String value) {
        if (TextUtils.isEmpty(text) && TextUtils.isEmpty(value)) {
            mLayoutBodyBlock2.setVisibility(GONE);
        } else {
            mLayoutBodyBlock2.setVisibility(VISIBLE);
            setTextPrimaryBlock2(text);
            setValuePrimaryBlock2(value);
        }
    }

    @Override
    public void startAccessibility() {
        mLayoutHeader.setContentDescription("Recolher " + getTitleHeader());

        String accessibilityBlock1 = "";
        String accessibilityBlock2 = "";

        if (mLayoutBodyBlock1.getVisibility() == VISIBLE) {
            accessibilityBlock1 = getTextBlock1() + "." + getValueBlock1();
            mPrimaryTextBody.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO);
            mPrimaryValueBody.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO);
        }

        if (mLayoutBodyBlock2.getVisibility() == VISIBLE) {
            accessibilityBlock2 = "," + getTextBlock2() + "." + getValueBlock2();
        }

        String description1 = "";
        if (!TextUtils.isEmpty(accessibilityBlock1)) {
            description1 = accessibilityBlock1;
        }

        String description2 = "";
        if (!TextUtils.isEmpty(accessibilityBlock2)) {
            description2 = accessibilityBlock2;
        }

        mLayoutBodyBlocks.setContentDescription(description1 + description2);

        mButton.setContentDescription(getNameTextButton());
    }

    private void setTextPrimaryBlock1(String text) {
        if (TextUtils.isEmpty(text)) {
            mPrimaryTextBody.setVisibility(GONE);
        } else {
            setTextBlock1(text);
            mPrimaryTextBody.setText(text);
        }
    }

    private void setValuePrimaryBlock1(String value) {
        if (TextUtils.isEmpty(value)) {
            mPrimaryValueBody.setVisibility(GONE);
        } else {
            setValueBlock1(value);
            mPrimaryValueBody.setText(value);
        }
    }

    private void setTextPrimaryBlock2(String text) {
        if (TextUtils.isEmpty(text)) {
        } else {
            setTextBlock2(text);
        }
    }

    private void setValuePrimaryBlock2(String value) {
        if (TextUtils.isEmpty(value)) {
        } else {
            setValueBlock2(value);
        }
    }

    @Override
    public void setContentDescriptionValuePrimaryBlock2(String text) {

    }

    @Override
    public void setContentDescriptionValuePrimaryBlock1(String text) {
        mPrimaryValueBody.setContentDescription(text);
    }

    public interface OnClickHeaderListener {
        void onClick(boolean bool);
    }

    public interface OnClickButtonListener {
        void onClick();
    }
}