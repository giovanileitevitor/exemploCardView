package com.example.exemplocardview.componenteCardView;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.exemplocardview.R;

import org.jetbrains.annotations.Nullable;

public class CardQuestion extends LinearLayout implements CardQuestionInterface {

    public static final int DURATION = 300;
    public static final int ROTATION_90 = 90;
    public static final int ROTATION_270 = 270;
    private boolean isCardOpen = true;
    private boolean isLikeIconSet = false;
    private boolean isUnlikeIconSet = false;

    private ConstraintLayout mLayoutHeader, mLayoutBodyBlock3;
    private LinearLayout mLayoutBodyBlocks, mLayoutBody, mLayoutBodyBlock1, mLayoutBodyBlock2;
    private ImageButton mUnlikeButton, mLikeButton;
    private ImageView mIconImageHeader, mArrowImageheader;
    private TextView mTextQuestion, mCounterTextRemarks, mTextHeader;
    private EditText mTextRemarks;

    private OnClickHeaderListener onHeaderClickListener;
    private OnClickLikeButtonListener onLikeButtonClickListener;
    private OnClickUnlikeButtonListener onUnlikeButtonClickListener;

    private String mTextBlock1, mValueBlock1, mTextBlock2, mValueBlock2, mTextBlock3, mValueBlock3, mTitleHeader;

    public CardQuestion(Context context) {
        super(context);
        this.init(context);
    }

    public CardQuestion(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context);
    }

    public CardQuestion(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(context);
    }

    public void onClickHeader(OnClickHeaderListener onHeaderClickListener) {
        this.onHeaderClickListener = onHeaderClickListener;
    }

    public void onUnlikeClickButton(OnClickUnlikeButtonListener onUnlikeButtonClickListener) {
        this.onUnlikeButtonClickListener = onUnlikeButtonClickListener;
    }

    public void onLikeClickButton(OnClickLikeButtonListener onLikeButtonClickListener) {
        this.onLikeButtonClickListener = onLikeButtonClickListener;
    }

    public String getTitleHeader() {
        return mTitleHeader;
    }

    public void setTitleHeader(String mTitleHeader) {
        this.mTitleHeader = mTitleHeader;
    }

    public interface OnClickHeaderListener {
        void onClick(boolean bool);
    }

    public interface OnClickLikeButtonListener {
        void onClick();
    }
    public interface OnClickUnlikeButtonListener {
        void onClick();
    }

    private void setValueBlock1(String mValueBlock1) {
        this.mValueBlock1 = mValueBlock1;
    }

    private void setValueBlock2(String mValueBlock2){
        this.mValueBlock2 = mValueBlock2;
    }

    private void setValueBlock3(String mValueBlock3){
        this.mValueBlock3 = mValueBlock3;
    }

    private void init(Context context) {
        View cardRoot = View.inflate(context, R.layout.card_question, this);

        //Header
        mLayoutHeader = cardRoot.findViewById(R.id.layout_header);
        mIconImageHeader = cardRoot.findViewById(R.id.icon_image_header);
        mArrowImageheader = cardRoot.findViewById(R.id.arrow_image_header);
        mTextHeader = cardRoot.findViewById(R.id.text_header);

        //Body
        mLayoutBody = cardRoot.findViewById(R.id.layout_body);
        mLayoutBodyBlocks = cardRoot.findViewById(R.id.layout_body_blocks);

        mLayoutBodyBlock1 = cardRoot.findViewById(R.id.layout_body_block1);
        mTextQuestion = cardRoot.findViewById(R.id.text_question);

        mLayoutBodyBlock2 = cardRoot.findViewById(R.id.layout_body_block2);
        mUnlikeButton = cardRoot.findViewById(R.id.unlike_button);
        mLikeButton = cardRoot.findViewById(R.id.like_button);

        mLayoutBodyBlock3 = cardRoot.findViewById(R.id.layout_body_block3);
        mTextRemarks = cardRoot.findViewById(R.id.text_remarks);
        mCounterTextRemarks = cardRoot.findViewById(R.id.text_counter);

        setClicks();

        updateTextCounter();
    }

    private void setClicks() {
        mLayoutHeader.setOnClickListener(getOnClickLayoutHeader());
        mLikeButton.setOnClickListener(getOnClickLikeButton());
        mUnlikeButton.setOnClickListener(getOnClickUnlikeButton());
    }

    private OnClickListener getOnClickLikeButton() {
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLikeButtonClickListener != null) {
                    onLikeButtonClickListener.onClick();
                }
            }
        };
    }

    private OnClickListener getOnClickUnlikeButton() {
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onUnlikeButtonClickListener != null){
                    onUnlikeButtonClickListener.onClick();
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

    private void openAnim() {
        ObjectAnimator.ofFloat(mArrowImageheader, View.ROTATION, ROTATION_270)
                .setDuration(DURATION).start();
    }

    private void closeAnim() {
        ObjectAnimator.ofFloat(mArrowImageheader, View.ROTATION, ROTATION_90)
                .setDuration(DURATION).start();
    }

    private void setValueOnBlock1(String value) {
        if (TextUtils.isEmpty(value)) {
            mTextQuestion.setVisibility(GONE);
        } else {
            setValueBlock1(value);
            mTextQuestion.setText(value);
        }
    }

    private void setValueOnBlock2(String value) {
        if (TextUtils.isEmpty(value)) {
            //TODO - Giovani - Disable visibility on components used on this block2
        } else {
            setValueBlock2(value);
            //TODO - Giovani - Set values on components used on this block2
        }
    }

    private void setValueOnBlock3(String value){
        if(TextUtils.isEmpty(value)){
            mTextRemarks.setVisibility(GONE);
        }else{
            setValueBlock3(value);
            mTextRemarks.setText(value);
        }
    }

    private void updateTextCounter(){
        mTextRemarks.addTextChangedListener(new CharacterWatcher() {
            @Override
            public void afterCharacterChanged(@Nullable Character character, @Nullable Integer count) {
                mCounterTextRemarks.setText(count.toString() + "/130");
            }
        });
    }

    @Override
    public void openCard() {
        isCardOpen = true;
        mLayoutHeader.setContentDescription("Recolher " + getTitleHeader());
        CardQuestionAnimation.expand(mLayoutBody);
        openAnim();
    }

    @Override
    public void closeCard() {
        isCardOpen = false;
        mLayoutHeader.setContentDescription("Expandir " + getTitleHeader());
        CardQuestionAnimation.collapse(mLayoutBody);
        closeAnim();
    }

    @Override
    public boolean isCardOpen() {
        return isCardOpen;
    }

    @Override
    public boolean isCardClose() {
        return isCardOpen;
    }

    @Override
    public void textHeader(@Nullable String text) {
        if (TextUtils.isEmpty(text)) {
            mTextHeader.setVisibility(GONE);
        } else {
            setTitleHeader(text);
            mTextHeader.setText(getTitleHeader());
        }
    }

    @Override
    public void textQuestion(@Nullable String text) {
        if (TextUtils.isEmpty(text)) {
            mLayoutBodyBlock1.setVisibility(GONE);
        } else {
            mLayoutBodyBlock1.setVisibility(VISIBLE);
            setValueOnBlock1(text);
        }
    }

    @Override
    public void textRemarks(@Nullable String text) {
        if (TextUtils.isEmpty(text)) {
            mLayoutBodyBlock3.setVisibility(GONE);
        } else {
            mLayoutBodyBlock3.setVisibility(VISIBLE);
            setValueOnBlock3(text);
        }
    }

    @Override
    public void setLikeIconActivated() {
        isLikeIconSet = true;
        mLikeButton.setImageResource(R.drawable.ic_thumbs_up_icon_2);
        mIconImageHeader.setImageResource(R.drawable.ic_thumbs_up_icon_2);
    }

    @Override
    public void setLikeIconDeactivated() {
        isLikeIconSet = false;
        mLikeButton.setImageResource(R.drawable.ic_thumbs_up_icon);
    }

    @Override
    public void setUnlikeIconActivated() {
        isUnlikeIconSet = true;
        mUnlikeButton.setImageResource(R.drawable.ic_thumbs_down_icon_2);
        mIconImageHeader.setImageResource(R.drawable.ic_thumbs_down_icon_2);
    }

    @Override
    public void setUnlikeIconDeactivated() {
        isUnlikeIconSet = false;
        mUnlikeButton.setImageResource(R.drawable.ic_thumbs_down_icon);
    }

    @Override
    public boolean isLikeSet() {
        return isLikeIconSet;
    }

    @Override
    public boolean isUnlikeSet() {
        return isUnlikeIconSet;
    }

    @Override
    public void resetComponent() {
        closeCard();
        mIconImageHeader.setImageResource(R.drawable.ic_thumbs_up_icon);
        mLikeButton.setImageResource(R.drawable.ic_thumbs_up_icon);
        mUnlikeButton.setImageResource(R.drawable.ic_thumbs_down_icon);
        mTextHeader.setText("");
        mTextQuestion.setText("");
        mTextRemarks.setText("");
    }
}