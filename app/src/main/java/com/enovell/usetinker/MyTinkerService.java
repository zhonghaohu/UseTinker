package com.enovell.usetinker;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.tencent.tinker.lib.service.AbstractResultService;
import com.tencent.tinker.lib.service.DefaultTinkerResultService;
import com.tencent.tinker.lib.service.PatchResult;



/**
 * @author : hu
 * Create time : 2020/1/10 15:28
 * Description :
 */
public class MyTinkerService extends DefaultTinkerResultService {
    @Override
    public void onPatchResult(final PatchResult result) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),"tinker:"+result.toString(),Toast.LENGTH_SHORT).show();

                Log.i(">>>", "run: "+result.toString());
            }
        });
    }
}
