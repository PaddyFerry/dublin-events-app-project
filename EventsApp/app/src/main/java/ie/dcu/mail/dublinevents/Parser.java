package ie.dcu.mail.dublinevents;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.widget.ExpandableListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class Parser extends AsyncTask<Void,Integer,Integer> {

    private Context c;
    private ExpandableListView expl;
    private String data;
    private String pType;

    private ArrayList<Object> group = new ArrayList<>();
    private ArrayList<Object> child = new ArrayList<>();
    ProgressDialog pd;

     public Parser(Context c, String data, ExpandableListView expl,String pType) {
        this.c = c;
        this.data = data;
        this.expl = expl;
        this.pType = pType;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Parser");
        pd.setMessage("Parsing ....Please wait");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... params) {
         if(pType.equals("event")) {
             return this.parseEvent();
         }
         else{
             return this.parseVenue();
         }

    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        if(integer == 1)
        {
            //ADAPTER
            //ArrayAdapter<String> adapter=new ArrayAdapter<String>(c,android.R.layout.simple_list_item_1,players);

            ExpandableListAdapter adapter = new ExpandableListAdapter(group, child);
            LayoutInflater vi = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            adapter.setInflater(vi);
            expl.setAdapter(adapter);


        }else
        {
            Toast.makeText(c,"Unable to Parse",Toast.LENGTH_SHORT).show();
        }

        pd.dismiss();
    }

    //PARSE RECEIVED DATA
    private int parseEvent()
    {
        try
        {
            //ADD THAT DATA TO JSON ARRAY FIRST
            JSONArray ja=new JSONArray(data);

            //CREATE JO OBJ TO HOLD A SINGLE ITEM
            JSONObject jo;

            group.clear();

            //LOOP THRU ARRAY
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

                //Retrieve NAME,Address,Phone number
                String name=jo.getString("name");
                String address=jo.getString("location");
                String datetime =jo.getString("datetime");

                //ADD IT TO OUR ARRAYLIST
                ArrayList<String> item = new ArrayList<>();
                item.add(name);
                item.add(address);
                item.add(datetime);
                group.add(item);

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
            //ADD THAT DATA TO JSON ARRAY FIRST
            JSONArray ja=new JSONArray(data);

            //CREATE JO OBJ TO HOLD A SINGLE ITEM
            JSONObject jo;

            group.clear();

            //LOOP THRU ARRAY
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

                //Retrieve NAME,Address,Phone number
                String name=jo.getString("name");
                String address=jo.getString("address");
                String cat =jo.getString("category");
                String rating = jo.getString("rating");

                //ADD IT TO OUR ARRAYLIST
                ArrayList<String> item = new ArrayList<>();
                item.add(name);
                item.add(address);
                item.add(cat);
                item.add(rating+"/5");
                group.add(item);

                String event = jo.getString("name");
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
}