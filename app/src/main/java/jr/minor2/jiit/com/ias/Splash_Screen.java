package jr.minor2.jiit.com.ias;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.steelkiwi.library.SlidingSquareLoaderView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Splash_Screen extends AppCompatActivity {
    ImageView imageView;
    SlidingSquareLoaderView slidingSquareLoaderView;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        imageView=(ImageView)findViewById(R.id.splash_pic);
        //slidingSquareLoaderView=(SlidingSquareLoaderView)findViewById(R.id.load_splash);
        Picasso.get().load(R.drawable.fact2).centerCrop().fit().into(imageView);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        final boolean check=sharedPreferences.getBoolean("login_status",false);
       // slidingSquareLoaderView.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //slidingSquareLoaderView.hide();
                if(!check){
                    startActivity(new Intent(Splash_Screen.this,MainActivity.class));
                }
                else{
                    startActivity(new Intent(Splash_Screen.this,Home_Act.class));

                }

            }
        },5000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
