package com.example.bingmp3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    TextView txtTitle, txtTimestar, txtTimeStop;
    ImageButton imgRev, imgNext, imgstop, imgPlay;
    SeekBar serbarTime;
    // tạo mảng danh sách
    ArrayList<Song> arrayList;
    // tạo mặt định
    int position = 0;
    MediaPlayer media;

    Animation annotation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        // Code
        addSong();
        //
        annotation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.animation_cd);
        //
        media= MediaPlayer.create(MainActivity.this,arrayList.get(position).getFile());
        txtTitle.setText(arrayList.get(position).getTitle());
        // tạo sự kiện click
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position ++;
                if (position > arrayList.size() - 1){
                    position = 0;
                }
               if ( media.isPlaying()){
                   media.stop();
               }

                media= MediaPlayer.create(MainActivity.this,arrayList.get(position).getFile());
                txtTitle.setText(arrayList.get(position).getTitle());
                media.start();
                imgPlay.setImageResource(R.drawable.pause);
                settimeSetting();
                Rutime();
            }
        });
        imgRev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if (position < 0){
                    position= arrayList.size() - 1;
                }
                if (media.isPlaying()){
                    media.stop();
                }
                media= MediaPlayer.create(MainActivity.this,arrayList.get(position).getFile());
                txtTitle.setText(arrayList.get(position).getTitle());
                media.start();
                imgPlay.setImageResource(R.drawable.pause);
                settimeSetting();
                Rutime();

            }

        });

        imgstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                media.stop();
                imgPlay.setImageResource(R.drawable.play);
                media= MediaPlayer.create(MainActivity.this,arrayList.get(position).getFile());
                txtTitle.setText(arrayList.get(position).getTitle());

                imageView.clearAnimation();



            }
        });

        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (media.isPlaying()){
                    media.pause();
                    imgPlay.setImageResource(R.drawable.play);
                }

                else {
                    media.start();
                    imgPlay.setImageResource(R.drawable.pause);

                }
                settimeSetting();
                Rutime();
                imageView.startAnimation(annotation);


            }
        });
        serbarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                  // sử dụng seeTo để nhảy đến vị trí người chọn trong video
                media.seekTo(seekBar.getProgress());
            }
        });
    }
    private void Rutime(){
        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simple= new SimpleDateFormat("mm:ss");
                txtTimestar.setText(simple.format(media.getCurrentPosition()));
                //update progressbar
                serbarTime.setProgress(media.getCurrentPosition());
                // KIểm tra thời gian bài hát nếu kết thúc -> next
                media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if (position > arrayList.size()-1){
                            position=0;
                        }
                        if (media.isPlaying()){
                            media.stop();
                        }
                        media= MediaPlayer.create(MainActivity.this,arrayList.get(position).getFile());
                        txtTitle.setText(arrayList.get(position).getTitle());
                        media.start();
                        imgPlay.setImageResource(R.drawable.pause);
                        settimeSetting();
                        Rutime();
                        imageView.startAnimation(annotation);

                    }
                });
                handler.postDelayed(this,500);

            }
        },100);
    }
    private void settimeSetting(){
        SimpleDateFormat time = new SimpleDateFormat("mm:ss");

        txtTimeStop.setText(time.format(media.getDuration()));
        // gán Max cho seerbar bằng tổng thời gian Duration
        serbarTime.setMax(media.getDuration());
    }
    private void addSong(){
       arrayList = new ArrayList<>();
       // Tạo thêm nhạc vào danh sách
       arrayList.add(new Song("25 - Táo", R.raw.hailam));
       arrayList.add(new Song("Cho tôi lang thang - Đen Vâu", R.raw.chotoilangthang));
       arrayList.add(new Song("Bật Tình yêu lên - Hòa Minzy", R.raw.battinhyeulen));
       arrayList.add(new Song("Thị mầu - Hòa Minzy", R.raw.thimau));

    }
    private void anhxa(){
        txtTitle = (TextView) findViewById(R.id.textviewNoidung);
        //
        txtTimestar= (TextView) findViewById(R.id.textTimestar);
        //
        txtTimeStop =(TextView)  findViewById(R.id.textTimeStop);
        //
        imgRev = (ImageButton) findViewById(R.id.imageRev);
        //
        imgNext= (ImageButton) findViewById(R.id.imgNext);
        //
        imgstop=(ImageButton) findViewById(R.id.imgStop);
        //
        imgPlay= (ImageButton) findViewById(R.id.imagePlay);
        //
        serbarTime= (SeekBar) findViewById(R.id.seebarMusic);
        //
        imageView= (ImageView) findViewById(R.id.imageViewCD);
    }
}