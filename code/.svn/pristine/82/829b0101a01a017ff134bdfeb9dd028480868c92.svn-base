package shlottery.gov.cn.lotterycenter.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import shlottery.gov.cn.lotterycenter.Base.BaseActivity;
import shlottery.gov.cn.lotterycenter.Base.Configure;
import shlottery.gov.cn.lotterycenter.R;
import shlottery.gov.cn.lotterycenter.aplication.BaseApplication;
import shlottery.gov.cn.lotterycenter.bean.NicknameBean;
import shlottery.gov.cn.lotterycenter.bean.UserInfoBean;
import shlottery.gov.cn.lotterycenter.bean.VcodeBean;
import shlottery.gov.cn.lotterycenter.callback.LoadCallback;
import shlottery.gov.cn.lotterycenter.manager.UrlManager;
import shlottery.gov.cn.lotterycenter.network.ParamsHelperInterface;
import shlottery.gov.cn.lotterycenter.protool.NicknameProtocol;
import shlottery.gov.cn.lotterycenter.protool.ResetUserinfoProtocol;
import shlottery.gov.cn.lotterycenter.service.UpdateUserinfoService;
import shlottery.gov.cn.lotterycenter.ui.view.CircleImageView;
import shlottery.gov.cn.lotterycenter.utils.FileUtils;
import shlottery.gov.cn.lotterycenter.utils.LogUtils;
import shlottery.gov.cn.lotterycenter.utils.MyCountDownTimer;
import shlottery.gov.cn.lotterycenter.utils.PrefUtils;
import shlottery.gov.cn.lotterycenter.utils.RegexUtils;
import shlottery.gov.cn.lotterycenter.utils.SharedPreferencesUtils;
import shlottery.gov.cn.lotterycenter.utils.StringUtils;
import shlottery.gov.cn.lotterycenter.utils.ToastUtils;
import shlottery.gov.cn.lotterycenter.utils.UIUtils;

import static shlottery.gov.cn.lotterycenter.R.color.black_gray1;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-16-0016 10:30
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class UserDataActivity extends BaseActivity implements LoadCallback, EasyPermissions.PermissionCallbacks {
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
    @BindView(R.id.usephoto_right_im)
    ImageView usephotoRightIm;
    @BindView(R.id.userinfo_icon_iv)
    CircleImageView userinfoIconIv;
    @BindView(R.id.userinfo_userphoto_rl)
    RelativeLayout userinfoUserphotoRl;
    @BindView(R.id.usenick_right_im)
    ImageView usenickRightIm;
    @BindView(R.id.userinfo_nickname_tv)
    TextView userinfoNicknameTv;
    @BindView(R.id.userinfo_idNumber_tv)
    TextView userinfoidNumbertv;
    @BindView(R.id.userinfo_realName_tv)
    TextView userinforealNametv;
    @BindView(R.id.userinfo_nickname_rl)
    RelativeLayout userinfoNicknameRl;
    @BindView(R.id.bindphone_rl)
    RelativeLayout bindphoneRl;
    @BindView(R.id.userpsw_right_im)
    ImageView userpswRightIm;
    @BindView(R.id.realName_right_im)
    ImageView realNamerightim;
    @BindView(R.id.idNumber_right_im)
    ImageView idNumberrightim;
    @BindView(R.id.userinfo_password_tv)
    TextView userinfoPasswordTv;
    @BindView(R.id.userinfo_phoneNumber_tv)
    TextView userinfo_phoneNumber_tv;
    @BindView(R.id.userinfo_loginpw_rl)
    RelativeLayout userinfoLoginpwRl;
    @BindView(R.id.userinfo_idNumber_rl)
    RelativeLayout userinfoidNumberrl;
    @BindView(R.id.userinfo_realName_rl)
    RelativeLayout userinforealNamerl;

    private UserInfoBean mUserinfoData;//sharePrefence中保存的用户信息
    private String oldPwd;//原密码
    private String phoneNum;//手机号
    private String smsCode;//验证码
    private String newPwd;//原密码
    private String reNewPwd;//重复密码

    private String photoUrl;//头像地址
    private String nickName;//昵称
    private boolean isNeedUpdate = false;//是否需要用户中心界面更新
    private Dialog mNicknameDialog;
    private Dialog mUserInfoDialog;
    private MyCountDownTimer myCountDownTimer;
    private String mRealName = "";
    private String mIdNumber = "";
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            UpdateUserinfoService service = ((UpdateUserinfoService.MyBinder) iBinder).getService();
            service.init(userinfoNicknameTv, userinfo_phoneNumber_tv, userinfoIconIv, userinforealNametv, userinfoidNumbertv);
            service.excute();
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //调用照相机返回图片临时文件
    private File tempFile;
    private String mUserIcon;
    private Bitmap bitMap;
    private Uri tempUri;
    private static final int RC_CAMERA_PERM = 123;
    private static final int RC_SETTINGS_SCREEN = 125;
    private PopupWindow popupWindow;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_personaldata);
        ButterKnife.bind(this);
        titlebarTv.setText("个人资料");
        if (null != mUserinfoData) {
            phoneNum = mUserinfoData.getMobile();
        } else {
            phoneNum = "1000000000";
        }
        String myName = mUserinfoData.getRealName();
        String myId = mUserinfoData.getIdNumber();
        LogUtils.i("userdateActivity getInfo:" + myName + ":::" + myId);
        if (!(myName.equals(null) || myName.isEmpty())) {
            realNamerightim.setVisibility(View.INVISIBLE);
            userinforealNamerl.setEnabled(false);
        }
        if (!(myId.equals(null) || myId.isEmpty())) {
            idNumberrightim.setVisibility(View.INVISIBLE);
            userinfoidNumberrl.setEnabled(false);
        }
        updateUserinfo();
//        Picasso.with(this).load(photoUrl).into(userinfoIconIv);
    }

    //返回
    @OnClick(R.id.titlebar_back_ll)
    void back() {
        LogUtils.i("userdateActivity back:" + isNeedUpdate);
        if (isNeedUpdate) {
            setResult(Configure.RESULT_UPDATE);
        } else {
            setResult(Configure.RESULT_NOUPDATE);
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        if (isNeedUpdate) {
            setResult(Configure.RESULT_UPDATE);
        } else {
            setResult(Configure.RESULT_NOUPDATE);
        }
        finish();
    }

    //点击修改密码
    @OnClick(R.id.userinfo_loginpw_rl)
    void photoClick() {
        createUpdatePwdDialog();
//        finish();
    }


    //点击修改真实姓名
    @OnClick(R.id.userinfo_realName_rl)
    void realnameClick() {
        createUpdatRealnameDialog();
//        finish();
    }

    //点击修改身份证号
    @OnClick(R.id.userinfo_idNumber_rl)
    void idnumerClick() {
        createUpdatRealnameDialog();
//        finish();
    }

    //修改昵称
    @OnClick(R.id.userinfo_nickname_rl)
    void nicknameClick() {
        createUpdateNickDialog();
    }

    //修改头像
    @OnClick(R.id.userinfo_userphoto_rl)
    void touxiangOnclik() {

        checkStoragePression();
//        startAlbum();
//        startCapture();
    }


    @Override
    protected void init() {
//        updateUserinfo();
        mUserinfoData = (UserInfoBean) SharedPreferencesUtils.getObject(Configure.SPKEY_USERINFO);
        photoUrl = mUserinfoData.getLogoUrl();
        nickName = mUserinfoData.getNickName();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        createCameraTempFile(savedInstanceState);
        LogUtils.e("Oncreate走了");
        super.onCreate(savedInstanceState);
    }

    /**
     * 创建调用系统照相机待存储的临时文件
     *
     * @param savedInstanceState
     */
    private void createCameraTempFile(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("tempFile")) {
            tempFile = (File) savedInstanceState.getSerializable("tempFile");
        } else {
            tempFile = new File(checkDirPath(FileUtils.getCacheDir() + "/image/"),
                    "usericon" + ".jpg");
        }
    }

    /**
     * 检查文件是否存在
     */
    private static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("tempFile", tempFile);
    }


    private void showPhotoDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow, null);
        TextView btnCarema = (TextView) view.findViewById(R.id.btn_camera);
        TextView btnPhoto = (TextView) view.findViewById(R.id.btn_photo);
        TextView btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        View parent = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        //popupWindow在弹窗的时候背景半透明
        final WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.alpha = 0.5f;
        this.getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                UserDataActivity.this.getWindow().setAttributes(params);
            }
        });
        btnCarema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到调用系统相机
                startCarema(popupWindow);
                popupWindow.dismiss();
            }
        });
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到调用系统图库
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
                popupWindow.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    private void startCarema(PopupWindow popupWindow) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        LogUtils.e(tempFile.toString() + "系统相机");
        startActivityForResult(intent, REQUEST_CAPTURE);
        popupWindow.dismiss();
    }


    //输入原始密码
    public void createUpdatePwdDialog() {
        View mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_resetpsw_check, null);
        final Dialog mDialog = new AlertDialog.Builder(this).create();
        mDialog.show();
        Window window = mDialog.getWindow();
        window.setContentView(mDialogView);
        mDialog.setCanceledOnTouchOutside(false);
        //避免输入框在dialog中的Edittext中无法弹出
        mDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        mDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        Button nextStep = (Button) mDialogView.findViewById(R.id.userinfo_next_btn);
        ImageView closedialog = (ImageView) mDialogView.findViewById(R.id.resetpsw_im);
        final EditText currentPswEdt = (EditText) mDialogView.findViewById(R.id.current_psw_edt);
        //下一步
        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                oldPwd = currentPswEdt.getText().toString();
                if ("".equals(oldPwd)) {
                    UIUtils.showToastSafe("密码不能为空");
                    return;
                } else if (!RegexUtils.checkPassWord(oldPwd)) {
                    UIUtils.showToastSafe("密码由6-16个英文+数字组成,区分大小写");
                    return;
                }
                OkGo.get(UrlManager.chkPassword(UserDataActivity.this, phoneNum, oldPwd)).connTimeOut(5000).execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        VcodeBean vcodeBean = gson.fromJson(s, VcodeBean.class);
                        if (null != vcodeBean) {
                            if (vcodeBean.getRet() == 100) {
                                mDialog.dismiss();
                                createVcodeDialog();
                            } else {
                                UIUtils.showToastSafe(vcodeBean.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        UIUtils.showToastSafe("网络异常,请检查网络设置");
                    }
                });

            }
        });
        //关闭dialog
        closedialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
    }

    //修改密码
    public void resetPswDialog() {
        View mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_resetpsw, null);
        final Dialog mDialog = new AlertDialog.Builder(this).create();
        mDialog.show();
        Window window = mDialog.getWindow();
        window.setContentView(mDialogView);
        mDialog.setCanceledOnTouchOutside(false);
        //避免输入框在dialog中的Edittext中无法弹出
        mDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        mDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        Button nextStep = (Button) mDialogView.findViewById(R.id.userinfo_next_btn);
        ImageView closedialog = (ImageView) mDialogView.findViewById(R.id.resetpsw_im);
        final EditText newPwdEdt = (EditText) mDialogView.findViewById(R.id.new_psw_edt);
        final EditText renewPwdEdt = (EditText) mDialogView.findViewById(R.id.renew_psw_edt);

        //下一步
        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                reNewPwd = renewPwdEdt.getText().toString();
                newPwd = newPwdEdt.getText().toString();
                if ("".equals(newPwd)) {
                    UIUtils.showToastSafe("密码不能为空");
                    return;
                } else if (!RegexUtils.checkPassWord(newPwd)) {
                    UIUtils.showToastSafe("密码6-16个英文+数字组成,区分大小写");
                    return;
                } else if ("".equals(reNewPwd)) {
                    UIUtils.showToastSafe("密码不能为空");
                    return;
                } else if (!RegexUtils.checkPassWord(reNewPwd)) {
                    UIUtils.showToastSafe("重复密码由6-16个英文+数字组成,区分大小写");
                    return;
                } else if (!newPwd.equals(reNewPwd)) {
                    UIUtils.showToastSafe("两次输入的密码不一致,请重新输入");
                    return;
                }
                OkGo.get(UrlManager.updatePassword(UserDataActivity.this, phoneNum, oldPwd, newPwd, smsCode)).connTimeOut(5000).execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        VcodeBean vcodeBean = gson.fromJson(s, VcodeBean.class);
                        if (null != vcodeBean) {
                            if (vcodeBean.getRet() == 100) {
                                UIUtils.showToastSafe("修改成功!");
                                mDialog.dismiss();
                                finish();
                            } else {
                                UIUtils.showToastSafe(vcodeBean.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        UIUtils.showToastSafe("网络异常,请检查网络设置");
                    }
                });

            }
        });
        //关闭dialog
        closedialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
    }

    //验证码弹出窗
    public void createVcodeDialog() {
        View mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_resetpsw_getvcode, null);
        final Dialog mDialog = new AlertDialog.Builder(this).create();
        mDialog.show();
        Window window = mDialog.getWindow();
        window.setContentView(mDialogView);
        mDialog.setCanceledOnTouchOutside(false);
        //避免输入框在dialog中的Edittext中无法弹出
        mDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        mDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        final TextView getvcodeTv = (TextView) mDialogView.findViewById(R.id.getvcode_tv);
        Button nextStep = (Button) mDialogView.findViewById(R.id.userinfo_next_btn);
        ImageView closedialog = (ImageView) mDialogView.findViewById(R.id.resetpsw_im);
        final EditText vCodeEdt = (EditText) mDialogView.findViewById(R.id.dialog_vcode_edt);

        //获取验证码
        getvcodeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                getvcodeTv.setEnabled(false);
                OkGo.get(UrlManager.getVcodeUrl(UserDataActivity.this, 3, phoneNum)).connTimeOut(5000).execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        VcodeBean vcodeBean = gson.fromJson(s, VcodeBean.class);
                        if (null != vcodeBean) {
                            if (vcodeBean.getRet() == 100) {
//                                mDialog.dismiss();
                                UIUtils.showToastSafe("获取成功,请稍后");
                                myCountDownTimer = new MyCountDownTimer(60000, 500, getvcodeTv);
                                myCountDownTimer.start();
                            } else {
                                UIUtils.showToastSafe(vcodeBean.getMsg());
                                getvcodeTv.setEnabled(true);
                            }
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        UIUtils.showToastSafe("网络异常,请检查网络设置");
                        if (myCountDownTimer != null) {
                            myCountDownTimer.cancel();
                        }
                        getvcodeTv.setText("获取验证码");
                        getvcodeTv.setEnabled(true);
                    }
                });
            }
        });

        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smsCode = vCodeEdt.getText().toString();
                if ("".equals(smsCode)) {
                    UIUtils.showToastSafe("验证码不能为空");
                    return;
                } else if (!smsCode.matches("^\\d{6}$")) {
                    UIUtils.showToastSafe("验证码格式错误");
                    return;
                } else {
                    OkGo.get(UrlManager.chkPwdCode(UserDataActivity.this, smsCode)).connTimeOut(5000).execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            Gson gson = new Gson();
                            VcodeBean vcodeBean = gson.fromJson(s, VcodeBean.class);
                            if (null != vcodeBean) {
                                if (vcodeBean.getRet() == 100) {
                                    mDialog.dismiss();
                                    resetPswDialog();
                                    UIUtils.showToastSafe("获取成功,请稍后");
                                } else {
                                    UIUtils.showToastSafe(vcodeBean.getMsg());
                                }
                            }
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);


                        }
                    });
                }
            }
        });
        //关闭dialog
        closedialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
    }

    //创建修改昵称的dialog
    private void createUpdateNickDialog() {
        View mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_resetnickname, null);
        String currentName = userinfoNicknameTv.getText().toString();
        mNicknameDialog = new android.app.AlertDialog.Builder(this).create();
        mNicknameDialog.show();
        Window window = mNicknameDialog.getWindow();
        window.setContentView(mDialogView);
        mNicknameDialog.setCanceledOnTouchOutside(false);
        //避免输入框在dialog中的Edittext中无法弹出
        mNicknameDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        mNicknameDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        ImageView closedialog = (ImageView) mDialogView.findViewById(R.id.resetpsw_im);
        final EditText currentPswEdt = (EditText) mDialogView.findViewById(R.id.current_psw_edt);
        currentPswEdt.setText(currentName);
        currentPswEdt.setSelection(currentPswEdt.getText().length());//将光标追踪到内容的最后
        Button submit = (Button) mDialogView.findViewById(R.id.userinfo_next_btn);
        submit.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          final String nickname = currentPswEdt.getText().toString();
                                          LogUtils.i("userDataActivity onclick:" + nickname);
                                          if (nickname == null || nickname.isEmpty() || nickname.length() < 2) {
                                              UIUtils.showToastSafe("昵称太短");
                                          } else if (RegexUtils.checkNickName(nickname)) {
                                              NicknameProtocol protocol = new NicknameProtocol(UserDataActivity.this);
                                              protocol.load(UserDataActivity.this, new ParamsHelperInterface() {
                                                  @Override
                                                  public LinkedHashMap<String, String> getParamas() {
                                                      LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
                                                      params.put("nickName", nickname);
                                                      return params;
                                                  }
                                              }, UserDataActivity.this);
                                          } else if (nickname.length() > 16) {
                                              UIUtils.showToastSafe("昵称太长");
                                          }
                                      }
                                  }

        );
        //关闭dialog
        closedialog.setOnClickListener(new View.OnClickListener()

                                       {
                                           @Override
                                           public void onClick(View v) {
                                               mNicknameDialog.dismiss();
                                           }
                                       }
        );
    }

    //创建修改真实姓名和身份证号的dialog
    private void createUpdatRealnameDialog() {
        View mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_resetname_idnum, null);
        String currentName = userinfoNicknameTv.getText().toString();
        mUserInfoDialog = new android.app.AlertDialog.Builder(this).create();
        mUserInfoDialog.show();
        Window window = mUserInfoDialog.getWindow();
        window.setContentView(mDialogView);
        mUserInfoDialog.setCanceledOnTouchOutside(false);
        //避免输入框在dialog中的Edittext中无法弹出
        mUserInfoDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        mUserInfoDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        ImageView closedialog = (ImageView) mDialogView.findViewById(R.id.resetpsw_im);
        final EditText realnamePswEdt = (EditText) mDialogView.findViewById(R.id.current_realname_edt);
        final EditText idnumPswEdt = (EditText) mDialogView.findViewById(R.id.current_idnumer_edt);
        realnamePswEdt.setSelection(realnamePswEdt.getText().length());//将光标追踪到内容的最后
        idnumPswEdt.setSelection(idnumPswEdt.getText().length());//将光标追踪到内容的最后
        Button submit = (Button) mDialogView.findViewById(R.id.userinfo_next_btn);
        submit.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          final String realname = realnamePswEdt.getText().toString();
                                          final String idnum = idnumPswEdt.getText().toString();
                                          mRealName = realname;
                                          mIdNumber = idnum;
                                          LogUtils.i("userDataActivity onclick:" + realname + ":::" + idnum);
                                          if (realname != null || !realname.isEmpty()) {
                                              if (idnum != null || !idnum.isEmpty()) {
                                                  if (RegexUtils.checkRealName(realname)) {
                                                      if (RegexUtils.checkIdCard(idnum)) {
                                                          LogUtils.i("userDataActivity begin reset");
                                                          ResetUserinfoProtocol protocol = new ResetUserinfoProtocol(UserDataActivity.this);
                                                          protocol.load(UserDataActivity.this, new ParamsHelperInterface() {
                                                              @Override
                                                              public LinkedHashMap<String, String> getParamas() {
                                                                  LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
                                                                  params.put("realName", realname);
                                                                  params.put("idNumber", idnum);
                                                                  return params;
                                                              }
                                                          }, UserDataActivity.this);
                                                      }
                                                      else {
                                                          UIUtils.showToastSafe("请正确输入身份证");
                                                      }
                                                  } else {
                                                      UIUtils.showToastSafe("请正确输入姓名");
                                                  }

                                              } else {
                                                  UIUtils.showToastSafe("信息不能为空");
                                              }
                                          } else {
                                              UIUtils.showToastSafe("信息不能为空");
                                          }

                                      }
                                  }

        );
        //关闭dialog
        closedialog.setOnClickListener(new View.OnClickListener()

                                       {
                                           @Override
                                           public void onClick(View v) {
                                               mUserInfoDialog.dismiss();
                                           }
                                       }
        );
    }


    @Override
    public void onSucess(Object o) {
        LogUtils.i("userDataActivity onsuccess" + o);
        if (o instanceof NicknameBean) {
            if (o != null) {
                NicknameBean data = (NicknameBean) o;
                NicknameBean.DataBean bean = data.getData();
                if (bean != null) {
                    String newNick = bean.getNickName();
                    LogUtils.i("userDataActivity updatenickname:" + newNick);
                //  userinfoNicknameTv.setText(newNick);
                    UIUtils.showToastSafe("修改成功");
                    updateUserinfo();
                    mNicknameDialog.dismiss();
                } else {
                    UIUtils.showToastSafe(data.getMsg());
                }
            }
        }
        if (o instanceof VcodeBean) {
            LogUtils.i("userDataActivity onsuccess  vcodebean"+o);
            if (o != null) {
                VcodeBean data = (VcodeBean) o;
                LogUtils.i("userDataActivity onsuccess  vcodebean ret::::"+data.getRet()+":::");
                    if (data.getRet() == 100) {
                        LogUtils.i("userDataActivity onsuccess  ret100");
                        UIUtils.showToastSafe("修改成功");
                        realNamerightim.setVisibility(View.INVISIBLE);
                        idNumberrightim.setVisibility(View.INVISIBLE);
                        userinforealNamerl.setEnabled(false);
                        userinfoidNumberrl.setEnabled(false);
                        userinforealNametv.setText(mRealName);
                        StringBuilder temp=new StringBuilder();
                        String   num1="";
                        if(mIdNumber.length()>7)
                        {
                            for (int i = 0; i <mIdNumber.length()-7 ; i++) {
                                temp.append("*");
                            }
                        }
                        num1=temp.toString();
                        String   num2=mIdNumber.substring(0,3);
                        String   num3=mIdNumber.substring(mIdNumber.length()-4,mIdNumber.length());
                        String number=num2+num1+num3;
                        userinfoidNumbertv.setText(number);
                        userinforealNametv.setTextColor(BaseApplication.getApplication().getResources().getColor(black_gray1));
                        userinfoidNumbertv.setTextColor(BaseApplication.getApplication().getResources().getColor(black_gray1));
                        updateUserinfo();
                        mUserInfoDialog.dismiss();
                    }
                else {
                    UIUtils.showToastSafe(data.getMsg());
                }
            }
        }
    }

    @Override
    public void onError() {
        LogUtils.i("userDataActivity onError");
        UIUtils.showToastSafe("修改失败");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                LogUtils.e("调用系统相机返回");
                if (resultCode == Activity.RESULT_OK) {
                    LogUtils.e("调用系统相机去裁剪页面");
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                LogUtils.e("调用系统相册返回");
                if (resultCode == Activity.RESULT_OK) {
                    LogUtils.e("调用系统相册返回");
                    Uri uri = data.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == Activity.RESULT_OK) {
                    final Uri uri = data.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = getRealFilePathFromUri(this.getApplicationContext(), uri);
                    String usericonPath = FileUtils.getCachePath() + "/usercenter/";
                    FileUtils.copyFile(cropImagePath, usericonPath, true);
                    bitMap = BitmapFactory.decodeFile(usericonPath);
                    mUserIcon = StringUtils.convertIconToString(bitMap);//将头像转化为string
                    LogUtils.e("调用了裁剪框" + tempFile);
                    upDatePhoto(mUserIcon);
                }
                break;
            case RC_SETTINGS_SCREEN:
                ToastUtils.showShort(UserDataActivity.this, "点击重试");
                break;
            default:
                break;
        }
    }

    private void upDatePhoto(String userLogo) {
        HttpParams httpParams = new HttpParams();
        Map<String, String> map = new HashMap<>();
        String Client = PrefUtils.getString(this, "Client", "");
        String signKey = "shgo12al";
        UserInfoBean infoBean = (UserInfoBean) SharedPreferencesUtils.getObject(Configure.SPKEY_USERINFO);
        String secret = infoBean.getSecret();
        String sign = StringUtils.getMD5(Client + secret + signKey + userLogo);
       /*  map.put("userLogo",userLogo);
        map.put("sign",sign);
        httpParams.put(map);*/
        httpParams.put("userLogo", userLogo);
        httpParams.put("sign", sign);
        httpParams.put("client", Client);
        OkGo.post(UrlManager.updateUserLogo()).cacheMode(CacheMode.NO_CACHE).connTimeOut(10000).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                VcodeBean vcodeBean = gson.fromJson(s, VcodeBean.class);
                if (null != vcodeBean) {
                    if (vcodeBean.getRet() == 100) {
                        UIUtils.showToastSafe("修改成功");
                        Picasso.with(UserDataActivity.this).load(vcodeBean.getData().getLogoUrl()).into(userinfoIconIv);
                        updateUserinfo();
                    } else {
                        UIUtils.showToastSafe(vcodeBean.getMsg());
                    }
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                UIUtils.showToastSafe("网络异常,请检查网络设置");
            }
        });
    }

    /**
     * 打开截图界面
     *
     * @param uri
     */

    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }


    /**
     * Try to return the absolute file path from the given Uri  兼容了file:///开头的 和 content://开头的情况
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }


    private void updateUserinfo() {
        LogUtils.i("userdateActivity updateUserinfo:" + isNeedUpdate);
        Intent intent = new Intent(this, UpdateUserinfoService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
        isNeedUpdate = true;
    }

    private void updateLocalUserinfo() {
        UserInfoBean bean = (UserInfoBean) SharedPreferencesUtils.getObject(Configure.SPKEY_USERINFO);
        userinfoNicknameTv.setText(bean.getNickName());
        userinfo_phoneNumber_tv.setText(bean.getMobile());
        Picasso.with(this).load(bean.getLogoUrl()).into(userinfoIconIv);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(RC_CAMERA_PERM)
    public void checkStoragePression() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // Have permission, do the thing!
            showPhotoDialog();
        } else {
            // Ask for one permission
            Toast.makeText(this, "需要读写内存卡权限", Toast.LENGTH_LONG).show();
            EasyPermissions.requestPermissions(this, "需要读写内存卡权限",
                    RC_CAMERA_PERM, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this, "如果没有读写权限，将无法完成头像上传。打开应用程序设置界面修改应用程序权限?")
                    .setTitle("应用设置")
                    .setPositiveButton("设置")
                    .setNegativeButton(getString(R.string.cancel), null /* click listener */)
                    .setRequestCode(RC_SETTINGS_SCREEN)
                    .build()
                    .show();
        }
    }

    @Override
    protected String getBaidutitle() {
        return "个人资料";
    }
}
