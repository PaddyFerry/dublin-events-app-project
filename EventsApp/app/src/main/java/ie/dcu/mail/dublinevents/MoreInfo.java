package ie.dcu.mail.dublinevents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class MoreInfo extends AppCompatActivity {


    //Used to inflate the more info screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        //get the name of the more info event
        Bundle bundle = getIntent().getExtras();
        final String name = bundle.getString("name");


        final ExpandableListView expandbleLis = findViewById(R.id.explistView);
        expandbleLis.setDividerHeight(2);
        expandbleLis.setGroupIndicator(null);
        expandbleLis.setClickable(true);
        //Download the info again and display the info
        Toast.makeText(Main2Activity.getContext(),name,Toast.LENGTH_SHORT).show();
        Downloader d = new Downloader(this, "http://159.65.84.145/moreInfo.php?name="+name, expandbleLis, "venue");
        d.execute();
    }

}
