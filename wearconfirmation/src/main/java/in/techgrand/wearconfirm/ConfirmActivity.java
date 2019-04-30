package com.mds.wearconfirm;

import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.wear.widget.CircularProgressLayout;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.TextView;

public class ConfirmActivity extends WearableActivity implements CircularProgressLayout.OnTimerFinishedListener, View.OnClickListener {

    private TextView tvMessage, tvConfMsg;
    private CircularProgressLayout mCircularProgress;

    private ResultReceiver resultReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        String msg = getIntent().getStringExtra(ConfirmConstants.MESSAGE);
        String confirmMsg = getIntent().getStringExtra(ConfirmConstants.CONFIRM_MESSAGE);
        resultReceiver = getIntent().getParcelableExtra(ConfirmConstants.RECEIVER);

        // Enables Always-on
        setAmbientEnabled();

        initLayouts();

        tvMessage.setText(msg);
        tvConfMsg.setText(confirmMsg);

        mCircularProgress.setTotalTime(3000);
        mCircularProgress.startTimer();
    }

    private void initLayouts() {
        tvMessage = findViewById(R.id.tv_message);
        mCircularProgress = findViewById(R.id.circular_progress);
        tvConfMsg = findViewById(R.id.tv_conf_msg);

        mCircularProgress.setOnTimerFinishedListener(this);
        mCircularProgress.setOnClickListener(this);
    }

    @Override
    public void onTimerFinished(CircularProgressLayout circularProgressLayout) {
        if (resultReceiver != null) {
            resultReceiver.send(ConfirmConstants.SUCCESS, null);
        }
        finish();
    }

    @Override
    public void onClick(View v) {
        mCircularProgress.stopTimer();
        if (resultReceiver != null) {
            resultReceiver.send(ConfirmConstants.FAILURE, null);
        }
        finish();
    }
}
