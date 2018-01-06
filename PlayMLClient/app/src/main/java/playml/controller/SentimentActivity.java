package playml.controller;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import playml.R;
import playml.bo.SentimentBo;
import playml.model.Sentiments;

public class SentimentActivity extends AppCompatActivity {

    private static final int FRAME_W = 195;
    private static final int FRAME_H = 195;
    private static final int NB_FRAMES_HAPPY = 7;
    private static final int NB_FRAMES_ANGRY = 16;
    private static final int NB_FRAMES_SAD = 11;
    private static final int COUNT_Y = 1;
    private static final int FRAME_DURATION = 200;
    private static final int SCALE_FACTOR = 1;

    private ImageButton btn_angry;
    private ImageButton btn_sad;
    private ImageButton btn_happy;
    private TextView tvSentiment;
    private ImageView ivShow;
    private Bitmap[] bmps;
    private List<String> statements = new ArrayList<>();
    private List<Integer> sentiments = new ArrayList<>();
    private Sentiments sen = new Sentiments();
    int num=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentiment);

        btn_angry = (ImageButton) findViewById(R.id.btn_angry);
        btn_happy = (ImageButton) findViewById(R.id.btn_happy);
        btn_sad = (ImageButton) findViewById(R.id.btnSad);
        tvSentiment = (TextView) findViewById(R.id.tvSentiment);
        ivShow = (ImageView) findViewById(R.id.ivShow);

        statements.add(0,"You are a lovely person");
        statements.add(1, "You are dumb");
        statements.add(2, "I lost the match");
        statements.add(3, "Lets to to party");
        statements.add(4, "I will slap you hard");
        statements.add(5,"I think you should get lost");

        sen.setStatements(statements);

        tvSentiment.setText(statements.get(num));

    }

    private void load_bitmaps(String s, int frames){
        //load bitmaps from assests
        Bitmap bmp = getBitmapFromAssets(this, s);

        if (bmp != null) {
            //cut  bitmaps from ningabmp to array of bitmaps
            bmps = new Bitmap[frames];
            int current_frame = 0;

            for (int i = 0; i < COUNT_Y; i++) {
                for (int j = 0; i < frames; j++) {
                    bmps[current_frame] = Bitmap.createBitmap(bmp, FRAME_W * j, FRAME_H * i,
                            FRAME_W, FRAME_H);

                    //apply scale factor
                    bmps[current_frame] = Bitmap.createScaledBitmap(bmps[current_frame],
                            FRAME_W * SCALE_FACTOR, FRAME_H * SCALE_FACTOR, true);

                    if (++current_frame >= frames) {
                        break;
                    }

                }
            }

            final AnimationDrawable animation = new AnimationDrawable();
            animation.setOneShot(false); //repeat aniimation

            for (int i = 0; i < frames; i++) {
                animation.addFrame(new BitmapDrawable(getResources(), bmps[i]), FRAME_DURATION);
            }

            ivShow.setBackground(animation);
            ivShow.post(new Runnable() {
                @Override
                public void run() {
                    animation.start();
                    ;
                }
            });

        }
    }

    private Bitmap getBitmapFromAssets(SentimentActivity sentimentActivity, String filepath) {
        AssetManager assetManager = sentimentActivity.getAssets();
        InputStream istr = null;
        Bitmap bitmap = null;

        try{
            istr = assetManager.open(filepath);
            bitmap = BitmapFactory.decodeStream(istr);

        }
        catch(IOException e){

        }
        finally{
            if(istr != null){
                try{
                    istr.close();
                }
                catch(IOException e){

                }
            }
        }

        return bitmap;
    }

    //on click function sad button
    public void btn_sad_click(View view){

        sentiments.add(num,0);
        num++;

        if(num == statements.size()){

//            SentimentBo sentbo = new SentimentBo();
//            sen.setSentiments(sentiments);
//            sentbo.postSentiment(sen);

            Intent intent = new Intent(SentimentActivity.this, PredictionActivity.class);
            startActivity(intent);
        }

        tvSentiment.setText(statements.get(num));

        load_bitmaps("sad_sprite.png", NB_FRAMES_SAD);
    }

    //on click function sad button
    public void btn_happy_click(View view){


        sentiments.add(num,1);
        num++;

        if(num == statements.size()){

//            SentimentBo sentbo = new SentimentBo();
//            sen.setSentiments(sentiments);
//            sentbo.postSentiment(sen);

            Intent intent = new Intent(SentimentActivity.this, PredictionActivity.class);
            startActivity(intent);
        }


        tvSentiment.setText(statements.get(num));

        load_bitmaps("happy_sprite.png", NB_FRAMES_HAPPY);
    }

    //on click function sad button
    public void btn_angry_click(View view){

        sentiments.add(num,2);
        num++;

        if(num == statements.size()){

//            SentimentBo sentbo = new SentimentBo();
//            sen.setSentiments(sentiments);
//            sentbo.postSentiment(sen);

            Intent intent = new Intent(SentimentActivity.this, PredictionActivity.class);
            startActivity(intent);
        }

        tvSentiment.setText(statements.get(num));

        load_bitmaps("angry_sprite.png", NB_FRAMES_ANGRY);
    }

}
