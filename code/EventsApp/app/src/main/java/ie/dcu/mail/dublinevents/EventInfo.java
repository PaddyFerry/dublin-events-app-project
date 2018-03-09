package ie.dcu.mail.dublinevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class EventInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        Bundle bundle = getIntent().getExtras();
        final String name = bundle.getString("name");


        final ExpandableListView expandbleLis = findViewById(R.id.explistView);
        expandbleLis.setDividerHeight(2);
        expandbleLis.setGroupIndicator(null);
        expandbleLis.setClickable(true);
        //Download the info again and display the info
        Downloader d = new Downloader(this, "http://159.65.84.145/eventInfo.php?name="+name, expandbleLis, "event");
        d.execute();
    }
}
