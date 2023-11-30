package com.dhxxn17.escape96app.ui.util

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import com.dhxxn17.escape96app.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputLayout

class TagInputView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    val chipGroup: ChipGroup

    init {
        LayoutInflater.from(context).inflate(R.layout.tags_input_layout, this, true)

        val inputLayout = findViewById<TextInputLayout>(R.id.i_input_v)
        val editText = inputLayout.editText!!
        chipGroup = findViewById(R.id.i_flex_box)
        editText.hint = "원하는 지역을 입력해주세요 (예: 강남, 홍대)"

        editText.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (editText.text.toString() == " "){
                    editText.text.clear()
                }
            } else {
                if (editText.text.isNullOrEmpty() && chipGroup.childCount > 0) {
                    editText.setText(" ")
                }
            }
        }

        editText.setOnKeyListener { _, _, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
//                onBackspacePressed, also edittext is empty
                if (chipGroup.childCount <= 0) return@setOnKeyListener false
                val lastChip = chipGroup.getChildAt(chipGroup.childCount - 1) as Chip
                editText.append(lastChip.text)
                chipGroup.removeView(lastChip)
            }
            false
        }

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {
                val text = editable.toString()
                if (text.isNotEmpty()) {
                    if (text.endsWith(",")) {
                        addNewChip(text.removeSuffix(","))
                        editable.clear()
                    }
                    if (text.endsWith(" ")) {
                        addNewChip(text.removeSuffix(" "))
                        editable.clear()
                    }
                }
            }
        })
    }

    fun addNewChip(text: String) {
        val newChip =
            LayoutInflater.from(context).inflate(R.layout.chip_item, chipGroup, false) as Chip
        newChip.id = ViewCompat.generateViewId()
        newChip.text = text
        newChip.setOnCloseIconClickListener {
            chipGroup.removeView(newChip)
        }
        chipGroup.addView(newChip)
    }
}