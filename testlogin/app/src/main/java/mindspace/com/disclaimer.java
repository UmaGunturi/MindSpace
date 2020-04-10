package mindspace.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.nio.channels.AcceptPendingException;

public class disclaimer extends Activity {
    private Button Accept;
    private Button Deny;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disclaimer);

        Accept=  findViewById(R.id.button4);
        Deny=findViewById(R.id.button3);
        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent res= new Intent(disclaimer.this,content.class);
                startActivity(res);
                //finish();
            }
        });
        Deny.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                moveTaskToBack(true);

            }
        });
    }
}