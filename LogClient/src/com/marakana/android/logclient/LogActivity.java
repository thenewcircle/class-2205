package com.marakana.android.logclient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.marakana.android.logcommon.LogMessage;

public class LogActivity extends Activity implements OnClickListener {
	private static final String TAG = LogActivity.class.getSimpleName();
	private static final int[] LOG_LEVEL = { Log.VERBOSE, Log.DEBUG, Log.DEBUG,
			Log.WARN, Log.ERROR };

	private Spinner priority;
	private EditText tag;
	private EditText msg;
	private Button button;
	
	private LogManager logManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.priority = (Spinner) super.findViewById(R.id.log_priority);
		this.tag = (EditText) super.findViewById(R.id.log_tag);
		this.msg = (EditText) super.findViewById(R.id.log_msg);
		this.button = (Button) super.findViewById(R.id.log_button);
		this.button.setOnClickListener(this);

		logManager = new LogManager(this);
	}

	public void onClick(View v) {
		int priorityPosition = this.priority.getSelectedItemPosition();
		if (priorityPosition != AdapterView.INVALID_POSITION) {
			int priority = LOG_LEVEL[priorityPosition];
			String tag = this.tag.getText().toString();
			String message = this.msg.getText().toString();
			logManager.logit( new LogMessage(priority, tag, message) );
			this.tag.getText().clear();
			this.msg.getText().clear();
			Toast.makeText(this, R.string.log_success, Toast.LENGTH_SHORT)
					.show();
		}
	}
}
