package com.example.mars0925.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView info;
    private Button GO1;
    private Button GO2;
    private Button GO3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info = findViewById(R.id.info);
        GO1 = findViewById(R.id.button);
        GO2 = findViewById(R.id.button2);
        GO3 = findViewById(R.id.button3);
        GO1.setOnClickListener(this);
        GO2.setOnClickListener(this);
        GO3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                new Job1Task().execute();
                break;
            case R.id.button2:
                new Job2Task().execute(3);
                break;
            case R.id.button3:
                new Job3Task().execute(10);
                break;
        }
    }

    class Job1Task extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            info.setText("DONE");
        }
    }

    class Job2Task extends AsyncTask<Integer,Void,Void>{

        @Override
        protected Void doInBackground(Integer... integers) {
            try {
                Thread.sleep(integers[0]*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            info.setText("DONE");
        }
    }

    class Job3Task extends AsyncTask<Integer,Integer,Void>{

        @Override
        protected Void doInBackground(Integer... integers) {
            for (int i=0;i<integers[0];i++){
                publishProgress(i);//呼叫此方法會自動呼叫onprogressUpdate方法 更新進度
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            info.setText("DONE");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            info.setText(String.valueOf(values[0]));
        }
    }
}
