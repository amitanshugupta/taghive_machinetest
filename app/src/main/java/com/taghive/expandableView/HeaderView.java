package com.taghive.expandableView;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.expand.Collapse;
import com.mindorks.placeholderview.annotations.expand.Expand;
import com.mindorks.placeholderview.annotations.expand.Parent;
import com.mindorks.placeholderview.annotations.expand.SingleTop;
import com.taghive.R;
import com.taghive.models.TickersModel;

@Parent
@SingleTop
@Layout(R.layout.item_ticker)
public class HeaderView {

    @View(R.id.tv_name)
    TextView tv_name;

    @View(R.id.tv_asset_name)
    TextView tv_asset_name;

    @View(R.id.tv_volume)
    TextView tv_volume;

    @View(R.id.tv_open_price)
    TextView tv_open_price;

    private final TickersModel tickersModel;

    public HeaderView(TickersModel tickersModel) {
        this.tickersModel = tickersModel;
    }

    @Resolve
    private void onResolve() {
        tv_name.setText(tickersModel.getBaseAsset().toUpperCase());
        tv_asset_name.setText(" / " + tickersModel.getFrom());
        tv_volume.setText(tickersModel.getVolume());
        tv_open_price.setText(tickersModel.getOpenPrice());
    }

    @Expand
    private void onExpand() {
//        Toast.makeText(mContext, "onExpand " + tickersModel, Toast.LENGTH_SHORT).show();
    }

    @Collapse
    private void onCollapse() {
//        Toast.makeText(mContext, "onCollapse " + tickersModel, Toast.LENGTH_SHORT).show();
    }
}
