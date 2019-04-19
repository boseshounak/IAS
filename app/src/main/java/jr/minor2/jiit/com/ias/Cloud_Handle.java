package jr.minor2.jiit.com.ias;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressPie;
import dmax.dialog.SpotsDialog;
import jr.minor2.jiit.com.ias.Locate.Locate_Act;
import jr.minor2.jiit.com.ias.Locate.Map_Input;
import jr.minor2.jiit.com.ias.Locate.MapsActivity;
import jr.minor2.jiit.com.ias.Locate.Recycler_Adp;
import jr.minor2.jiit.com.ias.MyPro.Details_Pro;
import jr.minor2.jiit.com.ias.MyPro.Pro_Industries;
import jr.minor2.jiit.com.ias.MyPro.Profile_Act;
import jr.minor2.jiit.com.ias.MyPro.Update_screen;
import jr.minor2.jiit.com.ias.New_Fact.Graph_Viewer;
import jr.minor2.jiit.com.ias.New_Fact.setup_data1;

public class Cloud_Handle extends AsyncTask {
    Context context;
    ProgressDialog pd;
    String link;
    String temp_data,read_data,phone,email,name,pass,encode,type,global_industry;
    SharedPreferences sharedPreferences;
    AlertDialog alertDialog;
    ACProgressPie acProgressPie;
    public Cloud_Handle(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Curr_Data.setup1.clear();
        Curr_Data.map.clear();
        Details_Pro.cl.clear();
        Details_Pro.area.clear();
        Details_Pro.iname.clear();
        Details_Pro.itype.clear();
        acProgressPie = new ACProgressPie.Builder(context)
                .ringColor(Color.WHITE)
                .pieColor(Color.WHITE)
                .updateType(ACProgressConstant.PIE_AUTO_UPDATE)
                .build();
        acProgressPie.show();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        link="https://phpandroid.000webhostapp.com/minor_jr/user_login.php";
        type=objects[0].toString();
        if(type.equals("login")){
            phone=objects[1].toString();
            encode= URLEncoder.encode("type")+"="+URLEncoder.encode("login")+"&"+
                    URLEncoder.encode("phone")+"="+URLEncoder.encode(phone);
        }
        if(type.equals("get_pie")){
            String area_name=objects[1].toString();
            String industry=objects[2].toString();
            global_industry=industry;

            encode= URLEncoder.encode("type")+"="+URLEncoder.encode("get_pie")+"&"+
                    URLEncoder.encode("industry")+"="+URLEncoder.encode(industry)+"&"+
                    URLEncoder.encode("area_name")+"="+URLEncoder.encode(area_name);
        }
        else if(type.equals("signup")){
            name=objects[1].toString();
            phone=objects[2].toString();
            email=objects[3].toString();
            pass=objects[4].toString();
            encode= URLEncoder.encode("type")+"="+URLEncoder.encode("signup")+"&"+
                    URLEncoder.encode("phone")+"="+URLEncoder.encode(phone)+"&"+
                    URLEncoder.encode("name")+"="+URLEncoder.encode(name)+"&"+
                    URLEncoder.encode("email")+"="+URLEncoder.encode(email)+"&"+
                    URLEncoder.encode("pass")+"="+URLEncoder.encode(pass);
        }
        else if(type.equals("area_search")){
            //phone=objects[1].toString();
            encode= URLEncoder.encode("type")+"="+URLEncoder.encode("area_search");
        }
        else if(type.equals("area_details")){
            String area_name=objects[1].toString();
            encode= URLEncoder.encode("type")+"="+URLEncoder.encode("area_details")+"&"+
                    URLEncoder.encode("area_name")+"="+URLEncoder.encode(area_name);
        }
        else if(type.equals("user_fact")){
            String phone=objects[1].toString();
            encode= URLEncoder.encode("type")+"="+URLEncoder.encode("user_fact")+"&"+
                    URLEncoder.encode("phone")+"="+URLEncoder.encode(phone);
        }
        else if(type.equals("fetch_myfact")){
            String iname=objects[1].toString();
            String type=objects[2].toString();
            encode= URLEncoder.encode("type")+"="+URLEncoder.encode("fetch_myfact")+"&"+
                    URLEncoder.encode("iname")+"="+URLEncoder.encode(iname)+"&"+
                    URLEncoder.encode("industry")+"="+URLEncoder.encode(type);
        }

        else if(type.equals("updt_myfact")){
            String iname=objects[1].toString();
            String chem1=objects[2].toString();
            String chem2=objects[3].toString();
            String chem3=objects[4].toString();

            encode= URLEncoder.encode("type")+"="+URLEncoder.encode("updt_myfact")+"&"+
                    URLEncoder.encode("chem1")+"="+URLEncoder.encode(chem1)+"&"+
                    URLEncoder.encode("chem2")+"="+URLEncoder.encode(chem2)+"&"+
                    URLEncoder.encode("chem3")+"="+URLEncoder.encode(chem3)+"&"+
                    URLEncoder.encode("iname")+"="+URLEncoder.encode(iname);
        }
        else if(type.equals("final_setup")){
            String industry=objects[1].toString();
            String iname=objects[2].toString();
            String phone=objects[3].toString();
            String area=objects[4].toString();
            String chem1=objects[5].toString();
            String chem2=objects[6].toString();
            String chem3=objects[7].toString();

            encode= URLEncoder.encode("type")+"="+URLEncoder.encode("final_setup")+"&"+
                    URLEncoder.encode("industry")+"="+URLEncoder.encode(industry)+"&"+
                    URLEncoder.encode("iname")+"="+URLEncoder.encode(iname)+"&"+
                    URLEncoder.encode("phone")+"="+URLEncoder.encode(phone)+"&"+
                    URLEncoder.encode("area_name")+"="+URLEncoder.encode(area)+"&"+
                    URLEncoder.encode("chem1")+"="+URLEncoder.encode(chem1)+"&"+
                    URLEncoder.encode("chem2")+"="+URLEncoder.encode(chem2)+"&"+
                    URLEncoder.encode("chem3")+"="+URLEncoder.encode(chem3);
        }
        read_data="";
        try{
            URL url= null;
            url = new URL(link);
            HttpURLConnection httpURLConnection= null;
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            OutputStream out = null;
            out = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter= null;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            bufferedWriter.write(encode);
            bufferedWriter.flush();
            bufferedWriter.close();
            out.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader= null;
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            while((temp_data=bufferedReader.readLine())!=null){
                read_data+=temp_data;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        acProgressPie.dismiss();
        if(!read_data.equals("")){
            //pd.dismiss();
            //alertDialog.dismiss();
            sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            if(type.equals("login")){
                if(read_data.contains("go_ahead")){
                    String name=read_data.split("#")[1];
                    editor.putString("user_phone",phone);
                    editor.putString("user_name",name);
                    editor.putBoolean("login_status",true);
                    editor.commit();
                    context.startActivity(new Intent(context,Home_Act.class));

                }
                else  if(read_data.equals("signup")){
                    context.startActivity(new Intent(context,Signup.class));
                }
                else{
                    Toast.makeText(context,"Unknown Error",Toast.LENGTH_LONG).show();
                }

            }
            else if(type.equals("signup")){
                if(read_data.equals("fail")){
                    Toast.makeText(context,"Error 478 Try Again",Toast.LENGTH_SHORT).show();
                }
                else{
                    editor.putBoolean("login_status",true);
                    editor.putString("user_name",name);
                    editor.putString("user_phone",phone);
                    editor.commit();
                    context.startActivity(new Intent(context,Home_Act.class));
                }
            }
            else if(type.equals("area_search")){
                Curr_Data.area_list.clear();
                String sectors[]=read_data.split("#");
                for(int i=0; i<sectors.length; i++){
                    Curr_Data.area_list.add(sectors[i]);
                }

                Locate_Act.recyclerView.setAdapter(new Recycler_Adp(Curr_Data.Recycler_Obj,Curr_Data.area_list));
            }
            else if(type.equals("area_details")){
                String temp2[]=read_data.split("@");
                int i,j;
                for(i=0; i<temp2.length; i++){
                    String temp3[]=temp2[i].split("#");
                    Map_Input mi=new Map_Input();
                    //for(j=0; j<temp3[i].length(); j++)
                    {
                        mi.iname=temp3[0];
                        mi.tot_area=temp3[1];
                        mi.lat=Double.parseDouble(temp3[2]);
                        mi.log=Double.parseDouble(temp3[3]);
                        mi.parea=Double.parseDouble(temp3[4]);
                        mi.latLng=new LatLng(mi.lat,mi.log);
                    }
                    Curr_Data.map.add(mi);
                }
                context.startActivity(new Intent(context,MapsActivity.class));
                //Toast.makeText(context,Curr_Data.map.get(0).iname,Toast.LENGTH_SHORT).show();
                //Toast.makeText(context,read_data,Toast.LENGTH_SHORT).show();
            }
            else if(type.equals("user_fact")){
                if(read_data.contains("no_fact")){
                    //Profile_Act.textView_warn.setVisibility(View.VISIBLE);
                    Details_Pro.myfactory=false;
                }
                else{
                    Details_Pro.myfactory=true;
                    //Profile_Act.textView_warn.setVisibility(View.INVISIBLE);
                    String temp1[]=read_data.split("@");
                    int i,j;
                    for(i=0; i<temp1.length; i++){
                        String temp2[]=temp1[i].split("#");
                    /*Pro_Industries pi=new Pro_Industries();
                    pi.area=temp2[0];
                    pi.name=temp2[1];
                    pi.type=temp2[2];
                    pi.chem1=temp2[3];
                    pi.chem2=temp2[4];
                    pi.chem3=temp2[5];
                    pi.nc1=temp2[6];
                    pi.nc2=temp2[7];
                    pi.nc3=temp2[8];
                    pi.sc1=temp2[9];
                    pi.sc2=temp2[10];
                    pi.sc3=temp2[11];*/
                        Details_Pro.area.add(temp2[0]);
                        Details_Pro.iname.add(temp2[1]);
                        Details_Pro.itype.add(temp2[2]);
                        //Details_Pro.cl.add(pi);
                    }

                }
                context.startActivity(new Intent(context,Profile_Act.class));
            }
            else if(type.equals("fetch_myfact")){
                String temp1[]=read_data.split("@");
                int i,j;
                for(i=0; i<temp1.length; i++){
                    String temp2[]=temp1[i].split("#");
                    Pro_Industries pi=new Pro_Industries();
                    pi.area=temp2[0];
                    pi.name=temp2[1];
                    pi.type=temp2[2];
                    pi.chem1=temp2[3];
                    pi.chem2=temp2[4];
                    pi.chem3=temp2[5];
                    pi.nc1=temp2[6];
                    pi.nc2=temp2[7];
                    pi.nc3=temp2[8];
                    pi.sc1=temp2[9];
                    pi.sc2=temp2[10];
                    pi.sc3=temp2[11];
                    Details_Pro.area.add(temp2[0]);
                    Details_Pro.iname.add(temp2[1]);
                    Details_Pro.itype.add(temp2[2]);
                    Details_Pro.cl.add(pi);
                }
                //Toast.makeText(context,Details_Pro.cl.get(0).name,Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, Update_screen.class));
            }
            else if(type.equals("updt_myfact")){
                if(read_data.equals("success")){
                    Toast.makeText(context,"Update sucessfully in Database",Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context,Home_Act.class));
                }
                else{
                    Toast.makeText(context,"Failure",Toast.LENGTH_SHORT).show();
                }
            }
            else if(type.equals("get_pie")){
                String temp1[]=read_data.split("@");
                for(int i=0; i<temp1.length; i++){
                    String temp2[]=temp1[i].split("#");
                    setup_data1 ob=new setup_data1();
                    ob.area_name=temp2[0];
                    ob.nc1=temp2[1];
                    ob.nc2=temp2[2];
                    ob.nc3=temp2[3];

                    ob.cap1=temp2[4];
                    ob.cap2=temp2[5];
                    ob.cap3=temp2[6];

                    ob.used1=temp2[7];
                    ob.used2=temp2[8];
                    ob.used3=temp2[9];

                    Curr_Data.setup1.add(ob);
                }
                Intent intent=new Intent(context, Graph_Viewer.class);
                intent.putExtra("industry",global_industry);
                context.startActivity(intent);
                //Toast.makeText(context,Curr_Data.setup1.get(0).used1,Toast.LENGTH_SHORT).show();
            }
            else if(read_data.equals("success") && type.equals("final_setup")){
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setMessage("Registration of industry sucessfull. Please check your inbox for furthur details");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        context.startActivity(new Intent(context,Home_Act.class));
                    }
                });
                builder.create().show();

            }

        }
        else{
           Toast.makeText(context,"Please check your internet connection",Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(context,read_data,Toast.LENGTH_SHORT).show();
    }
}
