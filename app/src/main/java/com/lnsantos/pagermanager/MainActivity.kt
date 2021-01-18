package com.lnsantos.pagermanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import com.lnsantos.pagermanager.manager.PagerManager
import com.lnsantos.pagermanager.manager.enums.PageId

class MainActivity : AppCompatActivity() {

    companion object{
        const val LAST_PAGE_KEY : String = "LAST_PAGER"
    }
    private val pagerManager = PagerManager.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setupPagerManager()
        setupLastPager(savedInstanceState)

    }

    private fun setupLastPager(savedInstanceState: Bundle?) {
        var nextPageId = PageId.SPLASH
        savedInstanceState?.let { bundle ->
            val lastPage = bundle.getString(LAST_PAGE_KEY)
            if (!lastPage.isNullOrEmpty()){
                nextPageId = PageId.valueOf(lastPage)
            }
        }

        pagerManager.goToPage(nextPageId)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putString(LAST_PAGE_KEY, pagerManager.currentPageId?.name ?: PageId.SPLASH.name)
        super.onSaveInstanceState(outState, outPersistentState)
    }

    private fun setupPagerManager() {
        pagerManager.activity = this
    }

    override fun onDestroy() {
        pagerManager.activity = null
        super.onDestroy()
    }
}