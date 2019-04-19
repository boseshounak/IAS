package jr.minor2.jiit.com.ias.MyPro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import jr.minor2.jiit.com.ias.Cloud_Handle;
import jr.minor2.jiit.com.ias.R;

public class Update_screen extends AppCompatActivity {
    SeekBar seek1,seek2,seek3;
    TextView text1,text2,text3;
    EditText editText1,editText2;
    Button btn;
    Pro_Industries pi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_screen);
        seek1=(SeekBar)findViewById(R.id.seek1);
        seek2=(SeekBar)findViewById(R.id.seek2);
        seek3=(SeekBar)findViewById(R.id.seek3);
        text1=(TextView)findViewById(R.id.text_chem1);
        text2=(TextView)findViewById(R.id.text_chem2);
        text3=(TextView)findViewById(R.id.text_chem3);
        editText1=(EditText)findViewById(R.id.edit_factname);
        editText2=(EditText)findViewById(R.id.edit_factcat);
        editText1.setEnabled(false);
        editText2.setEnabled(false);

        btn=(Button)findViewById(R.id.btn_updt);
        pi=Details_Pro.cl.get(0);
        seek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b==true){
                    String temp1=pi.nc1+" Permissible Quantity- "+pi.sc1+" Current Quantity- "+i;
                    pi.chem1=Integer.toString(i);
                    text1.setText(temp1);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b==true){
                    String temp1=pi.nc2+" Permissible Quantity- "+pi.sc2+" Current Quantity- "+i;
                    pi.chem2=Integer.toString(i);
                    text2.setText(temp1);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seek3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b==true){
                    String temp3=pi.nc3+" Permissible Quantity- "+pi.sc3+" Current Quantity- "+i;
                    pi.chem3=Integer.toString(i);
                    text3.setText(temp3);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),pi.name+" "+pi.chem1,Toast.LENGTH_SHORT).show();
                 new Cloud_Handle(Update_screen.this).execute("updt_myfact",pi.name,pi.chem1,pi.chem2,pi.chem3);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        editText1.setText(pi.name);
        editText2.setText(pi.area);
        String temp1="",temp2="",temp3="";
        temp1=pi.nc1+" Permissible Quantity- "+pi.sc1+" Current Quantity- "+pi.chem1;
        temp2=pi.nc2+" Permissible Quantity- "+pi.sc2+" Current Quantity- "+pi.chem2;
        temp3=pi.nc3+" Permissible Quantity- "+pi.sc3+" Current Quantity- "+pi.chem3;
        text1.setText(temp1);
        text2.setText(temp2);
        text3.setText(temp3);
        seek1.setMax(Integer.parseInt(pi.sc1));
        seek2.setMax(Integer.parseInt(pi.sc2));
        seek3.setMax(Integer.parseInt(pi.sc3));
        seek1.setProgress(Integer.parseInt(pi.chem1));
        seek2.setProgress(Integer.parseInt(pi.chem2));
        seek3.setProgress(Integer.parseInt(pi.chem3));
        //Toast.makeText(this,pi.chem1+" "+pi.chem2+" "+pi.chem3,Toast.LENGTH_SHORT).show();
    }


}
