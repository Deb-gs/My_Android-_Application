package AdamDovciak.napier.ac.uk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ActivitySixGetPlayerInfo extends AppCompatActivity implements View.OnClickListener {

    private DatabaseManipulator dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six_get_player_info);

        /**
         * Creates entity save button
         */

        View saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(this);

        /**
         * Creates entity back button
         */

        View back = findViewById(R.id.back_button);
        back.setOnClickListener(this);
    }


    public void onClick(View v) {

        int player1Points;
        int player2Points;

        String player1PointsToString;
        String player2PointsToString;

        ActivityTwo neededPoints =  new ActivityTwo();

        player1Points = neededPoints.player1Points;
        player1PointsToString = String.valueOf(player1Points);

        player2Points = neededPoints.player2Points;
        player2PointsToString = String.valueOf(player2Points);

        switch (v.getId()) {
            /**
             * Returns the game to menu
             */
            case R.id.back_button:
                Intent gameMenu = new Intent(this, ActivityThree.class);
                startActivity(gameMenu);
                break;

            /**
             * Saves the scores to db and returns to main menu
             */
            case R.id.save_button:
                String namePlayer1 = ((EditText) findViewById(R.id.add_name_player_1)).getText().toString();
                String scorePlayer1 = player1PointsToString;
                String namePlayer2 = ((EditText) findViewById(R.id.add_name_player_2)).getText().toString();
                String scorePlayer2 = player2PointsToString;

                this.dbm = new DatabaseManipulator(this);
                this.dbm.insert(namePlayer1, scorePlayer1, namePlayer2, scorePlayer2);

                Intent backToMainMenu = new Intent(this, MainActivity.class);
                startActivity(backToMainMenu);

                break;
        }

        /**
         * Resets scores and makes a the game ready for new players
         */

        ActivityTwo scoreReset = new ActivityTwo();

        scoreReset.player1Points = 0;
        scoreReset.player2Points = 0;
    }
}
