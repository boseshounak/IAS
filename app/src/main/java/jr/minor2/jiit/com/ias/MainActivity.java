package jr.minor2.jiit.com.ias;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;
import com.yarolegovich.lovelydialog.LovelyChoiceDialog;
import com.yarolegovich.lovelydialog.LovelyTextInputDialog;

import de.hdodenhof.circleimageview.CircleImageView;
import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MaterialEditText home_phone;
    FancyButton btn;
    CircleImageView circleImageView;
    AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //home_phone=(MaterialEditText)findViewById(R.id.home_phone);
        circleImageView=(CircleImageView)findViewById(R.id.imageView_front);
        Picasso.get().load(R.drawable.fact1).centerCrop().fit().into(circleImageView);
        btn=(FancyButton) findViewById(R.id.log_btn);
        btn.setOnClickListener(this);
        //request_permission();
    }

    private void request_permission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
                        ,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},
                1);

    }
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //open_gallery();
                    //Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Toast.makeText(getApplicationContext(),"Write/Read And Camera Permission Denied",Toast.LENGTH_SHORT).show();
                    //Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }

    }

    @Override
    public void onClick(View view) {
        String phone="";
        /*String phone=home_phone.getText().toString();
        Curr_Data.phone=phone;
        new Cloud_Handle(getApplicationContext()).execute("login",phone);*/
        if(!isStoragePermissionGranted()){
            request_permission();
        }
        new LovelyTextInputDialog(this)
                .setTopColor(Color.parseColor("#f47142"))
                .setTitle("LOGIN")
                .setMessage("Please enter phone number to continue")
                .setInputFilter("Invalid Phone Number", new LovelyTextInputDialog.TextFilter() {
                    @Override
                    public boolean check(String text) {
                        boolean check=true;
                        if(text.length()!=10){
                            return false;
                        }
                        for(int i=0; i<10; i++){
                            if(text.charAt(i)>'9' && text.charAt(i)<'0')
                                return false;
                        }
                        return true;
                    }
                })
                .setConfirmButton("OK", new LovelyTextInputDialog.OnTextInputConfirmListener() {
                    @Override
                    public void onTextInputConfirmed(String text) {
                        Curr_Data.phone=text;
                        new Cloud_Handle(MainActivity.this).execute("login",text);
                        //Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    }
                })
                .show();

    }
}
