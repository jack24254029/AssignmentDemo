package com.github.ininmm.arch.base

/**
 * Created by Michael Lien
 * on 2018/7/29.
 */
interface BaseView<T> {

    /**
     * 如果 Fragment 之類要取得 Presenter 就可以用此方法
     */
    fun setPresenter(presenter: T)
}