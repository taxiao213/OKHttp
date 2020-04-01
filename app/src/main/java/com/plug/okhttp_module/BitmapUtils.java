package com.plug.okhttp_module;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;


/**
 * Created by A35 on 2020/3/27
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/taxiao213
 */
public class BitmapUtils {
    public static Bitmap base64ToBitmap(String st) {
        if (!TextUtils.isEmpty(st)) {
            byte[] decode = Base64.decode(st, Base64.DEFAULT);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length, options);
            return bitmap;
        }
        return null;
    }
}
