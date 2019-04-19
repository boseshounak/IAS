package jr.minor2.jiit.com.ias;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.TileOverlay;
import com.squareup.picasso.Picasso;

import java.net.URI;

import de.hdodenhof.circleimageview.CircleImageView;
import mehdi.sakout.fancybuttons.FancyButton;

public class Signup extends AppCompatActivity implements View.OnClickListener {
    EditText ename,epass,ephone,eemail;
    FancyButton submit;
    CircleImageView dp;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    boolean dp_check;
    String picturePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        submit=(FancyButton) findViewById(R.id.sign_btn);
        submit.setOnClickListener(this);

        ename=(EditText)findViewById(R.id.sign_name);
        epass=(EditText)findViewById(R.id.sign_pass);
        ephone=(EditText)findViewById(R.id.sign_phone);
        eemail=(EditText)findViewById(R.id.sign_email);
        dp=(CircleImageView)findViewById(R.id.profile_dp);
        dp.setOnClickListener(this);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        editor=sharedPreferences.edit();
        Picasso.get().load(R.drawable.fact1).fit().into(dp);
}


    @Override
    protected void onResume() {
        super.onResume();
        ephone.setText(Curr_Data.phone);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        if(id==R.id.sign_btn){
            String msg="";
            boolean check=true;
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Error");
            String name,email,pass;
            name=ename.getText().toString().trim();
            email=eemail.getText().toString().trim();
            pass=epass.getText().toString().trim();
            if(name.length()==0 || email.length()==0 || pass.length()==0){
                msg="Required feilds cannot be left blank";
                check=false;
            }
            if(pass.length()<6){
                msg="Password length should be more than 5";
                check=false;
            }
            if(!email.contains("@") || !email.contains(".com")){
                msg="Invalid email";
                check=false;
            }

            if(check){
                new Cloud_Handle(this).execute("signup",name,Curr_Data.phone,email,pass);
            }
            else{
                builder.setMessage(msg);
                builder.setPositiveButton("OK",null);
                builder.create().show();
            }

        }


    }




}
