package shlottery.gov.cn.lotterycenter.callback;

import android.view.View;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2017/3/27 09:36
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public interface DispatchListener {
   void dispatch(View view,int x, int y, int oldx, int oldy);
}
