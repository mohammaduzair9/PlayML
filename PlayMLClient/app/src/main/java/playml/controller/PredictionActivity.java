package playml.controller;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import playml.R;
import playml.bo.SentimentBo;

public class PredictionActivity extends AppCompatActivity {


    private static final int FRAME_W = 195;
    private static final int FRAME_H = 195;
    private static final int NB_FRAMES_HAPPY = 7;
    private static final int NB_FRAMES_ANGRY = 16;
    private static final int NB_FRAMES_SAD = 11;
    private static final int COUNT_Y = 1;
    private static final int FRAME_DURATION = 200;
    private static final int SCALE_FACTOR = 1;

    Button btnPrdict;
    ImageView ivResult;
    TextView tvResult;
    EditText etStatement;
    private Bitmap[] bmps;

    SentimentBo sentbo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);

        btnPrdict = (Button) findViewById(R.id.btnPrdict);
        ivResult = (ImageView) findViewById(R.id.ivResult);
        tvResult =(TextView) findViewById(R.id.tvResult);
        etStatement = (EditText) findViewById(R.id.etSentiment);
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

            ivResult.setBackground(animation);
            ivResult.post(new Runnable() {
                @Override
                public void run() {
                    animation.start();
                    ;
                }
            });

        }
    }

    private Bitmap getBitmapFromAssets(PredictionActivity predictionActivity, String filepath) {
        AssetManager assetManager = predictionActivity.getAssets();
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

    public void predict_result(View view){

//        int r = sentbo.getPrediction(etStatement.getText().toString());

        int r=1;
        if(r == 0){
            tvResult.setText("SAD");
            load_bitmaps("sad_sprite.png", NB_FRAMES_SAD);
        }
        else if(r==1){
            tvResult.setText("HAPPY");
            load_bitmaps("happy_sprite.png", NB_FRAMES_HAPPY);
        }
        else if(r==2){
            tvResult.setText("ANGRY");
            load_bitmaps("angry_sprite.png", NB_FRAMES_ANGRY);
        }
    }
}
