package shlottery.gov.cn.lotterycenter.bean.Jincai;

import java.util.List;

/**
 * Created by yongchao.bei on 2016/8/16.
 */
public class SchemePayBean {

    /**
     * ret : 100
     * data : {"stakeId":[478]}
     * msg : 1个单子提交投注
     */

    private int ret;
    private DataBean data;
    private String msg;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private List<Integer> stakeId;

        public List<Integer> getStakeId() {
            return stakeId;
        }

        public void setStakeId(List<Integer> stakeId) {
            this.stakeId = stakeId;
        }
    }
}
