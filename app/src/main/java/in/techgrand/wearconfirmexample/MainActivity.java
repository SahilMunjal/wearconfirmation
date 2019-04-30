package in.techgrand.wearconfirmexample;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Toast;

import com.mds.wearconfirm.ConfirmBuilder;
import com.mds.wearconfirm.ConfirmListener;

public class MainActivity extends WearableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Enables Always-on
        setAmbientEnabled();
    }

    public void onClick(View view) {
        new ConfirmBuilder(this)
                .setConfirmMsg(R.string.confirm)
                .setMessage(R.string.please_confirm)
                .setListener(new ConfirmListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(MainActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure() {
                        Toast.makeText(MainActivity.this, R.string.failure, Toast.LENGTH_SHORT).show();
                    }
                }).build();
    }
}
