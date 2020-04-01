package com.plug.okhttp_module;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * 登陆确认弹框验证
 * Created by Han on 2018/8/11
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/yin13753884368
 */

public class DialogLoginConfirm {

    private AlertDialog mAlertDialog;
    private TextView tv_login;
    private EditText edittext_1;
    private EditText edittext_2;
    private EditText edittext_3;
    private EditText edittext_4;

    public DialogLoginConfirm(Activity context, String code) {
        this.mAlertDialog = new AlertDialog
                .Builder(context)
                .setCancelable(true)
                .create();
        mAlertDialog.show();
        init(context, code);
    }

    private void init(Activity context, String code) {
        Window window = mAlertDialog.getWindow();
        window.setBackgroundDrawable(new BitmapDrawable());
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setContentView(R.layout.dialog_login_confirm_layout);
        window.setGravity(Gravity.CENTER);
        ImageView tv_code = (ImageView) window.findViewById(R.id.tv_code);
        edittext_1 = (EditText) window.findViewById(R.id.edittext_1);
        edittext_2 = (EditText) window.findViewById(R.id.edittext_2);
        edittext_3 = (EditText) window.findViewById(R.id.edittext_3);
        edittext_4 = (EditText) window.findViewById(R.id.edittext_4);
        tv_login = (TextView) window.findViewById(R.id.tv_login);
        window.findViewById(R.id.iv_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dissmiss();
            }
        });

//        edittext_1.requestFocus();
//        edittext_1.setSelection(edittext_1.getText().length());
        // 验证码
        String st = "iVBORw0KGgoAAAANSUhEUgAAAEEAAAASCAIAAACLjyRLAAABgElEQVR42rVWMU4DQQz0SyLRUKflDdQoVeq0VHyLB1DyBoTyGyI5MpbHM16tQLKivb3s2uMZ22ff169sn+8feT1aOT5aOcLuEZfHfiyM/Sn/IiQW096+gNGCLDBMvMNwFwnJm9q9ICT/k7nztbX5YPGtqKv11LrAV8IF461i2IgSYyqJENlp3THRCk2aUMLb+TWMOXg6PoSJgJ4fL2EiU6eXA5rI2m89oHZ9gRjyYY/bHwWMAJAf1zFoKu71gAn2dY67tRIxw1AAlJ0c0C3ioskgQSvKmC6RAS3cFQx+hGFATnRrqRjKLVlFDkNXvBBSCRppYe0haBnNGL5FNnQxREBR0JgORkUUQ6lgFIJhNbeiGmtjhYqWhHbMlUrQpNnYsB3GOIN0XypNth2jpZrbCaO0pNPc9tYWA77VfalFkkn4FwyaBxRuLgOBIQfKqpnNexuDZgBCPDGtmcRxxmmJs9nMtEAxjB0pf2UEmI2aRgBt8xm09IeGhbj3tdtqjF31A7onpIkj4rGyAAAAAElFTkSuQmCC";
        tv_code.setImageBitmap(BitmapUtils.base64ToBitmap(st));

        edittext_1.addTextChangedListener(new MyTextWatcher(edittext_2));
        edittext_2.addTextChangedListener(new MyTextWatcher(edittext_3));
        edittext_3.addTextChangedListener(new MyTextWatcher(edittext_4));
        edittext_4.addTextChangedListener(new MyTextWatcher(null));

        edittext_1.setOnKeyListener(new MyOnKeylistener(edittext_1, null));
        edittext_2.setOnKeyListener(new MyOnKeylistener(edittext_2, edittext_1));
        edittext_3.setOnKeyListener(new MyOnKeylistener(edittext_3, edittext_2));
        edittext_4.setOnKeyListener(new MyOnKeylistener(edittext_4, edittext_3));
    }

    private void dissmiss() {
        if (mAlertDialog != null) {
            mAlertDialog.dismiss();
        }
    }

    public DialogLoginConfirm setSureOnCilck(final Function<Boolean> action) {
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_login != null && tv_login.isSelected()) {
                    if (action != null) {
                        action.action(true);
                    }
                    dissmiss();
                }
            }
        });
        return this;
    }

    private void changeView() {
        boolean isChange = false;
        if (edittext_1 != null && edittext_1.getText().length() > 0
                && edittext_2 != null && edittext_2.getText().length() > 0
                && edittext_3 != null && edittext_3.getText().length() > 0
                && edittext_4 != null && edittext_4.getText().length() > 0) {
            isChange = true;
        }
        if (tv_login != null) {
            tv_login.setSelected(isChange);
        }
    }

    public class MyTextWatcher implements TextWatcher {
        private EditText editText;

        public MyTextWatcher(EditText edittext_2) {
            this.editText = edittext_2;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s != null && s.length() > 0) {
                if (editText != null && editText.getText().length() == 0) {
                    editText.setFocusableInTouchMode(true);
                    editText.requestFocus();
                    editText.setSelection(editText.getText().length());
                }
            }
            changeView();
        }
    }

    public class MyOnKeylistener implements View.OnKeyListener {
        private EditText edittext_current;
        private EditText edittext_front;

        public MyOnKeylistener(EditText edittext_current, EditText edittext_front) {
            this.edittext_current = edittext_current;
            this.edittext_front = edittext_front;
        }

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                if (edittext_current != null && edittext_current.getText().length() == 0) {
                    if (edittext_front != null && edittext_front.getText().length() != 0) {
                        edittext_front.setFocusableInTouchMode(true);
                        edittext_front.requestFocus();
                        edittext_front.setSelection(edittext_front.getText().length());
                    }
                }
            }
            return false;
        }
    }
}
