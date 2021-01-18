package com.lnsantos.pagermanager.manager

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.lnsantos.pagermanager.manager.enums.PageId
import com.lnsantos.pagermanager.manager.exceptions.NavigationException
import com.lnsantos.pagermanager.manager.listeners.FragmentStateListener
import com.lnsantos.pagermanager.manager.models.TransitionPageConfig
import com.lnsantos.pagermanager.manager.override.BaseFragment

object PagerManager : FragmentStateListener {

    var currentPageId : PageId? = PageId.HOME
    var activity : AppCompatActivity? = null
    fun getInstance() : PagerManager = this

    /**
     * Change current fragment of container register
     * */
    fun goToPage(nextPageId: PageId?){
        nextPageId?.let {
            try {

              currentPageId?.getNextPage(nextPageId)?.let {
                  currentPageId = nextPageId
                  makeTransition(nextPageId, it)
              }


            } catch (exception : NavigationException){
                Log.i("GO_TO_PAGE", exception.message.toString())
            }

        }
    }

    /**
     * Remove fragment enum
     * */
    override fun onFragmentRemoved(fragment: BaseFragment) {
        PageId.getByFragment(fragment)?.let {
            it.fragment = null
        }
    }

    private fun makeTransition(nextPageId: PageId, config : TransitionPageConfig){
        activity?.supportFragmentManager?.beginTransaction()?.let { transaction ->

            setCustomAnimation(config, transaction)

            val transitionPageConfig = config.getFragment()
                transitionPageConfig.onBootBaseFragment(this)

            val container = config.nextPageId.containerId
            val nextPage = config.nextPageId.fragment

            if (config.removeCurrent){
                if (config.replaceNext){
                    transaction.remove(currentPageId!!.fragment!!)

                    if (nextPage != null) {
                        transaction.replace(container, nextPage, nextPageId.name)
                    }

                }
            } else {
                if (nextPage != null) {
                    transaction.replace(container, nextPage, nextPageId.name)
                }
            }
            transaction.commit()
        }
    }

    /**
     * Apply animation between fragment
     * */
    private fun setCustomAnimation(config : TransitionPageConfig?, transaction: FragmentTransaction){

        config?.animation?.let {
            val startAnimation = it.inTransition
            val exitAnimation = it.outTransition

            if (startAnimation > 0 && exitAnimation > 0){
                transaction.setCustomAnimations(startAnimation, exitAnimation)
            }
        }

    }

}