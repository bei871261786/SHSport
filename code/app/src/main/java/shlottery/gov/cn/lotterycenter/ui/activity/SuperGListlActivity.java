package shlottery.gov.cn.lotterycenter.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.SuperGGuestBean;
import shlottery.gov.cn.lotterycenter.bean.SuperGListBean;
import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.network.ParamsHelperInterface;
import shlottery.gov.cn.lotterycenter.protool.SuperGGuestProtocol;
import shlottery.gov.cn.lotterycenter.protool.SuperGListProtocol;
import shlottery.gov.cn.lotterycenter.ui.view.DividerItemDecoration;
import shlottery.gov.cn.lotterycenter.ui.widget.CustomGridviewManager;
import shlottery.gov.cn.lotterycenter.ui.widget.EmptyRecycleview;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;


public class SuperGListlActivity extends BaseActivity implements LoadCallback {
    private EmptyRecycleview mRecycleView;
    private MyAdapter myAdapter;
    private String mGueststr = "";
    private int mPageNo = 1;
    private int mPageSize = 20;
    private ArrayList<SuperGListBean.DataBean.ListBean> mData = new ArrayList<>();
    private PopupWindow mPopwindow;
    private View mPopContentViw;
    private ImageView mFlitrateImg;
    private PopupWindow mFlitratePopupWindow;
    private RelativeLayout mTitlebarLayout;
    private MyListener mListener;
    private ArrayList<String> mGuestList = new ArrayList<>();
    private ArrayList<Boolean> mGuesStatusListList = new ArrayList<>();
    private HashMap<Integer, Boolean> mFlitrateselectedPosition = new HashMap<>();
    private View emptyView;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_super_gdeail);
        mRecycleView = (EmptyRecycleview) findViewById(R.id.superG_detai_Reycleview);
        mTitlebarLayout = (RelativeLayout) findViewById(R.id.base_titleBar);
        mRecycleView.setAdapter(myAdapter);
        emptyView = findViewById(R.id.emptytiple);

        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        initTitleBar();
        loadList();
        loadGuest();
    }

    private void initTitleBar() {
        LinearLayout mBack = (LinearLayout) findViewById(R.id.titlebar_back_ll);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titlebarTv = (TextView) findViewById(R.id.titlebar_tv);
        titlebarTv.setText("超G竞彩");
        mFlitrateImg = (ImageView) findViewById(R.id.titlebar_indicator);
        mFlitrateImg.setImageResource(R.mipmap.filtrate);
        mFlitrateImg.setOnClickListener(mListener);
    }

    @Override
    protected void init() {
        myAdapter = new MyAdapter();
        mListener = new MyListener();
    }

    private void loadList() {
        SuperGListProtocol protocol = new SuperGListProtocol(this);
        protocol.load(this, new ParamsHelperInterface() {
            @Override
            public LinkedHashMap<String, String> getParamas() {
                LinkedHashMap<String, String> params = new LinkedHashMap();
                params.put("guest", mGueststr);
                params.put("pageNo", mPageNo + "");
                params.put("pageSize", mPageSize + "");
                return params;
            }
        }, this);
    }

    private void loadGuest() {
        SuperGGuestProtocol guestProtocol = new SuperGGuestProtocol(this);
        guestProtocol.load(this, this);
    }

    @Override
    public void onSucess(Object o) {
        LogUtils.i("superglistactivity success:");
        if (o != null) {
            if (o instanceof SuperGListBean) {
                SuperGListBean listBean = (SuperGListBean) o;
                if (listBean.getRet() == 100) {
                    mData.clear();
                    mData.addAll(listBean.getData().getList());
                    LogUtils.i("superglistactivity mdata size:" + mData + ":::" + mData.size());
                    if (mData == null || mData.isEmpty()) {
                        LogUtils.i("superglistactivity mdata noData:");
                        emptyView.setVisibility(View.VISIBLE);
                        mRecycleView.setVisibility(View.GONE);
                    }
                    else {
                        emptyView.setVisibility(View.GONE);
                        mRecycleView.setVisibility(View.VISIBLE);
                    }
                    LogUtils.i("superglistactivity mdata:" + mData.size());
                    myAdapter.notifyDataSetChanged();
                }
            }

            if (o instanceof SuperGGuestBean) {
                SuperGGuestBean guestBean = (SuperGGuestBean) o;
                if (guestBean.getRet() == 100) {
                    mGuestList.clear();
                    mGuestList.addAll(guestBean.getData().getGuest());
                    mGuesStatusListList.clear();
                    for (int i = 0; i < mGuestList.size(); i++) {
                        mGuesStatusListList.add(true);
                        if (!mFlitrateselectedPosition.containsKey(i)) {
                            mFlitrateselectedPosition.put(i, true);
                        }
                    }

                }
            }
        }
    }

    @Override
    public void onError() {

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void createFlitratePop(View v) {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_superg_flitrate, null);
        mFlitratePopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mFlitratePopupWindow.setOutsideTouchable(true);
        mFlitratePopupWindow.setFocusable(true);
        //让pop可以点击外面消失掉
        mFlitratePopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        Button cancle = (Button) view.findViewById(R.id.base_dialog_cancle_btn);
        Button submit = (Button) view.findViewById(R.id.base_dialog_submit_btn);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.superG_flitrata_recycleview);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, R.drawable.divider));
        CustomGridviewManager gridLayoutManager = new CustomGridviewManager(this, 3);
        gridLayoutManager.setScrollEnabled(false);
        recyclerView.setLayoutManager(gridLayoutManager);
        FlitrateAdapter flitrateAdapter = new FlitrateAdapter();
        recyclerView.setAdapter(flitrateAdapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGueststr = "";
                for (int i = 0; i < mGuesStatusListList.size(); i++) {
                    if (mGuesStatusListList.get(i)) {
                        mGueststr += mGuestList.get(i) + ",";
                    }
                }
                if (mGueststr.contains(",")) {
                    mGueststr = mGueststr.substring(0, mGueststr.length() - 1);
                }
                loadList();
                if (!mFlitrateselectedPosition.containsValue(true)) {
                    for (int i = 0; i < mGuestList.size(); i++) {
                        mGuesStatusListList.remove(i);
                        mGuesStatusListList.add(i, true);
                        mFlitrateselectedPosition.put(i, true);
                    }
                }

                mFlitratePopupWindow.dismiss();
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFlitratePopupWindow.dismiss();
            }
        });
        int[] location = new int[2];
        v.getLocationOnScreen(location);
//        mFlitratePopupWindow.showAsDropDown(view, Gravity.CENTER, 0,0);
        mFlitratePopupWindow.showAtLocation(v, Gravity.CENTER, location[0], location[1] - 100);
    }


    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.titlebar_indicator:
                    createFlitratePop(mTitlebarLayout);
                    break;
            }
        }
    }

    class FlitrateAdapter extends RecyclerView.Adapter<FlitrateAdapter.PopHolder> {
        @Override
        public PopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(SuperGListlActivity.this).inflate(R.layout.adapter_superg_flitrate, parent, false);
            return new PopHolder(view);
        }

        @Override
        public void onBindViewHolder(PopHolder holder, final int position) {
            LogUtils.i("superglistactivity onBindViewHolder:");
            holder.name.setText(mGuestList.get(position));
            if (mFlitrateselectedPosition.containsKey(position)) {
                holder.cb.setChecked(mFlitrateselectedPosition.get(position));
                LogUtils.i("superglistactivity getSelectedStatus:" + mFlitrateselectedPosition.get(position));
            }
            holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    mGuesStatusListList.remove(position);
                    mGuesStatusListList.add(position, b);
                    mFlitrateselectedPosition.put(position, b);
                    LogUtils.i("superglistactivity onCheckedChanged:" + mFlitrateselectedPosition.get(position));
                }
            });
        }

        @Override
        public int getItemCount() {
            return mGuestList.size();
        }

        class PopHolder extends RecyclerView.ViewHolder {
            CheckBox cb;
            TextView name;

            public PopHolder(View itemView) {
                super(itemView);
                cb = (CheckBox) itemView.findViewById(R.id.adapter_superflitrate_cb);
                name = (TextView) itemView.findViewById(R.id.adapter_superflitrate_name);
            }
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LogUtils.i("superglistactivity onCreateViewHolder:");
            View view = LayoutInflater.from(SuperGListlActivity.this).inflate(R.layout.adapter_superglistl, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, final int position) {
            holder.title.setText(mData.get(position).getTitle());
            holder.date.setText(mData.get(position).getPlayTime());
            holder.guest.setText(mData.get(position).getGuest());
            String url = mData.get(position).getPicUrl();
            LogUtils.i("superglistactivity onBindViewHolder:" + position + ":::" + url);
            if (url != null || !url.isEmpty()) {
                Picasso.with(SuperGListlActivity.this).load(url).into(holder.img);
            }
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SuperGListlActivity.this, SuperGActivity.class);
                    int id = mData.get(position).getId();
                    intent.putExtra("id", id + "");
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            LogUtils.i("superglistactivity getItemCount:");
            return mData.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView title;
            TextView guest;
            TextView date;
            ImageView img;
            View view;

            public MyHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.adapter_super_title);
                guest = (TextView) itemView.findViewById(R.id.adapter_super_guest);
                date = (TextView) itemView.findViewById(R.id.adapter_super_date);
                img = (ImageView) itemView.findViewById(R.id.adapter_super_img);
                view = itemView;
            }
        }
    }
    @Override
    protected String getBaidutitle() {
        return "超G竞彩列表";
    }
}
