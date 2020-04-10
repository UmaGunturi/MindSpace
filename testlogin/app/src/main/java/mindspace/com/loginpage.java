package mindspace.com;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginpage extends AppCompatActivity {
    private EditText loginEmailText;
    private EditText loginPassText;
    private Toolbar toolbar;
    private Button loginBtn;
    private FirebaseAuth mAuth;
    private Button createbtn;
    private Toolbar Toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
////   setting the title
        toolbar.setTitle("Login to MindSpace!");

        mAuth = FirebaseAuth.getInstance();
        createbtn=findViewById(R.id.button3);
        loginEmailText = findViewById(R.id.editText3);
        loginPassText = findViewById(R.id.editText4);
        loginBtn = findViewById(R.id.button4);
        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regint= new Intent(loginpage.this,MainActivity.class);
                startActivity(regint);
                finish();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String loginEmail = loginEmailText.getText().toString();
                String loginPass = loginPassText.getText().toString();

                if(!TextUtils.isEmpty(loginEmail) && !TextUtils.isEmpty(loginPass)){
                    //loginProgress.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(loginEmail, loginPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //loginProgress.setVisibility(View.GONE);
                            if(task.isSuccessful()){
                                Log.d("hello","12345");
                                sendToMain();

                            } else {

                                String errorMessage = task.getException().getMessage();
                                Toast.makeText(loginpage.this, "Error : " + errorMessage, Toast.LENGTH_LONG).show();


                            }

                        }
                    });

                }


            }
        });




    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){

            sendToMain();

        }


    }


    private void sendToMain() {

        Intent mainIntent = new Intent(loginpage.this, disclaimer.class);
        startActivity(mainIntent);
        finish();

    }
}


