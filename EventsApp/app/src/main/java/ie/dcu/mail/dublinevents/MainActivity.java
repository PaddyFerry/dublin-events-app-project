package ie.dcu.mail.dublinevents;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    protected String url = "http://159.65.84.145/connt.php";
    private static Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();

        //Create Expandable list view
        final ExpandableListView expandbleLis = findViewById(R.id.explistView);
        expandbleLis.setDividerHeight(2);
        expandbleLis.setGroupIndicator(null);
        expandbleLis.setClickable(true);

        //Create toolbar for View
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("CHOOSE CATEGORY ");

        Spinner spinner = findViewById(R.id.spinner);
        spinner.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {


                if (parentView.getItemAtPosition(position).toString().equals("Date")) {
                    Downloader d = new Downloader(MainActivity.this, "http://159.65.84.145/events.php?" +
                            "table=events&order=datetime", expandbleLis, "event");
                    d.execute();
                }

                if (parentView.getItemAtPosition(position).toString().equals("Venues")) {
                    Downloader d = new Downloader(MainActivity.this, url + "?table=pew&where=category&" +
                            "category=Bar&order=Name", expandbleLis, "venue");
                    d.execute();
                }
                if (parentView.getItemAtPosition(position).toString().equals("Pubs")) {
                    Downloader d = new Downloader(MainActivity.this, url + "?table=pew&where=category" +
                            "&category=Pub&order=Name", expandbleLis, "venue");
                    d.execute();
                }

                if (parentView.getItemAtPosition(position).toString().equals("Favourites")) {
                    Intent myIntent = new Intent(MainActivity.this, FavouriteActivity.class);
                    MainActivity.this.startActivity(myIntent);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // TODO
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        /* Getting a reference to the search view */
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView my_search_view = (SearchView) MenuItemCompat.getActionView(searchItem);
        ((EditText) my_search_view.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setTextColor(Color.WHITE);

        return super.onCreateOptionsMenu(menu);
    }

    public static Context getContext() {
        return mContext;
    }
}