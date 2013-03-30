package org.gdg.korea.android.codelab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.api.services.youtube.model.Playlist;

public class PlayListAdapter extends ArrayAdapter<Playlist>{

	private LayoutInflater inflater = null;

	public PlayListAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		inflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if(null == v) {
			v = inflater.inflate(
					android.R.layout.simple_list_item_1,
					parent,
					false);
		}
		Playlist playlist = getItem(position);
		TextView tv = (TextView)v.findViewById(android.R.id.text1);
		tv.setText(playlist.getSnippet().getTitle());
		return v;
	}
}
