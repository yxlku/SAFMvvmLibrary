package com.safmvvm.ext.ui.progressview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.safmvvm.ext.ui.R;
import com.safmvvm.ext.ui.progressview.item.LinearDividerItemDecoration;
import com.safmvvm.ext.ui.progressview.item.StepNodeItemDecoration;

import java.util.ArrayList;
import java.util.List;

import me.jessyan.autosize.utils.AutoSizeUtils;

/**
 * Created by dcq on 2017/3/22.
 * <p>
 * 使用RecyclerView加载并展示每个记录，使用ItemDecoration装饰item，显示节点
 *
 * https://github.com/dcq123/StepView
 */

public class PorgressStepView extends RecyclerView {

    public static final int DEFAULT_LEFT_MARGIN = 30;
    public static final int DEFAULT_RIGHT_MARGIN = 50;
    public static final int DEFAULT_DOT_RADIUS = 6;
    private static final int DEFAULT_LINE_WIDTH = 1;

    private int leftMargin, rightMargin;
    private int lineColor, lineWidth;
    private int defaultDotColor, highDotColor;
    private int dotPosition;
    private int radius;
    private int defaultTextColor, highTextColor;
    private Drawable defaultDotDrawable;
    private Drawable highDotDrawable;
    private int defaultColor = Color.parseColor("#eeeeee");

    private List itemDatas = new ArrayList<>();
    private StepAdapter mAdapter;
    private BindViewListener mListener;

    public PorgressStepView(Context context) {
        this(context, null);
    }

    public PorgressStepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PorgressStepView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        leftMargin = AutoSizeUtils.mm2px(context, DEFAULT_LEFT_MARGIN);
        rightMargin = AutoSizeUtils.mm2px(context, DEFAULT_RIGHT_MARGIN);
        lineWidth = AutoSizeUtils.mm2px(context, DEFAULT_LINE_WIDTH);
        radius = AutoSizeUtils.mm2px(context, DEFAULT_DOT_RADIUS);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ProgressStepView, defStyle, 0);

        leftMargin = (int) ta.getDimension(R.styleable.ProgressStepView_leftMargin, leftMargin);
        rightMargin = (int) ta.getDimension(R.styleable.ProgressStepView_rightMargin, rightMargin);
        lineWidth = (int) ta.getDimension(R.styleable.ProgressStepView_lineWidth, lineWidth);
        radius = (int) ta.getDimension(R.styleable.ProgressStepView_radius, radius);
        lineColor = ta.getColor(R.styleable.ProgressStepView_lineColor, defaultColor);
        defaultDotColor = ta.getColor(R.styleable.ProgressStepView_defaultDotColor, Color.parseColor("#d0d0d0"));
        highDotColor = ta.getColor(R.styleable.ProgressStepView_highDotColor, Color.parseColor("#1c980f"));
        dotPosition = ta.getInteger(R.styleable.ProgressStepView_dotPosition, StepNodeItemDecoration.POSITION_TOP);
        defaultDotDrawable = ta.getDrawable(R.styleable.ProgressStepView_defaultDotDrawable);
        highDotDrawable = ta.getDrawable(R.styleable.ProgressStepView_highDotDrawable);

        defaultTextColor = ta.getColor(R.styleable.ProgressStepView_defaultTextColor, defaultColor);
        highTextColor = ta.getColor(R.styleable.ProgressStepView_highTextColor, defaultColor);

        ta.recycle();

        init();
    }

    private void init() {
        mAdapter = new StepAdapter();
        setHasFixedSize(true);
        setLayoutManager(new LinearLayoutManager(getContext()));
        setAdapter(mAdapter);


        // 添加左侧节点item装饰
        addItemDecoration(
                new StepNodeItemDecoration.Builder(getContext())
                        .setLineColor(lineColor)
                        .setLeftMargin(leftMargin)
                        .setRightMargin(rightMargin)
                        .setDefaultDotColor(defaultDotColor)
                        .setHighDotColor(highDotColor)
                        .setLineWidth(lineWidth)
                        .setRadius(radius)
                        .setDefaultDotDrawable(defaultDotDrawable)
                        .setHighDotDrawable(highDotDrawable)
                        .setDotPosition(dotPosition)
                        .build()
        );
//        // 添加底部分隔线
//        addItemDecoration(
//                new LinearDividerItemDecoration.Builder()
//                        .setOrientation(LinearLayoutManager.VERTICAL)
//                        .setDividerColor(defaultColor)
//                        .setDividerHeight(2)
//                        .isShowLastDivider(false)
//                        .build()
//        );
    }

    @SuppressWarnings("unchecked")
    public void setDatas(List datas) {
        itemDatas.clear();
        itemDatas.addAll(datas);
        mAdapter.notifyDataSetChanged();
    }

    class StepViewHolder extends ViewHolder {

        TextView itemMsg;
        TextView itemDate;

        StepViewHolder(View itemView) {
            super(itemView);
            itemMsg = (TextView) itemView.findViewById(R.id.itemMsg);
            itemDate = (TextView) itemView.findViewById(R.id.itemDate);
        }
    }

    private class StepAdapter extends Adapter<StepViewHolder> {

        @Override
        public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.extui_step_view_item, parent, false);
            return new StepViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(StepViewHolder holder, int position) {
            Object data = itemDatas.get(position);
            if (position == 0) {
                holder.itemMsg.setTextColor(highTextColor);
                holder.itemDate.setTextColor(highTextColor);
            }else{
                holder.itemMsg.setTextColor(defaultTextColor);
                holder.itemDate.setTextColor(defaultTextColor);
            }
            if (mListener != null) {
                mListener.onBindView(holder.itemMsg, holder.itemDate, data);
            }
        }

        @Override
        public int getItemCount() {
            return itemDatas.size();
        }
    }

    public static interface BindViewListener {
        void onBindView(TextView itemMsg, TextView itemDate, Object data);
    }

    public void setBindViewListener(BindViewListener listener) {
        this.mListener = listener;
    }
}
