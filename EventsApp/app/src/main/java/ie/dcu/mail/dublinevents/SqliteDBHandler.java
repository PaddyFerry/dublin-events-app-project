package ie.dcu.mail.dublinevents;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class SqliteDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 10;
    private static final String DATABASE_NAME = "favourites.db";
    public static final String TABLE_VENUES = "venues";
    public static final String VENUE_ID = "_id";
    public static final String VENUE_NAME = "name";
    public static final String VENUE_LOCATION = "location";
    public static final String VENUE_CATEGORY = "category";

    /*This class is primarily in charge of handling the favourites sqlite database.
    * It will save all the information that we will use to display favourites
    */
    public SqliteDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //When database is created
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_VENUES + "("
                + VENUE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + VENUE_NAME + " TEXT, "
                + VENUE_LOCATION + " TEXT, "
                + VENUE_CATEGORY + " TEXT "
                + ");";
        db.execSQL(query);
    }

    //if changing database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENUES);
    }


    //add Venues
    public void addVenue(String name,String location,String category){

        SQLiteDatabase db = getWritableDatabase();
        String namer = name.replaceAll("'","").replaceAll("\"","\"\"");
        Cursor resultSet= db.rawQuery("SELECT name from venues where name LIKE '"+namer+"'",null);

        if (resultSet.getCount() == 0) {

            ContentValues values = new ContentValues();
            values.put(VENUE_NAME, namer);
            values.put(VENUE_LOCATION, location);
            values.put(VENUE_CATEGORY, category);
            db.insert(TABLE_VENUES, null, values);
            Toast.makeText(MainActivity.getContext(),"Added To Favourites",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.getContext(), name+" already in favourites", Toast.LENGTH_SHORT).show();
        }

        db.close();
        resultSet.close();
    }

    public ArrayList<Object> databaseToList(){

        ArrayList<Object> listOfLists = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        //Create Query for db
        String query = "SELECT * FROM " + TABLE_VENUES + " WHERE 1";
        //Cursor
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        //Add each result to an array
        while(!cursor.isAfterLast()){
            ArrayList<String> dbString = new ArrayList<String>();
            if(cursor.getString(cursor.getColumnIndex("name"))!= null){
                dbString.add(cursor.getString(cursor.getColumnIndex("name")));
                dbString.add(cursor.getString(cursor.getColumnIndex("location")));
                dbString.add(cursor.getString(cursor.getColumnIndex("category")));
            }

            listOfLists.add(dbString);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return listOfLists;
    }

}
