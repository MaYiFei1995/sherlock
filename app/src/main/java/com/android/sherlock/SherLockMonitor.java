package com.android.sherlock;

import android.accounts.AccountManager;
import android.app.AndroidAppHelper;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.media.MediaDrm;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.os.Handler;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.net.NetworkInterface;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class SherLockMonitor implements IXposedHookLoadPackage {

    /**
     * 示例：
     * <pre>
     *     XposedHelpers.findAndHookMethod(
     *     "需要hook的方法所在类的完整类名",
     *     lpparam.classLoader,      // 类加载器
     *     "需要hook的方法名",
     *     Class<?>,  // 参数类型
     *     new XC_MethodHook() {
     *
     *         @Override
     *         protected void beforeHookedMethod(MethodHookParam param) {
     *             XposedBridge.log("调用XXX()获取了XXX");
     *         }
     *
     *         @Override
     *         protected void afterHookedMethod(MethodHookParam param) throws Throwable {
     *             XposedBridge.log(getMethodStack());
     *             super.afterHookedMethod(param);
     *         }
     *     }
     * );
     *
     * @param lpparam @see <a href="https://api.xposed.info/reference/de/robv/android/xposed/callbacks/XC_LoadPackage.LoadPackageParam.html">...</a>
     */
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) {

        if (lpparam == null) {
            return;
        }

        final ApplicationInfo appInfo = lpparam.appInfo;

        // IMEI
        try {
            XposedHelpers.findAndHookMethod(
                    TelephonyManager.class.getName(),
                    lpparam.classLoader,
                    "getDeviceId",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getDeviceId()获取IMEI";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }
        XposedHelpers.findAndHookMethod(
                TelephonyManager.class.getName(),
                lpparam.classLoader,
                "getDeviceId",
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        String msg = "调用getDeviceId(int)获取IMEI";
                        XposedBridge.log(msg);
                        showToast(appInfo, msg);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );
        try {
            XposedHelpers.findAndHookMethod(
                    TelephonyManager.class.getName(),
                    lpparam.classLoader,
                    "getImei",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getImei()获取IMEI";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }
        XposedHelpers.findAndHookMethod(
                TelephonyManager.class.getName(),
                lpparam.classLoader,
                "getImei",
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        String msg = "调用getImei(int)获取IMEI";
                        XposedBridge.log(msg);
                        showToast(appInfo, msg);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

        // MEID
        try {
            XposedHelpers.findAndHookMethod(
                    TelephonyManager.class.getName(),
                    lpparam.classLoader,
                    "getMeid",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getMeid()获取MEID";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }
        try {
            XposedHelpers.findAndHookMethod(
                    TelephonyManager.class.getName(),
                    lpparam.classLoader,
                    "getMeid",
                    int.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getMeid(int)获取MEID";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }

        // OAID
        try {
            Class<?> mdidSdkHelperClass;
            Class<?> iIdentifierListenerClass;
            try {
                mdidSdkHelperClass = lpparam.classLoader.loadClass("com.bun.miitmdid.core.MdidSdkHelper");
                iIdentifierListenerClass = lpparam.classLoader.loadClass("com.bun.miitmdid.interfaces.IIdentifierListener");
            } catch (Throwable tr) {
                mdidSdkHelperClass = null;
                iIdentifierListenerClass = null;
            }
            if (mdidSdkHelperClass != null) {
                // init sdk
                XposedHelpers.findAndHookMethod(
                        mdidSdkHelperClass,
                        "InitSdk",
                        Context.class, boolean.class, iIdentifierListenerClass,
                        new XC_MethodHook() {

                            boolean isNeedPrintStack;

                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) {
                                isNeedPrintStack = true;
                                String msg = "调用MdidSdkHelper#InitSdk()方法初始化";
                                XposedBridge.log(msg);
                                showToast(appInfo, msg);
                            }

                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                if (isNeedPrintStack) {
                                    XposedBridge.log(getMethodStack());
                                }
                                super.afterHookedMethod(param);
                            }
                        }
                );
            }
            Class<?> idSupplierClass;
            try {
                idSupplierClass = lpparam.classLoader.loadClass("com.bun.miitmdid.interfaces.IdSupplier");
            } catch (Throwable tr) {
                tr.printStackTrace();
                idSupplierClass = null;
            }
            if (idSupplierClass != null) {
                // get oaid
                XposedHelpers.findAndHookMethod(
                        idSupplierClass,
                        "getOAID",
                        new XC_MethodHook() {
                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                String msg = "调用IdSupplier#getOAID()方法获取了OAID";
                                XposedBridge.log(msg);
                                showToast(appInfo, msg);
                            }

                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                XposedBridge.log(getMethodStack());
                            }
                        }

                );
            }
            Class<?> idProviderImplClass;
            try {
                idProviderImplClass = lpparam.classLoader.loadClass("com.android.id.impl.IdProviderImpl");
            } catch (Throwable tr) {
                tr.printStackTrace();
                idProviderImplClass = null;
            }
            if (idProviderImplClass != null) {
                // miui
                XposedHelpers.findAndHookMethod(
                        idProviderImplClass,
                        "getOAID",
                        new XC_MethodHook() {
                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                String msg = "调用IdProviderImpl#getOAID()方法获取了OAID";
                                XposedBridge.log(msg);
                                showToast(appInfo, msg);
                            }

                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                XposedBridge.log(getMethodStack());
                            }
                        }
                );
            }
        } catch (Throwable tr) {
            tr.printStackTrace();
        }

        // DRM
        try {
            XposedHelpers.findAndHookMethod(
                    MediaDrm.class.getName(),
                    lpparam.classLoader,
                    "getPropertyByteArray",
                    String.class,
                    new XC_MethodHook() {

                        boolean isNeedPrintStack;

                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            if (param.args[0] != null && param.args[0].equals(MediaDrm.PROPERTY_DEVICE_UNIQUE_ID)) {
                                isNeedPrintStack = true;
                                String msg = "调用MediaDrm获取ID";
                                XposedBridge.log(msg);
                                showToast(appInfo, msg);
                            }
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            if (isNeedPrintStack) {
                                XposedBridge.log(getMethodStack());
                            }
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }

        // GAID
        try {
            XposedHelpers.findAndHookMethod(
                    "com.google.android.gms.ads.identifier.AdvertisingIdClient$Info",
                    lpparam.classLoader,
                    "getId",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用AdvertisingIdClient获取了GAID";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }

        // NAI
        try {
            XposedHelpers.findAndHookMethod(
                    TelephonyManager.class.getName(),
                    lpparam.classLoader,
                    "getNai",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getNai()获取NAI";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }

        // IMSI
        try {
            XposedHelpers.findAndHookMethod(
                    TelephonyManager.class.getName(),
                    lpparam.classLoader,
                    "getSubscriberId",
                    int.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getSubscriberId(int)获取了IMSI";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }

        // SimSerialNumber
        try {
            XposedHelpers.findAndHookMethod(
                    TelephonyManager.class.getName(),
                    lpparam.classLoader,
                    "getSimSerialNumber",
                    int.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getSimSerialNumber()获取了Sim卡串号";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }

        // Serial
        try {
            XposedHelpers.findAndHookMethod(
                    Build.class.getName(),
                    lpparam.classLoader,
                    "getSerial",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "getSerial()获取了串号";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }

        // AndroidID
        try {
            XposedHelpers.findAndHookMethod(
                    Settings.Secure.class.getName(),
                    lpparam.classLoader,
                    "getString",
                    ContentResolver.class, String.class,
                    new XC_MethodHook() {

                        private boolean isNeedPrintStack;

                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            Object name = param.args[1];
                            if (Settings.Secure.ANDROID_ID.equals(name)) {
                                isNeedPrintStack = true;
                                String msg = "getString()获取了AndroidID";
                                XposedBridge.log(msg);
                                showToast(appInfo, msg);
                            }
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            if (isNeedPrintStack) {
                                XposedBridge.log(getMethodStack());
                            }
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }

        // N以下获取mac地址
        try {
            XposedHelpers.findAndHookMethod(
                    WifiInfo.class.getName(),
                    lpparam.classLoader,
                    "getMacAddress",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getMacAddress()获取了mac地址";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }

        // 获取mac地址
        try {
            XposedHelpers.findAndHookMethod(
                    NetworkInterface.class.getName(),
                    lpparam.classLoader,
                    "getHardwareAddress",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getHardwareAddress()获取了mac地址";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }

        // IP
        try {
            XposedHelpers.findAndHookMethod(
                    WifiInfo.class.getName(),
                    lpparam.classLoader,
                    "getIpAddress",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getIpAddress()获取了IP";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }

        // 定位
        try {
            XposedHelpers.findAndHookMethod(
                    LocationManager.class.getName(),
                    lpparam.classLoader,
                    "getLastKnownLocation",
                    String.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getLastKnownLocation(String)获取了GPS地址";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }
        try {
            XposedHelpers.findAndHookMethod(
                    TelephonyManager.class.getName(),
                    lpparam.classLoader,
                    "getAllCellInfo",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getAllCellInfo()获取了位置信息";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable tr) {
            tr.printStackTrace();
        }
        try {
            XposedHelpers.findAndHookMethod(
                    TelephonyManager.class.getName(),
                    lpparam.classLoader,
                    "getCellLocation",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getCellLocation()获取了位置信息";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable tr) {
            tr.printStackTrace();
        }

        // 网络接入标识等信息
        try {
            XposedHelpers.findAndHookMethod(
                    NetworkInterface.class.getName(),
                    lpparam.classLoader,
                    "getNetworkInterfaces",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getNetworkInterfaces()获取了网络信息";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }
        try {
            XposedHelpers.findAndHookMethod(
                    ConnectivityManager.class.getName(),
                    lpparam.classLoader,
                    "getNetworkCapabilities",
                    Network.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getNetworkCapabilities(Network)获取了网络信息";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }
        try {
            XposedHelpers.findAndHookMethod(
                    ConnectivityManager.class.getName(),
                    lpparam.classLoader,
                    "getActiveNetworkInfo",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getActiveNetworkInfo()获取了网络信息";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }
        try {
            XposedHelpers.findAndHookMethod(
                    ConnectivityManager.class.getName(),
                    lpparam.classLoader,
                    "getNetworkInfo",
                    int.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getNetworkInfo(int)获取了网络信息";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }
        try {
            XposedHelpers.findAndHookMethod(
                    ConnectivityManager.class.getName(),
                    lpparam.classLoader,
                    "getNetworkInfo",
                    Network.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getNetworkInfo(Network)获取了网络信息";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }

        // 应用列表
        try {
            XposedHelpers.findAndHookMethod(
                    "android.app.ApplicationPackageManager",
                    lpparam.classLoader,
                    "getInstalledPackages",
                    int.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getInstalledPackages(int)获取了应用列表";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }
        try {
            XposedHelpers.findAndHookMethod(
                    "android.app.ApplicationPackageManager",
                    lpparam.classLoader,
                    "queryIntentActivities",
                    Intent.class, int.class,
                    new XC_MethodHook() {

                        private boolean isNeedPrintStack;

                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            if (param.args[0] != null) {
                                Intent intent = (Intent) param.args[0];
                                if (intent.getCategories() != null && intent.getCategories().contains(Intent.CATEGORY_LAUNCHER)) {
                                    isNeedPrintStack = true;
                                    String msg = "调用queryIntentActivities(int)获取了应用列表";
                                    XposedBridge.log(msg);
                                    showToast(appInfo, msg);
                                }
                            }
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            if (isNeedPrintStack) {
                                XposedBridge.log(getMethodStack());
                            }
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }

        // 通讯录
        try {
            XposedHelpers.findAndHookMethod(
                    ContentResolver.class.getName(),
                    lpparam.classLoader,
                    "query",
                    Uri.class, String[].class, String.class, String[].class, String.class,
                    new XC_MethodHook() {

                        private boolean isNeedPrintStack;

                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            if (param.args[0] != null) {
                                Uri uri = (Uri) param.args[0];
                                String msg = null;
                                if (uri == ContactsContract.CommonDataKinds.Phone.CONTENT_URI) {
                                    msg = "调用query(Contacts)获取了通讯录";
                                } else if (uri.toString().startsWith("content://sms")) {
                                    msg = "调用query(\"SMS\")获取了短信";
                                } else if (uri == CallLog.Calls.CONTENT_URI) {
                                    msg = "调用query(CallLog)获取了通话记录";
                                }
                                if (msg != null) {
                                    isNeedPrintStack = true;
                                    XposedBridge.log(msg);
                                    showToast(appInfo, msg);
                                }
                            }
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            if (isNeedPrintStack) {
                                XposedBridge.log(getMethodStack());
                            }
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }

        // 剪切板
        try {
            XposedHelpers.findAndHookMethod(
                    ClipboardManager.class.getName(),
                    lpparam.classLoader,
                    "getPrimaryClip",
                    new XC_MethodHook() {

                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getPrimaryClip()获取了剪切板";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }
        try {
            XposedHelpers.findAndHookMethod(
                    ClipboardManager.class.getName(),
                    lpparam.classLoader,
                    "setPrimaryClip",
                    ClipData.class,
                    new XC_MethodHook() {

                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用setPrimaryClip()写入了剪切板";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }

        // 账户
        try {
            XposedHelpers.findAndHookMethod(
                    AccountManager.class.getName(),
                    lpparam.classLoader,
                    "getAccounts",
                    new XC_MethodHook() {

                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用AccountManager.getAccounts()获取账户";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }

        // 手机号|运营商
        try {
            XposedHelpers.findAndHookMethod(
                    TelephonyManager.class.getName(),
                    lpparam.classLoader,
                    "getProvidersName",
                    new XC_MethodHook() {

                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getProvidersName()获取运营商";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }
        try {
            XposedHelpers.findAndHookMethod(
                    TelephonyManager.class.getName(),
                    lpparam.classLoader,
                    "getNativePhoneNumber",
                    new XC_MethodHook() {

                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getNativePhoneNumber()获取手机号";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }
        try {
            XposedHelpers.findAndHookMethod(
                    TelephonyManager.class.getName(),
                    lpparam.classLoader,
                    "getLine1Number",
                    new XC_MethodHook() {

                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getLine1Number()获取手机号";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }
        try {
            XposedHelpers.findAndHookMethod(
                    TelephonyManager.class.getName(),
                    lpparam.classLoader,
                    "getSimOperator",
                    new XC_MethodHook() {

                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getSimOperator()获取运营商";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }
        XposedHelpers.findAndHookMethod(
                TelephonyManager.class.getName(),
                lpparam.classLoader,
                "getNetworkOperator",
                new XC_MethodHook() {

                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        String msg = "调用getNetworkOperator()获取运营商";
                        XposedBridge.log(msg);
                        showToast(appInfo, msg);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );
        try {
            XposedHelpers.findAndHookMethod(
                    TelephonyManager.class.getName(),
                    lpparam.classLoader,
                    "getNetworkOperatorName",
                    new XC_MethodHook() {

                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getNetworkOperatorName()获取运营商";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }
        try {
            XposedHelpers.findAndHookMethod(
                    TelephonyManager.class.getName(),
                    lpparam.classLoader,
                    "getSimOperatorName",
                    new XC_MethodHook() {

                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            String msg = "调用getSimOperatorName()获取运营商";
                            XposedBridge.log(msg);
                            showToast(appInfo, msg);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log(getMethodStack());
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }

        // 传感器
        try {
            XposedHelpers.findAndHookMethod(
                    SensorManager.class.getName(),
                    lpparam.classLoader,
                    "registerListener",
                    SensorEventListener.class, Sensor.class, int.class, Handler.class,
                    new XC_MethodHook() {

                        boolean isNeedPrintStack;

                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            if (param.args[1] != null) {
                                Sensor sensor = (Sensor) param.args[1];
                                String msg = null;
                                if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                                    msg = "调用registerListener()注册运动传感器";
                                }
                                if (msg != null) {
                                    XposedBridge.log(msg);
                                    showToast(appInfo, msg);
                                }
                            }
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            if (isNeedPrintStack) {
                                XposedBridge.log(getMethodStack());
                            }
                            super.afterHookedMethod(param);
                        }
                    }
            );
        } catch (Throwable ignore) {

        }
    }

    private void showToast(ApplicationInfo appInfo, final String msg) {
        try {
            final String label = appInfo.loadLabel(AndroidAppHelper.currentApplication().getPackageManager()).toString();
            new Handler(AndroidAppHelper.currentApplication().getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(AndroidAppHelper.currentApplication(), "[" + label + "]" + msg, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Throwable ignore) {

        }
    }

    private String getMethodStack() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        StringBuilder stringBuilder = new StringBuilder();

        for (StackTraceElement temp : stackTraceElements) {
            stringBuilder.append(temp.toString()).append("\n");
        }

        return stringBuilder.toString();
    }

}
