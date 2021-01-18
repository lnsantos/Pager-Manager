package com.lnsantos.pagermanager.manager.enums

import androidx.fragment.app.Fragment
import com.lnsantos.pagermanager.R
import com.lnsantos.pagermanager.manager.exceptions.NavigationException
import com.lnsantos.pagermanager.manager.models.TransitionPageConfig
import com.lnsantos.pagermanager.manager.override.BaseFragment
import com.lnsantos.pagermanager.pages.HomeFragment
import com.lnsantos.pagermanager.pages.SplashFragment

enum class PageId(val containerId: Int) {
    SPLASH(R.id.fragment_container_1) {
        override fun getFragmentInstance(): BaseFragment = SplashFragment()

        override fun getNextPage(page: PageId): TransitionPageConfig {
            return when(page){

                HOME -> {
                   TransitionPageConfig(nextPageId = page, animation = TransitionPageAnimation.SLIDE_RIGHT_TO_LEFT)
                }

                else -> throw NavigationException("You can't navigate from " + this.name + " to " + page);
            }
        }

    },

    HOME(R.id.fragment_container_1) {
        override fun getFragmentInstance(): BaseFragment = HomeFragment()

        override fun getNextPage(page: PageId): TransitionPageConfig {
            return when(page){
                SPLASH -> {
                    TransitionPageConfig(nextPageId = page, animation = TransitionPageAnimation.SLIDE_RIGHT_TO_LEFT)
                }

                else -> throw NavigationException("You can't navigate from " + this.name + " to " + page);
            }
        }
    };

    var fragment : BaseFragment? = null
        get() {
        return field ?: run {
            fragment = getFragmentInstance()
            fragment
        }
    }

    abstract fun getFragmentInstance() : BaseFragment

    @Throws(NavigationException::class)
    abstract fun getNextPage(page: PageId) : TransitionPageConfig

    companion object{
        fun getByFragment(search : Fragment) : PageId?{
            var findFragment : PageId? = null
            values().forEach { pageId ->
                if (pageId.fragment == search){
                    findFragment = pageId
                    return@forEach
                }
            }

            return findFragment
        }
    }

}