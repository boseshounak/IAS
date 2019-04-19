package jr.minor2.jiit.com.ias.MyPro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import jr.minor2.jiit.com.ias.R;

public class Profile_Act extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView textView_warn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_pro);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        textView_warn=(TextView)findViewById(R.id.updt_nofact);
        //textView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(Details_Pro.myfactory){
            recyclerView.setAdapter(new Adapter2(this,Details_Pro.area,Details_Pro.iname,Details_Pro.itype));
            textView_warn.setVisibility(View.INVISIBLE);
        }
        else{
            textView_warn.setVisibility(View.VISIBLE);
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
