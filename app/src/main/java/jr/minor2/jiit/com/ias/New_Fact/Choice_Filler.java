package jr.minor2.jiit.com.ias.New_Fact;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import jr.minor2.jiit.com.ias.Cloud_Handle;
import jr.minor2.jiit.com.ias.R;
import mehdi.sakout.fancybuttons.FancyButton;

public class Choice_Filler extends AppCompatActivity implements View.OnClickListener {
    Spinner spin_loc,spin_type;
    FancyButton btn;
    ArrayList<String> loc;
    ArrayList<String> type;
    String stype,sloc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice__filler);
        spin_loc=(Spinner)findViewById(R.id.spinner_loc);
        spin_type=(Spinner)findViewById(R.id.spinner_type);
        btn=(FancyButton) findViewById(R.id.btn_pro1);
        btn.setOnClickListener(this);
        loc=new ArrayList<String>();
        type=new ArrayList<String>();

        stype="";
        sloc="";

        initialize();
        spin_type.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,type));
        spin_loc.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,loc));

        spin_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stype=type.get(i);
                if(stype.equals("Iron & Steal")){
                    stype="IS";
                }
                else if(stype.equals("Textile & Leather")){
                    stype="TL";
                }
                else if(stype.equals("Pulp & Paper")){
                    stype="PP";
                }
                else if(stype.equals("Chemicals")){
                    stype="CM";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spin_loc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sloc=loc.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void initialize() {
        type.add("Iron & Steal");
        type.add("Textile & Leather");
        type.add("Pulp & Paper");
        type.add("Chemicals");

        loc.add("Sector 62");
        loc.add("Sector 128");
        loc.add("Sector 18");

    }

    @Override
    public void onClick(View view) {
         //Toast.makeText(this,stype+" "+sloc,Toast.LENGTH_SHORT).show();
         new Cloud_Handle(this).execute("get_pie",sloc,stype);
    }
}
