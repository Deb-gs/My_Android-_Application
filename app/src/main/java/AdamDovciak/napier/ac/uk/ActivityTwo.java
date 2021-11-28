package AdamDovciak.napier.ac.uk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityTwo extends AppCompatActivity implements View.OnClickListener {

    private Button[][] playerTiles = new Button[3][3];

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    private boolean turnPlayer1 = true;

    private int roundCounter;
    public static int player1Points;
    public static int player2Points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        textViewPlayer1 = findViewById(R.id.text_view_points1);
        textViewPlayer2 = findViewById(R.id.text_view_points2);

        /**
         * Looks for tiles with an id and makes them ready to be used
         */

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "tiles_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());

                playerTiles[i][j] = findViewById(resID);
                playerTiles[i][j].setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }

        /**
         * Checks if its player 1 turn and if it is it assign the symbol
         */

        if (turnPlayer1) {
            ((Button) view).setText("X");
        } else {
            ((Button) view).setText("O");
        }

        roundCounter++;

        /**
         * Checks if player won and in not it ties the game
         */

        if (checkWin()) {
            if (turnPlayer1) {
                playerWins();
            } else {
                player2Wins();
            }
        } else if (roundCounter == 9) {
            tie();
        } else {
            turnPlayer1 = !turnPlayer1;
        }
    }

    /**
     * Checks if player won using if statments and tiles checking if its true
     */

    private boolean checkWin() {
        String[][] tilesChecked = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tilesChecked[i][j] = playerTiles[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (tilesChecked[i][0].equals(tilesChecked[i][2]) && tilesChecked[i][0].equals(tilesChecked[i][1]) && !tilesChecked[i][0].equals("")) {
                return true;
            }
        }

        if (tilesChecked[0][0].equals(tilesChecked[2][2]) && tilesChecked[0][0].equals(tilesChecked[1][1]) && !tilesChecked[0][0].equals("")) {
            return true;
        }

        if (tilesChecked[0][2].equals(tilesChecked[2][0]) && tilesChecked[0][2].equals(tilesChecked[1][1])  && !tilesChecked[0][2].equals("")) {
            return true;
        }

        for (int i = 0; i < 3; i++) {
            if (tilesChecked[0][i].equals(tilesChecked[1][i]) && tilesChecked[0][i].equals(tilesChecked[2][i]) && !tilesChecked[0][i].equals("")) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adds points once player 1 wins
     */

    private void playerWins() {
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();

        player1Points++;

        updatePointsText();

        resetBoard();
    }

    /**
     * Ties the game and returns to orginal state
     */

    private void tie() {
        Toast.makeText(this, "Tie!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    /**
     * Adds points once player 2 wins
     */

    private void player2Wins() {
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();

        player2Points++;

        updatePointsText();
        resetBoard();
    }

    /**
     * Updates points in textView
     */

    private void updatePointsText() {
        textViewPlayer1.setText("" + player1Points);
        textViewPlayer2.setText("" + player2Points);
    }

    /**
     * Resets board
     */

    private void resetBoard() {
        roundCounter = 0;

        turnPlayer1 = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                playerTiles[i][j].setText("");
            }
        }
    }

    /**
     * Opens a menu
     */

    public void openMenu(View view) {
        Intent intent = new Intent(this, ActivityThree.class);
        startActivity(intent);
    }
}

