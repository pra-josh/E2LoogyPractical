package com.pra.e2loogypractical.presentation.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity:AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourceId())
        initComponents()
        prepareView()
        setActionListener()
    }

    abstract fun getLayoutResourceId(): View
    abstract fun initComponents()
    abstract fun prepareView()
    abstract fun setActionListener()
}