package mindspace.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.nio.channels.AcceptPendingException;
import java.util.ArrayList;
import java.util.Map;

public class content<DatabaseReference, reference> extends Activity {
    private Button logout;
    private Button contactus;
    FirebaseAuth mauth;


    ListView simpleList;
    ArrayAdapter<String> arrayAdapter;
    private String[] keys = {"No data"};
    private Map<String, Map> data;
    private Toolbar Toolbar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
////   setting the title
        toolbar.setTitle("Welcome to MindSpace!");



        simpleList = (ListView)findViewById(R.id.simpleListView);

        String[] demo;

        mauth= FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        com.google.firebase.database.DatabaseReference myRef =  database.getReference().child("content").child("diagnosis");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Map<String, Map> mp1 = (Map<String, Map>) dataSnapshot.getValue();
                keys = mp1.keySet().toArray(new String[0]);
                showLV(keys, mp1);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        contactus = findViewById(R.id.button5);

        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reth = new Intent(content.this, chat.class);
                startActivity(reth);
            }
        });




        logout = findViewById(R.id.button4);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent r = new Intent(content.this, loginpage.class);
                mauth.signOut();
                startActivity(r);
            }
        });


        if(keys.length>=2){

            showLV(keys, data);
//            arrayAdapter = new ArrayAdapter<String>(this, R.layout.content_activity, R.id.textView,  keys);
//        simpleList.setAdapter(arrayAdapter);
        }

    }
    public void showLV( String[] s, Map<String, Map> temp){
        data = temp;
        keys = s;
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.content_activity, R.id.textView,  s);
        simpleList.setAdapter(arrayAdapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent appInfo = new Intent(content.this, content_view.class);
                appInfo.putExtra("disease", keys[position]);
                String desc = data.get(keys[position]).get("description").toString();
                String symps = data.get(keys[position]).get("symptoms").toString();
                String treatment = data.get(keys[position]).get("treatment").toString();
                appInfo.putExtra("description", desc);
                appInfo.putExtra("symptoms", symps);
                appInfo.putExtra("treatment", treatment);
                startActivity(appInfo);
            }
        });



    }

}

//{
// Insomnia=
// {symptoms=[Difficulty falling asleep at night, Waking up during the night, Daytime tiredness or sleepiness, Difficulty paying attention, focusing on tasks or remembering],
// treatment=[Light therapy and Cognitive behavioral therapy, Improving sleep habits, Sedative and antidepressants], description=Persistent problems falling and staying asleep.},
// Depression=
// {symptoms=[Anxiety, apathy, general discontent, Agitation, excessive crying, irritability, restlessness, Thoughts of suicide.]
// , treatment=[Antidepressants, Cognitive behavioral therapy, Psychotherapy], description=A mental health disorder characterised by persistently depressed mood or loss of interest in activities, causing significant impairment in daily life.},
// Hypochondria=
// {symptoms=[Being preoccupied with having or getting a serious disease or health condition, Worrying that minor symptoms or body sensations mean you have a serious illness, Being easily alarmed about your health status],
// treatment=[Therapies and Stress management], description=Obsession with the idea of having a serious but undiagnosed medical condition.}}
//




