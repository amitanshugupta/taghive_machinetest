package com.taghive.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.taghive.R
import com.taghive.adapters.ViewPagerAdapter
import com.taghive.fragments.INRFragment
import com.taghive.fragments.USDTFragment
import com.taghive.fragments.WRXFragment
import com.taghive.models.TickersModel
import com.taghive.viewModels.HomeActivityViewModel

class HomeActivity : BaseActivity() {

    private lateinit var mViewpager: ViewPager
    private lateinit var mTabLayout: TabLayout
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private var mainTickersList = ArrayList<TickersModel>()
    private var inrTickersList = ArrayList<TickersModel>()
    private var usdtTickersList = ArrayList<TickersModel>()
    private var wrxTickersList = ArrayList<TickersModel>()
    private lateinit var mHomeActivityViewModel: HomeActivityViewModel
    private var SELECTEDTAB = 0

    companion object {
        fun open(myActivity: Activity) {
            val intent = Intent(myActivity, HomeActivity::class.java)
            myActivity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mHomeActivityViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(this.application)
        ).get(HomeActivityViewModel::class.java)

        initViews()
        initLiveData()
        getTickersList(SELECTEDTAB)
    }

    fun getTickersList(selected: Int) {
        SELECTEDTAB = selected
        mainTickersList.clear()
        inrTickersList.clear()
        usdtTickersList.clear()
        wrxTickersList.clear()
        mHomeActivityViewModel.getTickersResult()
    }

    private fun initLiveData() {
        mHomeActivityViewModel.getDisplayProgressLiveData().observe(this) {
            if (it) {
                showLoader(this, "Please wait...")
            } else {
                dismissLoader()
            }
        }

        mHomeActivityViewModel.getTickersResultLiveData().observe(this) {
            mainTickersList.clear()
            mainTickersList.addAll(it)
            for (i in 0 until mainTickersList.size) {
                when (mainTickersList[i].quoteAsset) {
                    "inr" -> {
                        inrTickersList.add(mainTickersList[i])
                    }
                    "usdt" -> {
                        usdtTickersList.add(mainTickersList[i])
                    }
                    "wrx" -> {
                        wrxTickersList.add(mainTickersList[i])
                    }
                }
            }
            setupViewPager()
            mHomeActivityViewModel.setDisplayProgressStatus(false)
        }
    }

    private fun setupViewPager() {
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addFrag(USDTFragment.newInstance(usdtTickersList), "USDT")
        viewPagerAdapter.addFrag(INRFragment.newInstance(inrTickersList), "INR")
        viewPagerAdapter.addFrag(WRXFragment.newInstance(wrxTickersList), "WRX")
        mViewpager.adapter = viewPagerAdapter
        mTabLayout.setupWithViewPager(mViewpager)
        mViewpager.currentItem = SELECTEDTAB
    }

    private fun initViews() {
        mViewpager = findViewById(R.id.viewpager)
        mTabLayout = findViewById(R.id.tab_layout)
    }
}