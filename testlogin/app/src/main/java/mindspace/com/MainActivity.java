package mindspace.com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity<FirebaseUser> extends AppCompatActivity {

    private ImageView img;
    private Button button1,button;
    private EditText email;
    private EditText number;
    private EditText password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();


        email=(EditText) findViewById(R.id.editText);
        password=(EditText) findViewById(R.id.editText2);
        number = (EditText) findViewById(R.id.editTextNum);

        button1=(Button)findViewById(R.id.button2);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(email.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())) {
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        SharedPreferences.Editor editor = getSharedPreferences("mypref", MODE_PRIVATE).edit();
                                        editor.putString("number", number.getText().toString());
                                        editor.apply();
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("hello", "createUserWithEmail:success");
                                        Toast.makeText(MainActivity.this, "User Created Successfully",
                                                Toast.LENGTH_SHORT).show();
                                        FirebaseUser user = (FirebaseUser) mAuth.getCurrentUser();
                                        Log.d("User", "this is user" + user);
                                        Intent intent=new Intent(MainActivity.this, disclaimer.class);
                                        startActivity(intent);
                                    } else {
                                        String errorMessage = task.getException().getMessage();
                                        Toast.makeText(MainActivity.this, "Error : " + errorMessage, Toast.LENGTH_LONG).show();

                                        // If sign in fails, display a message to the user.
                                        Log.w("hi", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        Toast.makeText(MainActivity.this, "Try Using Password Of Minimum 6 Characters",
                                                Toast.LENGTH_LONG).show();

                                    }

                                    // ...
                                }

                            });
                }

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent var=new Intent(MainActivity.this, loginpage.class);
                startActivity(var);
            }
        });






        Log.d("hello","12345");
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = (FirebaseUser) mAuth.getCurrentUser();
        if(currentUser != null){

            sendToMain();

        }


    }


    private void sendToMain() {

        Intent mainIntent = new Intent(MainActivity.this, loginpage.class);
        startActivity(mainIntent);
        finish();

    }




}

