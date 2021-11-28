package AdamDovciak.napier.ac.uk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openMainGame(View view) {
        Intent gameView = new Intent(this, ActivityTwo.class);
        startActivity(gameView);
    }

    public void openStatstics(View view) {
        Intent statisticsView = new Intent(this, AcitivityFourStatistics.class);
        startActivity(statisticsView);
    }
}
