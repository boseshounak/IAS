package jr.minor2.jiit.com.ias;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;

import jr.minor2.jiit.com.ias.Locate.Locate_Act;
import jr.minor2.jiit.com.ias.MyPro.Details_Pro;
import jr.minor2.jiit.com.ias.New_Fact.Choice_Filler;

public class Home_Act extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences sharedPreferences;
    ImageView imageView3,imageView4,imageView5;
    TextView tname;
    AdView adView;
    InterstitialAd interstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);

        //imageView3=(ImageView)findViewById(R.id.imageView3);
        imageView4=(ImageView)findViewById(R.id.imageView4);
        imageView5=(ImageView)findViewById(R.id.imageView5);

        //Picasso.get().load(R.drawable.cap3).fit().into(imageView3);
        Picasso.get().load(R.drawable.cap4).fit().into(imageView4);
        Picasso.get().load(R.drawable.cap5).fit().into(imageView5);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View view=navigationView.getHeaderView(0);
        tname=(TextView)view.findViewById(R.id.nav_title);
        tname.setText(sharedPreferences.getString("user_name","Admin 1"));
        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");
        adView=(AdView)findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        interstitialAd=new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                startActivity(new Intent(Home_Act.this, Choice_Filler.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        String phone=sharedPreferences.getString("user_phone","7007481429");
        int id = item.getItemId();
        if (id == R.id.nav_locate) {
            startActivity(new Intent(this,Locate_Act.class));
        }
        else if (id == R.id.nav_profile) {
            Details_Pro.cl.clear();
            Details_Pro.area.clear();
            Details_Pro.iname.clear();
            Details_Pro.itype.clear();
            new Cloud_Handle(this).execute("user_fact",phone);
        }
        else if (id == R.id.nav_setup) {
            if(interstitialAd.isLoaded()){
                interstitialAd.show();
            }

        }
        else if (id == R.id.nav_signout) {
            sharedPreferences.edit().putBoolean("login_status",false);


            startActivity(new Intent(this, MainActivity.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
