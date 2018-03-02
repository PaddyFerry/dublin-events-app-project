package ie.dcu.mail.dublinevents;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class FavouriteActivity extends AppCompatActivity {

    SqliteDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        dbHandler = new SqliteDBHandler(this, null, null, 10);
        ArrayList<Object> listOfResults = dbHandler.databaseToList();
        ListAdapter myAdapter = new ie.dcu.mail.dublinevents.ListAdapter(this, listOfResults);
        ListView myListView = findViewById(R.id.faveListView);

        myListView.setAdapter(myAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        /* Getting a reference to the search view */
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView my_search_view = (SearchView) MenuItemCompat.getActionView(searchItem);
        ((EditText)my_search_view.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setTextColor(Color.WHITE);

        return super.onCreateOptionsMenu(menu);
    }

}
