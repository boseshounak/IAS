package jr.minor2.jiit.com.ias.Locate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import jr.minor2.jiit.com.ias.Cloud_Handle;
import jr.minor2.jiit.com.ias.Curr_Data;
import jr.minor2.jiit.com.ias.R;

public class Locate_Act extends AppCompatActivity {
    public static RecyclerView recyclerView;
    ArrayList areas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate_);
        recyclerView=(RecyclerView)findViewById(R.id.recycler);
        areas=new ArrayList<String>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Curr_Data.Recycler_Obj=this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Curr_Data.area="";
        if(areas.size()!=0){
            areas.clear();
            Curr_Data.area_list.clear();
        }
        new Cloud_Handle(this).execute("area_search");
        initialize_areas();
        recyclerView.setAdapter(new Recycler_Adp(this,areas));
        //demo();
    }

    private void demo() {
        areas.add("Sector 62");
        areas.add("Sector 128");
        areas.add("Sector 18");
        recyclerView.setAdapter(new Recycler_Adp(this,areas));
    }

    private void initialize_areas() {
        areas=Curr_Data.area_list;
    }
}
