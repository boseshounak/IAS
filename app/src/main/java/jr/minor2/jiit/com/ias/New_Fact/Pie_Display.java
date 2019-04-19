package jr.minor2.jiit.com.ias.New_Fact;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import jr.minor2.jiit.com.ias.Cloud_Handle;
import jr.minor2.jiit.com.ias.Curr_Data;
import jr.minor2.jiit.com.ias.R;
import mehdi.sakout.fancybuttons.FancyButton;

public class Pie_Display extends AppCompatActivity implements View.OnClickListener, RewardedVideoAdListener {
    MaterialEditText setup_name,setup_type,eu1,eu2,eu3;
    TextView tnc1,tnc2,tnc3,tper1,tper2,tper3;
    Button btn;
    String industry,area;
    SharedPreferences sharedPreferences;
    RewardedVideoAd mRewardedVideoAd;
    String name;
    String st_us1="";
    String st_us2="";
    String st_us3="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie__display);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        setup_name=(MaterialEditText)findViewById(R.id.setup_name);
        setup_type=(MaterialEditText)findViewById(R.id.setup_type);

        industry=getIntent().getExtras().getString("industry");
        area=Curr_Data.setup1.get(0).area_name;

        setup_type.setText(industry);
        setup_type.setEnabled(false);

        tnc1=(TextView)findViewById(R.id.text_nc1);
        tnc2=(TextView)findViewById(R.id.text_nc2);
        tnc3=(TextView)findViewById(R.id.text_nc3);

        tper1=(TextView)findViewById(R.id.t_perm1);
        tper2=(TextView)findViewById(R.id.t_perm2);
        tper3=(TextView)findViewById(R.id.t_perm3);

        eu1=(MaterialEditText)findViewById(R.id.euse1);
        eu2=(MaterialEditText)findViewById(R.id.euse2);
        eu3=(MaterialEditText)findViewById(R.id.euse3);

        btn=(Button) findViewById(R.id.setup_submit);
        btn.setOnClickListener(this);
        mRewardedVideoAd= MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                new AdRequest.Builder().build());


    }

    @Override
    protected void onStart() {
        super.onStart();
        tnc1.setText(Curr_Data.setup1.get(0).nc1);
        tnc2.setText(Curr_Data.setup1.get(0).nc2);
        tnc3.setText(Curr_Data.setup1.get(0).nc3);

        tper1.setText("Permissible Emission "+Curr_Data.setup1.get(0).cap1);
        tper2.setText("Permissible Emission "+Curr_Data.setup1.get(0).cap2);
        tper3.setText("Permissible Emission "+Curr_Data.setup1.get(0).cap3);
    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        name=setup_name.getText().toString().trim();
        st_us1=eu1.getText().toString().trim();
        st_us2=eu2.getText().toString().trim();
        st_us3=eu3.getText().toString().trim();
        boolean check=true;

        if(name.equals("") || st_us1.equals("") || st_us1.equals("") || st_us1.equals("")){
            check=false;
            builder.setMessage("Enteries Cannot be left blank");
            builder.setPositiveButton("OK",null);
            builder.create().show();

        }
        else{
            if((Double.parseDouble(st_us1)+Double.parseDouble(Curr_Data.setup1.get(0).used1))>=
                    Double.parseDouble(Curr_Data.setup1.get(0).cap1)){
                check=false;
                builder.setMessage("Emission of "+Curr_Data.setup1.get(0).nc1+" exceeding the required limit");
                builder.setPositiveButton("OK",null);
                builder.create().show();
            }
            if((Double.parseDouble(st_us2)+Double.parseDouble(Curr_Data.setup1.get(0).used2))>=
                    Double.parseDouble(Curr_Data.setup1.get(0).cap2)){
                check=false;
                builder.setMessage("Emission of "+Curr_Data.setup1.get(0).nc2+" exceeding the required limit");
                builder.setPositiveButton("OK",null);
                builder.create().show();
            }
            if((Double.parseDouble(st_us3)+Double.parseDouble(Curr_Data.setup1.get(0).used3))>=
                    Double.parseDouble(Curr_Data.setup1.get(0).cap3)){
                check=false;
                builder.setMessage("Emission of "+Curr_Data.setup1.get(0).nc3+" exceeding the required limit");
                builder.setPositiveButton("OK",null);
                builder.create().show();
            }

        }
        if(check){
            connect_cloud();
            //Toast.makeText(this,st_us1+" "+Curr_Data.setup1.get(0).used1,Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRewardedVideoAdLoaded() {
        if(mRewardedVideoAd.isLoaded()){
            mRewardedVideoAd.show();
        }
    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    private void connect_cloud() {
        String phone=sharedPreferences.getString("user_phone","7007481429");
        //Toast.makeText(this,name+st_us1+" "+Curr_Data.setup1.get(0).used1,Toast.LENGTH_SHORT).show();
        new Cloud_Handle(this).execute("final_setup",industry,name,phone,area,st_us1,st_us2,st_us3);
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardedVideoCompleted() {

    }

    @Override
    protected void onPause() {
        mRewardedVideoAd.pause(this);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mRewardedVideoAd.destroy(this);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        mRewardedVideoAd.resume(this);
        super.onResume();
    }
}
