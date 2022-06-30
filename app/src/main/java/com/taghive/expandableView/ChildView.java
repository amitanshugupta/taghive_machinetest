package com.taghive.expandableView;

import android.content.Context;
import android.widget.TextView;

import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.taghive.R;
import com.taghive.models.TickersModel;


@Layout(R.layout.child_layout)
public class ChildView {

    @View(R.id.tv_open_price)
    TextView tvOpenPrice;

    @View(R.id.tv_low_price)
    TextView tvLowPrice;

    @View(R.id.tv_high_price)
    TextView tvHighPrice;

    @View(R.id.tv_last_price)
    TextView tvLastPrice;

    @View(R.id.tv_bid_price)
    TextView tvBidPrice;

    @View(R.id.tv_ask_price)
    TextView tvAskPrice;

    private final TickersModel tickersModel;

    public ChildView(TickersModel tickersModel) {
        this.tickersModel = tickersModel;
    }

    @Resolve
    private void onResolve() {
        tvOpenPrice.setText(tickersModel.getOpenPrice());
        tvLowPrice.setText(tickersModel.getLowPrice());
        tvHighPrice.setText(tickersModel.getHighPrice());
        tvLastPrice.setText(tickersModel.getLastPrice());
        tvBidPrice.setText(tickersModel.getBidPrice());
        tvAskPrice.setText(tickersModel.getAskPrice());
    }
}