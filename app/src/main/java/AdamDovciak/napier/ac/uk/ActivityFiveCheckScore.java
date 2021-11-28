package AdamDovciak.napier.ac.uk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityFiveCheckScore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_check_score);
    }

    public void noButtonPressed(View view){
        Intent noPressed = new Intent(this, ActivityTwo.class);
        startActivity(noPressed);

        ActivityTwo scoreReset = new ActivityTwo();

        scoreReset.player1Points = 0;
        scoreReset.player2Points = 0;

    }

    public void yesButtonPressed(View view){
        Intent yesPressed = new Intent(this, ActivitySixGetPlayerInfo.class);
        startActivity(yesPressed);
    }
}
