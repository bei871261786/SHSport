package shlottery.gov.cn.lotterycenter.bean;

import java.util.List;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/12/7 15:02
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class NewsListBean {
    /**
     * ret : 100
     * data : {"count":3,"pageNo":1,"pageCount":1,"list":[{"id":3,"title":"坚守车牌号近两年 彩民喜中排列五200万大奖","summary":"","tags":"排列五","picUrl":"http://vpn.gooooal.com/shtcapi/pic/1001/573043.jpg","newsTime":"2016-12-01","source":"上海体育"},{"id":2,"title":"[浦东]新区召开超级大乐透 ","summary":"","tags":"浦东新区","picUrl":"http://vpn.gooooal.com/shtcapi/pic/1001/177002.jpg","newsTime":"2016-11-18","source":"上海体育"},{"id":1,"title":"公益金大小兼顾 体彩冠名支持多项全国业余赛事","tags":"大乐透","picUrl":"http://vpn.gooooal.com/shtcapi/pic/1001/115001.jpg","newsTime":"2016-11-01","source":"上海体育"}]}
     */

    private int ret;
    /**
     * count : 3
     * pageNo : 1
     * pageCount : 1
     * list : [{"id":3,"title":"坚守车牌号近两年 彩民喜中排列五200万大奖","summary":"","tags":"排列五","picUrl":"http://vpn.gooooal.com/shtcapi/pic/1001/573043.jpg","newsTime":"2016-12-01","source":"上海体育"},{"id":2,"title":"[浦东]新区召开超级大乐透 ","summary":"","tags":"浦东新区","picUrl":"http://vpn.gooooal.com/shtcapi/pic/1001/177002.jpg","newsTime":"2016-11-18","source":"上海体育"},{"id":1,"title":"公益金大小兼顾 体彩冠名支持多项全国业余赛事","tags":"大乐透","picUrl":"http://vpn.gooooal.com/shtcapi/pic/1001/115001.jpg","newsTime":"2016-11-01","source":"上海体育"}]
     */

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
        private int count;
        private int pageNo;
        private int pageCount;
        /**
         * id : 3
         * title : 坚守车牌号近两年 彩民喜中排列五200万大奖
         * summary :
         * tags : 排列五
         * picUrl : http://vpn.gooooal.com/shtcapi/pic/1001/573043.jpg
         * newsTime : 2016-12-01
         * source : 上海体育
         */

        private List<ListBean> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int id;
            private String title;
            private String summary;
            private String tags;
            private String picUrl;
            private String newsTime;
            private String source;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
                this.tags = tags;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public String getNewsTime() {
                return newsTime;
            }

            public void setNewsTime(String newsTime) {
                this.newsTime = newsTime;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }
        }
    }
}
