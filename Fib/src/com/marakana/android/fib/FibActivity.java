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
    		
    		// Java recursive
    		long start = System.currentTimeMillis();
    		long resultJ = FibLib.fibJ(n);
    		long timeJ = System.currentTimeMillis() - start;
    		out.append( String.format("\nfibJ(%d)=%d (%d ms)", n, resultJ, timeJ) );
    		
    		// Java iterative
    		start = System.nanoTime();
    		long resultJI = FibLib.fibJI(n);
    		long timeJI = System.nanoTime() - start;
    		out.append( String.format("\nfibJI(%d)=%d (%d ns)", n, resultJI, timeJI) );

    		// Native recursive
    		start = System.currentTimeMillis();
    		long resultN = FibLib.fibN(n);
    		long timeN = System.currentTimeMillis() - start;
    		out.append( String.format("\nfibN(%d)=%d (%d ms)", n, resultN, timeN) );
    		
    		// Native iterative
    		start = System.nanoTime();
    		long resultNI = FibLib.fibNI(n);
    		long timeNI = System.nanoTime() - start;
    		out.append( String.format("\nfibNI(%d)=%d (%d ns)", n, resultNI, timeNI) );

    }
}