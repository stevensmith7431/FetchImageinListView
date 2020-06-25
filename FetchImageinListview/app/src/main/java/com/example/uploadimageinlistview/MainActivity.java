package com.example.uploadimageinlistview;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String NAMESPACE = "http://tempuri.org/";
    private static String URL;
    public ListView listView;
    public List<MyList> listitems;
    public MyAdapter adapter;
    public int listposition;
    public JSONArray jsonArray = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listy);

        String[] s = {"studno","name","date","session","section","class","rollno","",""};
        (new getStudentDetail()).execute(s);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                listposition = position;
                Intent intent = new Intent(MainActivity.this, Detailview.class);
                JSONObject jsonobj = null;

                try {
                    jsonobj = new JSONObject(jsonArray.get(listposition).toString());

                    String RollNo = jsonobj.getString("RollNo");
                    String LeaveDetails = jsonobj.getString("LeaveDetails");
                    String Reason = jsonobj.getString("Reason");
                    String Session = jsonobj.getString("Session");
                    String Name = jsonobj.getString("Name");
                    String StudNo = jsonobj.getString("StudNo");

                    intent.putExtra("roll", RollNo);
                    intent.putExtra("leavedetail", LeaveDetails);
                    intent.putExtra("studno", StudNo);
                    intent.putExtra("reason", Reason);
                    intent.putExtra("session", Session);
                    intent.putExtra("name", Name);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                startActivity(intent);

            }
        });
    }

    public class getStudentDetail extends AsyncTask<String, String, String> {

        private ProgressDialog pdia;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdia = new ProgressDialog(MainActivity.this);
            pdia.setMessage("Loading, Please Wait...");
            pdia.show();
            pdia.setCanceledOnTouchOutside(false);
        }

        protected String doInBackground(String... params) {
            String[] paras = {"studno", "name", "date", "session", "section", "class", "rollno", "extra1", "extra2"};
            String[] values = {params[0], params[1], params[2], params[3], params[4], params[5], params[6], params[7], params[8]};
            String methodname = "StudentDetail";
            URL = "http://";
            return Helper.WebServiceCall(paras, values, methodname, NAMESPACE, URL);
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pdia.dismiss();

            listitems = new ArrayList<>();
            try {
                jsonArray = new JSONArray(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonobj = new JSONObject(jsonArray.get(i).toString());

                        String RollNo = jsonobj.getString("RollNo");
                        String RequestDate = jsonobj.getString("RequestDate");
                        String LeaveReqDate = jsonobj.getString("LeaveReqDate");
                        String LeaveDetails = jsonobj.getString("LeaveDetails");
                        String Reason = jsonobj.getString("Reason");
                        String Remarks = jsonobj.getString("Remarks");
                        String Session = jsonobj.getString("Session");
                        String Name = jsonobj.getString("Name");
                        String StudNo = jsonobj.getString("StudNo");

                        listitems.add(new MyList(RollNo, Session, LeaveReqDate, Name, LeaveDetails, StudNo, "http://File path" + StudNo.toUpperCase() + ".jpg"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter = new MyAdapter(getApplicationContext(), listitems);
                listView.setAdapter(adapter);

            }
        }
    }
}
