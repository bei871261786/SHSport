package shlottery.gov.cn.lotterycenter.ui.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
import pub.devrel.easypermissions.EasyPermissions;
import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.bean.CheckVersionBean;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.ui.view.HorizontalProgressBarWithNumber;
import shlottery.gov.cn.lotterycenter.ui.widget.LoadingDialog;
import shlottery.gov.cn.lotterycenter.utils.DataCleanManager;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.PackageUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-16-0016 14:30
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class SystemSettingActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    @BindView(R.id.titlebar_back_ll)
    LinearLayout titlebarBackLl;
    @BindView(R.id.titlebar_tv)
    TextView titlebarTv;
    @BindView(R.id.titlebar_indicator)
    ImageView titlebarIndicator;
    @BindView(R.id.titlebar_indicatordown)
    ImageView titlebarIndicatordown;
    @BindView(R.id.titlebar_title)
    LinearLayout titlebarTitle;
    @BindView(R.id.filtrate_tv)
    ImageView filtrateTv;
    @BindView(R.id.main_calendar)
    ImageView mainCalendar;
    @BindView(R.id.main_setting)
    ImageView mainSetting;
    @BindView(R.id.base_titleBar)
    RelativeLayout baseTitleBar;
    @BindView(R.id.send_setting_tv)
    TextView sendSettingTv;
    @BindView(R.id.usephoto_right_im)
    ImageView usephotoRightIm;
    @BindView(R.id.send_setting_rl)
    RelativeLayout sendSettingRl;
    @BindView(R.id.default_match_tv)
    TextView defaultMatchTv;
    @BindView(R.id.default_match_im)
    ImageView defaultMatchIm;
    @BindView(R.id.default_matchname_tv)
    TextView defaultMatchnameTv;
    @BindView(R.id.default_match_rl)
    RelativeLayout defaultMatchRl;
    @BindView(R.id.tip_setting_tv)
    TextView tipSettingTv;
    @BindView(R.id.tip_setting_im)
    ImageView tipSettingIm;
    @BindView(R.id.tip_setting_rl)
    RelativeLayout tipSettingRl;
    @BindView(R.id.videosetting_tv)
    TextView videosettingTv;
    @BindView(R.id.videosetting_im)
    ImageView videosettingIm;
    @BindView(R.id.videosetting_name_rl)
    TextView videosettingNameRl;
    @BindView(R.id.videosetting_rl)
    RelativeLayout videosettingRl;
    @BindView(R.id.cache_setting_tv)
    TextView cacheSettingTv;
    @BindView(R.id.cache_setting_im)
    ImageView cacheSettingIm;
    @BindView(R.id.cache_size_tv)
    TextView cacheSizeTv;
    @BindView(R.id.cache_setting_rl)
    RelativeLayout cacheSettingRl;
    @BindView(R.id.new_version_tv)
    TextView newVersionTv;
    @BindView(R.id.new_versionnum_tv)
    TextView newVersionnumTv;
    @BindView(R.id.new_version_rl)
    RelativeLayout newVersionRl;


    private LoadingDialog loadingDialog;//加载框

    HorizontalProgressBarWithNumber horizontalProgressBarWithNumber;

    Dialog mDownNewDialog;

    // 外存sdcard存放路径 新版app下载所在位置
    private static final String FILE_PATH = Environment.getExternalStorageDirectory() + File.separator + "download" + File.separator;
    // 下载应用存放全路径
    private static final String FILE_NAME = FILE_PATH + "SHSportsLottery.apk";
    String cache;
    private String mDownloadUrl;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        titlebarTv.setText("系统设置");
        newVersionnumTv.setText(PackageUtils.getVersionName());
//        defaultMatchRl.setVisibility(View.GONE);
//        tipSettingRl.setVisibility(View.GONE);
//        videosettingRl.setVisibility(View.GONE);
        updateCache();
    }

    //计算缓存大小并更新到ui
    private void updateCache() {
        try {
            cache = DataCleanManager.getTotalCacheSize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cache.equals("0K")) {
            UIUtils.showToastSafe("清理成功!");
        }
        cacheSizeTv.setText(cache);
    }

    @Override
    protected void init() {

    }

    //返回
    @OnClick(R.id.titlebar_back_ll)
    void back() {
        finish();
    }

    //点击推送设置
    @OnClick(R.id.send_setting_rl)
    void photoClick() {
        startActivity(new Intent(this, SendSettingActivity.class));
    }

    //点击清除缓存
    @OnClick(R.id.cache_setting_rl)
    void cacheClick() {
        DataCleanManager.clearAllCache(this);
        updateCache();
    }

    //版本升级
    @OnClick(R.id.new_version_rl)
    void checkNewVersion() {
        loadingDialog = new LoadingDialog(this, "拼命检测中...");
        int type = 2;//type=2表示android
        String verNum = PackageUtils.getVersionCode() + "";//版本号
        LogUtils.i("checkNewVersion verNum:" + verNum);
        OkGo.get(UrlManager.chkVersion(this, type, verNum)).cacheMode(CacheMode.NO_CACHE).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                CheckVersionBean checkVersionBean = gson.fromJson(s, CheckVersionBean.class);
                if (checkVersionBean.getRet() == 100) {
                    if (checkVersionBean.getData().isNewVersion()) {
                        newVersionDialog(checkVersionBean);
                    } else {
                        UIUtils.showToastSafe("已经是最新版本");
                    }
                } else {
                    UIUtils.showToastSafe(checkVersionBean.getMsg());
                }
                loadingDialog.close();
            }

            @Override
            public void onBefore(BaseRequest request) {
                super.onBefore(request);
                loadingDialog.show();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                UIUtils.showToastSafe("网络异常,请检查网络");
                loadingDialog.close();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity销毁时，取消网络请求
        OkGo.getInstance().cancelTag(SystemSettingActivity.this);
    }

    //版本升级的dialog
    public void newVersionDialog(final CheckVersionBean checkVersionBean) {
        LogUtils.i("SystemSetting newVersionDialog");
        View mDialogView = LayoutInflater.from(this).inflate(R.layout.dingdan_football_backdialog, null);
        final Dialog mDialog = new android.app.AlertDialog.Builder(this).create();
        mDialog.show();
        Window window = mDialog.getWindow();
        window.setContentView(mDialogView);
        mDialog.setCanceledOnTouchOutside(true);
        Button cancel = (Button) mDialogView.findViewById(R.id.base_dialog_cancle_btn);
        Button querrn = (Button) mDialogView.findViewById(R.id.base_dialog_submit_btn);
        TextView textView = (TextView) mDialogView.findViewById(R.id.rowcontainer_spf);
        TextView titleView = (TextView) mDialogView.findViewById(R.id.title_dialog);
        titleView.setText("版本升级");
        textView.setText("检测到新版本,是否马上升级?");
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        querrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDownloadUrl = checkVersionBean.getData().getDownUrl();
                LogUtils.i("SystemSetting querrn:"+mDownloadUrl);
                if (mDownloadUrl != null || !mDownloadUrl.isEmpty()) {
                    checkPermission();
                } else {
                    UIUtils.showToastSafe("暂无新版本");
                }
                mDialog.dismiss();
            }
        });
    }

    //正在更新的dialog
    public void startnewVerDialog() {
        LogUtils.i("SystemSetting startnewVerDialog");
        View mDialogView = LayoutInflater.from(this).inflate(R.layout.down_newversion_dialog, null);
        mDownNewDialog = new android.app.AlertDialog.Builder(this).create();
        mDownNewDialog.show();
        Window window = mDownNewDialog.getWindow();
        window.setContentView(mDialogView);
        mDownNewDialog.setCanceledOnTouchOutside(true);
        Button cancel = (Button) mDialogView.findViewById(R.id.download_cancle_bt);
        // Button querrn = (Button) mDialogView.findViewById(R.id.base_dialog_submit_btn);
        horizontalProgressBarWithNumber = (HorizontalProgressBarWithNumber) mDialogView.findViewById(R.id.down_progress);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDownNewDialog.cancel();
                mDownNewDialog.dismiss();
            }
        });
        mDownNewDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                OkGo.getInstance().cancelTag(SystemSettingActivity.this);//dialog消失,取消网络请求
            }
        });
    }

    //下载新版本
    public void downloadNewVersion(String url) {
        LogUtils.i("SystemSetting downloadNewVersion");
        OkGo.get(url)//
                .tag(SystemSettingActivity.this)
                //.params("param1", "paramValue1")//
                .execute(new FileCallback("SHSportsLottery.apk") {
                    @Override
                    public void onBefore(BaseRequest request) {
                        startnewVerDialog();
                    }

                    @Override
                    public void onSuccess(File file, Call call, Response response) {
                        if (mDownNewDialog != null) {
                            mDownNewDialog.cancel();
                        }
                        installApp(SystemSettingActivity.this, FILE_NAME);
                        UIUtils.showToastSafe("下载完成");
                    }

                    @Override
                    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        String downloadLength = Formatter.formatFileSize(getApplicationContext(), currentSize);
                        String totalLength = Formatter.formatFileSize(getApplicationContext(), totalSize);
                        String netSpeed = Formatter.formatFileSize(getApplicationContext(), networkSpeed);
                        horizontalProgressBarWithNumber.setMax(100);
                        horizontalProgressBarWithNumber.setProgress((int) (progress * 100));
                    }

                    @Override
                    public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                        super.onError(call, response, e);
                        LogUtils.i("SystemSetting onError" + e + ":::" + response);
                        UIUtils.showToastSafe("下载出错");
                        if (mDownNewDialog != null) {
                            mDownNewDialog.cancel();
                        }
                    }
                });
    }

    private void checkPermission() {
        LogUtils.i("SystemSetting checkPermission");
        //申请sd文件读取卡权限
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {//检查是否获取该权限
            LogUtils.d("sd卡已授权");
            downloadNewVersion(mDownloadUrl);
        } else {
            //第二个参数是被拒绝后再次申请该权限的解释
            //第三个参数是请求码
            //第四个参数是要申请的权限
            EasyPermissions.requestPermissions(this, "需要读写内存卡权限", 0, perms);
        }
    }

    /**
     * 安装新版本应用
     */
    private void installApp(Context context, String FILE_NAME) {
        File appFile = new File(FILE_NAME);
        if (!appFile.exists()) {
            return;
        }
        // 跳转到新版本应用安装页面
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + appFile.toString()), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    @Override
    protected String getBaidutitle() {
        return "系统设置";
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //把申请权限的回调交由EasyPermissions处理
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //下面两个方法是实现EasyPermissions的EasyPermissions.PermissionCallbacks接口
    //分别返回授权成功和失败的权限
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        LogUtils.i("SystemSetting onPermissionsGranted");
        LogUtils.d("获取成功的权限" + perms);
        downloadNewVersion(mDownloadUrl);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        LogUtils.d("获取失败的权限" + perms);
        UIUtils.showToastSafe("权限获取失败,不能下载");
    }
}
