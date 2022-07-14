package com.love.date;

import android.app.*;
import android.media.*;
import android.os.*;
import android.widget.*;
import java.util.*;

public class MainActivity extends Activity 
{
	Calendar calStart,calEnd;
 int yearStart=2021;
int monthStart=2;
 int dayStart=22;
 int hourStart=8;
int minStart=25;
int secStart=20;
	final long ticksPerSec=1000;
	final long ticksPerMin=1000*60;
	final long ticksPerHour=1000*60*60;
    final long ticksPerDay=1000*60*60*24;
	TextView tv;
	TextView tvdate;
	TextView tvho;
	TextView tvmu;
	TextView tvse;
 MediaPlayer mu;
	Handler handler=new Handler();
	Runnable runnable=new Runnable(){
		@Override
		public void run()
		{
			handler.postDelayed(this,1000);
			calculate();
		}
	};
    @Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tvdate=findViewById(R.id.tvdate);
		tvho=findViewById(R.id.tvho);
		tvmu=findViewById(R.id.tvmu);
		tvse=findViewById(R.id.tvse);
		calStart=Calendar.getInstance();
		calStart.set(yearStart,monthStart-1,dayStart,hourStart,minStart,secStart);
		calEnd=Calendar.getInstance();
		handler.post(runnable);
	}
	private void calculate(){
		calStart=Calendar.getInstance();
		calStart.set(yearStart,monthStart-1,dayStart,hourStart,minStart,secStart);
		long diff=calEnd.getTimeInMillis()-calStart.getTimeInMillis();
		int days=(int)Math.floor(diff/ticksPerDay);
		diff=diff%ticksPerDay;
		int hours=(int)Math.floor(diff/ticksPerHour);
		diff=diff%ticksPerHour;
		int mins=(int)Math.floor(diff/ticksPerMin);
		diff=diff%ticksPerMin;
		int secs=(int)(diff/ticksPerSec);
		tvdate.setText(days+" Days  ");
		tvho.setText(hours+" Hours  ");
		tvmu.setText(mins+" Minutes ");
		tvse.setText(secs+" Seconds  ");
	}
	@Override
	public void onStart() {
		super.onStart();
		mu = MediaPlayer.create(getApplicationContext(), R.raw.love);
		mu.start();
	}
	@Override
	public void onBackPressed() {
		if (mu.isPlaying()) {
			mu.pause();
			finishAffinity();
		}
		}
}
