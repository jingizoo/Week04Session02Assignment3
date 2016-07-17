package com.jalaj.firstapp.progressbar;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
ProgressBar prgBar1, prgBar2, prgBar3, prgBar4;
    Button btnStartPBars;
    int totalCurrentRunning = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prgBar1 = (ProgressBar)findViewById(R.id.prgBar1);
        prgBar2 = (ProgressBar)findViewById(R.id.prgBar2);
        prgBar3 = (ProgressBar)findViewById(R.id.prgBar3);
        prgBar4 = (ProgressBar)findViewById(R.id.prgBar4);

        btnStartPBars = (Button) findViewById(R.id.btnStartProgressBars);
        btnStartPBars.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        RunBar1 runBar1 = new RunBar1(new ProgressBar[]{prgBar1,prgBar2});
        runBar1.execute();
        RunBar1 runBar2 = new RunBar1(new ProgressBar[]{prgBar3,prgBar4});
        runBar2.execute();
    }

    class RunBar1 extends AsyncTask <Integer,Integer,Integer>{

        int progressStatus=0;
        ProgressBar prgBar1, prgBar2;

        public RunBar1(ProgressBar[] prgBar) {
            this.prgBar1 = prgBar[0];
            this.prgBar2 = prgBar[1];
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            totalCurrentRunning++;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            prgBar1.setProgress((int)values[0]);
            prgBar2.setProgress((int)values[0]);
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            while (progressStatus < 100){
                progressStatus ++;
                publishProgress(new Integer(progressStatus));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return totalCurrentRunning--;
        }
    }

}
