package AdamDovciak.napier.ac.uk;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

public class AcitivityFourStatistics extends ListActivity {

    TextView selection;
    DatabaseManipulator dbm;
    List<String[]> names2 = null;
    String[] stg1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avitivity_four_statistics);

        dbm = new DatabaseManipulator(this);
        names2 = dbm.selectAll();
        stg1 = new String[names2.size()];
        int x = 0;
        String stg;

        for (String[] name : names2) {
            stg = name[1] + " - "
                    + name[2] + " - "
                    + name[3] + " - "
                    + name[4];
            stg1[x] = stg;
            x++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                stg1);
        this.setListAdapter(adapter);
        selection = (TextView) findViewById(R.id.check_selection);
    }

    public void onListItemClick(ListView parent, View v, int position, long id) {
        selection.setText(stg1[position]);
    }

    public void returnToMain(View view){
        Intent returnToMainMenu = new Intent(this, MainActivity.class);
        startActivity(returnToMainMenu);
    }
}
