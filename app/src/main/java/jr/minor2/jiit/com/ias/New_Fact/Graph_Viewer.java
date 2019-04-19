package jr.minor2.jiit.com.ias.New_Fact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.moos.library.CircleProgressView;
import com.moos.library.HorizontalProgressView;

import jr.minor2.jiit.com.ias.Curr_Data;
import jr.minor2.jiit.com.ias.R;
import mehdi.sakout.fancybuttons.FancyButton;

public class Graph_Viewer extends AppCompatActivity implements View.OnClickListener{
    TextView graph_title,graph_nc1,graph_nc2,graph_nc3;
    CircleProgressView overallp;
    HorizontalProgressView chem1_pro,chem2_pro,chem3_pro;
    FancyButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph__viewer);
        graph_title=(TextView)findViewById(R.id.graph_title);
        graph_nc1=(TextView)findViewById(R.id.graph_nc1);
        graph_nc2=(TextView)findViewById(R.id.graph_nc2);
        graph_nc3=(TextView)findViewById(R.id.graph_nc3);

        chem1_pro=(HorizontalProgressView)findViewById(R.id.chem1_progress);
        chem2_pro=(HorizontalProgressView)findViewById(R.id.chem2_progress);
        chem3_pro=(HorizontalProgressView)findViewById(R.id.chem3_progress);

        overallp=(CircleProgressView)findViewById(R.id.progressView_overall);
        btn=(FancyButton)findViewById(R.id.btn_pro1);
        btn.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        overallp.setStartProgress(0);
        double used1=Double.parseDouble(Curr_Data.setup1.get(0).used1);
        double used2=Double.parseDouble(Curr_Data.setup1.get(0).used2);
        double used3=Double.parseDouble(Curr_Data.setup1.get(0).used3);
        double cap1=Double.parseDouble(Curr_Data.setup1.get(0).cap1);
        double cap2=Double.parseDouble(Curr_Data.setup1.get(0).cap2);
        double cap3=Double.parseDouble(Curr_Data.setup1.get(0).cap3);

        double percent=((used1+used2+used3)/(cap1+cap2+cap3))*100;
        overallp.setEndProgress((float)percent);
        overallp.startProgressAnimation();

        double percent1=(used1/cap1)*100;
        double percent2=(used2/cap2)*100;
        double percent3=(used3/cap3)*100;


        chem1_pro.setStartProgress(0);
        chem2_pro.setStartProgress(0);
        chem3_pro.setStartProgress(0);

        chem1_pro.setEndProgress((float)percent1);
        chem2_pro.setEndProgress((float)percent2);
        chem3_pro.setEndProgress((float)percent3);

        chem1_pro.startProgressAnimation();
        chem2_pro.startProgressAnimation();
        chem3_pro.startProgressAnimation();

        graph_title.setText("Area Details "+Curr_Data.setup1.get(0).area_name);
        graph_nc1.setText(Curr_Data.setup1.get(0).nc1);
        graph_nc2.setText(Curr_Data.setup1.get(0).nc2);
        graph_nc3.setText(Curr_Data.setup1.get(0).nc3);
    }

    @Override
    public void onClick(View view) {
        String industry=getIntent().getExtras().getString("industry");
        Intent intent=new Intent(this,Pie_Display.class);
        intent.putExtra("industry",industry);
        startActivity(intent);
    }
}
