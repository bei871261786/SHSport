package shlottery.gov.cn.lotterycenter.bean;

import java.util.List;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/12/6 17:57
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class SuperGGuestBean {
    /**
     * ret : 100
     * data : {"guest":["黄宇龙","韩益忠","朱沁沁"]}
     */

    private int ret;
    private DataBean data;

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

    public static class DataBean {
        private List<String> guest;

        public List<String> getGuest() {
            return guest;
        }

        public void setGuest(List<String> guest) {
            this.guest = guest;
        }
    }
}
