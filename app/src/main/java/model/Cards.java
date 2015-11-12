package model;

import android.content.Context;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Администратор on 06.11.2015.
 */
public class Cards {
    private final static String [] cards = {"Червей", "Крести", "Пика", "Бубей"};
    private TextView mast_type;
    private CountDownTimer timer;
    private TextView textView;
    private ImageView cardView;
    private Runnable runnable;
    private Handler handler;
    private Context context;
    private boolean threadStatus = false;

    private int timerTime = 5000;
    private int postTime = 0;
//    private int minTime = 150000;
//    private int maxTime = 210000;
    private int minTime = 60000;
    private int maxTime = 90000;
//    private int minTime = 5000;
//    private int maxTime = 10000;

    public Cards(TextView mast_type, ImageView cardView, TextView textView, Context context) {
        this.textView = textView;
        this.cardView = cardView;
        this.context = context;
        this.mast_type = mast_type;
        this.__init();
    }

    private void __init() {
        timerFinish();
        countDownStart();
    }
    public void countDownStart() {
        threadStatus = true;
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
               if(!threadStatus) return;
               handler.postDelayed(this, 1000);
                try {
                    if(timerTime > 0) {
                        postTime = timerTime;

                        int minutes = postTime / (60 * 1000);
                        postTime -= minutes * (60 * 1000);
                        int seconds = postTime / 1000;
                        timerTime -= 1000;
                        String min_prefix = "", sec_prefix = "";
                        if(minutes <= 9) min_prefix = "0";
                        if(seconds <= 9) sec_prefix = "0";
                        String group = min_prefix+minutes + " : " + sec_prefix+seconds;
                        Log.w("LOG_TAG", group);
                        textView.setText(group);
                    } else {
                        timerFinish();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        handler.postDelayed(runnable, 1000);
    }

    private void timerFinish() {
        timerTime = minTime + (int)(Math.random() * ((maxTime - minTime) + 1));

        //changeCard
        int cardNum = (int)(Math.random() * 4);
        int resId = context.getResources().getIdentifier("img"+String.valueOf(cardNum), "drawable", context.getPackageName());
        cardView.setImageResource(resId);
        Animation animation = AnimationUtils.loadAnimation(context, context.getResources().getIdentifier("frommiddle", "anim", context.getPackageName()));
        cardView.startAnimation(animation);

        mast_type.setText(cards[cardNum]);
        Animation animation2 = AnimationUtils.loadAnimation(context, context.getResources().getIdentifier("alpha", "anim", context.getPackageName()));
        mast_type.startAnimation(animation2);

        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_MUSIC, 90);
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
    }

    public void stopHandler() {
        this.threadStatus = false;
    }
}
