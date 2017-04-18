package shlottery.gov.cn.lotterycenter.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import shlottery.gov.cn.lotterycenter.Base.Configure_JC;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.Jincai.MatchsBean;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/1 14:31
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class SoccerDialogUtils {
    private static AlertDialog mZjqDialog;
    private static AlertDialog mBfDialog;
    private static AlertDialog mBqcDialog;
    private static AlertDialog mMixDialog;
    private static AlertDialog mSfcDialog;
    private static AlertDialog mMixBtDialog;
    private static ArrayList<View> mCheckTextViews = new ArrayList<>();
    private HashMap<Integer, Boolean> mZjqSubmitStateMap = new HashMap<>();
    private HashMap<Integer, Boolean> mSubmitStateMap = new HashMap<>();
    private HashMap<Integer, HashMap<Integer, Boolean>> mMixSubmitStateMap = new HashMap<>();
    private MatchsBean mData;
    private HashMap<Integer, MatchsBean> mMixData;
    private HashMap<Integer, MatchsBean> mMixBkData;
    private Handler mHandlerForListview;
    private LayoutInflater inflater;
    private MixHolder mHolder;
    private MatchsBean spfData = null;
    private MatchsBean bfData = null;
    private MatchsBean zjqData = null;
    private MatchsBean rqspfData = null;
    private MatchsBean bqcData = null;

    private MatchsBean sfData = null;
    private MatchsBean xsfData = null;
    private MatchsBean dxData = null;
    private MatchsBean sfcData = null;
    private MatchsBean msgData = null;
    private int mixId;

    public void updateDataByDingdanDialog(HashMap<Integer, Boolean> stateMap, MatchsBean data) {
        Iterator iter = stateMap.entrySet().iterator();
        LogUtils.i("bf key size:" + stateMap.size());
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            int id = (int) entry.getKey();
            Boolean isChecked = (Boolean) entry.getValue();
            LogUtils.i("bf key:" + id + "values:" + isChecked);
            // JcDataUtils.setZjqSelectedBoolean(data, id, isChecked);
            JcDataUtils.setSpsState(data, id, isChecked);
        }
    }

    public void showZjqDialog(Context context, MatchsBean data, final Handler handler) {
        LogUtils.i("dialog bqc");
        LogUtils.i("dialog context是否为空：" + (context == null) + ":" + context);
        if (mZjqDialog == null) {
            if (null == inflater) {
                inflater = LayoutInflater.from(context);
            }
            mData = data;
            LinearLayout mDialogView = (LinearLayout) inflater.inflate(R.layout.football_zongjinqiu_dialog, null);
            // if (null == mZjqDialog) {
            mZjqDialog = new AlertDialog.Builder(context).create();
            mZjqDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    // TODO Auto-generated method stub
                    mZjqDialog = null;
                }
            });
            mZjqDialog.show();
            Window window = mZjqDialog.getWindow();
            window.setContentView(mDialogView);
            mZjqDialog.setCanceledOnTouchOutside(true);
            mHolder = new MixHolder();
            mHolder.hostname = (TextView) mDialogView.findViewById(R.id.dialog_zjq_hostame);
            mHolder.visitname = (TextView) mDialogView.findViewById(R.id.dialog_zjq_visitname);
            mHolder.hostrank = (TextView) mDialogView.findViewById(R.id.dialog_zjq_hostrank);
            mHolder.visitrank = (TextView) mDialogView.findViewById(R.id.dialog_zjq_visitrank);

            mHolder.zjqCbs = new ArrayList();
            mHolder.zjqCbs.add((CheckedTextView) mDialogView.findViewById(R.id.zjq_dialog_0_ct));
            mHolder.zjqCbs.add((CheckedTextView) mDialogView.findViewById(R.id.zjq_dialog_1_ct));
            mHolder.zjqCbs.add((CheckedTextView) mDialogView.findViewById(R.id.zjq_dialog_2_ct));
            mHolder.zjqCbs.add((CheckedTextView) mDialogView.findViewById(R.id.zjq_dialog_3_ct));
            mHolder.zjqCbs.add((CheckedTextView) mDialogView.findViewById(R.id.zjq_dialog_4_ct));
            mHolder.zjqCbs.add((CheckedTextView) mDialogView.findViewById(R.id.zjq_dialog_5_ct));
            mHolder.zjqCbs.add((CheckedTextView) mDialogView.findViewById(R.id.zjq_dialog_6_ct));
            mHolder.zjqCbs.add((CheckedTextView) mDialogView.findViewById(R.id.zjq_dialog_7_ct));
            Button cancel = (Button) mDialogView.findViewById(R.id.base_dialog_cancle_btn);
            Button submit = (Button) mDialogView.findViewById(R.id.base_dialog_submit_btn);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mZjqDialog.dismiss();
                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateDataByDingdanDialog(mSubmitStateMap, mData);
                    mZjqDialog.dismiss();
                    handler.sendEmptyMessage(0);
                }
            });

            setZjqDialogData(data);
            LogUtils.i("bqc -showover");
        }
    }

    private void setZjqDialogData(MatchsBean data) {
        mSubmitStateMap.clear();
        //LogUtils.i("bqc 传递接受:"+data.getHostName()+"::"+data.getSpsState(0)+"::"+data.getSpsState(1));
        for (int i = 0; i < mHolder.zjqCbs.size(); i++) {
            final int k = i;
            mHolder.zjqCbs.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckedTextView ct = (CheckedTextView) v;
                    Boolean ischecked = ct.isChecked();
                    if (ischecked) {
                        mSubmitStateMap.put(k, false);
                        ct.setChecked(false);

                    } else {
                        mSubmitStateMap.put(k, true);
                        ct.setChecked(true);
                    }
                    LogUtils.i("bqc 当前状态是:" + ct.isChecked());
                    LogUtils.i("dialog checkview:" + "clickable:" + v.isClickable() + "   enable:" + v.isEnabled() + "   selected:" + v.isSelected() + "checked:" + ct.isChecked());
                }
            });
        }

        LogUtils.i("bqc setDialog");
        mHolder.hostname.setText(data.getHostName());
        mHolder.visitname.setText(data.getVisitName());
        if (null == data.getHostRank() || data.getHostRank().equals("")) {
            mHolder.hostrank.setText("");
            mHolder.visitrank.setText("");
        } else {
            mHolder.hostrank.setText("[" + data.getHostRank() + "]");
            mHolder.visitrank.setText("[" + data.getVisitRank() + "]");
        }
        for (int i = 0; i < Configure_JC.FB_DIALOG_ZJQ.length; i++) {
            TextUtils.setTextSpan(mHolder.zjqCbs.get(i), (Configure_JC.FB_DIALOG_ZJQ[i] + "  " + data.getSps().get(i)));
        }

        for (int i = 0; i < mHolder.zjqCbs.size(); i++) {
            // LogUtils.i("bqc 初始化状态"+data.getSpsState(i));
            final int k = i;
            CheckedTextView checkTextView = mHolder.zjqCbs.get(i);
            checkTextView.setChecked(JcDataUtils.getSpsState(data, i));
        }
    }

    public void showBqcDialog(Context context, MatchsBean data, final Handler handler) {
        LogUtils.i("dialog bqc");
        LogUtils.i("dialog context是否为空：" + (context == null) + ":" + context);
        if (mBqcDialog == null) {
            if (null == inflater) {
                inflater = LayoutInflater.from(context);
            }
            mData = data;
            LinearLayout mDialogView = (LinearLayout) inflater.inflate(R.layout.football_banquanchang_dialog, null);
            mBqcDialog = new AlertDialog.Builder(context).create();
            mBqcDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    // TODO Auto-generated method stub
                    mBqcDialog = null;
                }
            });
            mBqcDialog.show();
            Window window = mBqcDialog.getWindow();
            window.setContentView(mDialogView);
            mBqcDialog.setCanceledOnTouchOutside(true);
            mHolder = new MixHolder();
            mHolder.hostname = (TextView) mDialogView.findViewById(R.id.bqc_dialog_homename);
            mHolder.hostrank = (TextView) mDialogView.findViewById(R.id.bqc_dialog_hos_rank);
            mHolder.visitname = (TextView) mDialogView.findViewById(R.id.bqc_dialog_visitname);
            mHolder.visitrank = (TextView) mDialogView.findViewById(R.id.bqc_dialog_visit_rankg);
            mHolder.bqcCbs = new ArrayList();
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.bqc_dialog_0_ct));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.bqc_dialog_1_ct));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.bqc_dialog_2_ct));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.bqc_dialog_3_ct));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.bqc_dialog_4_ct));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.bqc_dialog_5_ct));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.bqc_dialog_6_ct));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.bqc_dialog_7_ct));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.bqc_dialog_8_ct));
            Button cancel = (Button) mDialogView.findViewById(R.id.base_dialog_cancle_btn);
            Button submit = (Button) mDialogView.findViewById(R.id.base_dialog_submit_btn);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBqcDialog.dismiss();
                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateDataByDingdanDialog(mSubmitStateMap, mData);
                    mBqcDialog.dismiss();
                    handler.sendEmptyMessage(0);
                }
            });

            setBqcDialogData(data);
            LogUtils.i("bqc -showover");
        }
    }

    private void setBqcDialogData(MatchsBean data) {
        mSubmitStateMap.clear();
        //LogUtils.i("bqc 传递接受:"+data.getHostName()+"::"+data.getSpsState(0)+"::"+data.getSpsState(1));
        for (int i = 0; i < mHolder.bqcCbs.size(); i++) {
            final int k = i;
            mHolder.bqcCbs.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckedTextView ct = (CheckedTextView) v;
                    Boolean ischecked = ct.isChecked();
                    if (ischecked) {
                        mSubmitStateMap.put(k, false);
                        ct.setChecked(false);

                    } else {
                        mSubmitStateMap.put(k, true);
                        ct.setChecked(true);
                    }
                    LogUtils.i("bqc 当前状态是:" + ct.isChecked());
                    LogUtils.i("dialog checkview:" + "clickable:" + v.isClickable() + "   enable:" + v.isEnabled() + "   selected:" + v.isSelected() + "checked:" + ct.isChecked());
                }
            });
        }

        LogUtils.i("bqc setDialog");
        mHolder.hostname.setText(data.getHostName());
        mHolder.visitname.setText(data.getVisitName());
        if (null == data.getHostRank() || data.getHostRank().equals("")) {
            mHolder.hostrank.setText(data.getHostRank());
            mHolder.visitrank.setText(data.getVisitRank());
        } else {
            mHolder.hostrank.setText("[" + data.getHostRank() + "]");
            mHolder.visitrank.setText("[" + data.getVisitRank() + "]");
        }
        for (int i = 0; i < Configure_JC.FB_DIALOG_BQC.length; i++) {
            TextUtils.setTextSpan(mHolder.bqcCbs.get(i), (Configure_JC.FB_DIALOG_BQC[i] + "\n" + data.getSps().get(i)));
        }


        for (int i = 0; i < mHolder.bqcCbs.size(); i++) {
            // LogUtils.i("bqc 初始化状态"+data.getSpsState(i));
            final int k = i;
            CheckedTextView checkTextView = mHolder.bqcCbs.get(i);
            checkTextView.setChecked(JcDataUtils.getSpsState(data, i));
        }
    }

    public void showSfcDialog(Context context, MatchsBean data, final Handler handler) {
        LogUtils.i("dialog bqc");
        LogUtils.i("dialog context是否为空：" + (context == null) + ":" + context);
        if (mSfcDialog == null) {
            if (null == inflater) {
                inflater = LayoutInflater.from(context);
            }
            mData = data;

            LinearLayout mDialogView = (LinearLayout) inflater.inflate(R.layout.basketball_shenfenchag_dialog, null);
            mSfcDialog = new AlertDialog.Builder(context).create();
            mSfcDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    // TODO Auto-generated method stub
                    mSfcDialog = null;
                }
            });
            mSfcDialog.show();
            Window window = mSfcDialog.getWindow();
            window.setContentView(mDialogView);
            mSfcDialog.setCanceledOnTouchOutside(true);
            mHolder = new MixHolder();
            mHolder.hostname = (TextView) mDialogView.findViewById(R.id.dialog_base_homename);
            mHolder.hostrank = (TextView) mDialogView.findViewById(R.id.dialog_base_hos_rank);
            mHolder.visitname = (TextView) mDialogView.findViewById(R.id.dialog_base_visitname);
            mHolder.visitrank = (TextView) mDialogView.findViewById(R.id.dialog_base_visit_rankg);
            mHolder.sfcCbs = new ArrayList();
            mHolder.sfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sfc_0_ct));
            mHolder.sfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sfc_1_ct));
            mHolder.sfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sfc_2_ct));
            mHolder.sfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sfc_3_ct));
            mHolder.sfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sfc_4_ct));
            mHolder.sfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sfc_5_ct));
            mHolder.sfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sfc_6_ct));
            mHolder.sfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sfc_7_ct));
            mHolder.sfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sfc_8_ct));
            mHolder.sfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sfc_9_ct));
            mHolder.sfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sfc_10_ct));
            mHolder.sfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sfc_11_ct));
            Button cancel = (Button) mDialogView.findViewById(R.id.base_dialog_cancle_btn);
            Button submit = (Button) mDialogView.findViewById(R.id.base_dialog_submit_btn);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSfcDialog.dismiss();
                }
            });

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateDataByDingdanDialog(mSubmitStateMap, mData);
                    mSfcDialog.dismiss();
                    handler.sendEmptyMessage(0);
                }
            });

            setSfcDialogData(mData);
            LogUtils.i("bqc -showover");
        }
    }

    private void setSfcDialogData(MatchsBean data) {
        mSubmitStateMap.clear();
        //LogUtils.i("bqc 传递接受:"+data.getHostName()+"::"+data.getSpsState(0)+"::"+data.getSpsState(1));
        for (int i = 0; i < mHolder.sfcCbs.size(); i++) {
            final int k = i;
            final MatchsBean bean = data;
            mHolder.sfcCbs.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckedTextView ct = (CheckedTextView) v;
                    Boolean ischecked = ct.isChecked();
                    if (ischecked) {
                        mSubmitStateMap.put(k, false);
                        ct.setChecked(false);
                        TextUtils.setStrColorSizeCenter(mHolder.sfcCbs.get(k), (Configure_JC.FB_DIALOG_SFC_VALUE[k] + "\n" + bean.getSps().get(k)), Configure_JC.FB_DIALOG_SFC_VALUE[k], BaseApplication.getApplication().getResources().getColor(R.color.black));
                    } else {
                        mSubmitStateMap.put(k, true);
                        ct.setChecked(true);
                        TextUtils.setStrColorSizeCenter(mHolder.sfcCbs.get(k), (Configure_JC.FB_DIALOG_SFC_VALUE[k] + "\n" + bean.getSps().get(k)), Configure_JC.FB_DIALOG_SFC_VALUE[k], BaseApplication.getApplication().getResources().getColor(R.color.white));
                    }
                    LogUtils.i("bqc 当前状态是:" + ct.isChecked());
                    LogUtils.i("dialog checkview:" + "clickable:" + v.isClickable() + "   enable:" + v.isEnabled() + "   selected:" + v.isSelected() + "checked:" + ct.isChecked());
                }
            });
        }

        LogUtils.i("bqc setDialog");
        mHolder.hostname.setText(data.getHostName());
        mHolder.visitname.setText(data.getVisitName());
        if (null == data.getHostRank() || data.getHostRank().equals("")) {
            mHolder.hostrank.setText(data.getHostRank());
            mHolder.visitrank.setText(data.getVisitRank());
        } else {
            mHolder.hostrank.setText("[" + data.getHostRank() + "]");
            mHolder.visitrank.setText("[" + data.getVisitRank() + "]");
        }
        for (int i = 0; i < Configure_JC.FB_DIALOG_SFC_VALUE.length; i++) {
            LogUtils.i("sfc 当前状态是:" + mHolder.sfcCbs.get(i).isChecked());
            if (JcDataUtils.getSpsState(data, i)) {
                TextUtils.setStrColorSizeCenter(mHolder.sfcCbs.get(i), (Configure_JC.FB_DIALOG_SFC_VALUE[i] + "\n" + data.getSps().get(i)), Configure_JC.FB_DIALOG_SFC_VALUE[i], BaseApplication.getApplication().getResources().getColor(R.color.white));
            } else {
                TextUtils.setStrColorSizeCenter(mHolder.sfcCbs.get(i), (Configure_JC.FB_DIALOG_SFC_VALUE[i] + "\n" + data.getSps().get(i)), Configure_JC.FB_DIALOG_SFC_VALUE[i], BaseApplication.getApplication().getResources().getColor(R.color.black));
            }
        }

        for (int i = 0; i < mHolder.sfcCbs.size(); i++) {
            // LogUtils.i("bqc 初始化状态"+data.getSpsState(i));
            final int k = i;
            CheckedTextView checkTextView = mHolder.sfcCbs.get(i);
            checkTextView.setChecked(JcDataUtils.getSpsState(data, i));
        }
    }

    public void showMixBasketDialog(Context context, HashMap<Integer, MatchsBean> data, final Handler handler) {
        LogUtils.i("dialog context是否为空：" + (context == null) + ":" + context + ":::" + data.size());
        if (mMixBtDialog == null) {
            mixId = JcDataUtils.getIndicator(data);
            LogUtils.i("dialog mix" + mixId);
            if (null == inflater) {
                inflater = LayoutInflater.from(context);
            }
            mMixData = data;
            final LinearLayout mDialogView = (LinearLayout) inflater.inflate(R.layout.football_mix_bask_dialog, null);
            //  if (null == mMixBtDialog) {
            mMixBtDialog = new AlertDialog.Builder(context).create();
            mMixBtDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    // TODO Auto-generated method stub
                    mMixBtDialog = null;
                }
            });
            mMixBtDialog.show();
            Window window = mMixBtDialog.getWindow();
            window.setContentView(mDialogView);
            mMixBtDialog.setCanceledOnTouchOutside(true);
            mHolder = new MixHolder();

            mHolder.sfCbs = new ArrayList<>();
            mHolder.xsfcCbs = new ArrayList<>();
            mHolder.dxcCbs = new ArrayList<>();
            mHolder.sfcCbs = new ArrayList<>();
            mHolder.hostname = (TextView) mDialogView.findViewById(R.id.dialog_base_homename);
            mHolder.hostrank = (TextView) mDialogView.findViewById(R.id.dialog_base_hos_rank);
            mHolder.visitname = (TextView) mDialogView.findViewById(R.id.dialog_base_visitname);
            mHolder.visitrank = (TextView) mDialogView.findViewById(R.id.dialog_base_visit_rankg);
            mHolder.cancel = (Button) mDialogView.findViewById(R.id.base_dialog_cancle_btn);
            mHolder.submit = (Button) mDialogView.findViewById(R.id.base_dialog_submit_btn);
            mHolder.sfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sf_0_ct));
            mHolder.sfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sf_1_ct));
            mHolder.xsfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_xsf_0_ct));
            mHolder.xsfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_xsf_1_ct));
            mHolder.xsfTip = (TextView) mDialogView.findViewById(R.id.dialog_dxsf_2_ct);
            mHolder.dxcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_dx_0_ct));
            mHolder.dxcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_dx_1_ct));
            mHolder.dxTip = (TextView) mDialogView.findViewById(R.id.dialog_dx_2_ct);
            mHolder.sfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sfc_0_ct));
            mHolder.sfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sfc_1_ct));
            mHolder.sfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sfc_2_ct));
            mHolder.sfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sfc_3_ct));
            mHolder.sfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sfc_4_ct));
            mHolder.sfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sfc_5_ct));
            mHolder.sfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sfc_6_ct));
            mHolder.sfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sfc_7_ct));
            mHolder.sfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sfc_8_ct));
            mHolder.sfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sfc_9_ct));
            mHolder.sfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sfc_10_ct));
            mHolder.sfcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dialog_sfc_11_ct));

            LogUtils.i("mix dialog show" + (null == mHolder) + ":::" + mMixBtDialog);
            mHolder.cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mMixBtDialog)
                        mMixBtDialog.dismiss();
                }
            });

            mHolder.submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateDataBaskByMixDialog(mMixSubmitStateMap, mMixData);
                    handler.sendEmptyMessage(0);
                    if (null != mMixBtDialog)
                        mMixBtDialog.dismiss();
                }
            });
            mMixSubmitStateMap.put(Configure_JC.TAB_SF, new HashMap<Integer, Boolean>());
            mMixSubmitStateMap.put(Configure_JC.TAB_DXF, new HashMap<Integer, Boolean>());
            mMixSubmitStateMap.put(Configure_JC.TAB_SFC, new HashMap<Integer, Boolean>());
            mMixSubmitStateMap.put(Configure_JC.TAB_XSF, new HashMap<Integer, Boolean>());

            setMixBasketDialogData();
        }
    }

    public void setMixBasketDialogData() {
        LogUtils.i("setMixBasketDialogData");
        sfData = null;
        xsfData = null;
        dxData = null;
        sfcData = null;
        zjqData = null;

        if (null != mMixData.get(Configure_JC.TAB_SF)) {
            sfData = mMixData.get(Configure_JC.TAB_SF);
            msgData = mMixData.get(Configure_JC.TAB_SF);
        }
        if (null != mMixData.get(Configure_JC.TAB_XSF)) {
            xsfData = mMixData.get(Configure_JC.TAB_XSF);
            msgData = mMixData.get(Configure_JC.TAB_XSF);
        }
        if (null != mMixData.get(Configure_JC.TAB_DXF)) {
            dxData = mMixData.get(Configure_JC.TAB_DXF);
            msgData = mMixData.get(Configure_JC.TAB_DXF);
        }
        if (null != mMixData.get(Configure_JC.TAB_SFC)) {
            sfcData = mMixData.get(Configure_JC.TAB_SFC);
            msgData = mMixData.get(Configure_JC.TAB_SFC);
        }

        if (null == msgData.getHostRank() || msgData.getHostRank().equals("")) {
            mHolder.hostrank.setText(msgData.getHostRank());
            mHolder.visitrank.setText(msgData.getVisitRank());
        } else {
            mHolder.hostrank.setText("[" + msgData.getHostRank() + "]");
            mHolder.visitrank.setText("[" + msgData.getVisitRank() + "]");
        }
        mHolder.hostname.setText(msgData.getHostName());
        mHolder.visitname.setText(msgData.getVisitName());
        mSubmitStateMap.clear();

        for (int i = 0; i < mHolder.sfCbs.size(); i++) {
            if (null != sfData) {
                final int k = i;
                mHolder.sfCbs.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckedTextView ct = (CheckedTextView) v;
                        Boolean ischecked = ct.isChecked();
                        mSubmitStateMap = mMixSubmitStateMap.get(mixId);
                        LogUtils.i("dialog id:" + mixId + ":::");
                        if (mixId != Configure_JC.TAB_SF) {
                            if (null != mMixData.get(mixId)) {
                                for (int i = 0; i < mMixData.get(mixId).getSps().size(); i++) {
                                    mSubmitStateMap.put(i, false);
                                    getBaskCbsById(mixId).get(i).setChecked(false);
                                    if (mixId == Configure_JC.TAB_SFC) {
                                        updateSfcCheckBox(false, i);
                                    }
                                }
                            }
                            mMixSubmitStateMap.put(mixId, mSubmitStateMap);
                            mixId = Configure_JC.TAB_SF;
                        }
                        mSubmitStateMap = mMixSubmitStateMap.get(Configure_JC.TAB_SF);
                        if (ischecked) {
                            mSubmitStateMap.put(k, false);
                            mMixSubmitStateMap.put(Configure_JC.TAB_SF, mSubmitStateMap);
                            ct.setChecked(false);
                        } else {
                            mSubmitStateMap.put(k, true);
                            mMixSubmitStateMap.put(Configure_JC.TAB_SF, mSubmitStateMap);
                            ct.setChecked(true);
                        }
                    }
                });
            } else {
                mHolder.sfCbs.get(i).setClickable(false);
                TextUtils.setTextSpan(mHolder.sfCbs.get(i), "----");
            }
        }

        for (int i = 0; i < mHolder.xsfcCbs.size(); i++) {
            if (null != xsfData) {
                mHolder.xsfTip.setText("让分胜负（主" + xsfData.getHand() + "）");
                final int k = i;
                mHolder.xsfcCbs.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckedTextView ct = (CheckedTextView) v;
                        Boolean ischecked = ct.isChecked();
                        mSubmitStateMap = mMixSubmitStateMap.get(mixId);
                        LogUtils.i("dialog id:" + mixId + ":::");
                        if (mixId != Configure_JC.TAB_XSF) {
                            if (null != mMixData.get(mixId)) {
                                for (int i = 0; i < mMixData.get(mixId).getSps().size(); i++) {
                                    mSubmitStateMap.put(i, false);
                                    getBaskCbsById(mixId).get(i).setChecked(false);
                                    if (mixId == Configure_JC.TAB_SFC) {
                                        updateSfcCheckBox(false, i);
                                    }
                                }
                            }
                            mMixSubmitStateMap.put(mixId, mSubmitStateMap);
                            mixId = Configure_JC.TAB_XSF;
                        }
                        mSubmitStateMap = mMixSubmitStateMap.get(Configure_JC.TAB_XSF);
                        if (ischecked) {
                            mSubmitStateMap.put(k, false);
                            mMixSubmitStateMap.put(Configure_JC.TAB_XSF, mSubmitStateMap);
                            ct.setChecked(false);
                        } else {
                            mSubmitStateMap.put(k, true);
                            mMixSubmitStateMap.put(Configure_JC.TAB_XSF, mSubmitStateMap);
                            ct.setChecked(true);
                        }
                    }
                });
            } else {
                mHolder.xsfcCbs.get(i).setClickable(false);
                TextUtils.setTextSpan(mHolder.xsfcCbs.get(i), "----");
            }
        }

        for (int i = 0; i < mHolder.dxcCbs.size(); i++) {
            if (null != dxData) {
                mHolder.dxTip.setText("预设分（" + dxData.getHand() + "）");
                final int k = i;
                mHolder.dxcCbs.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckedTextView ct = (CheckedTextView) v;
                        Boolean ischecked = ct.isChecked();
                        mSubmitStateMap = mMixSubmitStateMap.get(mixId);
                        LogUtils.i("dialog id:" + mixId + ":::");
                        if (mixId != Configure_JC.TAB_DXF) {
                            if (null != mMixData.get(mixId)) {
                                for (int i = 0; i < mMixData.get(mixId).getSps().size(); i++) {
                                    mSubmitStateMap.put(i, false);
                                    getBaskCbsById(mixId).get(i).setChecked(false);
                                    if (mixId == Configure_JC.TAB_SFC) {
                                        updateSfcCheckBox(false, i);
                                    }
                                }
                            }
                            mMixSubmitStateMap.put(mixId, mSubmitStateMap);
                            mixId = Configure_JC.TAB_DXF;
                        }
                        mSubmitStateMap = mMixSubmitStateMap.get(Configure_JC.TAB_DXF);
                        if (ischecked) {
                            mSubmitStateMap.put(k, false);
                            mMixSubmitStateMap.put(Configure_JC.TAB_DXF, mSubmitStateMap);
                            ct.setChecked(false);
                        } else {
                            mSubmitStateMap.put(k, true);
                            mMixSubmitStateMap.put(Configure_JC.TAB_DXF, mSubmitStateMap);
                            ct.setChecked(true);
                        }
                    }
                });
            } else {
                mHolder.dxcCbs.get(i).setClickable(false);
                TextUtils.setTextSpan(mHolder.dxcCbs.get(i), "----");
            }
        }
        for (int i = 0; i < mHolder.sfcCbs.size(); i++) {
            if (null != sfcData) {
                final int k = i;
                mHolder.sfcCbs.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckedTextView ct = (CheckedTextView) v;
                        Boolean ischecked = ct.isChecked();
                        mSubmitStateMap = mMixSubmitStateMap.get(mixId);
                        LogUtils.i("dialog id:" + mixId + ":::");
                        if (mixId != Configure_JC.TAB_SFC) {
                            if (null != mMixData.get(mixId)) {
                                for (int i = 0; i < mMixData.get(mixId).getSps().size(); i++) {
                                    mSubmitStateMap.put(i, false);
                                    getBaskCbsById(mixId).get(i).setChecked(false);
                                }
                            }
                            mMixSubmitStateMap.put(mixId, mSubmitStateMap);
                            mixId = Configure_JC.TAB_SFC;
                        }
                        mSubmitStateMap = mMixSubmitStateMap.get(Configure_JC.TAB_SFC);
                        if (ischecked) {
                            mSubmitStateMap.put(k, false);
                            mMixSubmitStateMap.put(Configure_JC.TAB_SFC, mSubmitStateMap);
                            TextUtils.setStrColorSizeCenter(mHolder.sfcCbs.get(k), (Configure_JC.FB_DIALOG_SFC_VALUE[k] + "\n" + sfcData.getSps().get(k)), Configure_JC.FB_DIALOG_SFC_VALUE[k], BaseApplication.getApplication().getResources().getColor(R.color.black));
                            ct.setChecked(false);
                        } else {
                            mSubmitStateMap.put(k, true);
                            mMixSubmitStateMap.put(Configure_JC.TAB_SFC, mSubmitStateMap);
                            TextUtils.setStrColorSizeCenter(mHolder.sfcCbs.get(k), (Configure_JC.FB_DIALOG_SFC_VALUE[k] + "\n" + sfcData.getSps().get(k)), Configure_JC.FB_DIALOG_SFC_VALUE[k], BaseApplication.getApplication().getResources().getColor(R.color.white));
                            ct.setChecked(true);
                        }
                    }
                });
            } else {
                mHolder.sfcCbs.get(i).setClickable(false);
                TextUtils.setTextSpan(mHolder.sfcCbs.get(i), "----");
            }
        }

        if (null != sfData && mMixData.get(Configure_JC.TAB_SF).getSps() != null) {
            for (int i = 0; i < mMixData.get(Configure_JC.TAB_SF).getSps().size(); i++) {
                TextUtils.setTextSpan(mHolder.sfCbs.get(i), Configure_JC.FB_DIALOG_SF[i] + "    " + mMixData.get(Configure_JC.TAB_SF).getSps().get(i));
                mHolder.sfCbs.get(i).setChecked(JcDataUtils.getSpsState(mMixData.get(Configure_JC.TAB_SF), i));
            }
        }
        if (null != xsfData && mMixData.get(Configure_JC.TAB_XSF).getSps() != null) {
            for (int i = 0; i < mMixData.get(Configure_JC.TAB_XSF).getSps().size(); i++) {
                TextUtils.setTextSpan(mHolder.xsfcCbs.get(i), Configure_JC.FB_DIALOG_SF[i] + "    " + mMixData.get(Configure_JC.TAB_XSF).getSps().get(i));
                mHolder.xsfcCbs.get(i).setChecked(JcDataUtils.getSpsState(mMixData.get(Configure_JC.TAB_XSF), i));
            }
        }
        if (null != dxData && mMixData.get(Configure_JC.TAB_DXF).getSps() != null) {
            for (int i = 0; i < mMixData.get(Configure_JC.TAB_DXF).getSps().size(); i++) {
                TextUtils.setTextSpan(mHolder.dxcCbs.get(i), Configure_JC.FB_DIALOG_DX[i] + "   " + mMixData.get(Configure_JC.TAB_DXF).getSps().get(i));
                mHolder.dxcCbs.get(i).setChecked(JcDataUtils.getSpsState(mMixData.get(Configure_JC.TAB_DXF), i));
            }
        }
        if (null != sfcData && mMixData.get(Configure_JC.TAB_SFC).getSps() != null) {
            for (int i = 0; i < mMixData.get(Configure_JC.TAB_SFC).getSps().size(); i++) {
                LogUtils.i("soccerDialogutil sfcData:" + mHolder.sfcCbs.get(i) + ":::" + Configure_JC.FB_DIALOG_SFC[i]);
                mHolder.sfcCbs.get(i).setChecked(JcDataUtils.getSpsState(mMixData.get(Configure_JC.TAB_SFC), i));
                if (JcDataUtils.getSpsState(mMixData.get(Configure_JC.TAB_SFC), i)) {
                    TextUtils.setStrColorSizeCenter(mHolder.sfcCbs.get(i), (Configure_JC.FB_DIALOG_SFC_VALUE[i] + "\n" + mMixData.get(Configure_JC.TAB_SFC).getSps().get(i)), Configure_JC.FB_DIALOG_SFC_VALUE[i], BaseApplication.getApplication().getResources().getColor(R.color.white));
                } else {
                    TextUtils.setStrColorSizeCenter(mHolder.sfcCbs.get(i), (Configure_JC.FB_DIALOG_SFC_VALUE[i] + "\n" + mMixData.get(Configure_JC.TAB_SFC).getSps().get(i)), Configure_JC.FB_DIALOG_SFC_VALUE[i], BaseApplication.getApplication().getResources().getColor(R.color.black));
                }
            }
        }
    }

    private void updateSfcCheckBox(boolean isChecked, int i) {
        if (isChecked) {
            TextUtils.setStrColorSizeCenter(mHolder.sfcCbs.get(i), (Configure_JC.FB_DIALOG_SFC_VALUE[i] + "\n" + mMixData.get(Configure_JC.TAB_SFC).getSps().get(i)), Configure_JC.FB_DIALOG_SFC_VALUE[i], BaseApplication.getApplication().getResources().getColor(R.color.white));
        } else {
            TextUtils.setStrColorSizeCenter(mHolder.sfcCbs.get(i), (Configure_JC.FB_DIALOG_SFC_VALUE[i] + "\n" + mMixData.get(Configure_JC.TAB_SFC).getSps().get(i)), Configure_JC.FB_DIALOG_SFC_VALUE[i], BaseApplication.getApplication().getResources().getColor(R.color.black));
        }
    }

    public void showMixDialog(Context context, HashMap<Integer, MatchsBean> data, final Handler handler) {
        LogUtils.i("dialog context是否为空：" + (context == null) + ":" + context + ":::" + data.size());
        if (mMixDialog == null) {
            mixId = JcDataUtils.getIndicator(data);
            LogUtils.i("dialog mix" + mixId);
            if (null == inflater) {
                inflater = LayoutInflater.from(context);
            }
            mMixData = data;
            final LinearLayout mDialogView = (LinearLayout) inflater.inflate(R.layout.football_mix_dialog, null);
            // if (null == mMixDialog) {
            mMixDialog = new AlertDialog.Builder(context).create();
            mMixDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    // TODO Auto-generated method stub
                    mMixDialog = null;
                }
            });
            mMixDialog.show();
            Window window = mMixDialog.getWindow();
            window.setContentView(mDialogView);
            mMixDialog.setCanceledOnTouchOutside(true);
            mHolder = new MixHolder();

            mHolder.spfCbs = new ArrayList<>();
            mHolder.rqspfCbss = new ArrayList<>();
            mHolder.bfCbs = new ArrayList<>();
            mHolder.bqcCbs = new ArrayList<>();
            mHolder.zjqCbs = new ArrayList<>();

            mHolder.hostname = (TextView) mDialogView.findViewById(R.id.mix_dialog_tv_homename);
            mHolder.visitname = (TextView) mDialogView.findViewById(R.id.mix_dialog_tv_awayname);
            mHolder.hostrank = (TextView) mDialogView.findViewById(R.id.mix_dialog_tv_homestanding);
            mHolder.visitrank = (TextView) mDialogView.findViewById(R.id.mix_dialog_tv_awaystanding);
            mHolder.cancel = (Button) mDialogView.findViewById(R.id.base_dialog_cancle_btn);
            mHolder.submit = (Button) mDialogView.findViewById(R.id.base_dialog_submit_btn);

            mHolder.spfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_spf1));
            mHolder.spfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_spf2));
            mHolder.spfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_spf3));

            mHolder.rqspfCbss.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_rqspf1));
            mHolder.rqspfCbss.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_rqspf2));
            mHolder.rqspfCbss.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_rqspf3));

            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf1));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf2));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf3));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf4));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf5));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf6));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf7));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf8));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf9));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf10));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf11));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf12));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf13));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf14));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf15));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf16));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf17));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf18));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf19));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf20));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf21));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf22));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf23));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf24));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf25));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf26));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf27));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf28));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf29));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf30));
            mHolder.bfCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bf31));

            mHolder.zjqCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_zjq1));
            mHolder.zjqCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_zjq2));
            mHolder.zjqCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_zjq3));
            mHolder.zjqCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_zjq4));
            mHolder.zjqCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_zjq5));
            mHolder.zjqCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_zjq6));
            mHolder.zjqCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_zjq7));
            mHolder.zjqCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_zjq8));

            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bc1_mix));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bc2_mix));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bc3_mix));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bc4_mix));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bc5_mix));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bc6_mix));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bc7_mix));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bc8_mix));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.toggleButton_bc9_mix));
            LogUtils.i("mix dialog show" + (null == mHolder));
            mHolder.cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mMixDialog)
                        mMixDialog.dismiss();
                }
            });
            mHolder.submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateDataByMixDialog(mMixSubmitStateMap, mMixData);

                    handler.sendEmptyMessage(0);

                    if (null != mMixDialog)
                        mMixDialog.dismiss();
                }
            });
            mMixSubmitStateMap.put(Configure_JC.TAB_SPF, new HashMap<Integer, Boolean>());
            mMixSubmitStateMap.put(Configure_JC.TAB_BF, new HashMap<Integer, Boolean>());
            mMixSubmitStateMap.put(Configure_JC.TAB_ZJQ, new HashMap<Integer, Boolean>());
            mMixSubmitStateMap.put(Configure_JC.TAB_RQSFP, new HashMap<Integer, Boolean>());
            mMixSubmitStateMap.put(Configure_JC.TAB_BQC, new HashMap<Integer, Boolean>());
            setMixDialogData();

        }
    }

    public void setMixDialogData() {

        spfData = null;
        bfData = null;
        bqcData = null;
        rqspfData = null;
        zjqData = null;

        if (null != mMixData.get(Configure_JC.TAB_SPF)) {
            spfData = mMixData.get(Configure_JC.TAB_SPF);
            msgData = mMixData.get(Configure_JC.TAB_SPF);
        }
        if (null != mMixData.get(Configure_JC.TAB_BF)) {
            bfData = mMixData.get(Configure_JC.TAB_BF);
            msgData = mMixData.get(Configure_JC.TAB_BF);
        }
        if (null != mMixData.get(Configure_JC.TAB_BQC)) {
            bqcData = mMixData.get(Configure_JC.TAB_BQC);
            msgData = mMixData.get(Configure_JC.TAB_BQC);
        }
        if (null != mMixData.get(Configure_JC.TAB_RQSFP)) {
            rqspfData = mMixData.get(Configure_JC.TAB_RQSFP);
            msgData = mMixData.get(Configure_JC.TAB_RQSFP);
        }
        if (null != mMixData.get(Configure_JC.TAB_ZJQ)) {
            zjqData = mMixData.get(Configure_JC.TAB_ZJQ);
            msgData = mMixData.get(Configure_JC.TAB_ZJQ);
        }
        LogUtils.i("soccerDialogutil mixUtil:" + mHolder.hostrank + ":::" + msgData.getHostRank());
        if (null == msgData.getHostRank() || msgData.getHostRank().equals("")) {
            mHolder.hostrank.setText(msgData.getHostRank());
            mHolder.visitrank.setText(msgData.getVisitRank());
        } else {
            mHolder.hostrank.setText("[" + msgData.getHostRank() + "]");
            mHolder.visitrank.setText("[" + msgData.getVisitRank() + "]");
        }
        mHolder.hostname.setText(msgData.getHostName());
//        mHolder.hostrank.setText("[" + msgData.getHostRank() + "]");
        mHolder.visitname.setText(msgData.getVisitName());
//        mHolder.visitrank.setText("[" + msgData.getVisitRank() + "]");
        mSubmitStateMap.clear();

        for (int i = 0; i < mHolder.bfCbs.size(); i++) {
            if (null != bfData) {
                final int k = i;
                mHolder.bfCbs.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckedTextView ct = (CheckedTextView) v;
                        Boolean ischecked = ct.isChecked();
                        mSubmitStateMap = mMixSubmitStateMap.get(mixId);
                        LogUtils.i("dialog id:" + mixId + ":::");
                        if (mixId != Configure_JC.TAB_BF) {
                            if (null != mMixData.get(mixId)) {
                                for (int i = 0; i < mMixData.get(mixId).getSps().size(); i++) {
                                    mSubmitStateMap.put(i, false);
                                    getCbsById(mixId).get(i).setChecked(false);
                                }
                            }
                            mMixSubmitStateMap.put(mixId, mSubmitStateMap);
                            mixId = Configure_JC.TAB_BF;
                        }
                        mSubmitStateMap = mMixSubmitStateMap.get(Configure_JC.TAB_BF);
                        if (ischecked) {
                            mSubmitStateMap.put(k, false);
                            mMixSubmitStateMap.put(Configure_JC.TAB_BF, mSubmitStateMap);
                            ct.setChecked(false);
                        } else {
                            mSubmitStateMap.put(k, true);
                            mMixSubmitStateMap.put(Configure_JC.TAB_BF, mSubmitStateMap);
                            ct.setChecked(true);
                        }
                    }
                });
            } else {
                mHolder.bfCbs.get(i).setClickable(false);
                TextUtils.setTextSpan(mHolder.bfCbs.get(i), "----");
            }
        }

        for (int i = 0; i < mHolder.bqcCbs.size(); i++) {
            if (null != bqcData) {
                final int k = i;
                mHolder.bqcCbs.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckedTextView ct = (CheckedTextView) v;
                        Boolean ischecked = ct.isChecked();
                        mSubmitStateMap = mMixSubmitStateMap.get(mixId);
                        LogUtils.i("dialog id:" + mixId + ":::");
                        if (mixId != Configure_JC.TAB_BQC) {
                            if (null != mMixData.get(mixId)) {
                                for (int i = 0; i < mMixData.get(mixId).getSps().size(); i++) {
                                    mSubmitStateMap.put(i, false);
                                    getCbsById(mixId).get(i).setChecked(false);
                                }
                            }
                            mMixSubmitStateMap.put(mixId, mSubmitStateMap);
                            mixId = Configure_JC.TAB_BQC;
                        }
                        mSubmitStateMap = mMixSubmitStateMap.get(Configure_JC.TAB_BQC);
                        if (ischecked) {
                            mSubmitStateMap.put(k, false);
                            mMixSubmitStateMap.put(Configure_JC.TAB_BQC, mSubmitStateMap);
                            ct.setChecked(false);
                        } else {
                            mSubmitStateMap.put(k, true);
                            mMixSubmitStateMap.put(Configure_JC.TAB_BQC, mSubmitStateMap);
                            ct.setChecked(true);
                        }
                    }
                });
            } else {
                mHolder.bqcCbs.get(i).setClickable(false);
                TextUtils.setTextSpan(mHolder.bqcCbs.get(i), "----");
            }
        }

        for (int i = 0; i < mHolder.zjqCbs.size(); i++) {
            if (null != zjqData) {
                final int k = i;
                mHolder.zjqCbs.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckedTextView ct = (CheckedTextView) v;
                        Boolean ischecked = ct.isChecked();
                        mSubmitStateMap = mMixSubmitStateMap.get(mixId);
                        LogUtils.i("dialog id:" + mixId + ":::");
                        if (mixId != Configure_JC.TAB_ZJQ) {
                            if (null != mMixData.get(mixId)) {
                                for (int i = 0; i < mMixData.get(mixId).getSps().size(); i++) {
                                    mSubmitStateMap.put(i, false);
                                    getCbsById(mixId).get(i).setChecked(false);
                                }
                            }
                            mMixSubmitStateMap.put(mixId, mSubmitStateMap);
                            mixId = Configure_JC.TAB_ZJQ;
                        }
                        mSubmitStateMap = mMixSubmitStateMap.get(Configure_JC.TAB_ZJQ);
                        if (ischecked) {
                            mSubmitStateMap.put(k, false);
                            mMixSubmitStateMap.put(Configure_JC.TAB_ZJQ, mSubmitStateMap);
                            ct.setChecked(false);
                        } else {
                            mSubmitStateMap.put(k, true);
                            mMixSubmitStateMap.put(Configure_JC.TAB_ZJQ, mSubmitStateMap);
                            ct.setChecked(true);
                        }
                    }
                });
            } else {
                mHolder.zjqCbs.get(i).setClickable(false);
                TextUtils.setTextSpan(mHolder.zjqCbs.get(i), "----");
            }

        }
        for (int i = 0; i < mHolder.spfCbs.size(); i++) {
            if (null != spfData) {
                final int k = i;
                mHolder.spfCbs.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckedTextView ct = (CheckedTextView) v;
                        Boolean ischecked = ct.isChecked();
                        mSubmitStateMap = mMixSubmitStateMap.get(mixId);
                        LogUtils.i("dialog id:" + mixId + ":::");
                        if (mixId != Configure_JC.TAB_SPF) {
                            if (null != mMixData.get(mixId)) {
                                for (int i = 0; i < mMixData.get(mixId).getSps().size(); i++) {
                                    mSubmitStateMap.put(i, false);
                                    getCbsById(mixId).get(i).setChecked(false);
                                }
                            }
                            mMixSubmitStateMap.put(mixId, mSubmitStateMap);
                            mixId = Configure_JC.TAB_SPF;
                        }
                        mSubmitStateMap = mMixSubmitStateMap.get(Configure_JC.TAB_SPF);
                        if (ischecked) {
                            mSubmitStateMap.put(k, false);
                            mMixSubmitStateMap.put(Configure_JC.TAB_SPF, mSubmitStateMap);
                            ct.setChecked(false);
                        } else {
                            mSubmitStateMap.put(k, true);
                            mMixSubmitStateMap.put(Configure_JC.TAB_SPF, mSubmitStateMap);
                            ct.setChecked(true);
                        }
                    }
                });
            } else {
                mHolder.spfCbs.get(i).setClickable(false);
                TextUtils.setTextSpan(mHolder.spfCbs.get(i), "----");
            }

        }

        for (int i = 0; i < mHolder.rqspfCbss.size(); i++) {
            final int k = i;
            if (null != rqspfData) {
                mHolder.rqspfCbss.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckedTextView ct = (CheckedTextView) v;
                        Boolean ischecked = ct.isChecked();
                        mSubmitStateMap = mMixSubmitStateMap.get(mixId);
                        LogUtils.i("dialog id:" + mixId + ":::");
                        if (mixId != Configure_JC.TAB_RQSFP) {
                            if (null != mMixData.get(mixId)) {
                                for (int i = 0; i < mMixData.get(mixId).getSps().size(); i++) {
                                    mSubmitStateMap.put(i, false);
                                    getCbsById(mixId).get(i).setChecked(false);
                                }
                            }
                            mMixSubmitStateMap.put(mixId, mSubmitStateMap);
                            mixId = Configure_JC.TAB_RQSFP;
                        }
                        mSubmitStateMap = mMixSubmitStateMap.get(Configure_JC.TAB_RQSFP);
                        if (ischecked) {
                            mSubmitStateMap.put(k, false);
                            mMixSubmitStateMap.put(Configure_JC.TAB_RQSFP, mSubmitStateMap);
                            ct.setChecked(false);
                        } else {
                            mSubmitStateMap.put(k, true);
                            mMixSubmitStateMap.put(Configure_JC.TAB_RQSFP, mSubmitStateMap);
                            ct.setChecked(true);
                        }
                    }
                });
            } else {
                mHolder.rqspfCbss.get(i).setClickable(false);
                TextUtils.setTextSpan(mHolder.rqspfCbss.get(i), "----");
            }
        }
        if (null != bqcData) {
            for (int i = 0; i < mMixData.get(Configure_JC.TAB_BQC).getSps().size(); i++) {
                TextUtils.setTextSpan(mHolder.bqcCbs.get(i), Configure_JC.FB_DIALOG_BQC[i] + "\n" + mMixData.get(Configure_JC.TAB_BQC).getSps().get(i));
                mHolder.bqcCbs.get(i).setChecked(JcDataUtils.getSpsState(mMixData.get(Configure_JC.TAB_BQC), i));
            }
        }
        LogUtils.i("dingdan bf size:" + mMixData.get(Configure_JC.TAB_BF).getSps().size());
        if (null != bqcData) {
            for (int i = 0; i < mMixData.get(Configure_JC.TAB_BF).getSps().size(); i++) {
                TextUtils.setTextSpan(mHolder.bfCbs.get(i), Configure_JC.FB_DIALOG_BF[i] + "\n" + mMixData.get(Configure_JC.TAB_BF).getSps().get(i));
                mHolder.bfCbs.get(i).setChecked(JcDataUtils.getSpsState(mMixData.get(Configure_JC.TAB_BF), i));
            }
        }
        if (null != rqspfData) {
            for (int i = 0; i < mMixData.get(Configure_JC.TAB_RQSFP).getSps().size(); i++) {
                TextUtils.setTextSpan(mHolder.rqspfCbss.get(i), Configure_JC.FB_DIALOG_SPF[i] + "   " + mMixData.get(Configure_JC.TAB_RQSFP).getSps().get(i));
                mHolder.rqspfCbss.get(i).setChecked(JcDataUtils.getSpsState(mMixData.get(Configure_JC.TAB_RQSFP), i));
            }
        }
        if (null != spfData) {
            for (int i = 0; i < mMixData.get(Configure_JC.TAB_SPF).getSps().size(); i++) {
                TextUtils.setTextSpan(mHolder.spfCbs.get(i), Configure_JC.FB_DIALOG_SPF[i] + "   " + mMixData.get(Configure_JC.TAB_SPF).getSps().get(i));
                mHolder.spfCbs.get(i).setChecked(JcDataUtils.getSpsState(mMixData.get(Configure_JC.TAB_SPF), i));
            }
        }
        if (null != zjqData) {
            for (int i = 0; i < mMixData.get(Configure_JC.TAB_ZJQ).getSps().size(); i++) {
                TextUtils.setTextSpan(mHolder.zjqCbs.get(i), i + "  " + mMixData.get(Configure_JC.TAB_ZJQ).getSps().get(i));
                mHolder.zjqCbs.get(i).setChecked(JcDataUtils.getSpsState(mMixData.get(Configure_JC.TAB_ZJQ), i));
            }
        }
    }

    public void updateDataByMixDialog(HashMap<Integer, HashMap<Integer, Boolean>> stateMap, HashMap<Integer, MatchsBean> data) {
        updateDataByDingdanDialog(stateMap.get(Configure_JC.TAB_BQC), data.get(Configure_JC.TAB_BQC));
        updateDataByDingdanDialog(stateMap.get(Configure_JC.TAB_BF), data.get(Configure_JC.TAB_BF));
        updateDataByDingdanDialog(stateMap.get(Configure_JC.TAB_RQSFP), data.get(Configure_JC.TAB_RQSFP));
        updateDataByDingdanDialog(stateMap.get(Configure_JC.TAB_SPF), data.get(Configure_JC.TAB_SPF));
        updateDataByDingdanDialog(stateMap.get(Configure_JC.TAB_ZJQ), data.get(Configure_JC.TAB_ZJQ));
    }

    public void updateDataBaskByMixDialog(HashMap<Integer, HashMap<Integer, Boolean>> stateMap, HashMap<Integer, MatchsBean> data) {
        updateDataByDingdanDialog(stateMap.get(Configure_JC.TAB_DXF), data.get(Configure_JC.TAB_DXF));
        updateDataByDingdanDialog(stateMap.get(Configure_JC.TAB_SFC), data.get(Configure_JC.TAB_SFC));
        updateDataByDingdanDialog(stateMap.get(Configure_JC.TAB_SF), data.get(Configure_JC.TAB_SF));
        updateDataByDingdanDialog(stateMap.get(Configure_JC.TAB_XSF), data.get(Configure_JC.TAB_XSF));
    }

    public List<CheckedTextView> getCbsById(int id) {
        switch (id) {
            case Configure_JC.TAB_BF:
                return mHolder.bfCbs;

            case Configure_JC.TAB_BQC:
                return mHolder.bqcCbs;

            case Configure_JC.TAB_ZJQ:
                return mHolder.zjqCbs;

            case Configure_JC.TAB_SPF:
                return mHolder.spfCbs;

            case Configure_JC.TAB_RQSFP:
                return mHolder.rqspfCbss;
        }
        return null;
    }

    public List<CheckedTextView> getBaskCbsById(int id) {
        switch (id) {
            case Configure_JC.TAB_SF:
                return mHolder.sfCbs;

            case Configure_JC.TAB_XSF:
                return mHolder.xsfcCbs;

            case Configure_JC.TAB_DXF:
                return mHolder.dxcCbs;

            case Configure_JC.TAB_SFC:
                return mHolder.sfcCbs;
        }
        return null;
    }

    public void showBfDialog(Context context, MatchsBean data, final Handler handler) {
        LogUtils.i("dialog bf");
        LogUtils.i("dialog 是否为空：" + (mBfDialog == null) + ":" + context);
        if (mBfDialog == null) {
            if (null == inflater) {
                inflater = LayoutInflater.from(context);
            }
            mData = data;
            LinearLayout mDialogView = (LinearLayout) inflater.inflate(R
                    .layout.football_bifen_dialog, null);
            mBfDialog = new AlertDialog.Builder(context).create();
            mBfDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    // TODO Auto-generated method stub
                    mBfDialog = null;
                }
            });
            mBfDialog.show();
            Window window = mBfDialog.getWindow();
            window.setContentView(mDialogView);
            mBfDialog.setCanceledOnTouchOutside(true);
            mHolder = new MixHolder();

            mHolder.hostname = (TextView) mDialogView.findViewById(R.id.tv_homename);
            mHolder.hostrank = (TextView) mDialogView.findViewById(R.id.tv_homestanding);
            mHolder.visitname = (TextView) mDialogView.findViewById(R.id.tv_awayname);
            mHolder.visitrank = (TextView) mDialogView.findViewById(R.id.tv_awaystanding);
            mHolder.cancel = (Button) mDialogView.findViewById(R.id.base_dialog_cancle_btn);
            mHolder.submit = (Button) mDialogView.findViewById(R.id.base_dialog_submit_btn);

            mHolder.bqcCbs = new ArrayList();
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score1));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score2));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score3));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score4));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score5));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score6));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score7));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score8));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score9));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score10));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score11));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score12));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score13));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score14));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score15));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score16));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score17));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score18));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score19));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score20));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score21));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score22));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score23));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score24));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score25));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score26));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score27));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score28));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score29));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score30));
            mHolder.bqcCbs.add((CheckedTextView) mDialogView.findViewById(R.id.dc_score31));

            mHolder.cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBfDialog.dismiss();
                }
            });
            mHolder.submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateDataByDingdanDialog(mSubmitStateMap, mData);
                    mBfDialog.dismiss();
                    handler.sendEmptyMessage(0);
                }
            });
            setBfDialogData(data);
            LogUtils.i("bqc -showover");
        }
    }

    private void setBfDialogData(MatchsBean data) {
        mSubmitStateMap.clear();
        //LogUtils.i("bqc 传递接受:"+data.getHostName()+"::"+data.getSpsState(0)+"::"+data.getSpsState(1));
        for (int i = 0; i < mHolder.bqcCbs.size(); i++) {
            final int k = i;
            mHolder.bqcCbs.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckedTextView ct = (CheckedTextView) v;
                    Boolean ischecked = ct.isChecked();
                    if (ischecked) {
                        mSubmitStateMap.put(k, false);
                        ct.setChecked(false);

                    } else {
                        mSubmitStateMap.put(k, true);
                        ct.setChecked(true);
                    }
                    LogUtils.i("bqc 当前状态是:" + ct.isChecked());
                    LogUtils.i("dialog checkview:" + "clickable:" + v.isClickable() + "   enable:" + v.isEnabled() + "   selected:" + v.isSelected() + "checked:" + ct.isChecked());
                }
            });
        }

        LogUtils.i("bqc setDialog");
        if (null == data.getHostRank() || data.getHostRank().equals("")) {
            mHolder.hostrank.setText(data.getHostRank());
            mHolder.visitrank.setText(data.getVisitRank());
        } else {
            mHolder.hostrank.setText("[" + data.getHostRank() + "]");
            mHolder.visitrank.setText("[" + data.getVisitRank() + "]");
        }
        mHolder.hostname.setText(data.getHostName());
        //  mHolder.hostrank.setText("[" + data.getHostRank() + "]");
        mHolder.visitname.setText(data.getVisitName());
        //mHolder.visitrank.setText("[" + data.getVisitRank() + "]");
        for (int i = 0; i < Configure_JC.FB_DIALOG_BF.length; i++) {
            TextUtils.setTextSpan(mHolder.bqcCbs.get(i), Configure_JC.FB_DIALOG_BF[i] + "\n" + data.getSps().get(i));
        }

        for (int i = 0; i < mHolder.bqcCbs.size(); i++) {
            final int k = i;
            CheckedTextView checkTextView = mHolder.bqcCbs.get(i);
            checkTextView.setChecked(JcDataUtils.getSpsState(data, i));
        }
    }

    public static void removeDialog() {
        LogUtils.i("dialog remove");
        mMixDialog = null;
        mBfDialog = null;
        mBqcDialog = null;
        mZjqDialog = null;
        mMixBtDialog = null;
        mSfcDialog = null;
        if (null != mCheckTextViews) {
            mCheckTextViews.clear();
        }
    }

    class MixHolder {
        ArrayList<CheckedTextView> spfCbs;
        ArrayList<CheckedTextView> rqspfCbss;
        ArrayList<CheckedTextView> bfCbs;
        ArrayList<CheckedTextView> bqcCbs;
        ArrayList<CheckedTextView> zjqCbs;
        ArrayList<CheckedTextView> sfCbs;
        ArrayList<CheckedTextView> xsfcCbs;
        ArrayList<CheckedTextView> dxcCbs;
        ArrayList<CheckedTextView> sfcCbs;
        TextView hostname;
        TextView visitname;
        TextView hostrank;
        TextView visitrank;
        Button cancel;
        Button submit;
        TextView xsfTip;
        TextView dxTip;
    }


    public static void ShowXieYiDialog(Context context) {
        View mDiaglogView = UIUtils.inflate(R.layout.goucaixieyidialog);
        Button mAgreeXieYiBtn;
        final android.support.v7.app.AlertDialog mAlertDialog = new android.support.v7.app.AlertDialog.Builder(context).create();
        mAlertDialog.setView(mDiaglogView);
        mAlertDialog.show();
        mAgreeXieYiBtn = (Button) mDiaglogView.findViewById(R.id.agreexiyi_btn);
        mAgreeXieYiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAlertDialog.dismiss();
            }
        });
    }
}

