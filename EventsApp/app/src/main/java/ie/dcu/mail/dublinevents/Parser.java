package ie.dcu.mail.dublinevents;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.widget.ExpandableListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Parser extends AsyncTask<Void,Integer,Integer> {

    private Context c;
    private ExpandableListView expl;
    private String data;
    private String pType;
    private String[] months =  {"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
    private ArrayList<Object> group = new ArrayList<>();
    private ArrayList<Object> child = new ArrayList<>();
    ProgressDialog pd;


    /*
    This class will be used when we receive the downloaded information from the
    Downloader class and we will be able to extract the relevant information and begin
    to format it
     */
     public Parser(Context c, String data, ExpandableListView expl,String pType) {
        this.c = c;
        this.data = data;
        this.expl = expl;
        this.pType = pType;
    }


    //Show the loading screen
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Parser");
        pd.setMessage("Gathering information ....Please wait");
        pd.show();
    }


    //While its loading we parse all of our events
    @Override
    protected Integer doInBackground(Void... params) {
         if(pType.equals("event")) {
             return this.parseEvent();
         }
         else{
             return this.parseVenue();
         }

    }

    //Display our results
    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        if(integer == 1)
        {

            //Set our adapter here to be displayed
            ExpandableListAdapter adapter = new ExpandableListAdapter(group, child);
            LayoutInflater vi = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            adapter.setInflater(vi);
            expl.setAdapter(adapter);

        }else
        {
            //if there was not information retrieved
            Toast.makeText(c,"Nothing found!",Toast.LENGTH_SHORT).show();
        }

        pd.dismiss();
    }

    //PARSE RECEIVED DATA
    private int parseEvent()
    {
        try
        {
            //Add data to json array first
            JSONArray ja=new JSONArray(data);

            //Create obj to hold single item
            JSONObject jo;

            group.clear();

            //Loop through array
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

                //Retrieve Name,Address,datetime
                String name=jo.getString("ename");
                String address=jo.getString("location");
                String datetime =jo.getString("datetime");
                datetime = datetime.substring(0, datetime.length() - 3);
                String monthDay = datetime.substring(5, datetime.length()-5);
                String month = months[Integer.parseInt(monthDay.substring(1,2))-1];
                String day = monthDay.substring(monthDay.length()-3, monthDay.length());
                String imgLink = jo.getString("elink");

                //Add it to our list
                ArrayList<String> item = new ArrayList<>();
                item.add(name);
                item.add("Location: "+address);
                item.add("Time: "+ datetime.substring(datetime.length() - 5, datetime.length()));

                if (!imgLink.equals("")) {
                    item.add(imgLink);
                }
                else{
                    item.add("empty");
                }
                item.add("event");
                item.add(month+ " " + day);
                group.add(item);

                //add the description
                String event = jo.getString("description");
                ArrayList<String> childItem = new ArrayList<>();
                childItem.add(event);
                child.add(childItem);
            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private int parseVenue()
    {
        try
        {
            //Add data to json array first
            JSONArray ja=new JSONArray(data);

            //Create object to hold single item
            JSONObject jo;

            group.clear();

            //Loop through array
            for(int i=0;i<ja.length();i++)
            {
                jo = ja.getJSONObject(i);

                //Retrieve NAME,Address,Phone number
                String name = jo.getString("name");
                String address = jo.getString("address");
                String cat = jo.getString("category");
                String rating = jo.getString("rating");
                String imgLink = jo.getString("link");
                String descrip = jo.getString("description");

                //Add it to our list
                ArrayList<String> item = new ArrayList<>();
                item.add(name);
                item.add("Location: "+address);
                item.add("Category: "+cat);
                item.add(imgLink);
                item.add("venue");
                item.add(rating+"/5.0");
                group.add(item);

                //add the events
                ArrayList<String> childItem = new ArrayList<>();
                childItem.add(descrip);
                child.add(childItem);

            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}