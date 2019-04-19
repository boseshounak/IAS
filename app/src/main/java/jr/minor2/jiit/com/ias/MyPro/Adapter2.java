package jr.minor2.jiit.com.ias.MyPro;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import jr.minor2.jiit.com.ias.Cloud_Handle;
import jr.minor2.jiit.com.ias.R;

public class Adapter2 extends RecyclerView.Adapter {
    Context context;
    ArrayList<String> area,iname,itype;
    TextView name,tarea,type;

    public Adapter2(Context context, ArrayList<String> area, ArrayList<String> iname, ArrayList<String> itype) {
        this.context = context;
        this.area = area;
        this.iname = iname;
        this.itype = itype;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View view) {
            super(view);
            name=(TextView)view.findViewById(R.id.pro_card_iname);
            tarea=(TextView)view.findViewById(R.id.pro_card_area);
            type=(TextView)view.findViewById(R.id.pro_card_itype);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_profile,parent,false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        name.setText(iname.get(position));
        tarea.setText(area.get(position));
        String temp="";
        if(itype.get(position).equals("TL")){
            temp="Textiles & Leather";
        }
        else if(itype.get(position).equals("IS")){
            temp="Iron & Steal";
        }
        else if(itype.get(position).equals("PP")){
            temp="Pulp & Paper";
        }
        else if(itype.get(position).equals("CM")){
            temp="Chemical";
        }
        type.setText(temp);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Cloud_Handle(context).execute("fetch_myfact",iname.get(position),itype.get(position));
                //Details_Pro.current_update=Details_Pro.cl.get(position);
                //context.startActivity(new Intent(context,Update_screen.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return iname.size();
    }
}
