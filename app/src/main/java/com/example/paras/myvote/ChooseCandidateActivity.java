package com.example.paras.myvote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ChooseCandidateActivity extends AppCompatActivity implements CandidateAdapter.ItemClickListener {
    TextView tv;
    private List<Candidate> candidateList =new ArrayList<>();
    private RecyclerView recyclerView;
    private CandidateAdapter candidateAdapter;
    CandidateAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tv=findViewById(R.id.textView4);

        recyclerView=findViewById(R.id.recycler_view);

        candidateAdapter =new CandidateAdapter(candidateList);
        RecyclerView.LayoutManager hLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(hLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(candidateAdapter);
        candidateAdapter.setmClickListener(this);
//        prepareHoldersData();

        new Thread(new Runnable() {
            @Override
            public void run() {
                final String response = executeGetHttpRequest("https://api.myjson.com/bins/tz2ih");
                Log.d("Paras", "response from api : " + response);
                candidateList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray parties = jsonObject.getJSONArray("parties");
                    for (int i = 0; i < parties.length(); i++) {
                        JSONObject partyJsonObject = parties.getJSONObject(i);
                        Log.d("Paras", "party info : " + partyJsonObject.getString("name"));

                        String name  = partyJsonObject.getString("name");
                        String partyName  = partyJsonObject.getString("partyName");

                        Candidate holder =new Candidate(name,partyName);
                        candidateList.add(holder);

                    }

                    recyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            candidateAdapter.notifyDataSetChanged();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    private void prepareHoldersData() {
        Candidate holder =new Candidate("Bharatiya Janata Party","Ram Nath Kovind");
        candidateList.add(holder);

        holder =new Candidate("Indian National Congress","Meira Kumar");
        candidateList.add(holder);

        holder =new Candidate("Rashtriya Janata Dal"," Lalu Prasad Yadav");
        candidateList.add(holder);

        holder =new Candidate("Patidar Anamat Andolan Samiti","Hardik Patel");
        candidateList.add(holder);

        candidateAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, Candidate candidate) {
        Toast.makeText(ChooseCandidateActivity.this ,"Vote : " + candidate.getPartyName(), Toast.LENGTH_LONG).show();

        String selectedCandidate=candidate.getName().toString();

        Intent j=new Intent(ChooseCandidateActivity.this,ConfirmationActivity.class);
        j.putExtra("selectedCandidate",selectedCandidate.toString());
        startActivity(j);
    }

    public static String executeGetHttpRequest(final String path) {
        String result = "Failed";
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(path);
            urlConnection = (HttpURLConnection) url.openConnection();
            result = readStream(urlConnection.getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return result;
    }

    private static String readStream(InputStream is) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("US-ASCII")));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            total.append(line);
        }
        if (reader != null) {
            reader.close();
        }
        return total.toString();
    }
}
