package com.example.rajkumar.mymap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class ProjectDetailActivity extends ActionBarActivity {

    ArrayList<ApartmentDetails> descriptionList;

    ApartmentDetailAdapter adapter;
    Button button;
    Button bookButton;
    Button callsupport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment_detail);
        descriptionList = new ArrayList<ApartmentDetails>();
        // Locate the button in activity_main.xml
        button = (Button) findViewById(R.id.mapbutton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MapViewActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext()," Tap the Markers For our other project details",Toast.LENGTH_SHORT).show();
            }
        });


        bookButton = (Button) findViewById(R.id.bookbutton);
        bookButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Thank you..BOOKING REGISTERED !! Our Representative Will call you for Further Assistance ",Toast.LENGTH_SHORT).show();
            }
        });

       callsupport = (Button) findViewById(R.id.callsupport);
       callsupport.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Dailing Bhaggyam Sahridaya.. Customer Support..Please Wait..!!", Toast.LENGTH_SHORT).show();
            }
        });









        new JSONAsyncTask().execute("http://54.254.240.217:8080/app-task/projects/1c8dd66071eda52b39953a2694a89ad8");

        ListView listview = (ListView) findViewById(R.id.list);
        adapter = new ApartmentDetailAdapter(getApplicationContext(), R.layout.row, descriptionList);



                listview.setAdapter(adapter);


            }


            class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

                ProgressDialog dialog;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    dialog = new ProgressDialog(ProjectDetailActivity.this);
                    dialog.setMessage("Loading, please wait");
                    dialog.setTitle("Connecting server");
                    dialog.show();
                    dialog.setCancelable(false);
                }

                @Override
                protected Boolean doInBackground(String... urls) {
                    try {

                        //------------------>>
                        HttpGet httppost = new HttpGet(urls[0]);
                        HttpClient httpclient = new DefaultHttpClient();
                        HttpResponse response = httpclient.execute(httppost);

                        // StatusLine stat = response.getStatusLine();
                        int status = response.getStatusLine().getStatusCode();

                        if (status == 200) {
                            HttpEntity entity = response.getEntity();
                            String data = EntityUtils.toString(entity);

                            System.out.println(data);
                            JSONObject object = new JSONObject(data);


                            ApartmentDetails detail = new ApartmentDetails();


                            detail.setDescription(object.getString("description"));


                            detail.setLogoimage(object.getString("builderLogo"));

                            detail.setBuilderdescription(object.getString("builderDescription"));
                            detail.setAddressline(object.getString("addressLine1"));
                            detail.setLocalityname(object.getString("locality"));
                            detail.setCityname(object.getString("city"));
                            descriptionList.add(detail);

                            return true;
                        }

                        //------------------>>

                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return false;
                }

                protected void onPostExecute(Boolean result) {
                    dialog.cancel();
                    adapter.notifyDataSetChanged();
                    if (result == false)
                        Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();

                          }

                      }


                }
