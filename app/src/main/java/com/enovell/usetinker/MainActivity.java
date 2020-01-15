package com.enovell.usetinker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


/**
 * @author : hu
 * Create time : 2020/1/10 16:48
 * Description :
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //如果没有权限，让用户添加权限
        if (!checkPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE})) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }


    public void click1(View view) {
        TinkerManager.loadPatch(Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk");

    }

    public void click2(View view) {

        Toast.makeText(this, "？？？", Toast.LENGTH_SHORT).show();

    }

    //检查是否有权限
    private boolean checkPermissions(String[] neededPermissions) {
        if (neededPermissions == null || neededPermissions.length == 0) {
            return true;
        }
        boolean allGranted = true;
        for (String neededPermission : neededPermissions) {
            allGranted &= ContextCompat.checkSelfPermission(this, neededPermission) == PackageManager.PERMISSION_GRANTED;
        }
        return allGranted;
    }

    public void killSelf(View view) {
        TinkerManager.killSelf();
    }
}
