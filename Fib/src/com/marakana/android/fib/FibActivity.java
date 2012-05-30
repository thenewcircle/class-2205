package com.marakana.android.fib;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FibActivity extends Activity {
	EditText in;
	TextView out;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        in = (EditText)findViewById(R.id.in);
        out = (TextView)findViewById(R.id.out);
    }
    
    /** Called when the button is clicked. */
    public void onClick(View v) {
    		long n = Long.parseLong( in.getText().toString() );
    		
    		long resultJ = FibLib.fibJ(n);
    		out.append( String.format("\nfib(%d)=%d", n, resultJ) );
    		
    		long resultN = FibLib.fibN(n);
    		out.append( String.format("\nfib(%d)=%d", n, resultN) );
    }
}