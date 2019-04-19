package jr.minor2.jiit.com.ias.Locate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import jr.minor2.jiit.com.ias.Cloud_Handle;
import jr.minor2.jiit.com.ias.Curr_Data;
import jr.minor2.jiit.com.ias.R;

public class Recycler_Adp extends RecyclerView.Adapter {
    Context context;
    ArrayList<String> areas;
    TextView card_txt;
    public Recycler_Adp(Context context, ArrayList<String> area) {
        this.context = context;
        this.areas = area;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View view) {
            super(view);
            card_txt=(TextView)view.findViewById(R.id.card_txt);

        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_xm,parent,false);

        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        card_txt.setText(areas.get(position));
        Curr_Data.area=areas.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 //Toast.makeText(context,areas.get(position),Toast.LENGTH_SHORT).show();
                //context.startActivity(new Intent(context,MapsActivity.class));

                new Cloud_Handle(context).execute("area_details",areas.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return areas.size();
    }
}
