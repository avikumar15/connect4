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

public class MainActivity extends AppCompatActivity {
    Board board;
    TextView winner,tend;
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
        Intent i = getIntent();
        x = i.getFloatExtra(TypeActivity.EXTRA_NUMBER3,7);
        y = i.getFloatExtra(TypeActivity.EXTRA_NUMBER4,6);
        setContentView(R.layout.activity_main);
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
            if(event.getAction()==MotionEvent.ACTION_DOWN)
            {
                cx = event.getX();
                cy = event.getY();
                selection=(int)Math.floor((cx-(bitmap.getWidth()/2f - 2*getWidth()/5f))*x/widthhh);
                if(selection<x&&selection>=0&&d[selection]<y) {
                    if (a % 2 == 0) {
                        d[selection]++;
                        a++;

                        int t = (int) y;
                        t = t + 3 - d[selection];
                        score[t][selection + 3] = 1;
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
                            winner.setVisibility(VISIBLE);
                            End();

                            if (score[p][q] == 1) {
                                winner.setTextColor(Color.parseColor("#4BB543"));
                                winner.setText("YOU WIN");
                                winner.setBackgroundColor(Color.parseColor("#98FB98"));
                            } else if (score[p][q] == 2) {
                                winner.setTextColor(Color.parseColor("#FF0000"));
                                winner.setText("COMPUTER WINS.");
                                winner.setBackgroundColor(Color.parseColor("#DC143C"));
                            }
                            nanganach = 1;
                        } else if (flagg == 0) {

                            End();
                            winner.setVisibility(VISIBLE);
                            nanganach = 1;
                            winner.setTextColor(Color.parseColor("#FF0000"));
                            winner.setText("MATCH DRAWN.");
                            winner.setBackgroundColor(Color.parseColor("#DC143C"));
                        }
                        if (nanganach != 1) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    int t = (int) y;
                                    int i, j;
                                    flag = 200;
                                    f = 1;
                                    for (i = (int) y + 2; i > 2; i--) {
                                        for (j = 3; j <= (int) x + 2; j++) {
                                            if (j >= 3)
                                                if (f == 1 && score[i][j] == score[i - 1][j] && score[i][j] == score[i - 2][j] && (score[i][j] == 1 || score[i][j] == 2) && d[j - 3] == y - (i - 5)) {
                                                    flag = j;
                                                    f = 2;
                                                    System.out.println("VERTICAL.");
                                                    break;
                                                } else if (j >= 4)
                                                    if (f == 1 && (score[i][j] == 1 || score[i][j] == 2) && score[i][j] == score[i][j + 1] && score[i][j] == score[i][j + 2] && ((y - (i - 3) - d[j - 4] == 1) || (y - (i - 3) - d[j] == 1))) {
                                                        f = 2;
                                                        if ((j > 3) && (y - (i - 3) - d[j - 4] == 1)) {
                                                            flag = j - 1;
                                                            System.out.println("horizontal1 " + j + " " + i + " " + score[i][j]);
                                                            break;
                                                        } else if ((y - (i - 3) - d[j] == 1)) {
                                                            flag = j + 3;
                                                            System.out.println("horizontal2 " + j + " " + i + " " + score[i][j]);
                                                            break;
                                                        }
                                                    }

                                            if (j >= 4)
                                                if (f == 1 && (score[i][j] == 1 || score[i][j] == 2) && score[i][j] == score[i - 1][j + 1] && score[i][j] == score[i - 2][j + 2] && ((d[j - 3] - d[j - 4] == 1 && d[j - 3] > 1) || (d[j] == d[j - 1] && d[j - 1] < y))) {
                                                    f = 2;
                                                    if ((d[j] == d[j - 1] && d[j - 1] < y)) {
                                                        flag = j;
                                                        break;
                                                    } else if ((d[j - 3] - d[j - 4] == 1 && d[j - 3] > 1)) {
                                                        flag = j - 4;
                                                        break;
                                                    }
                                                }
                                        }
                                    }
                                    selection = flag - 3;
                                    if (selection < 0)
                                        selection = 0;

                                    if (selection >= x) {
                                        Random rand = new Random();
                                        selection = rand.nextInt((int) x);
                                        System.out.println("random");
                                    }
                                    while (d[selection] >= y) {
                                        Random rand2 = new Random();
                                        selection = rand2.nextInt((int) x);
                                    }
                                    d[selection]++;
                                    a++;

                                    t = t + 3 - d[selection];
                                    score[t][selection + 3] = 2;

                                    rr = t;
                                    ss = selection + 3;

                                    System.out.println((selection) + " and " + (t - 3) + " set to " + score[t][selection + 3]);

                                    int p = t;
                                    int q = selection + 3;
                                    tend = (TextView)findViewById(R.id.temp);

                                    int pix;
                                    int flagg = 0;
                                    for (pix = 3; pix < (3 + x); pix++)
                                        if (score[3][pix] == 0)
                                            flagg = 1;

                                    if (nanganach != 1)
                                        dropballs(getHeight() / 4f);

                                    if ((((score[p][q] == score[p + 1][q] && score[p][q] == score[p + 2][q] && score[p][q] == score[p + 3][q]) || (score[p][q] == score[p + 1][q] && score[p][q] == score[p - 1][q] && score[p][q] == score[p + 2][q]) || (score[p][q] == score[p + 1][q] && score[p][q] == score[p - 2][q] && score[p][q] == score[p - 1][q]) || (score[p][q] == score[p - 1][q] && score[p - 1][q] == score[p - 2][q] && score[p - 2][q] == score[p - 3][q]) || (score[p][q] == score[p][q + 1] && score[p][q] == score[p][q + 2] && score[p][q] == score[p][q + 3]) || (score[p][q] == score[p][q + 1] && score[p][q] == score[p][q + 2] && score[p][q] == score[p][q - 1]) || (score[p][q] == score[p][q + 1] && score[p][q] == score[p][q - 2] && score[p][q] == score[p][q - 1]) || (score[p][q] == score[p][q - 1] && score[p][q] == score[p][q - 2] && score[p][q] == score[p][q - 3]) || (score[p][q] == score[p + 1][q + 1] && score[p][q] == score[p + 2][q + 2] && score[p][q] == score[p + 3][q + 3]) || (score[p][q] == score[p + 1][q + 1] && score[p][q] == score[p + 2][q + 2] && score[p][q] == score[p - 1][q - 1]) || (score[p][q] == score[p + 1][q + 1] && score[p][q] == score[p - 2][q - 2] && score[p][q] == score[p - 1][q - 1]) || (score[p][q] == score[p - 1][q - 1] && score[p][q] == score[p - 2][q - 2] && score[p][q] == score[p - 3][q - 3]) || (score[p][q] == score[p + 1][q - 1]) && (score[p][q] == score[p + 2][q - 2]) && (score[p][q] == score[p + 3][q - 3]) || (score[p][q] == score[p + 1][q - 1]) && score[p][q] == score[p + 2][q - 2] && score[p][q] == score[p - 1][q + 1]) || (score[p][q] == score[p + 1][q - 1] && score[p][q] == score[p - 2][q + 2] && score[p][q] == score[p - 1][q + 1]) || (score[p][q] == score[p - 1][q + 1] && score[p][q] == score[p - 2][q + 2] && score[p][q] == score[p - 3][q + 3]) && ((score[p][q] == 2) || (score[p][q] == 1)))) {
                                        winner.setVisibility(VISIBLE);
                                        End();
                                        if (score[p][q] == 1) {
                                            winner.setTextColor(Color.parseColor("#4BB543"));
                                            winner.setText("PLAYER WINS.");
                                            winner.setBackgroundColor(Color.parseColor("#98FB98"));
                                        } else if (score[p][q] == 2) {
                                            winner.setTextColor(Color.parseColor("#FF0000"));
                                            winner.setText("COMPUTER WINS.");
                                            winner.setBackgroundColor(Color.parseColor("#DC143C"));
                                        }
                                        nanganach = 1;
                                    } else if (flagg == 0) {

                                        End();
                                        winner.setVisibility(VISIBLE);
                                        nanganach = 1;
                                        winner.setTextColor(Color.parseColor("#FF0000"));
                                        winner.setText("MATCH DRAWN.");
                                        winner.setBackgroundColor(Color.parseColor("#DC143C"));

                                    }
                                }
                            }, 1800);
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
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.lay);
        RelativeLayout.LayoutParams vp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        ball.setLayoutParams(vp);
        relativeLayout.addView(ball);
        MediaPlayer ring = MediaPlayer.create(MainActivity.this, R.raw.blip);
        ring.start();
        ball.animate().translationY(f + (1 - (1 / y)) * heightt - (d[selection] - 1) * heightt / (y));
        ball.animate().setDuration(1500);
    }

    public void Set()
    {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.lay);
        board= new Board(this);
        tend = new TextView(this);
        tend = (TextView)findViewById(R.id.temp);

        RelativeLayout.LayoutParams ap = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        ap.addRule(RelativeLayout.ABOVE,tend.getId());
        board.setLayoutParams(ap);

        winner = new TextView(MainActivity.this);
        winner=(TextView)findViewById(R.id.winner);
        winner.setVisibility(INVISIBLE);

        relativeLayout.addView(board);
    }

    public void End()
    {
        b = new Button(MainActivity.this);
        tend = (TextView)findViewById(R.id.temp);
        tend.setVisibility(INVISIBLE);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.lay);
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
                Intent intent = new Intent(MainActivity.this,StartActivity.class);
                startActivity(intent);
            }
        });
    }
}