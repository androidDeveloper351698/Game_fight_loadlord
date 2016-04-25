package com.game.xx.fight.loadlord;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.game.fight.loadlord.CardUtil;
import com.game.fight.loadlord.GameRule;
import com.game.fight.loadlord.Gamers;

public class ActivityTestGame extends BaseActivity implements OnClickListener {

	private GridView mGdViewCard;

	private GridView mGdViewCard2;

	private GridView mGdViewCard3;

	private CardAdapter mCardAdapter;

	private CardAdapter mCardAdapter2;

	private CardAdapter mCardAdapter3;

	private Button bt_chupai;
	
	private Button bt_rehupai;
	
	private Button bt_restart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_game);
		initView();
		setListener();
	}

	@Override
	void initView() {
		bt_chupai = (Button) findViewById(R.id.bt_chupai);
		bt_rehupai = (Button) findViewById(R.id.bt_rechupai);
		bt_restart = (Button) findViewById(R.id.bt_restart);

		mGdViewCard = (GridView) findViewById(R.id.gv_cards);
		mCardAdapter = new CardAdapter(CardUtil.pukepai);
		mGdViewCard.setAdapter(mCardAdapter);

		mGdViewCard2 = (GridView) findViewById(R.id.gv_cards2);
		mCardAdapter2 = new CardAdapter(CardUtil.pukepai);
		mGdViewCard2.setAdapter(mCardAdapter2);

		mGdViewCard3 = (GridView) findViewById(R.id.gv_cards3);
		mCardAdapter3 = new CardAdapter(null);
		mGdViewCard3.setAdapter(mCardAdapter3);
	}

	@Override
	void setListener() {
		bt_chupai.setOnClickListener(this);
		bt_rehupai.setOnClickListener(this);
		bt_restart.setOnClickListener(this);
	}

	GameRule localGuize = new GameRule();

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.bt_chupai: {
			Gamers wanjiaA = new Gamers();
			wanjiaA.name = "路人甲";
			wanjiaA.status = "农民";
			wanjiaA.position = "左";
			wanjiaA.operationStatus = "出牌";
			wanjiaA.chuPai = CardUtil
					.transitionToInts(mCardAdapter3.listPukepais);
			if (localGuize.isCanChuPai(wanjiaA)) {
				localGuize.paiZHuo.add(wanjiaA.chuPai);
				mCardAdapter3.listPukepais.clear();
				mCardAdapter3.notifyDataSetChanged();
			}
		}

			break;
		case R.id.bt_rechupai: {
			mCardAdapter3.listPukepais.clear();
			mCardAdapter3.notifyDataSetChanged();

		}
			break;
		case R.id.bt_restart: {
			localGuize.paiZHuo.clear();
			mCardAdapter3.listPukepais.clear();
			mCardAdapter3.notifyDataSetChanged();

		}
			break;

		default:
			break;
		}

	}

	class CardAdapter extends BaseAdapter {

		private String[] pukepais;
		/**
		 * 玩家出的牌
		 */
		private List<String> listPukepais = new ArrayList<String>();

		public CardAdapter(String[] pukepais) {
			this.pukepais = pukepais;
		}

		public void setData(String pukepai) {
			this.listPukepais.add(pukepai);
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			if (pukepais != null) {
				return pukepais.length;
			} else {
				return listPukepais.size();
			}
		}

		@Override
		public Object getItem(int arg0) {
			if (pukepais != null) {
				return pukepais[arg0];
			} else {
				return listPukepais.get(arg0);
			}
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			HoldView localHoldView = null;
			if (arg1 == null) {
				arg1 = new TextView(ActivityTestGame.this);
				GridView.LayoutParams llParams = new GridView.LayoutParams(100,
						100);
				arg1.setLayoutParams(llParams);
				localHoldView = new HoldView();
				localHoldView.tv_card = (TextView) arg1;
				arg1.setTag(localHoldView);
			} else {
				localHoldView = (HoldView) arg1.getTag();
			}
			if (pukepais != null) {
				final String pukepai = pukepais[arg0];
				localHoldView.tv_card.setText(pukepai);
				localHoldView.tv_card.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						ActivityTestGame.this.mCardAdapter3.setData(pukepai);
					}
				});

			} else {
				localHoldView.tv_card.setText(listPukepais.get(arg0));
			}
			return arg1;
		}

		abstract class Test {
			abstract void setCard(String card);
		}

		class HoldView {
			TextView tv_card;
		}
	}

}
