package shlottery.gov.cn.lotterycenter.bean;

import java.util.List;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/12/16 14:58
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class MyQuestionsBean {
    /**
     * ret : 100
     * data : {"count":1,"pageNo":1,"pageCount":1,"list":[{"id":4,"question":"大乐透有几个字","questionTime":1481871678}]}
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
         * count : 1
         * pageNo : 1
         * pageCount : 1
         * list : [{"id":4,"question":"大乐透有几个字","questionTime":1481871678}]
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
             * id : 4
             * question : 大乐透有几个字
             * questionTime : 1481871678
             */

            private int id;
            private String question;
            private String answer;
            private int questionTime;

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getQuestion() {
                return question;
            }

            public void setQuestion(String question) {
                this.question = question;
            }

            public int getQuestionTime() {
                return questionTime;
            }

            public void setQuestionTime(int questionTime) {
                this.questionTime = questionTime;
            }
        }
    }
}
