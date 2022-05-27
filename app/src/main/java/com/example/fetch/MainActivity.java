package com.example.fetch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity   {
  //https://fetch-hiring.s3.amazonaws.com/hiring.json

    private static String JSON_URL = "https://fetch-hiring.s3.amazonaws.com/hiring.json";

    List<ItemsListClass> itemsList;

    RecyclerView recyclerView;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        itemsList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        GetData getData = new GetData();
        getData.execute();

    }
    public  class GetData extends AsyncTask<String, String, String > {

        @Override
        protected String doInBackground(String... strings) {

            String current = " ";

            try{
                URL url;
                HttpURLConnection urlConnection = null;

                try {
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    int data = isr.read();
                    while(data != -1){
                        current += ( char) data;
                        data = isr.read();



                    }
                    return current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (urlConnection != null){
                        urlConnection.disconnect();
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }


            return current;
        }

        @Override
        protected void onPostExecute(String s){

            try{

                JSONArray jsonArray = new JSONArray(s);


                for( int i = 0; i < jsonArray.length(); i++ ) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    ItemsListClass list = new ItemsListClass();

                    if (!jsonObject.getString("name").equals("")){
                        if (!jsonObject.isNull("name")){

                         // if(jsonObject.getString("listId").equals("1")) {
                              list.setId(jsonObject.getString("id"));
                              list.setListId(jsonObject.getString("listId"));
                              list.setName(jsonObject.getString("name"));

                              itemsList.add(list);




                        }

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();

            }
            //PutDataIntoRecyclerView( itemsList);


            Collections.sort(itemsList, new Comparator<ItemsListClass>() {
                        @Override
                        public int compare(ItemsListClass o1, ItemsListClass o2) {
                            if(Integer.parseInt(o1.getName().substring(5)) > Integer.parseInt(o2.getName().substring(5))) {
                                return -1;
                            }

                            else if(Integer.parseInt(o1.getName().substring(5)) == Integer.parseInt(o2.getName().substring(5))){
                                return 0;


                            }else {
                                return 1;
                            }
                        }

                        });
            Collections.sort(itemsList, new Comparator<ItemsListClass>() {
                @Override
                public int compare(ItemsListClass o1, ItemsListClass o2) {


                    if (Integer.parseInt(o1.getListId()) > Integer.parseInt(o2.getListId())) {
                        return -1;

                    } else if (Integer.parseInt(o1.getListId()) == Integer.parseInt(o2.getListId())) {
                        return 0;
                    } else {
                        return 1;
                    }
                }});




            PutDataIntoRecyclerView(itemsList);
        }


        }


    private  void  PutDataIntoRecyclerView(List<ItemsListClass> itemsList){
         Adaptery  adaptery = new  Adaptery(this, itemsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptery);
    }
}