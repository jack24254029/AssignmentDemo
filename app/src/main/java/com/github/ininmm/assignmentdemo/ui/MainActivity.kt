package com.github.ininmm.assignmentdemo.ui

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleRegistry
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import androidx.core.widget.toast
import com.github.ininmm.assignmentdemo.contract.MainPageContract
import com.github.ininmm.assignmentdemo.PresenterFactory
import com.github.ininmm.assignmentdemo.R
import com.github.ininmm.assignmentdemo.adapter.WeatherAdapter
import com.github.ininmm.assignmentdemo.extension.dpToPx
import com.github.ininmm.assignmentdemo.presenter.MainPagePresenter
import com.github.ininmm.assignmentdemo.util.DialogUtils
import com.github.ininmm.assignmentdemo.util.DividerItemDecoration
import com.github.ininmm.database.entity.DailyWord
import com.github.ininmm.database.entity.Weather
import com.github.ininmm.database.entity.WeatherWeek
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainPageContract.View {

    private val lifecycleRegistry = LifecycleRegistry(this)

    private lateinit var weatherAdapter: WeatherAdapter

    private var dialog: AlertDialog? = null

    private lateinit var presenter: MainPagePresenter

    private var sampleList = mutableListOf("1", "2", "3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = PresenterFactory.getInstance().createMainPagePresenter(this)
        weatherAdapter = WeatherAdapter(mutableListOf())
        initView()
        toast("長按以刪除")
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    override fun clearTitle() {
        tvTitle.text = ""
    }

    override fun clearList() {
        weatherAdapter.refresh(mutableListOf())
    }

    override fun clearDailyWord() {
        tvDescription.text = ""
    }

    override fun showDailyWord(dailyWord: DailyWord) {
        tvDescription.text = dailyWord.word
    }

    override fun showDailyTitle(weather: Weather) {
        tvTitle.text = weather.title
    }

    override fun showWeatherList(weatherWeek: List<WeatherWeek>) {
        weatherAdapter.refresh(mutableListOf())
    }

    override fun showErrorMessage() {
        toast("Oops! Something Wrong")
    }

    override fun showDeleteItemMessage() {
        toast("Delete Item")
    }

    private fun initView() {
        // 客製化 CollapsingToolbar，讓長度超過的天氣標題可以完整顯示
        collapsingToolbar.apply {
            setCollapsedTitleTextAppearance(R.style.TextAppearance_MyApp_Title_Collapsed)
            setExpandedTitleTextAppearance(R.style.TextAppearance_MyApp_Title_Expanded)

            // 最重要的地方，這裡不能放 title
            title = ""
        }

        appbar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    // AppBarLayout 的偏移量
                    scrollRange = appBarLayout.totalScrollRange
                }

                // 上滑時顯示的 title ，由 collapsingToolbar.title 控制
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.title = applicationContext.getString(R.string.app_name)
                    isShow = true
                } else if (isShow) {
                    // 下滑時顯示的內容讓 CollapsingToolbarLayout 控制，collapsingToolbar.title 要 reset
                    // collapsingToolbar.title 必須加空白鍵不然不會作用
                    collapsingToolbar.title = " "
                    isShow = false
                }
            }
        })

        rvWeather.apply {
            setHasFixedSize(true)
            adapter = weatherAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(this@MainActivity.dpToPx(5f).toInt()))
        }

        weatherAdapter.deleteWeatherItem = { pair ->
            showDialog(title = getString(R.string.app_title_delete_data),
                    message = getString(R.string.app_message_delete_data),
                    confirmListener = DialogInterface.OnClickListener { dialog, which ->
//                        presenter.deleteWeatherWeek(WeatherWeek())
//                        sampleList.remove(pair)
//                        weatherAdapter.refresh(sampleList)
                    })
        }
    }

    private fun showDialog(title: String, message: String, confirmListener: DialogInterface.OnClickListener) {
        dialog?.dismiss()
        dialog = DialogUtils.createConfirmDialog(this,
                title = title,
                message = message,
                confirmListener = confirmListener,
                cancelListener = null)
        dialog?.show()
    }
}