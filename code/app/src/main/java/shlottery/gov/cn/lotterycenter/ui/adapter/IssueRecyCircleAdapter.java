package shlottery.gov.cn.lotterycenter.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.BaseBean;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;

import static android.support.v7.recyclerview.R.styleable.RecyclerView;
import static android.view.View.inflate;
import static shlottery.gov.cn.lotterycenter.Base.Configure.RECY_BLOCK;
import static shlottery.gov.cn.lotterycenter.Base.Configure.RECY_NORMAL_CIRCLR;
import static shlottery.gov.cn.lotterycenter.Base.Configure.RECY_SMALL_CIRCLR;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/10/27 17:48
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class IssueRecyCircleAdapter extends RecyclerView.Adapter<IssueRecyCircleAdapter.Holder> {
    private ArrayList<BaseBean> data;
    private Context context;
    private int id = 0;


    public IssueRecyCircleAdapter(Context context, ArrayList<BaseBean> data, int id) {
        this.context = context;
        this.data = data;
        this.id = id;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LogUtils.i("IBDetailNumberCircle onCreateViewHolder:");
        View view = null;
        if (id == RECY_NORMAL_CIRCLR) {
        view = inflate(context, R.layout.number_item_circle_issuenormal, null);
        } else if(id==RECY_SMALL_CIRCLR){
            view = View.inflate(context, R.layout.number_item_circle_issuesmall, null);
        }
        else if(id==RECY_BLOCK)
        {
            view = View.inflate(context, R.layout.number_item_block_issue, null);
        }
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        LogUtils.i("recyadapter onBindViewHolder"+data.get(position).getMsg());
        holder.option.setText(data.get(position).getMsg());
        holder.option.setEnabled(data.get(position).isRed());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    protected class Holder extends RecyclerView.ViewHolder {
        public TextView option;

        public Holder(View itemView) {
            super(itemView);
            option = (TextView) itemView.findViewById(R.id.issue_option);
        }
    }
}
