package com.example.project_akhir;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;

public class Daftar_Activity extends AppCompatActivity {

    TextView shubuh,dzuhur,ashar,magrib,isya,searchLocation,tvlokasi,tvdate;
    String sh, dz, as, mg, is, country, state, city, location, date;
    Button Search;
    EditText lokasi;

    private static final String TAG = "tag";

    String url;

    String tag_json_obj = "json_obj_req";

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        shubuh = findViewById(R.id.tvRShubuh);
        dzuhur = findViewById(R.id.tvRDzuhur);
        ashar = findViewById(R.id.tvRAshar);
        magrib = findViewById(R.id.tvRMagrib);
        isya = findViewById(R.id.tvRIsya);
        lokasi = findViewById(R.id.etLokasi);
        searchLocation = findViewById(R.id.btnSearch);

        searchLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loc = lokasi.getText().toString().trim();
                if (loc.isEmpty()){
                    Toast.makeText(Daftar_Activity.this, "Masukkan Lokasi", Toast.LENGTH_SHORT).show();
                }
                else {
                    url = "https://muslimsalat.com/"+loc+".json?key=fa5f62193a53ec36cc2209a7234da4f3";
                    searchLocation();
                }
            }
        });

    }

    private void searchLocation() {

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{

                            country = response.get("country").toString();
                            state = response.get("state").toString();
                            city = response.get("city").toString();
                            location = country+", "+state+", "+city;

                            date = response.getJSONArray("items").getJSONObject(0).get("date_for").toString();

                            sh = response.getJSONArray("items").getJSONObject(0).get("fajr").toString();
                            dz = response.getJSONArray("items").getJSONObject(0).get("dhuhr").toString();
                            as = response.getJSONArray("items").getJSONObject(0).get("asr").toString();
                            mg = response.getJSONArray("items").getJSONObject(0).get("maghrib").toString();
                            is = response.getJSONArray("items").getJSONObject(0).get("isha").toString();

                            shubuh.setText(sh);
                            dzuhur.setText(dz);
                            ashar.setText(as);
                            magrib.setText(mg);
                            isya.setText(is);
                            searchLocation.setText(city);
                            tvlokasi.setText(location);
                            tvdate.setText(date);
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(Daftar_Activity.this,"Error",Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                pDialog.hide();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}

