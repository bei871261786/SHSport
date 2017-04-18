package shlottery.gov.cn.lotterycenter.callback;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/4 14:22
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public interface LoadCallback<Data> {
    void onSucess(Data data);

    void onError();
}
