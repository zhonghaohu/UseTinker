package com.enovell.usetinker;

import android.content.Context;

import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;

/**
 * @author : hu
 * Create time : 2020/1/10 15:08
 * Description :
 */
public class TinkerManager {
    private static boolean isInstalled = false;//是否已经初始化标志位
    private static ApplicationLike mApplicationLike;

    /**
     * 完成Tinker初始化
     *
     * @param applicationLike
     */
    public static void installedTinker(ApplicationLike applicationLike) {
        mApplicationLike = applicationLike;
        if (isInstalled) {
            return;
        }
        Tinker tinker = new Tinker.Builder(applicationLike.getApplication()).build();
        Tinker.create(tinker);
        tinker.install(applicationLike.getTinkerResultIntent(),MyTinkerService.class, new UpgradePatch());
        isInstalled = true;
    }

    /**
     * 完成patch文件的加载
     *
     * @param path 补丁文件路径
     */
    public static void loadPatch(String path) {
        if (Tinker.isTinkerInstalled()) {//是否已经安装过
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), path);
        }
    }

    /**
     * 利用Tinker代理Application 获取应用全局的上下文
     * @return 全局的上下文
     */
    private static Context getApplicationContext() {
        if (mApplicationLike != null)
            return mApplicationLike.getApplication().getApplicationContext();
        return null;
    }

    public static void killSelf(){
        ShareTinkerInternals.killAllOtherProcess(getApplicationContext());
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
