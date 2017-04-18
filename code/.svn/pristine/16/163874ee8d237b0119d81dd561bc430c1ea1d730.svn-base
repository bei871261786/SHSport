package shlottery.gov.cn.lotterycenter.ui.activity;

import android.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.CashingBean;
import shlottery.gov.cn.lotterycenter.bean.CashingListBean;
import shlottery.gov.cn.lotterycenter.bean.JifenBean;
import shlottery.gov.cn.lotterycenter.bean.VoucherBean;
import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.network.ParamsHelperInterface;
import shlottery.gov.cn.lotterycenter.protool.CashingListProtocol;
import shlottery.gov.cn.lotterycenter.protool.CashingProtocol;
import shlottery.gov.cn.lotterycenter.protool.JifenProtacol;
import shlottery.gov.cn.lotterycenter.protool.VoucherPaotocal;
import shlottery.gov.cn.lotterycenter.ui.view.DividerItemDecoration;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.TextUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

import static shlottery.gov.cn.lotterycenter.R.id.titlebar_indicator;

public class CashingActivity extends BaseActivity implements LoadCallback {
    private TextView mTotalJifen;
    private TextView mTotalVoucher;
    private RecyclerView mRecycleView;
    private ArrayList<CashingListBean.DataBean.ListBean> mData = new ArrayList<>();
    private MyAdapter mAdapter;
    private AlertDialog mDialog;
    private int mCashCount = 1;
    private int mTotalJifenCount;
    private int mTotalVoucherCount;




    @Override
    protected void initView() {
        setContentView(R.layout.activity_cashing);
        mRecycleView = (RecyclerView) findViewById(R.id.cashing_recycleview);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mTotalJifen = (TextView) findViewById(R.id.cashing_jifen);
        mTotalVoucher = (TextView) findViewById(R.id.cashing_voucher);
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.addItemDecoration(new DividerItemDecoration(this, R.drawable.divider));
        initTitleBar();
        load();
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
        titlebarTv.setText("兑换");
        ImageView mIndicatorImg = (ImageView) findViewById(titlebar_indicator);
        mIndicatorImg.setVisibility(View.GONE);
    }

    @Override
    protected void init() {
        mAdapter = new MyAdapter();
    }

    private void load() {
        CashingListProtocol protocol = new CashingListProtocol(this);
        protocol.load(this, this);
    }

    @Override
    public void onSucess(Object object) {
        if (object != null) {
            if (object instanceof CashingListBean) {
                CashingListBean cashingBean = (CashingListBean) object;
                if (null != cashingBean.getData() && null != cashingBean.getData().getList()) {
                    mData.clear();
                    mData.addAll(cashingBean.getData().getList());
                    mAdapter.notifyDataSetChanged();
                    updateTitle();
                }
            }
            if (object instanceof CashingBean) {
                CashingBean cashingBean = (CashingBean) object;
                if (cashingBean.getRet() == 100) {
                    UIUtils.showToastSafe("兑换成功");
                    mDialog.dismiss();
                } else {
                    UIUtils.showToastSafe(cashingBean.getMsg());
                }
                load();
                updateTitle();
            }
            if (object instanceof VoucherBean) {
                VoucherBean voucherBean = (VoucherBean) object;
                if (voucherBean.getRet() == 100) {
                    mTotalVoucherCount = voucherBean.getData().getCount();
                    TextUtils.setStrColor(mTotalVoucher, "代金券数：" + mTotalVoucherCount + "张", mTotalVoucherCount + "", getResources().getColor(R.color.select_red));
                }

            }
            if (object instanceof JifenBean) {
                JifenBean jifenBean = (JifenBean) object;
                LogUtils.i("JifenBean  1: " + object);
                if (jifenBean.getRet() == 100) {
                    LogUtils.i("JifenBean   2:" + jifenBean);
                    mTotalJifenCount = jifenBean.getData().getIntegral();
                    TextUtils.setStrColor(mTotalJifen, "积分数：" + mTotalJifenCount + "分", mTotalJifenCount + "", getResources().getColor(R.color.select_red));
                }
            }
        }
    }

    @Override
    public void onError() {

    }

    private void updateTitle() {
        LogUtils.i("CashingActivity updateTitle");
        JifenProtacol jifenProtacol = new JifenProtacol(this);
        VoucherPaotocal voucherProtocol = new VoucherPaotocal(this);
        jifenProtacol.load(this, this);
        voucherProtocol.load(this, this);
    }

    private void createDialog(final int integral, final int voucherId) {
        View mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_cashing, null);
        mDialog = new android.app.AlertDialog.Builder(this).create();
        mDialog.show();
        Window window = mDialog.getWindow();
        window.setContentView(mDialogView);
        mDialog.setCanceledOnTouchOutside(false);


        ImageView closedialog = (ImageView) mDialogView.findViewById(R.id.dialog_close);
        ImageView minus = (ImageView) mDialogView.findViewById(R.id.dialog_cash_minus);
        ImageView plus = (ImageView) mDialogView.findViewById(R.id.dialog_cash_plus);
        final TextView countTv = (TextView) mDialogView.findViewById(R.id.dialog_cash_edit);
        Button submit = (Button) mDialogView.findViewById(R.id.dialog_cash_submit);
        submit.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          CashingProtocol protocol = new CashingProtocol(CashingActivity.this);
                                          protocol.load(CashingActivity.this, new ParamsHelperInterface() {
                                              @Override
                                              public LinkedHashMap<String, String> getParamas() {
                                                  LinkedHashMap<String, String> parmas = new LinkedHashMap<String, String>();
                                                  parmas.put("voucherId", voucherId + "");
                                                  parmas.put("amount", mCashCount + "");

                                                  return parmas;
                                              }
                                          }, CashingActivity.this);
                                      }
                                  }

        );
        //关闭dialog
        closedialog.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               mDialog.dismiss();
                                           }
                                       }
        );

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCashCount--;
                if (mCashCount < 1) {
                    mCashCount = 1;
                }
                countTv.setText(mCashCount + "");

            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCashCount++;
                if (integral * mCashCount > mTotalJifenCount) {
                    mCashCount--;
                    UIUtils.showToastSafe("您不能兑换更多了");
                }
                countTv.setText(mCashCount + "");
            }
        });
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

        @Override
        public MyAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(BaseApplication.getApplication()).inflate(R.layout.item_cashinglist, parent, false);
            return new MyAdapter.MyHolder(view);
        }


        @Override
        public void onBindViewHolder(MyAdapter.MyHolder holder, final int position) {
            LogUtils.i("voucher onBindViewHolder " + mData.get(position).getVoucherName());
            holder.type.setText(mData.get(position).getVoucherName());
            holder.money.setText(mData.get(position).getVoucherMoney() + "元");
            holder.jifen.setText(mData.get(position).getIntegral() + "积分");
            final int voucherId = mData.get(position).getVoucherId();
            final int interal = mData.get(position).getVoucherMoney() * 100;
            holder.cash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    createDialog(interal, voucherId);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            private TextView type;
            private TextView money;
            private TextView jifen;
            private ImageView cash;

            public MyHolder(View itemView) {
                super(itemView);
                type = (TextView) itemView.findViewById(R.id.cashlist_type);
                money = (TextView) itemView.findViewById(R.id.cashlist_money);
                jifen = (TextView) itemView.findViewById(R.id.cashlist_jifen);
                cash = (ImageView) itemView.findViewById(R.id.cashlist_cashbt);
            }
        }
    }
    @Override
    protected String getBaidutitle() {
        return "";
    }
}
