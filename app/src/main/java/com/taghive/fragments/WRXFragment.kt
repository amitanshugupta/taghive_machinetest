package com.taghive.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mindorks.placeholderview.ExpandablePlaceHolderView
import com.taghive.R
import com.taghive.activities.HomeActivity
import com.taghive.expandableView.ChildView
import com.taghive.expandableView.HeaderView
import com.taghive.models.TickersModel


class WRXFragment(
    private var tickersList: ArrayList<TickersModel>
) : Fragment() {

    private var isPause = false
    private lateinit var fragmentView: View
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var expandablePlaceHolderView: ExpandablePlaceHolderView

    companion object {
        fun newInstance(tickersList: ArrayList<TickersModel>): WRXFragment {
            return WRXFragment(tickersList)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentView = inflater.inflate(R.layout.layout_common_fragment, container, false)
        initViews()
        initListeners()
        return fragmentView
    }

    private fun setAdapter() {
        swipeRefreshLayout.isRefreshing = false
        if (!isPause) {
            getHeaderAndChild()
        }
    }

    private fun initListeners() {
        swipeRefreshLayout.setOnRefreshListener {
            (requireActivity() as HomeActivity).getTickersList(2)
        }
    }

    private fun initViews() {
        expandablePlaceHolderView = fragmentView.findViewById(R.id.expandablePlaceHolder)

        swipeRefreshLayout = fragmentView.findViewById(R.id.swipe_refresh)
        swipeRefreshLayout.setProgressViewOffset(false, 0, 200)
        swipeRefreshLayout.setColorSchemeResources(R.color.black)
    }

    override fun onPause() {
        super.onPause()
        isPause = true
    }

    override fun onResume() {
        super.onResume()
        isPause = false
        setAdapter()
    }

    private fun getHeaderAndChild() {
        for (tickersModel in tickersList) {
            tickersModel.from = "WRX"
            expandablePlaceHolderView.addView(HeaderView(tickersModel))
            expandablePlaceHolderView.addView(ChildView(tickersModel))
        }
    }
}