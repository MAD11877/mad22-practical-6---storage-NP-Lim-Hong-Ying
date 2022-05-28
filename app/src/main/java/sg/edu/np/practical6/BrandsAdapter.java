package sg.edu.np.practical6;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BrandsAdapter extends RecyclerView.Adapter<BrandViewHolder>  {
    ArrayList<User> data;

    public BrandsAdapter(ArrayList<User> input) {
        data = input;
    }

    @NonNull
    @Override
    public BrandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item;

        if (viewType == 1) {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout2, parent, false);
        }

        else {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout1, parent, false);
        }

        return new BrandViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandViewHolder holder, int position) {
        User s = data.get(position);
        holder.name.setText(s.getName());
        holder.description.setText("Description: " + s.getDescription());

        holder.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                builder.setTitle("Profile");
                builder.setMessage(s.getName());
                builder.setCancelable(false);
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Bundle info = new Bundle();

                        info.putInt("id", s.getId());

                        Intent activityName = new Intent(view.getContext(), MainActivity.class);
                        activityName.putExtras(info);
                        view.getContext().startActivity(activityName);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).getName().endsWith("7")) {
            return 1;
        }

        else {
            return 0;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}