package com.example.exemplocardview.componenteCardView

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.Resources
import android.os.Parcel
import android.os.Parcelable
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.exemplocardview.R

class CardQuestion : LinearLayout {

    private var mLayoutHeader: ConstraintLayout? = null
    private var mIconImageHeader: ImageView? = null
    private var mArrowImageheader: ImageView? = null
    private var mLayoutBody: LinearLayout? = null
    private var mLikeButton: ImageButton? = null
    private var mUnlikeButton: ImageButton? = null
    private var mTextHeader: TextView? = null
    private var mTextQuestion: TextView? = null
    private var mCounterTextRemarks: TextView? = null
    private var mTextRemarks: EditText? = null

    var isCardOpen: Boolean = true
        set(value) {
            field = value
            openCloseCard(field)
        }

    var status: Int = 0
        set(value) {
            field = value
            configStatus(field)
        }

    var header: String = ""
        set(value) {
            field = value
            mTextHeader?.text = field
        }
    var question: String = ""
        set(value) {
            field = value
            mTextQuestion?.text = field

            if (TextUtils.isEmpty(field.trim())) {
                mTextQuestion?.visibility = View.GONE
            } else {
                mTextQuestion?.visibility = View.VISIBLE
            }
        }

    var remarks: String = ""
        set(value) {
            field = value

            mTextRemarks?.let {
                it.removeTextChangedListener(onRemarksChange)
                it.setText(field)
                it.setSelection(field.length)
                it.addTextChangedListener(onRemarksChange)
                updateCounter(field)
            }
        }

    private fun configStatus(status: Int) {
        when (status) {
            0 -> {
                mIconImageHeader?.setImageResource(R.drawable.ic_thumbs_up_icon)
                mLikeButton?.setImageResource(R.drawable.ic_thumbs_up_icon)
                mUnlikeButton?.setImageResource(R.drawable.ic_thumbs_down_icon)
            }
            1 -> {
                mLikeButton?.setImageResource(R.drawable.ic_thumbs_up_icon_2)
                mUnlikeButton?.setImageResource(R.drawable.ic_thumbs_down_icon)
                mIconImageHeader?.setImageResource(R.drawable.ic_thumbs_up_icon_2)
            }
            2 -> {
                mLikeButton?.setImageResource(R.drawable.ic_thumbs_up_icon)
                mUnlikeButton?.setImageResource(R.drawable.ic_thumbs_down_icon_2)
                mIconImageHeader?.setImageResource(R.drawable.ic_thumbs_down_icon_2)
            }
        }
    }

    private fun openCloseCard(status: Boolean) {
        mLayoutBody?.animateVisibility(status)

        if (status) {
            openAnim()
        } else {
            closeAnim()
        }
    }

    private fun openAnim() {
        ObjectAnimator.ofFloat(
            mArrowImageheader,
            View.ROTATION,
            ROTATION_270.toFloat()
        )
            .setDuration(DURATION.toLong()).start()
    }

    private fun closeAnim() {
        ObjectAnimator.ofFloat(
            mArrowImageheader,
            View.ROTATION,
            ROTATION_90.toFloat()
        )
            .setDuration(DURATION.toLong()).start()
    }

    private fun updateCounter(text: String) {
        val res: Resources = resources
        var counter= res.getString(R.string.card_remarks_counter_max, text.length.toString())
        mCounterTextRemarks?.text = counter
    }

    constructor(context: Context?) : super(context) {
        initialize(null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initialize(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initialize(attrs)
    }

    private fun initialize(attrs: AttributeSet?) {

        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val cardRoot = inflater.inflate(R.layout.card_question, this)

        //Header
        mLayoutHeader = cardRoot.findViewById(R.id.layout_header)
        mIconImageHeader = cardRoot.findViewById(R.id.icon_image_header)
        mArrowImageheader = cardRoot.findViewById(R.id.arrow_image_header)

        //Body
        mLayoutBody = cardRoot.findViewById(R.id.layout_body)


        mTextHeader = cardRoot.findViewById(R.id.text_header)
        mTextQuestion = cardRoot.findViewById(R.id.text_question)
        mTextRemarks = cardRoot.findViewById(R.id.text_remarks)
        mCounterTextRemarks = cardRoot.findViewById(R.id.text_counter)

        //Buttons
        mUnlikeButton = cardRoot.findViewById(R.id.unlike_button)
        mLikeButton = cardRoot.findViewById(R.id.like_button)

        setClicks()

        attrs?.let {
            val typedArray =
                context.obtainStyledAttributes(it, R.styleable.CardQuestion, 0, 0)

            isCardOpen = typedArray.getBoolean(R.styleable.CardQuestion_isCardOpen, true)
            status = typedArray.getInt(R.styleable.CardQuestion_status, 0)
            header = typedArray.getString(R.styleable.CardQuestion_header) ?: ""
            question = typedArray.getString(R.styleable.CardQuestion_question) ?: ""
            remarks = typedArray.getString(R.styleable.CardQuestion_remarks) ?: ""
        }
    }

    private fun setClicks() {
        mLayoutHeader?.setOnClickListener {
            isCardOpen = !isCardOpen
        }

        mLikeButton?.setOnClickListener {
            status = 1
        }

        mUnlikeButton?.setOnClickListener {
            status = 2
        }

        mTextRemarks?.addTextChangedListener(onRemarksChange)
    }

    private val onRemarksChange = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            remarks = s.toString()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // Sem Uso
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // Sem Uso
        }

    }

    fun resetComponent() {
        isCardOpen = false
        status = 0
        header = ""
        question = ""
        remarks = ""
    }

    companion object {
        const val DURATION = 5
        const val ROTATION_90 = 90
        const val ROTATION_270 = 270
    }

    override fun dispatchSaveInstanceState(container: SparseArray<Parcelable>) {
        super.dispatchFreezeSelfOnly(container)
    }

    override fun dispatchRestoreInstanceState(
        container: SparseArray<Parcelable>
    ) {
        super.dispatchThawSelfOnly(container)
    }

    override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState()
        val ss = SavedState(superState!!)
        ss.isCardOpen = isCardOpen
        ss.status = status
        ss.header = header
        ss.question = question
        ss.remarks = remarks
        return ss
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        val ss = state as SavedState
        super.onRestoreInstanceState(ss.superState)
        //
        isCardOpen = ss.isCardOpen
        status = ss.status
        header = ss.header
        question = ss.question
        remarks = ss.remarks
    }

    /**
     * Saved State inner static class
     */
    private class SavedState : View.BaseSavedState {
        var isCardOpen: Boolean = true
        var status: Int = 0
        var header: String = ""
        var question: String = ""
        var remarks: String = ""

        constructor(superState: Parcelable) : super(superState)

        private constructor(source: Parcel) : super(source) {
            isCardOpen = source.readInt() != 0
            status = source.readInt()
            header = source.readString()!!
            question = source.readString()!!
            remarks = source.readString()!!
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)

            out.writeInt(if (isCardOpen) 1 else 0)
            out.writeInt(status)
            out.writeString(header)
            out.writeString(question)
            out.writeString(remarks)
        }

        companion object CREATOR : Parcelable.Creator<SavedState> {

            override fun createFromParcel(parcel: Parcel): SavedState {
                return SavedState(parcel)
            }

            override fun newArray(size: Int): Array<SavedState?> {
                return arrayOfNulls(size)
            }

        }

        override fun describeContents(): Int {
            return 0
        }
    }
}