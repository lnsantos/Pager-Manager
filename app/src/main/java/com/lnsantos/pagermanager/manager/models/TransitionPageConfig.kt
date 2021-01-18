package com.lnsantos.pagermanager.manager.models

import com.lnsantos.pagermanager.manager.enums.PageId
import com.lnsantos.pagermanager.manager.enums.TransitionPageAnimation
import com.lnsantos.pagermanager.manager.override.BaseFragment

data class TransitionPageConfig(
    var nextPageId : PageId,
    var removeCurrent : Boolean = false,
    var replaceNext : Boolean = false,
    var animation : TransitionPageAnimation
){

    constructor(nextPageId : PageId, animation : TransitionPageAnimation) : this(nextPageId, false, false, animation){
        this.nextPageId = nextPageId
        this.animation = animation
    }

    fun getFragment() : BaseFragment = nextPageId.fragment!!
}