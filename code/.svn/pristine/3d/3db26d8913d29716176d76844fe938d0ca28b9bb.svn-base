package shlottery.gov.cn.lotterycenter.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.NewsListBean;
import shlottery.gov.cn.lotterycenter.bean.VcodeBean;
import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.manager.LoginManager;
import shlottery.gov.cn.lotterycenter.network.ParamsHelperInterface;
import shlottery.gov.cn.lotterycenter.protool.AskProtocol;
import shlottery.gov.cn.lotterycenter.protool.NewsListProtocol;
import shlottery.gov.cn.lotterycenter.ui.view.DividerItemDecoration;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadDataScrollController;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.ToastUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

import static shlottery.gov.cn.lotterycenter.R.id.dialogask_tag_1;
import static shlottery.gov.cn.lotterycenter.R.id.dialogask_tag_2;
import static shlottery.gov.cn.lotterycenter.R.id.dialogask_tag_3;
import static shlottery.gov.cn.lotterycenter.R.id.dialogask_tag_4;
import static shlottery.gov.cn.lotterycenter.R.id.dialogask_tag_5;

public class KefuActivity extends BaseActivity implements LoadCallback, LoadDataScrollController.OnRecycleRefreshListener, EasyPermissions.PermissionCallbacks {

    private ImageView mSearch;
    private ImageView mSelfQuestion;
    private RecyclerView mRecycleview;
    private String mTags = "";
    private int mPageNo = 1;
    private int mPageSize = 20;
    private String mKeyWord = "";//关键字
    private int mPageCount;
    private SwipeRefreshLayout mRefreshLayout;
    private LoadDataScrollController mController;
    private MyAdapter mAdapter;
    private boolean isLoadMore = false;
    private ArrayList<NewsListBean.DataBean.ListBean> mAdapterData = new ArrayList<>();
    private MyListener mListener;
    private LinearLayout mSuggestionLayout;
    private LinearLayout mCallLayout;
    private int mAskType = 0;
    private AlertDialog mAskDialog;
    private AlertDialog mCallDialog;
    private AlertDialog mSearchDialog;
    private static final int RC_CAMERA_PERM = 123;
    private static final int RC_SETTINGS_SCREEN = 125;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_kefu);
        mRecycleview = (RecyclerView) findViewById(R.id.keful_recycleview);
        mRecycleview.setLayoutManager(new LinearLayoutManager(this));
        mRecycleview.addItemDecoration(new DividerItemDecoration(this, R.drawable.divider));
        mSuggestionLayout = (LinearLayout) findViewById(R.id.kefu_viewLayout);
        mCallLayout = (LinearLayout) findViewById(R.id.call_viewLayout);
        mSuggestionLayout.setOnClickListener(mListener);
        mCallLayout.setOnClickListener(mListener);
        initRefreshLayout();
        initTitleBar();
        beginLoad();
    }

    @Override
    protected void init() {
        mAdapter = new MyAdapter();
        mListener = new MyListener();
    }

    //在recycleview被初始化之后调用
    private void initRefreshLayout() {
        mController = new LoadDataScrollController(this);
        mRecycleview.addOnScrollListener(mController);
        mRecycleview.setAdapter(mAdapter);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.account_jifen_refreshLayout);
        mRefreshLayout.setOnRefreshListener(mController);
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
        titlebarTv.setText("客服热线");
        mSearch = (ImageView) findViewById(R.id.titlebar_indicator2);
        mSearch.setImageResource(R.mipmap.search2);
        mSearch.setVisibility(View.VISIBLE);
        mSelfQuestion = (ImageView) findViewById(R.id.titlebar_indicator);
        mSelfQuestion.setVisibility(View.VISIBLE);
        mSelfQuestion.setImageResource(R.mipmap.mine);
        mSelfQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoginManager.getInstance().login(KefuActivity.this)) {
                    Intent intent = new Intent(KefuActivity.this, SelfQuestionActivity.class);
                    startActivity(intent);
                }

            }
        });
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchDialog();
            }
        });
    }

    public void beginLoad() {
        NewsListProtocol protocol = new NewsListProtocol(this);
        protocol.load(this, new ParamsHelperInterface() {
            @Override
            public LinkedHashMap<String, String> getParamas() {
                LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
                params.put("type", "6");
                params.put("tags", mTags + "");
                params.put("keyword", mKeyWord);
                params.put("pageNo", mPageNo + "");
                params.put("pageSize", mPageSize + "");
                return params;
            }
        }, this);
    }

    private void submitAsk(final String question) {
        AskProtocol protocol = new AskProtocol(this);
        protocol.load(this, new ParamsHelperInterface() {
            @Override
            public LinkedHashMap<String, String> getParamas() {
                LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
                LogUtils.i("submitAsk params:" + mAskType + "::::" + question);
                params.put("type", mAskType + "");
                params.put("question", question);
                return params;
            }
        }, this);
    }

    @Override
    public void onSucess(Object o) {
        mController.setLoadDataStatus(false);
        mRefreshLayout.setRefreshing(false);
        if (o != null) {
            if (o instanceof NewsListBean) {
                NewsListBean listbean = (NewsListBean) o;
                if (listbean.getRet() == 100) {
                    mPageCount = listbean.getData().getPageCount();
                    if (!isLoadMore) {
                        mAdapterData.clear();
                    }

                    mAdapterData.addAll(listbean.getData().getList());
                }
                LogUtils.i("newsFragment onsuccess:" + mAdapterData.size());
                mAdapter.notifyDataSetChanged();
                isLoadMore = false;
                if (mSearchDialog != null && mSearchDialog.isShowing()) {
                    mSearchDialog.dismiss();
                }
            }

            if (o instanceof VcodeBean) {
                VcodeBean vcodeBean = (VcodeBean) o;
                if (vcodeBean.getRet() == 100) ;
                {
                    mAskType=0;
                    mAskDialog.dismiss();
                }
                UIUtils.showToastSafe(vcodeBean.getMsg());
            }
        }
    }

    @Override
    public void onError() {

    }

    @Override
    public void refresh() {
        isLoadMore = false;
        mPageNo = 1;
        beginLoad();
    }

    @Override
    public void loadMore() {
        isLoadMore = true;
        mRefreshLayout.setRefreshing(true);
        mPageNo++;
        LogUtils.i("loadMore:" + mPageNo + ":::" + mPageCount);
        if (mPageNo > mPageCount) {
            isLoadMore = false;
            mPageNo--;
            UIUtils.showToastSafe("没有更多数据了");
            mRefreshLayout.setRefreshing(false);
        } else {
            beginLoad();
        }

        LogUtils.i("accountVoucherfragmnt loadMore" + mPageNo);
    }

    private void searchDialog() {
        mSearchDialog = new AlertDialog.Builder(this).create();
        View view = LayoutInflater.from(KefuActivity.this).inflate(R.layout.dialog_search, null);
        mSearchDialog.setView(view);
        mSearchDialog.show();
        mSearchDialog.setCanceledOnTouchOutside(true);
        final EditText editText = (EditText) view.findViewById(R.id.dialogsearch_edit);

        Button submit = (Button) view.findViewById(R.id.dialog_search_search);
        Button clear = (Button) view.findViewById(R.id.dialog_search_clear);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = editText.getText().toString();
                mKeyWord = question;
                beginLoad();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
            }
        });
    }

    private void createAskDialog() {
        if (LoginManager.getInstance().login(this)) {
            mAskDialog = new AlertDialog.Builder(this).create();
            View view = LayoutInflater.from(KefuActivity.this).inflate(R.layout.dialog_ask, null);
            mAskDialog.setView(view);
            mAskDialog.show();
            mAskDialog.setCanceledOnTouchOutside(true);
            RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.dialogask_radioGroup);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (i == dialogask_tag_1) {
                        mAskType = 1;
                    } else if (i == dialogask_tag_2) {
                        mAskType = 2;
                    } else if (i == dialogask_tag_3) {
                        mAskType = 3;
                    } else if (i == dialogask_tag_4) {
                        mAskType = 4;
                    } else if (i == dialogask_tag_5) {
                        mAskType = 5;
                    }
                    LogUtils.i("submitAsk radioGroup params:" + mAskType + "::::" + i);
                }
            });
            final EditText editText = (EditText) view.findViewById(R.id.dialogask_inputEdit);

            Button submit = (Button) view.findViewById(R.id.dialogask_send);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String question = editText.getText().toString();
                    if (question != null && !question.isEmpty()) {
                        LogUtils.i("KefuActivity dialog question:" + question);
                        if(mAskType==0)
                        {
                            UIUtils.showToastSafe("请选择一个标题 ");
                        }
                        else
                        {
                            submitAsk(question);
                        }

                    } else {
                        UIUtils.showToastSafe("问题不能为空");
                    }
                }
            });
        }
    }

    private void createCallDialog() {
        mCallDialog = new AlertDialog.Builder(this).create();
        View view = LayoutInflater.from(KefuActivity.this).inflate(R.layout.dialog_call, null);
        mCallDialog.setView(view);
        mCallDialog.show();
        mCallDialog.setCanceledOnTouchOutside(true);
        TextView cancel = (TextView) view.findViewById(R.id.dialogcall_cancel);
        TextView call = (TextView) view.findViewById(R.id.dialogcall_call);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallDialog.dismiss();
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkStoragePression();
                mCallDialog.dismiss();
            }
        });
    }

    private void call() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + "95086"));
        startActivity(intent);
    }

    private class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.kefu_viewLayout:
                    createAskDialog();
                    break;
                case R.id.call_viewLayout:
                    createCallDialog();
                    break;
            }
        }
    }


    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {


        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(KefuActivity.this).inflate(R.layout.adapter_newslist, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(KefuActivity.MyAdapter.MyHolder holder, final int position) {
            LogUtils.i("newsFragment adapter onBindViewHolder:" + position + ":::" + mAdapterData.size());
            holder.title.setText(mAdapterData.get(position).getTitle());
            holder.date.setText(mAdapterData.get(position).getNewsTime());
            String tags = mAdapterData.get(position).getTags();
            String[] tagArr;
            if (tags != null && !tags.isEmpty()) {
                if (tags.contains(",")) {
                    tagArr = tags.split(",");
                } else {
                    tagArr = new String[1];
                    tagArr[0] = tags;
                    holder.tab1.setVisibility(View.VISIBLE);
                    holder.tab2.setVisibility(View.GONE);
                    holder.tab3.setVisibility(View.GONE);
                }
                if (tagArr.length == 1) {
                    holder.tab1.setText(tagArr[0]);
                    holder.tab1.setVisibility(View.VISIBLE);
                    holder.tab2.setVisibility(View.GONE);
                    holder.tab3.setVisibility(View.GONE);
                } else if (tagArr.length == 2) {
                    holder.tab1.setText(tagArr[0]);
                    holder.tab2.setText(tagArr[1]);
                    holder.tab1.setVisibility(View.VISIBLE);
                    holder.tab2.setVisibility(View.VISIBLE);
                    holder.tab3.setVisibility(View.GONE);
                } else {
                    holder.tab1.setText(tagArr[0]);
                    holder.tab2.setText(tagArr[1]);
                    holder.tab3.setText(tagArr[2]);
                    holder.tab1.setVisibility(View.VISIBLE);
                    holder.tab2.setVisibility(View.VISIBLE);
                    holder.tab3.setVisibility(View.VISIBLE);
                }
            } else {
                holder.tab1.setVisibility(View.GONE);
                holder.tab2.setVisibility(View.GONE);
                holder.tab3.setVisibility(View.GONE);
            }
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent inten = new Intent(KefuActivity.this, NewsDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", mAdapterData.get(position).getId());
                    bundle.putString("title", "资讯");
                    inten.putExtras(bundle);
                    startActivity(inten);

                }
            });
        }

        @Override
        public int getItemCount() {
            LogUtils.i("newsFragment adapter getItemCount:" + mAdapterData.size());
            return mAdapterData.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView title;
            TextView tab1;
            TextView tab2;
            TextView tab3;
            TextView date;
            View view;

            public MyHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.adapter_newlist_title);
                tab1 = (TextView) itemView.findViewById(R.id.adapter_newlist_tab1);
                tab2 = (TextView) itemView.findViewById(R.id.adapter_newlist_tab2);
                tab3 = (TextView) itemView.findViewById(R.id.adapter_newlist_tab3);
                date = (TextView) itemView.findViewById(R.id.adapter_newlist_date);
                view = itemView;
            }
        }

    }


    @AfterPermissionGranted(RC_CAMERA_PERM)
    public void checkStoragePression() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CALL_PHONE)) {
            // Have permission, do the thing!
            call();
        } else {
            // Ask for one permission
            Toast.makeText(this, "需要电话权限", Toast.LENGTH_LONG).show();
            EasyPermissions.requestPermissions(this, "需要电话权限",
                    RC_CAMERA_PERM, Manifest.permission.CALL_PHONE);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        UIUtils.showToastSafe("已经获取到电话权限,点击重试");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this, "如果没有电话权限，无法拨打电话。打开应用程序设置界面修改应用程序权限?")
                    .setTitle("应用设置")
                    .setPositiveButton("设置")
                    .setNegativeButton(getString(R.string.cancel), null /* click listener */)
                    .setRequestCode(RC_SETTINGS_SCREEN)
                    .build()
                    .show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SETTINGS_SCREEN) {
            ToastUtils.showShort(KefuActivity.this, "点击客服热线重试");
        }
    }
    @Override
    protected String getBaidutitle() {
        return "客服";
    }
}
