package developers.naked.com.pushnotificationuserend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    String uName,Uid,ToUname,ToUid;
    Button save,send;
    EditText username,userid,tousername,touserid;
    DatabaseReference mNotificationDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotificationDatabase=FirebaseDatabase.getInstance().getReference().child("notification");
        username=(EditText)findViewById(R.id.editText);
        userid=(EditText)findViewById(R.id.editText2);
        tousername=(EditText)findViewById(R.id.editText3);
        touserid=(EditText)findViewById(R.id.editText4);
        save=(Button)findViewById(R.id.button2);
        send=(Button)findViewById(R.id.button3);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uName=username.getText().toString();
                Uid=userid.getText().toString();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,String> notificationData=new HashMap<>();
                notificationData.put("from",uName);
                ToUname=tousername.getText().toString();
                ToUid=touserid.getText().toString();
                mNotificationDatabase.child(ToUname).push().setValue(notificationData).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "data successfully sent ", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}
