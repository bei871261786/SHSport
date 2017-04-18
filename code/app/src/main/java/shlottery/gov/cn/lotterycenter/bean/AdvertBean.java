package shlottery.gov.cn.lotterycenter.bean;

import java.io.Serializable;
import java.util.List;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/18 18:01
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class AdvertBean implements Serializable {

    /**
     * ret : 100
     * data : {"pic":[{"picUrl":"http://139.224.133.50/pic/info/1001/837143.jpg","title":"广告测试1","linkType":2,"linkUrl":"","linkItem":"498","beginTime":1487779200,"endTime":1488391200}]}
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

    public static class DataBean implements Serializable{
        private List<PicBean> pic;

        public List<PicBean> getPic() {
            return pic;
        }

        public void setPic(List<PicBean> pic) {
            this.pic = pic;
        }

        public static class PicBean implements Serializable{
            /**
             * picUrl : http://139.224.133.50/pic/info/1001/837143.jpg
             * title : 广告测试1
             * linkType : 2
             * linkUrl :
             * linkItem : 498
             * beginTime : 1487779200
             * endTime : 1488391200
             */

            private String picUrl;
            private String title;
            private int linkType;
            private String linkUrl;
            private String linkItem;
            private int beginTime;
            private int endTime;

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getLinkType() {
                return linkType;
            }

            public void setLinkType(int linkType) {
                this.linkType = linkType;
            }

            public String getLinkUrl() {
                return linkUrl;
            }

            public void setLinkUrl(String linkUrl) {
                this.linkUrl = linkUrl;
            }

            public String getLinkItem() {
                return linkItem;
            }

            public void setLinkItem(String linkItem) {
                this.linkItem = linkItem;
            }

            public int getBeginTime() {
                return beginTime;
            }

            public void setBeginTime(int beginTime) {
                this.beginTime = beginTime;
            }

            public int getEndTime() {
                return endTime;
            }

            public void setEndTime(int endTime) {
                this.endTime = endTime;
            }
        }
    }
}
