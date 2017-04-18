package shlottery.gov.cn.lotterycenter.bean;

import java.util.List;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/12/13 18:13
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class RmdFamousListBean {

    /**
     * ret : 100
     * data : {"count":5,"pageNo":1,"pageCount":1,"list":[{"id":6,"name":"韩益忠","logo":"http://vpn.gooooal.com/shtcapi/pic/1001/447005.jpg","lotId":"jlrsf","lotName":"竞彩篮球 让分胜负","issueNo":"20161212","playType":"4串5","amount":5,"multiple":1,"totalMoney":10,"validTime":1481731200},{"id":5,"name":"黄宇龙","logo":"http://vpn.gooooal.com/shtcapi/pic/1001/546004.jpg","lotId":"jldx","lotName":"竞彩篮球 大小分","issueNo":"20161212","playType":"4串4","amount":12,"multiple":1,"totalMoney":24,"validTime":1481731200},{"id":4,"name":"韩益忠","logo":"http://vpn.gooooal.com/shtcapi/pic/1001/447005.jpg","lotId":"sf14","lotName":"胜负彩 14场","issueNo":"2016193","playType":"14场","amount":384,"multiple":1,"totalMoney":768,"validTime":1481731200},{"id":3,"name":"黄宇龙","logo":"http://vpn.gooooal.com/shtcapi/pic/1001/546004.jpg","lotId":"jzxspf","lotName":"竞彩足球 让球胜平负","issueNo":"20161212","playType":"3串1","amount":1,"multiple":1,"totalMoney":2,"validTime":1481731200},{"id":2,"name":"韩益忠","logo":"http://vpn.gooooal.com/shtcapi/pic/1001/447005.jpg","lotId":"jqc","lotName":"进球彩","issueNo":"2016199","playType":"进球彩","amount":4,"multiple":1,"totalMoney":8,"validTime":1481731200}]}
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
        /**
         * count : 5
         * pageNo : 1
         * pageCount : 1
         * list : [{"id":6,"name":"韩益忠","logo":"http://vpn.gooooal.com/shtcapi/pic/1001/447005.jpg","lotId":"jlrsf","lotName":"竞彩篮球 让分胜负","issueNo":"20161212","playType":"4串5","amount":5,"multiple":1,"totalMoney":10,"validTime":1481731200},{"id":5,"name":"黄宇龙","logo":"http://vpn.gooooal.com/shtcapi/pic/1001/546004.jpg","lotId":"jldx","lotName":"竞彩篮球 大小分","issueNo":"20161212","playType":"4串4","amount":12,"multiple":1,"totalMoney":24,"validTime":1481731200},{"id":4,"name":"韩益忠","logo":"http://vpn.gooooal.com/shtcapi/pic/1001/447005.jpg","lotId":"sf14","lotName":"胜负彩 14场","issueNo":"2016193","playType":"14场","amount":384,"multiple":1,"totalMoney":768,"validTime":1481731200},{"id":3,"name":"黄宇龙","logo":"http://vpn.gooooal.com/shtcapi/pic/1001/546004.jpg","lotId":"jzxspf","lotName":"竞彩足球 让球胜平负","issueNo":"20161212","playType":"3串1","amount":1,"multiple":1,"totalMoney":2,"validTime":1481731200},{"id":2,"name":"韩益忠","logo":"http://vpn.gooooal.com/shtcapi/pic/1001/447005.jpg","lotId":"jqc","lotName":"进球彩","issueNo":"2016199","playType":"进球彩","amount":4,"multiple":1,"totalMoney":8,"validTime":1481731200}]
         */

        private int count;
        private int pageNo;
        private int pageCount;
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
            /**
             * id : 6
             * name : 韩益忠
             * logo : http://vpn.gooooal.com/shtcapi/pic/1001/447005.jpg
             * lotId : jlrsf
             * lotName : 竞彩篮球 让分胜负
             * issueNo : 20161212
             * playType : 4串5
             * amount : 5
             * multiple : 1
             * totalMoney : 10
             * validTime : 1481731200
             */

            private int id;
            private String name;
            private String logo;
            private String lotId;
            private String lotName;
            private String issueNo;
            private String playType;
            private int amount;
            private int multiple;
            private int totalMoney;
            private int validTime;
            private double bonusEstimate;

            public double getBonusEstimate() {
                return bonusEstimate;
            }

            public void setBonusEstimate(double bonusEstimate) {
                this.bonusEstimate = bonusEstimate;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getLotId() {
                return lotId;
            }

            public void setLotId(String lotId) {
                this.lotId = lotId;
            }

            public String getLotName() {
                return lotName;
            }

            public void setLotName(String lotName) {
                this.lotName = lotName;
            }

            public String getIssueNo() {
                return issueNo;
            }

            public void setIssueNo(String issueNo) {
                this.issueNo = issueNo;
            }

            public String getPlayType() {
                return playType;
            }

            public void setPlayType(String playType) {
                this.playType = playType;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public int getMultiple() {
                return multiple;
            }

            public void setMultiple(int multiple) {
                this.multiple = multiple;
            }

            public int getTotalMoney() {
                return totalMoney;
            }

            public void setTotalMoney(int totalMoney) {
                this.totalMoney = totalMoney;
            }

            public int getValidTime() {
                return validTime;
            }

            public void setValidTime(int validTime) {
                this.validTime = validTime;
            }
        }
    }
}
