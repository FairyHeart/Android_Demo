package com.lib.jetpack_databinding.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import com.lib.jetpack_databinding.R
import com.lib.jetpack_databinding.databinding.UiMyTextviewBinding

/**
 * 自定义UI
 *
 * @author: GuaZi.
 * @date  : 2020/12/4.
 */
class MyTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    LinearLayoutCompat(context, attrs, defStyleAttr) {

    val infoField = ObservableField<String>()

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DataBindingUtil.inflate<UiMyTextviewBinding>(
            inflater,
            R.layout.ui_my_textview,
            this,
            true
        )
        binding.setTv(this)

        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.MyTextView)
        val info = typeArray.getString(R.styleable.MyTextView_item_text_tip)
        infoField.set(info)
    }

    fun setInfoTv(info: String) {
        infoField.set(info)
    }
}