package com.jiyuren.myapp.app;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * @author jiyuren on 2022-04-15 11:26
 * @name com.jiyuren.myapp.app.MyApp
 * @email 1126618648@qq.com
 * @descr MyApp
 * @copyright www.juming.com
 */
public class MyApp extends TinkerApplication {

    public MyApp() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.jiyuren.myapp.app.AppLike",
                "com.tencent.tinker.loader.TinkerLoader", false);

    }

}
