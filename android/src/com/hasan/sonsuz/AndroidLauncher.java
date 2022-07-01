package com.hasan.sonsuz;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.hasan.sonsuz.sema;

public class AndroidLauncher extends AndroidApplication  implements AdsController {
	private InterstitialAd mInterstitialAd;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new sema(this), config);

		MobileAds.initialize(this, new OnInitializationCompleteListener() {
			@Override
			public void onInitializationComplete(InitializationStatus initializationStatus) {
			}
		});
		mInterstitialAd = new InterstitialAd(this);
		// test mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
		mInterstitialAd.setAdUnitId("ca-app-pub-6139386909716103/2681024841");


	}

	@SuppressLint("MissingPermission")
	@Override
	public void loadInterstitialAd() {
		mInterstitialAd.loadAd(new AdRequest.Builder().build());
	}

	@Override
	public void showInterstitialAd() {

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (mInterstitialAd.isLoaded()){
					mInterstitialAd.show();
				}
				else{
					loadInterstitialAd();
				}
			}
		});
	}
}
