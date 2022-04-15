package com.jiyuren.myapp.app.task;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import com.socks.library.KLog;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.tencent.bugly.beta.ui.UILifecycleListener;
import com.tencent.bugly.crashreport.CrashReport;
import org.jay.launchstarter.Task;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

/**
 * @author jiyuren on 2020-04-08 14:55
 * @name com.juming.domain.common.app.tasks.InitBuglyTask
 * @email 1126618648@qq.com
 * @descr InitBuglyTask
 * @copyright www.juming.com
 */
public class InitBuglyTask extends Task {
    private final boolean mIsBuildDebug;
    private final Application mApp;

    public InitBuglyTask(Application app, boolean isBuildDebug) {
        mApp = app;
        mIsBuildDebug = isBuildDebug;
    }

    @Override
    public void run() {
        //----------应用升级---------------
        Beta.autoInit = true;
        Beta.autoCheckUpgrade = true;
        Beta.upgradeCheckPeriod = 60 * 1000;
        Beta.initDelay = 1000;
        Beta.storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        Beta.showInterruptedStrategy = true;
        try {
            Class clazz = Class.forName("com.juming.domain.MainTabActivity");
            Beta.canShowUpgradeActs.add(clazz);
        }catch (Exception e){
            e.printStackTrace();
        }
        Beta.enableNotification = true;
        Beta.upgradeDialogLifecycleListener = mUILifecycleListener();

        // 设置是否开启热更新能力，默认为true
        Beta.enableHotfix = true;
        // 设置是否自动下载补丁，默认为true
        Beta.canAutoDownloadPatch = true;
        // 设置是否自动合成补丁，默认为true
        Beta.canAutoPatch = true;
        // 设置是否提示用户重启，默认为false
        Beta.canNotifyUserRestart = true;
        // 补丁回调接口
        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String patchFile) {
                KLog.v("补丁下载地址" + patchFile);
            }

            @Override
            public void onDownloadReceived(long savedLength, long totalLength) {
                KLog.v(String.format(Locale.getDefault(), "%s %d%%",
                        Beta.strNotificationDownloading,
                        (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength)));
            }

            @Override
            public void onDownloadSuccess(String msg) {
                KLog.w("补丁下载成功");
            }

            @Override
            public void onDownloadFailure(String msg) {
                KLog.e("补丁下载失败");
            }

            @Override
            public void onApplySuccess(String msg) {
                KLog.v("补丁应用成功");
            }

            @Override
            public void onApplyFailure(String msg) {
                KLog.e("补丁应用失败");
            }

            @Override
            public void onPatchRollback() {

            }
        };
        // 获取当前包名
        String packageName = mContext.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(mContext);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        strategy.setAppReportDelay(5 * 1000);
        // 初始化Bugly
        // 设置开发设备，默认为false，上传补丁如果下发范围指定为“开发设备”，需要调用此接口来标识开发设备
        String channel = "offical";
        Bugly.setAppChannel(mContext, channel);
        Bugly.setIsDevelopmentDevice(mContext, mIsBuildDebug);
        Bugly.init(mApp, "33dfb0dc45", mIsBuildDebug, strategy);
    }


    private UILifecycleListener mUILifecycleListener() {
        return new UILifecycleListener<UpgradeInfo>() {
            @Override
            public void onCreate(Context context, View view, UpgradeInfo upgradeInfo) {
                KLog.d("onCreate");
            }

            @Override
            public void onStart(Context context, View view, UpgradeInfo upgradeInfo) {
                KLog.d("onStart");
            }

            @Override
            public void onResume(Context context, View view, UpgradeInfo upgradeInfo) {
                KLog.d("onResume");
            }

            @Override
            public void onPause(Context context, View view, UpgradeInfo upgradeInfo) {
                KLog.d("onPause");
            }

            @Override
            public void onStop(Context context, View view, UpgradeInfo upgradeInfo) {
                KLog.d("onStop");
            }

            @Override
            public void onDestroy(Context context, View view, UpgradeInfo upgradeInfo) {
                KLog.d("onDestroy");
            }
        };
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
