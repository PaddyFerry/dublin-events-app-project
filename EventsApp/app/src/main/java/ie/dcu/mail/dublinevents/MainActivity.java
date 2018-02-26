package ie.dcu.mail.dublinevents;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.support.v7.widget.Toolbar;
import android.widget.Spinner;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Object> groupItem = new ArrayList<>();
    private ArrayList<Object> childItem = new ArrayList<>();
    protected String url="http://159.65.84.145/conn.php";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnSpinnerItemSelection();


        ExpandableListView expandbleLis = (ExpandableListView) findViewById(R.id.explistView);
        expandbleLis.setDividerHeight(2);
        expandbleLis.setGroupIndicator(null);
        expandbleLis.setClickable(true);

        setGroupData();
        setChildGroupData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sort By");

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        final Downloader d = new Downloader(this, url, expandbleLis);
        d.execute();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        /* Getting a referrence to the search view */

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView my_search_view = (SearchView) MenuItemCompat.getActionView(searchItem);


        ((EditText)my_search_view.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setTextColor(Color.WHITE);

        return super.onCreateOptionsMenu(menu);
    }

    public void addListenerOnSpinnerItemSelection() {
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void setGroupData() {


        ArrayList<String> item2 = new ArrayList<String>();
        item2.add("BoogyTown");
        item2.add("12/12/18");
        item2.add("BooogiePalace");
        groupItem.add(item2);

        ArrayList<String> item3 = new ArrayList<String>();
        item3.add("BoogyTown");
        item3.add("12/12/18");
        item3.add("BooogiePalace");
        groupItem.add(item3);
    }

    public void setChildGroupData() {
        /**
         * Add Data For TecthNology
         */
        ArrayList<String> child = new ArrayList<String>();
        child.add("This is a long sentence This " +
                "is a long sentenceThis is a long " +
                "sentenceThis is a long " +
                "sentenceThis is a l" +
                "ong sentenceThis is a long " +
                "sentenceThis is a long sentenc" +
                "eThis is a long sentenceThis is a long sentenceThis" +
                " is a long sentenceThis is a long sentence");
        childItem.add(child);

        ArrayList<String> child1 = new ArrayList<String>();
        child1.add("This is a long sentence This" +
                " is a long sentenceThis is a long sentence" +
                " is a long sentenceThis is a long sentence" +
                " is a long sentenceThis is a long sentence");
        childItem.add(child1);

        ArrayList<String> child2 = new ArrayList<String>();
        child2.add("This is a long sentence This" +
                " is a long sentenceThis is a long sentence" +
                " is a long sentenceThis is a long sentence" +
                " is a long sentenceThis is a long sentence");
        childItem.add(child2);
        ArrayList<String> child3 = new ArrayList<String>();
        child3.add("This is a long sentence This" +
                " is a long sentenceThis is a long sentence" +
                " is a long sentenceThis is a long sentence" +
                " is a long sentenceThis is a long sentence");
        childItem.add(child3);
    }
}