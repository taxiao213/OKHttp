package com.plug.okhttp_module;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.view.InputDevice;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;

/**
 * Created by A35 on 2020/4/12
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/taxiao213
 */
public class MediaButtonReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();
        Log.e("MediaButtonReceiver ", " intentAction == " + intentAction);
        if (Intent.ACTION_MEDIA_BUTTON.equals(intentAction)) {
            KeyEvent event = (KeyEvent) intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
            if (event == null) {
                return;
            }
            int keyCode = event.getKeyCode();
            switch (keyCode) {
                case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                    break;
                case KeyEvent.KEYCODE_MEDIA_NEXT:
                    break;
                case KeyEvent.KEYCODE_MEDIA_PAUSE:
                    break;
                case KeyEvent.KEYCODE_MEDIA_PLAY:
                    break;
            }
        }
    }
}
