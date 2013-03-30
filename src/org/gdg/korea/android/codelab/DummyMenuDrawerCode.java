package org.gdg.korea.android.codelab;

import android.util.Log;

import net.simonvt.menudrawer.MenuDrawer;

/*
 * This code will be build successfully, 
 * if Android Menu Drawer library is successfully linked. 
 */
public class DummyMenuDrawerCode {

	private static final String TAG = "DummyMenuDrawerCode";

	static {
		Log.v(TAG, "Android Menu Drawer is successfuly loaded: " + MenuDrawer.class.getName());
	}

}//end class
