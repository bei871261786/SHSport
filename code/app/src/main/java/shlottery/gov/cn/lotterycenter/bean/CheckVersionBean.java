package shlottery.gov.cn.lotterycenter.bean;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-21-0021 17:13
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class CheckVersionBean {

    /**
     * ret : 100
     * msg : APP版本错误
     * data : {"newVersion":false,"verNum":2,"downUrl":"http://vpn.gooooal.com/shtcapi/download/app.apk","forceUpdate":true}
     */

    private int ret;
    private String msg;
    private DataBean data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * newVersion : false
         * verNum : 2
         * downUrl : http://vpn.gooooal.com/shtcapi/download/app.apk
         * forceUpdate : true
         */

        private boolean newVersion;
        private int verNum;
        private String downUrl;
        private boolean forceUpdate;

        public boolean isNewVersion() {
            return newVersion;
        }

        public void setNewVersion(boolean newVersion) {
            this.newVersion = newVersion;
        }

        public int getVerNum() {
            return verNum;
        }

        public void setVerNum(int verNum) {
            this.verNum = verNum;
        }

        public String getDownUrl() {
            return downUrl;
        }

        public void setDownUrl(String downUrl) {
            this.downUrl = downUrl;
        }

        public boolean isForceUpdate() {
            return forceUpdate;
        }

        public void setForceUpdate(boolean forceUpdate) {
            this.forceUpdate = forceUpdate;
        }
    }
}
