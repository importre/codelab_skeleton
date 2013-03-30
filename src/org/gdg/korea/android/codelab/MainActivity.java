
package org.gdg.korea.android.codelab;

import java.util.List;

import net.simonvt.menudrawer.MenuDrawer;

import org.gdg.korea.android.codelab.YouTubeChannelClient.Callbacks;
import org.gdg.korea.android.oscl1.R;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistItem;

public class MainActivity extends SherlockActivity
implements OnItemClickListener, Callbacks {

	final String TAG = "CODELAB";

	ActionBar actionBar;
	MenuDrawer menuDrawer;
	YouTubeChannelClient client;
	ListView lv;

	final String apiKey = "AIzaSyD7PZvGMPy3CCq-h0-9fex_DTmGM2m-2g0";
	final String channelID = "UCVHFbqXqoYvEWM1Ddxl0QDg";

	ConnectivityManager manager;
	NetworkInfo netinfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		manager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);

		// 셜록은 getSupportActionBar로 액션바를 얻음
		actionBar = getSupportActionBar();
		// 홈 버튼 활성화
		actionBar.setHomeButtonEnabled(true);

		// 왼쪽 메뉴 설정
		menuDrawer = MenuDrawer.attach(this, MenuDrawer.MENU_DRAG_WINDOW);
		menuDrawer.setContentView(R.layout.content_sample);
		menuDrawer.setMenuView(R.layout.menu_scrollview);

		lv = (ListView)menuDrawer.getMenuView().findViewById(R.id.listView1);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

		menuDrawer.peekDrawer();
		client = YouTubeChannelClient.newYouTubeChannelClient(apiKey, channelID);
	}

	@Override
	protected void onResume() {
		super.onResume();
		netinfo = manager.getActiveNetworkInfo();
		if(isConnected()) {
			client.getPlayList(this);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// 홈버튼 누르면 메뉴 토글
		if(item.getItemId() == android.R.id.home) {
			menuDrawer.toggleMenu();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onLoadPlaylist(List<Playlist> playlist) {
		if(isConnected()) {
			PlayListAdapter adapter = new PlayListAdapter(
					this, android.R.layout.simple_list_item_1);
			for(Playlist list: playlist) {
				adapter.add(list);
			}
			lv.setAdapter(adapter);
			lv.setOnItemClickListener(this);
		}
		else {
			Toast.makeText(this, "check network", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onLoadPlaylistItem(String playlistId,
			List<PlaylistItem> playlistItem) {
		Log.d(TAG, "onLoadPlaylistItem");
	}

	// 네트워크 연결 상태 확인
	boolean isConnected() {
		if(null != netinfo && netinfo.isConnected()) {
			return true;
		}
		return false;
	}

	// 왼쪽 스크롤메뉴에서 아이템을 클릭했을 때
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Playlist playlist = (Playlist)arg0.getAdapter().getItem(arg2);
		Toast.makeText(
				this,
				playlist.getSnippet().toString(),
				Toast.LENGTH_SHORT).show();
		client.getPlaylistItem(playlist.getId(), this);
	}

}//end of class
