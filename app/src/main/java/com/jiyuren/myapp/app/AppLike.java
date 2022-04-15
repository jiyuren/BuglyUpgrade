package com.jiyuren.myapp.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.multidex.MultiDex;

import com.jiyuren.myapp.app.task.InitBuglyTask;
import com.socks.library.KLog;
import com.tencent.bugly.beta.Beta;
import com.tencent.tinker.entry.DefaultApplicationLike;

import org.jay.launchstarter.TaskDispatcher;

/**
 * @author jiyuren on 2022-04-15 11:23
 * @name com.jiyuren.myapp.app.AppLike
 * @email 1126618648@qq.com
 * @descr AppLike
 * @copyright www.juming.com
 */
public class AppLike extends DefaultApplicationLike {
    public AppLike(Application application) {
        this(application, 0, false,
                0, 0, null);
    }

    public AppLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }


    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        MultiDex.install(base);
        Beta.installTinker(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        KLog.init(true);
        TaskDispatcher.init(getApplication());
        TaskDispatcher dispatcher = TaskDispatcher.createInstance();
        dispatcher
                .addTask(new InitBuglyTask(getApplication(), true))
                .start();
        dispatcher.await();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Beta.unInit();
    }
}
