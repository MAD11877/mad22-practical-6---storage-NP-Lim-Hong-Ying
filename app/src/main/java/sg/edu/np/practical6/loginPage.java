package sg.edu.np.practical6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class loginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        Button login = findViewById(R.id.button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userinput = findViewById(R.id.username);
                String user = userinput.getText().toString();
                EditText passinput = findViewById(R.id.password);
                String pass = passinput.getText().toString();

                FirebaseDatabase fbrtdb = FirebaseDatabase.getInstance("https://practical-6-6168f-default-rtdb.asia-southeast1.firebasedatabase.app");
                fbrtdb.getReference().child("Users").child(user).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Failed to retrieve information.", Toast.LENGTH_SHORT).show();
                        }

                        else {
                            DataSnapshot result = task.getResult();
                            String username = result.child("username").getValue(String.class);
                            String password = result.child("password").getValue(String.class);

                            if (pass.equals(password)) {
                                Intent listpage = new Intent(getApplicationContext(), ListActivity.class);
                                getApplicationContext().startActivity(listpage);
                            }

                            else {
                                Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
    }
}