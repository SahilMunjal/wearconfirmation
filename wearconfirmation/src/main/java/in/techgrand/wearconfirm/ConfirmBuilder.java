package com.mds.wearconfirm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.StringRes;

public class ConfirmBuilder {

    private Context context;

    private String msg;
    private String confirmMsg;
    private ConfirmListener confirmListener;

    public ConfirmBuilder(Context context) {
        this.context = context;
    }

    public ConfirmBuilder setMessage(@StringRes int msg) {
        return setMessage(context.getString(msg));
    }

    public ConfirmBuilder setMessage(String msg) {
        this.msg = msg;
        return this;
    }

    public ConfirmBuilder setConfirmMsg(@StringRes int confirmMsg) {
        return setConfirmMsg(context.getString(confirmMsg));
    }

    public ConfirmBuilder setConfirmMsg(String confirmMsg) {
        this.confirmMsg = confirmMsg;
        return this;
    }

    public ConfirmBuilder setListener(ConfirmListener confirmListener) {
        this.confirmListener = confirmListener;
        return this;
    }

    public void build() {
        if (msg == null || confirmListener == null) {
            throw new NullPointerException("Message and Listener can't be null");
        }
        Intent intent = new Intent(context, ConfirmActivity.class);
        intent.putExtra(ConfirmConstants.MESSAGE, msg);
        intent.putExtra(ConfirmConstants.CONFIRM_MESSAGE, confirmMsg);
        intent.putExtra(ConfirmConstants.RECEIVER, new ResultReceiver(new Handler()) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                if (resultCode == ConfirmConstants.SUCCESS) {
                    confirmListener.onSuccess();
                } else {
                    confirmListener.onFailure();
                }
            }
        });
        context.startActivity(intent);
    }
}
