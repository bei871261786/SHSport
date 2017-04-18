package shlottery.gov.cn.lotterycenter.protool;

import android.content.Context;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import shlottery.gov.cn.lotterycenter.bean.IssueBonusNumberDetailBean;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/16 15:50
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class IssueBonusNumDetailProtocol extends BaseProtocol<IssueBonusNumberDetailBean> {

    public IssueBonusNumDetailProtocol(Context context) {
        super(context);
    }

    @Override
    protected LinkedHashMap<String, String> getHeadParams() {
        return null;
    }

    @Override
    protected String getDetailUrl() {
        return UrlManager.getBonusDetail;
    }

    @Override
    protected IssueBonusNumberDetailBean parseData(String json) {
        Gson mGson = new Gson();
        IssueBonusNumberDetailBean issueBonusBean = mGson.fromJson(json, IssueBonusNumberDetailBean.class);
        if (issueBonusBean.getRet() == 100) {
            String lotid = issueBonusBean.getData().getLotId();
            ArrayList<IssueBonusNumberDetailBean.DataBean.BonusListBean> listBeen = (ArrayList<IssueBonusNumberDetailBean.DataBean.BonusListBean>) issueBonusBean.getData().getBonusList();
            if (lotid.equals("dlt")) {
                return paraseLotto(issueBonusBean);
            }
            for (int i = 0; i < listBeen.size(); i++) {
                listBeen.get(i).setLotid(lotid);
            }
            issueBonusBean.getData().setBonusList(listBeen);
        }
        return issueBonusBean;
    }

    @Override
    protected boolean isNeedLogin() {
        return false;
    }

    private IssueBonusNumberDetailBean paraseLotto(IssueBonusNumberDetailBean issueBonusBean) {
        int flag = 0;
        String lotid = issueBonusBean.getData().getLotId();
        ArrayList<IssueBonusNumberDetailBean.DataBean.BonusListBean> listBeen = (ArrayList<IssueBonusNumberDetailBean.DataBean.BonusListBean>) issueBonusBean.getData().getBonusList();
        for (int i = 0; i < listBeen.size(); i++) {
            if (listBeen.get(i).getBonusName().contains("追加")) {
                flag = i;
                break;
            }
        }
        ArrayList<AddTemp> tempList = new ArrayList<>();
        for (int i = flag; i < listBeen.size(); i++) {
            AddTemp temp = new AddTemp();
            temp.setAddamaount(listBeen.get(i).getBonusAmount());
            temp.setAddMoney(listBeen.get(i).getBonusMoney());
            tempList.add(temp);
        }
        ArrayList<IssueBonusNumberDetailBean.DataBean.BonusListBean> newListbean = new ArrayList<>();

        for (int i = 0; i < flag; i++) {
            if (i >= tempList.size()) {
                LogUtils.i("parseData IBD templist  新添加:" + i + "::::");
                AddTemp temp = new AddTemp();
                temp.setAddamaount(0);
                temp.setAddMoney(0);
                tempList.add(temp);
            }
            LogUtils.i("parseData IBD templist:" + tempList.get(i).getAddamaount() + "::::" + tempList.get(i).getAddMoney());
            listBeen.get(i).setAddbonusAmount(tempList.get(i).getAddamaount());
            listBeen.get(i).setAddbonusMoney(tempList.get(i).getAddMoney());
            listBeen.get(i).setLotid(lotid);
            newListbean.add(listBeen.get(i));

        }
        issueBonusBean.getData().setBonusList(newListbean);

        return issueBonusBean;
    }

    class AddTemp {
        int addMoney;
        int addamaount;

        public int getAddamaount() {
            return addamaount;
        }

        public void setAddamaount(int addamaount) {
            this.addamaount = addamaount;
        }

        public int getAddMoney() {
            return addMoney;
        }

        public void setAddMoney(int addMoney) {
            this.addMoney = addMoney;
        }
    }
}
