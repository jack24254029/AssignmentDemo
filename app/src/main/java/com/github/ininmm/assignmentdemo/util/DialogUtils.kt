package com.github.ininmm.assignmentdemo.util

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog


/**
 * Created by Michael Lien on 2018/7/25.
 */
object DialogUtils {

    /**
     * 產生一般說明訊息框
     *
     * @param context context
     * @param title 標題
     * @param message 內容
     * @param confirmListener confirm listener
     * @param cancelListener cancel listener
     * @return
     */
    fun createConfirmDialog(context: Context, title: String?,
                            message: String,
                            confirmListener: DialogInterface.OnClickListener?,
                            cancelListener: DialogInterface.OnClickListener?): AlertDialog {
        return createConfirmDialog(context, title, message, context.getString(android.R.string.ok),
                context.getString(android.R.string.cancel), confirmListener, cancelListener)
    }

    /**
     * 基本的訊息框
     * @param context context
     * @param title 標題
     * @param message 內容
     * @param cancelText 確認文字
     * @param cancelText 取消文字
     * @param confirmListener confirm listener
     * @param cancelListener cancel listener
     * @return
     */
    private fun createConfirmDialog(context: Context, title: String?,
                            message: String,
                            confirmText: String,
                            cancelText: String,
                            confirmListener: DialogInterface.OnClickListener?,
                            cancelListener: DialogInterface.OnClickListener?): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(confirmText, confirmListener)
                .setNegativeButton(cancelText, cancelListener)
        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        return dialog
    }
}