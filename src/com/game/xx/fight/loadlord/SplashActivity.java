package com.game.xx.fight.loadlord;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_main_view);
		Intent intent = new Intent(this, ActivityTestGame.class);
		startActivity(intent);
	}

	@Override
	void initView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void setListener() {
		// TODO Auto-generated method stub
		
	}

}
