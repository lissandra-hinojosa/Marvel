package com.example.apirequest.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apirequest.adapters.CharacterAdapter
import com.example.apirequest.models.character.Character
import android.widget.AbsListView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager


abstract class OnScrollListener( val layoutManager: GridLayoutManager): RecyclerView.OnScrollListener() {

    private var currentItems = 0
    private var totalItems = 0
    private var scrollOutItems = 0
    private var isScrolling = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        currentItems = layoutManager.childCount
        totalItems = layoutManager.itemCount
        scrollOutItems = layoutManager.findFirstVisibleItemPosition()
        if(isScrolling && (currentItems + scrollOutItems == totalItems)){
            loadElements()
            isScrolling = false
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
            isScrolling = true
        }
    }

    abstract fun loadElements(): Boolean
}