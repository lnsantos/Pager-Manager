package com.lnsantos.pagermanager.manager.override

import android.util.Log
import androidx.fragment.app.Fragment
import com.lnsantos.pagermanager.manager.listeners.FragmentStateListener

open class BaseFragment : Fragment() {

    private var listenerDestroy : FragmentStateListener? = null

    fun onBootBaseFragment(listener : FragmentStateListener){
        this.listenerDestroy = listener
    }

    override fun onDestroy() {
        super.onDestroy()
        listenerDestroy?.onFragmentRemoved(this)
            ?: Log.i(this::class.java.simpleName, "FragmentStateListener is null! \nException in your face!!")
    }

}