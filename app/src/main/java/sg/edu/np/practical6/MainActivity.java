package sg.edu.np.practical6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle receiving = getIntent().getExtras();
        User user = new User();

        int id = -1;

        if (receiving != null) {
            id = receiving.getInt("id");
        }

        user = finduser(id);

        TextView username = (TextView) findViewById(R.id.textView3);
        username.setText(user.getName());

        TextView description2 = (TextView) findViewById(R.id.textView2);
        description2.setText("Description" + user.getDescription());

        Button followbutton = (Button) findViewById(R.id.button3);

        if (user.getFollowed()) {
            followbutton.setText("Unfollow");
        }

        else {
            followbutton.setText("Follow");
        }

        followonclick(followbutton, user);
    }

    public User finduser(int id) {
        User user = new User();
        DataHandler dataHandler = new DataHandler(this, null, null, 1);
        user = dataHandler.findUserById(id);

        return user;
    }

    public boolean updateuser(User u) {
        DataHandler dataHandler = new DataHandler(this, null, null, 1);
        boolean result = dataHandler.updateUser(u);

        return result;
    }

    public void followonclick(Button followbutton, User u) {
        followbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (u.getFollowed() == false) {
                    u.setFollowed(true);
                    updateuser(u);
                    followbutton.setText("Unfollow");
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                }

                else if (u.getFollowed() == true) {
                    u.setFollowed(false);
                    updateuser(u);
                    followbutton.setText("Follow");
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}