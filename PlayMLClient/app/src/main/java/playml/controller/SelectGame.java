package playml.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import playml.R;

public class SelectGame extends AppCompatActivity {

    ImageButton btn_sentiment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game);

        btn_sentiment = (ImageButton) findViewById(R.id.btn_sentiment);
    }

    public void btn_sentiment_click(View view){

        Intent intent = new Intent(SelectGame.this, SentimentActivity.class);
        startActivity(intent);
    }
}
