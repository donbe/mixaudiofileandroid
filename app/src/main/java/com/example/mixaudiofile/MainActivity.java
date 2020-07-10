package com.example.mixaudiofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.arthenica.mobileffmpeg.Config;
import com.arthenica.mobileffmpeg.FFmpeg;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.arthenica.mobileffmpeg.Config.RETURN_CODE_CANCEL;
import static com.arthenica.mobileffmpeg.Config.RETURN_CODE_SUCCESS;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mixbutton = findViewById(R.id.mixbutton);
        mixbutton.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {


        String f1 = "file:///data/data/com.example.mixaudiofile/files/2.mp3";
        String f2 = "file:///data/data/com.example.mixaudiofile/files/recording_file_2004222.wav";
        String output = "file:///data/data/com.example.mixaudiofile/files/4.mp3";

//        AssetManager assetManager= this.getAssets();
//        InputStream streamStream = null;
//        try {
//            streamStream = assetManager.open("recording_file_2004222.wav");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        FileOutputStream outStream = null;
//        try {
//            outStream = openFileOutput("recording_file_2004222.wav", Context.MODE_PRIVATE);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        //创建一个Buffer字符串
//        byte[] buffer = new byte[1024];
////每次读取的字符串长度，如果为-1，代表全部读取完毕
//        int len = 0;
////使用一个输入流从buffer里把数据读取出来
//        while(true){
//            try {
//                if (!((len=streamStream.read(buffer)) != -1)) break;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
////用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
//            try {
//                outStream.write(buffer, 0, len);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
////关闭输入流
//        try {
//            streamStream .close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        int rc = FFmpeg.execute("-i "+f1+" -i "+f2+" -filter_complex amerge "+output);

        if (rc == RETURN_CODE_SUCCESS) {
            Log.i(Config.TAG, "Command execution completed successfully.");
        } else if (rc == RETURN_CODE_CANCEL) {
            Log.i(Config.TAG, "Command execution cancelled by user.");
        } else {
            Log.i(Config.TAG, String.format("Command execution failed with rc=%d and the output below.", rc));
            Config.printLastCommandOutput(Log.INFO);
        }


    }
}
