package com.github.ininmm.common.util

import android.util.Log
import com.github.ininmm.common.BuildConfig

/**
 * 這是 [Log] 的 extension
 * Created by Michael Lien
 * on 2018/7/24.
 */
fun <T : Any> T.logV(msg: String, tag: String = this::class.java.simpleName, isDeBug: Boolean = BuildConfig.DEBUG) {
    if (isDeBug) Log.v(tag, msg)
}

fun <T : Any> T.logD(msg: String, tag: String = this::class.java.simpleName, isDeBug: Boolean = BuildConfig.DEBUG) {
    if (isDeBug) Log.d(tag, msg)
}

fun <T : Any> T.logI(msg: String, tag: String = this::class.java.simpleName, isDeBug: Boolean = BuildConfig.DEBUG) {
    if (isDeBug) Log.i(tag, msg)
}

fun <T : Any> T.logW(msg: String, tag: String = this::class.java.simpleName, isDeBug: Boolean = BuildConfig.DEBUG) {
    if (isDeBug) Log.w(tag, msg)
}

fun <T : Any> T.logE(msg: String, tag: String = this::class.java.simpleName, isDeBug: Boolean = BuildConfig.DEBUG) {
    if (isDeBug) Log.e(tag, msg)
}

fun <T : Any> T.logWTF(msg: String, tag: String = this::class.java.simpleName, isDeBug: Boolean = BuildConfig.DEBUG) {
    if (isDeBug) Log.wtf(tag, msg)
}
