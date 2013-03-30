package org.gdg.korea.android.codelab;

import android.util.Log;

import com.actionbarsherlock.app.ActionBar;

/*
 * This code will be build successfully, 
 * if actionbarSherlock library is successfully linked. 
 */
public class DummyActionBarCode {

	private static final String TAG = "DummyActionBarCode";

	static {
		ActionBar.LayoutParams param = new ActionBar.LayoutParams(0, 0);
		Log.v(TAG, "ActionbarSherlock is successfuly loaded: " + param.getClass().getName());
	}

}//end class
