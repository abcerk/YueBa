package com.yueba.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.yueba.R;
import com.yueba.base.BaseActivity;

import java.util.ArrayList;

public class EnrollUserDistributionActivity extends BaseActivity {

    private HorizontalBarChart mBarChart;
    private BarData mBarData;
    private ValueFormatter f = new ValueFormatter() {

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return (int)value+"";
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_user_distribution);

        mBarChart = (HorizontalBarChart) findViewById(R.id.chart);
        mBarData = getBarData(10, 60);
        showBarChart(mBarChart, mBarData);

    }

    private void showBarChart(BarChart barChart, BarData barData) {

        barChart.setDrawBorders(false);

        barChart.setDescription("");// 数据描述

        // 如果没有数据的时候，会显示这个，类似ListView的EmptyView
        barChart.setNoDataTextDescription("You need to provide data for the chart.");
        barChart.setTouchEnabled(false); // 设置是否可以触摸

        barChart.setDragEnabled(false);// 是否可以拖拽
        barChart.setScaleEnabled(false);// 是否可以缩放

        barChart.setPinchZoom(false);//

        barChart.setBackgroundColor(Color.WHITE);// 设置背景

        //设置显示数字为整数
        YAxis yr = barChart.getAxisRight();
        yr.setValueFormatter((YAxisValueFormatter) f);
        YAxis yl = barChart.getAxisLeft();
        yl.setValueFormatter((YAxisValueFormatter) f);

        barChart.setDrawBarShadow(true);
        barChart.setData(barData); // 设置数据

    }

    /**
     * 获取图表显示的数据
     *
     * @param count
     * @param range
     * @return
     */
    private BarData getBarData(int count, int range) {

        // 这里没用到count 自己添加的数据
        ArrayList<String> xValues = new ArrayList<String>();
        xValues.add("成都信息工程学院");
        xValues.add("成都医学院");
        xValues.add("四川音乐学院");
        xValues.add("西南交通大学");
        xValues.add("西华大学");
        xValues.add("四川大学");
        xValues.add("成都理工大学");
        xValues.add("电子科技大学");
        xValues.add("西南财经大学");
        xValues.add("四川外国语大学");

        ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();
        yValues.add(new BarEntry(12, 0));
        yValues.add(new BarEntry(5, 1));
        yValues.add(new BarEntry(20, 2));
        yValues.add(new BarEntry(9, 3));
        yValues.add(new BarEntry(16, 4));
        yValues.add(new BarEntry(23, 5));
        yValues.add(new BarEntry(18, 6));
        yValues.add(new BarEntry(21, 7));
        yValues.add(new BarEntry(2, 8));
        yValues.add(new BarEntry(5, 9));

        // y轴的数据集合
        BarDataSet barDataSet = new BarDataSet(yValues, "报名人数");

        barDataSet.setColor(Color.rgb(242, 147, 26));

        barDataSet.setValueFormatter(f);

        ArrayList<BarDataSet> barDataSets = new ArrayList<BarDataSet>();
        barDataSets.add(barDataSet); // add the datasets

        BarData barData = new BarData(xValues, (IBarDataSet) barDataSets);

        return barData;
    }

}