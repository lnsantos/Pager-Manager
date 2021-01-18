package com.lnsantos.pagermanager.manager.listeners

import com.lnsantos.pagermanager.manager.override.BaseFragment

interface FragmentStateListener {
    fun onFragmentRemoved(fragment: BaseFragment)
}