package com.tck.suspensionview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    private LinearLayout suspensionLl;
    private TextView ongoingTv;
    private View ongoingLine;
    private TextView overTv;
    private View overLine;

    private TextView ongoingSuspensionTv;
    private View ongoingSuspensionLine;
    private TextView overSuspensionTv;
    private View overSuspensionLine;

    private MyAdapter mMyAdapter;

    private boolean isSuspension = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);
        suspensionLl = (LinearLayout) findViewById(R.id.suspension_ll);

        ongoingSuspensionTv = (TextView) findViewById(R.id.ongoing_tv1);
        ongoingSuspensionLine = (View) findViewById(R.id.ongoing_line1);
        overSuspensionTv = (TextView) findViewById(R.id.over_tv1);
        overSuspensionLine = (View) findViewById(R.id.over_line1);

        ongoingSuspensionTv.setOnClickListener(this);
        overSuspensionTv.setOnClickListener(this);

        //listView头部
        View headView = LayoutInflater.from(this).inflate(R.layout.head_layout, null);
        listView.addHeaderView(headView);

        View suspensionView = LayoutInflater.from(this).inflate(R.layout.suspension_layout, null);
        ongoingTv = (TextView) suspensionView.findViewById(R.id.ongoing_tv);
        ongoingLine = (View) suspensionView.findViewById(R.id.ongoing_line);
        overTv = (TextView) suspensionView.findViewById(R.id.over_tv);
        overLine = (View) suspensionView.findViewById(R.id.over_line);
        ongoingTv.setSelected(true);
        ongoingTv.setOnClickListener(this);
        overTv.setOnClickListener(this);

        listView.addHeaderView(suspensionView);


        mMyAdapter = new MyAdapter(this, getList1());
        listView.setAdapter(mMyAdapter);

        ListViewMontior();
    }

    private void ListViewMontior() {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //根据滑动的条目的位置动态显示与影藏悬浮布局，并且改变相应的状态
                if (firstVisibleItem >= 1) {
                    suspensionLl.setVisibility(View.VISIBLE);
                    if (isSuspension) {
                        suspensionStatus1();
                    } else {
                        suspensionStatus2();
                    }
                } else {
                    suspensionLl.setVisibility(View.GONE);
                    if (isSuspension) {
                        normalStatus1();
                    } else {
                        normalStatus2();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ongoing_tv:
                if (!isSuspension) {
                    normalStatus1();
                    isSuspension = true;
                    changeData(isSuspension);
                }

                break;
            case R.id.over_tv:
                if (isSuspension) {
                    normalStatus2();
                    isSuspension = false;
                    changeData(isSuspension);
                }
                break;
            case R.id.ongoing_tv1:
                if (!isSuspension) {
                    suspensionStatus1();
                    isSuspension = true;
                    changeData(isSuspension);
                }
                break;
            case R.id.over_tv1:
                if (isSuspension) {
                    suspensionStatus2();
                    isSuspension = false;
                    changeData(isSuspension);
                }
                break;
        }
    }

    /**
     * 切换数据的方法
     *
     * @param status
     */
    private void changeData(boolean status) {
        if (status) {
            mMyAdapter.update(getList1());
        } else {
            mMyAdapter.update(getList2());
        }
    }

    private void normalStatus1() {
        ongoingTv.setSelected(true);
        overTv.setSelected(false);
        ongoingLine.setVisibility(View.VISIBLE);
        overLine.setVisibility(View.INVISIBLE);
    }

    private void normalStatus2() {
        ongoingTv.setSelected(false);
        overTv.setSelected(true);
        ongoingLine.setVisibility(View.INVISIBLE);
        overLine.setVisibility(View.VISIBLE);
    }

    private void suspensionStatus1() {
        ongoingSuspensionTv.setSelected(true);
        ongoingSuspensionLine.setVisibility(View.VISIBLE);
        overSuspensionTv.setSelected(false);
        overSuspensionLine.setVisibility(View.INVISIBLE);
    }

    private void suspensionStatus2() {
        ongoingSuspensionTv.setSelected(false);
        ongoingSuspensionLine.setVisibility(View.INVISIBLE);
        overSuspensionTv.setSelected(true);
        overSuspensionLine.setVisibility(View.VISIBLE);
    }

    private List<String> getList1() {
        List<String> mDataList = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            mDataList.add("开始招聘 " + i + " 位Android开发者");
        }
        return mDataList;
    }

    private List<String> getList2() {
        List<String> mDataList = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            mDataList.add("结束招聘 " + i + " 位Android开发者");
        }
        return mDataList;
    }
}
