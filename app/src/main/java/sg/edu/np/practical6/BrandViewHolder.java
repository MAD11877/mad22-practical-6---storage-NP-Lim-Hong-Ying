package sg.edu.np.practical6;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class BrandViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView description;
    ImageView profile;

    public BrandViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        description = itemView.findViewById(R.id.description);
        profile = itemView.findViewById(R.id.imageView2);
    }
}