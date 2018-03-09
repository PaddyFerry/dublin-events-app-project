package ie.dcu.mail.dublinevents;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FavFragment extends Fragment {

    SqliteDBHandler dbHandler;
    private SwipeRefreshLayout swipeContainer;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.activity_favourite, container, false);
        dbHandler = new SqliteDBHandler(getActivity(), null, null, 14);

        ArrayList<Object> listOfResults = dbHandler.databaseToList();
        android.widget.ListAdapter myAdapter = new ie.dcu.mail.dublinevents.ListAdapter(getActivity(), listOfResults);
        ListView myListView = rootView.findViewById(R.id.faveListView);
        myListView.setAdapter(myAdapter);

        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ArrayList<Object> listOfResults = dbHandler.databaseToList();
                android.widget.ListAdapter myAdapter = new ie.dcu.mail.dublinevents.ListAdapter(getActivity(), listOfResults);
                ListView myListView = rootView.findViewById(R.id.faveListView);
                myListView.setAdapter(myAdapter);
                swipeContainer.setRefreshing(false);

            }
        });


        return rootView;
    }

}
