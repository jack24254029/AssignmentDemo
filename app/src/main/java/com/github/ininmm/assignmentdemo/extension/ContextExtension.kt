package com.github.ininmm.assignmentdemo.extension

import android.content.Context
import android.util.TypedValue

/**
 * Created by Michael Lien
 * on 2018/7/28.
 */
/**
 * [dp] 轉成 px
 * @return px
 */
fun Context.dpToPx(dp: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
/**
 * [sp] 轉成 px
 * @return px
 */
fun Context.spToPx(sp: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, resources.displayMetrics)