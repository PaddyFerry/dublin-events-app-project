package ie.dcu.mail.dublinevents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.widget.ExpandableListView;
import android.widget.Toast;


/* The function of this class is to allow users to search
    for events/pubs that are in our database
 */

public class SearchActivity extends AppCompatActivity {

    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        //initialise the expandable list view
        final ExpandableListView expandbleLis = findViewById(R.id.explistView);
        expandbleLis.setDividerHeight(2);
        expandbleLis.setGroupIndicator(null);
        expandbleLis.setClickable(true);

        //get a message from the previous activity telling us what we need to search for
        Bundle bundle = getIntent().getExtras();
        final String message = bundle.getString("message");


        //initialise the search view
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Enter search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // If the user is searching for an event
                if (message.equals("events")){
                    Downloader d = new Downloader(SearchActivity.this, "http://159.65.84.145/search.php?name=" + query, expandbleLis, "event");
                    d.execute();
                }
                //if the user is searching for a pub
                else if(message.equals("pub")){
                    Downloader d = new Downloader(SearchActivity.this, "http://159.65.84.145/searchVens.php?category=Pub&where==&name=" + query, expandbleLis, "venues");
                    d.execute();
                }

                else{
                    Downloader d = new Downloader(SearchActivity.this, "http://159.65.84.145/searchVens.php?category=Pub&where=<>&name=" + query, expandbleLis, "venues");
                    d.execute();
                }

                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}