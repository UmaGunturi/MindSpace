package mindspace.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

public class content_view extends Activity {
    //private Button Back;
    String dislabel;
    private ListView sympList;
    private ListView treatList;

    ArrayAdapter<String> rtreatAdapter;
    ArrayAdapter<String> sympAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_nav);
        Intent intent =getIntent();

        sympList = (ListView)findViewById(R.id.sympList);
        treatList = (ListView)findViewById(R.id.treatList);
        dislabel = intent.getStringExtra("disease");
        String description= intent.getStringExtra("description");
        TextView txtView = (TextView)findViewById(R.id.disLabel);
        txtView.setText(dislabel);
        TextView txtView2 = (TextView)findViewById(R.id.desclabel);
        txtView2.setText(description);




        String rsymp = intent.getStringExtra("symptoms");
        String rtreat = intent.getStringExtra("treatment");

        String[] stokenizer = (String[])rsymp.split(",");
        String[] ttokenizer = (String[])rtreat.split(",");
        stokenizer[0] = "Symptoms\n\n\n" + stokenizer[0]  ;
        ttokenizer[0] = "Treatments\n\n\n" + ttokenizer[0];
        sympAdapter  = new ArrayAdapter<String>(this, R.layout.content_nav, R.id.itemText, stokenizer);
        sympList.setAdapter(sympAdapter);
        rtreatAdapter  = new ArrayAdapter<String>(this, R.layout.content_nav, R.id.treatText, ttokenizer);
        treatList.setAdapter(rtreatAdapter);
//

//        Back = (Button) findViewById(R.id.button1);
//        Back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent ru = new Intent(content_view.this, content.class);
//                startActivity(ru);arrayAdapter1 = new ArrayAdapter<String>(this, R.layout.content_activity, R.id.textView,  s);
//                //finish();
//            }
//        });





    }



}
//
