package sg.edu.np.practical6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ArrayList<User> users = new ArrayList<>();
        users = getuser();

        if (users.size() == 0) {
            for (int i = 0; i < 20; i++) {
                Random random = new Random();
                int randomint = random.nextInt(999999);
                int randomint2 = random.nextInt(999999);
                boolean following = false;
                if (i % 2 == 0) {
                    following = true;
                }

                else {
                    following = false;
                }
                User user = new User("Name" + randomint, "" + randomint2, i, following);
                adduser(user);
            }
            users = getuser();
        }

        RecyclerView recyclerView = findViewById(R.id.users);
        BrandsAdapter mAdapter = new BrandsAdapter(users);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    public void adduser(User u) {
        DataHandler dataHandler = new DataHandler(this, null, null, 1);
        dataHandler.addUser(u);
    }

    public ArrayList getuser() {
        ArrayList<User> users = new ArrayList<>();
        DataHandler dataHandler = new DataHandler(this, null, null, 1);
        users = dataHandler.getUsers();
        return users;
    }
}