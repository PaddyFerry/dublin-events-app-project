package ie.dcu.mail.dublinevents;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Parser extends AsyncTask<Void,Integer,Integer> {

    Context c;
    ExpandableListView expl;
    String data;

    private ArrayList<Object> group = new ArrayList<>();
    private ArrayList<Object> child = new ArrayList<>();
    ProgressDialog pd;

    public Parser(Context c, String data, ExpandableListView expl) {
        this.c = c;
        this.data = data;
        this.expl = expl;
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

        return this.parse();
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
    private int parse()
    {
        try
        {
            //ADD THAT DATA TO JSON ARRAY FIRST
            JSONArray ja=new JSONArray(data);

            //CREATE JO OBJ TO HOLD A SINGLE ITEM
            JSONObject jo=null;

            group.clear();

            //LOOP THRU ARRAY
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

                //RETRIOEVE NAME
                String name=jo.getString("name");
                String address=jo.getString("address");
                String phone =jo.getString("phone");

                //ADD IT TO OUR ARRAYLIST
                ArrayList<String> item = new ArrayList<>();
                item.add(name);
                item.add(address);
                item.add(phone);
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