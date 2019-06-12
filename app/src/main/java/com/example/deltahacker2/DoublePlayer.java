package com.example.deltahacker2;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import static android.view.View.INVISIBLE;

public class DoublePlayer extends AppCompatActivity {
    Board board;
    TextView winnerrr,tend;
    Button b;
    int[][] score = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
    float x=7,y=6;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        board= new Board(this);
        b= new Button(this);
        Intent j = getIntent();
        x = j.getFloatExtra(TypeActivity.EXTRA_NUMBER3,7);
        y = j.getFloatExtra(TypeActivity.EXTRA_NUMBER4,6);
        setContentView(R.layout.activity_double_player);
        Set();
    }


    int l=6;
    float heightt,widthhh;
    int selection;
    float r;
    float cx=-1,cy=-1;
    int[] d={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    int a=0;
    int f=1;
    int nanganach=0;
    int pp,qq,rr,ss;

    public class Board extends View
    {
        public Paint cir;
        public Paint rect;
        public Bitmap bitmap;

        public Board(Context context)
        {
            super(context);
            rect= new Paint();
            cir = new Paint();
        }
        @Override
        protected void dispatchDraw(Canvas canvas)
        {
            super.dispatchDraw(canvas);
            if (bitmap == null)
            {
                Create();
            }
            canvas.drawBitmap(bitmap, 0, (getHeight())/4f, null);
        }

        public void Create()
        {
            bitmap = Bitmap.createBitmap(getWidth(),getHeight(), Bitmap.Config.ARGB_4444);
            Canvas temp = new Canvas(bitmap);
            rect.setColor(Color.parseColor("#255ff4"));
            temp.drawRect(temp.getWidth()/2-2*getWidth()/5-50,0,temp.getWidth()/2+2*getWidth()/5+50,3*getHeight()/5f,rect);
            cir.setColor(Color.parseColor("#80003366"));
            heightt= 3*getHeight()/5f;
            widthhh=4*getWidth()/5f;
            float dia=(widthhh/x) < (heightt/y) ? (widthhh/x):(heightt/y);
            r=dia/2-2*getResources().getDisplayMetrics().density;
            for(float i=1;i<=y;i+=1)
            {
                for (float j = 1; j <= x; j+=1)
                {
                    temp.drawCircle(((2*j-1)/(2*x))*widthhh+temp.getWidth()/2f - 2*getWidth()/5f, ((2*i-1)/(2*y))*heightt,r, cir);
                }
            }
        }

        int flag=200;

        @Override
        public boolean onTouchEvent(MotionEvent event)
        {
            if(event.getAction()==MotionEvent.ACTION_DOWN) {
                cx = event.getX();
                cy = event.getY();
                selection = (int) Math.floor((cx - (bitmap.getWidth() / 2f - 2 * getWidth() / 5f)) * x / widthhh);
                if (selection < x && selection >= 0 && d[selection] < y) {
                    {
                        d[selection]++;
                        a++;

                        int t = (int) y;
                        t = t + 3 - d[selection];
                        if(a%2==0)
                            score[t][selection + 3] = 1;
                        else
                            score[t][selection + 3] = 2;
                        System.out.println((selection) + " and " + (t - 3) + " set to " + score[t][selection + 3]);

                        pp = t;
                        qq = selection + 3;

                        int p = t;
                        int q = selection + 3;

                        int pix;
                        int flagg = 0;
                        for (pix = 3; pix < (3 + x); pix++)
                            if (score[3][pix] == 0)
                                flagg = 1;

                        if (nanganach != 1)
                            dropballs(getHeight() / 4f);

                        if ((((score[p][q] == score[p + 1][q] && score[p][q] == score[p + 2][q] && score[p][q] == score[p + 3][q]) || (score[p][q] == score[p + 1][q] && score[p][q] == score[p - 1][q] && score[p][q] == score[p + 2][q]) || (score[p][q] == score[p + 1][q] && score[p][q] == score[p - 2][q] && score[p][q] == score[p - 1][q]) || (score[p][q] == score[p - 1][q] && score[p - 1][q] == score[p - 2][q] && score[p - 2][q] == score[p - 3][q]) || (score[p][q] == score[p][q + 1] && score[p][q] == score[p][q + 2] && score[p][q] == score[p][q + 3]) || (score[p][q] == score[p][q + 1] && score[p][q] == score[p][q + 2] && score[p][q] == score[p][q - 1]) || (score[p][q] == score[p][q + 1] && score[p][q] == score[p][q - 2] && score[p][q] == score[p][q - 1]) || (score[p][q] == score[p][q - 1] && score[p][q] == score[p][q - 2] && score[p][q] == score[p][q - 3]) || (score[p][q] == score[p + 1][q + 1] && score[p][q] == score[p + 2][q + 2] && score[p][q] == score[p + 3][q + 3]) || (score[p][q] == score[p + 1][q + 1] && score[p][q] == score[p + 2][q + 2] && score[p][q] == score[p - 1][q - 1]) || (score[p][q] == score[p + 1][q + 1] && score[p][q] == score[p - 2][q - 2] && score[p][q] == score[p - 1][q - 1]) || (score[p][q] == score[p - 1][q - 1] && score[p][q] == score[p - 2][q - 2] && score[p][q] == score[p - 3][q - 3]) || (score[p][q] == score[p + 1][q - 1]) && (score[p][q] == score[p + 2][q - 2]) && (score[p][q] == score[p + 3][q - 3]) || (score[p][q] == score[p + 1][q - 1]) && score[p][q] == score[p + 2][q - 2] && score[p][q] == score[p - 1][q + 1]) || (score[p][q] == score[p + 1][q - 1] && score[p][q] == score[p - 2][q + 2] && score[p][q] == score[p - 1][q + 1]) || (score[p][q] == score[p - 1][q + 1] && score[p][q] == score[p - 2][q + 2] && score[p][q] == score[p - 3][q + 3]) && ((score[p][q] == 2) || (score[p][q] == 1)))) {
                            winnerrr.setVisibility(VISIBLE);
                            End();

                            if (score[p][q] == 1) {
                                winnerrr.setTextColor(Color.parseColor("#4BB543"));
                                winnerrr.setText("PLAYER 2 WINS");
                                winnerrr.setBackgroundColor(Color.parseColor("#98FB98"));
                            } else if (score[p][q] == 2) {
                                winnerrr.setTextColor(Color.parseColor("#FF0000"));
                                winnerrr.setText("PLAYER 1 WINS");
                                winnerrr.setBackgroundColor(Color.parseColor("#DC143C"));
                            }
                            nanganach = 1;
                        } else if (flagg == 0) {

                            End();
                            winnerrr.setVisibility(VISIBLE);
                            nanganach = 1;
                            winnerrr.setTextColor(Color.parseColor("#FF0000"));
                            winnerrr.setText("MATCH DRAWN.");
                            winnerrr.setBackgroundColor(Color.parseColor("#DC143C"));
                        }

                    }
                }
            }
            return true;
        }
    }

    public class ballGenerate extends View
    {
        Paint redcolor,yellowcolor;

        public ballGenerate(Context context)
        {
            super(context);
            redcolor=new Paint();
            redcolor.setColor(Color.RED);
            yellowcolor=new Paint();
            yellowcolor.setColor(Color.YELLOW);
        }

        @Override
        public void onDraw(Canvas canvas)
        {
            redcolor.setStrokeWidth(5);
            yellowcolor.setStrokeWidth(5);
            if(a%2==1)
                canvas.drawCircle(((2*selection+1)/(2*x))*widthhh+getWidth()/2 - 2*getWidth()/5,heightt/(2*y),r,redcolor);
            else
                canvas.drawCircle(((2*selection+1)/(2*x))*widthhh+getWidth()/2 - 2*getWidth()/5,heightt/(2*y),r,yellowcolor);
        }
    }

    public void dropballs(float f)
    {
        ballGenerate ball = new ballGenerate(this);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.layyy);
        RelativeLayout.LayoutParams vp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        ball.setLayoutParams(vp);
        relativeLayout.addView(ball);
        MediaPlayer ring = MediaPlayer.create(DoublePlayer.this, R.raw.blip);
        ring.start();
        ball.animate().translationY(f + (1 - (1 / y)) * heightt - (d[selection] - 1) * heightt / (y));
        ball.animate().setDuration(1500);
    }

    public void Set()
    {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.layyy);
        board= new Board(this);
        tend = new TextView(this);
        tend = (TextView)findViewById(R.id.temppp);

        RelativeLayout.LayoutParams ap = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        ap.addRule(RelativeLayout.ABOVE,tend.getId());
        board.setLayoutParams(ap);

        winnerrr = new TextView(DoublePlayer.this);
        winnerrr=(TextView)findViewById(R.id.winnerrr);
        winnerrr.setVisibility(INVISIBLE);

        relativeLayout.addView(board);
    }

    public void End()
    {
        b = new Button(DoublePlayer.this);
        tend = (TextView)findViewById(R.id.temppp);
        tend.setVisibility(INVISIBLE);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.layyy);
        RelativeLayout.LayoutParams add = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        b.setGravity(Gravity.CENTER_HORIZONTAL);
        relativeLayout.setGravity(RelativeLayout.CENTER_HORIZONTAL);
        add.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,relativeLayout.getId());
        add.addRule(RelativeLayout.CENTER_HORIZONTAL,relativeLayout.getId());
        add.bottomMargin=100;
        b.setText("START AGAIN");
        b.setLayoutParams(add);
        relativeLayout.addView(b);

        b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(DoublePlayer.this,StartActivity.class);
                startActivity(intent);
            }
        });
    }
}