package com.lnsantos.pagermanager.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lnsantos.pagermanager.R
import com.lnsantos.pagermanager.manager.PagerManager
import com.lnsantos.pagermanager.manager.enums.PageId
import com.lnsantos.pagermanager.manager.override.BaseFragment
import kotlinx.android.synthetic.main.splash_screen.*

class HomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            PagerManager.goToPage(PageId.SPLASH)
        }
    }

}