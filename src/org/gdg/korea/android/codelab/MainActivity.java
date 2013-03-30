
package org.gdg.korea.android.codelab;

import net.simonvt.menudrawer.MenuDrawer;

import org.gdg.korea.android.oscl1.R;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class MainActivity extends SherlockActivity implements OnClickListener {

	ActionBar actionBar;
	MenuDrawer menuDrawer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(true);

		menuDrawer = MenuDrawer.attach(this, MenuDrawer.MENU_DRAG_WINDOW);
		menuDrawer.setContentView(R.layout.content_sample);
		menuDrawer.setMenuView(R.layout.menu_scrollview);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

		menuDrawer.peekDrawer();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home) {
			menuDrawer.toggleMenu();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}//end of class
