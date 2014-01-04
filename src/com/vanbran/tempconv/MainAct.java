package com.vanbran.tempconv;

import java.text.DecimalFormat;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainAct extends Activity implements RadioGroup.OnCheckedChangeListener
{
    /** Called when the activity is first created. */
	
	private String spinnerArray[];
//	RadioButton radioCtoF = (RadioButton)findViewById(R.id.celciusToFahr);	
	protected static RadioGroup  radioGrp  ;
	int selectInt ;
	
	@Override
	public void onCheckedChanged(RadioGroup arg0, int checkedId)
	{
		doCalc();
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
        try
        {
        	radioGrp = (RadioGroup)findViewById(R.id.convSelect);
        	radioGrp.setOnCheckedChangeListener(this);
	        Spinner spinner = (Spinner) findViewById(R.id.inputSpinner);
	        spinnerArray = new String[230];
	        
	        int i = -60 ;
	        int c = 0   ;

	        while (i < 161)
	        {	        		
	        	spinnerArray[c] = "" + i ;
	        	i++;
	        	c++;
	        }
	        
	        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArray);
	        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        spinner.setAdapter(adapter);
  	        spinner.setSelection(60, true);
	        
        	spinner.setOnItemSelectedListener(new OnItemSelectedListener()
	        {
	        	public void onItemSelected(AdapterView<?> arg0, View _view, int arg2, long arg3) 	
	        	{
	        		TextView selected 		= (TextView)_view;
	        		selectInt     		= Integer.parseInt(selected.getText().toString()) ;
	        		doCalc();
	        	}	
	        	public void onNothingSelected(AdapterView <?> arg0) 
	        	{
	        		
	        	}
	        }); //Close spinner.setOnItemSelectedListener()
        }
        catch(Exception ex)
        {
	      	Context context = getApplicationContext();
	  		CharSequence text = "onCreate: " + ex.toString();
	  		int duration = 500000 ; //Toast.LENGTH_LONG;
	  		
	  		Toast toast = Toast.makeText(context, text, duration);
	  		toast.show();
      }
    }
	  //Create a menu
    @Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		//Inflate the menu
		MenuInflater menuInf = getMenuInflater();
		menuInf.inflate(R.menu.menu, menu);
		return true;
	}
    
    //Do something when a menu option is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch (item.getItemId())
    	{
    		case R.id.about:  
    			try
        		{
        			Intent intent = new Intent(MainAct.this, about.class);
        			startActivity(intent);
        		}
        		catch(Exception ex)
        		{
        			Context context = getApplicationContext();
        			CharSequence text = ex.toString();
        			int duration = Toast.LENGTH_LONG ;
        			
        			Toast toast = Toast.makeText(context, text, duration);
        			toast.show();
        		}
        		break;            		
    	}
    	return true;
    }

    private void doCalc()
	{
    	RadioButton radioCtoF   = (RadioButton)findViewById(R.id.celciusToFahr);
        TextView tempOut  		= (TextView)findViewById(R.id.tempOut);
		
		String tempoutStr 		= ""  ;
		double factor	  		= 0.0 ;
		DecimalFormat decFormat = new DecimalFormat("#0.00");
		
		try
		{
			if (radioCtoF.isChecked())
			{
				factor = 9.0/5.0 ;
				tempoutStr = decFormat.format(factor * (double)selectInt + 32.0 );
			}else
			{
				factor = 5.0/9.0 ;
				tempoutStr = decFormat.format(factor * ((double)selectInt - 32.0));
			}
			
			tempOut.setText(tempoutStr);
		}
		catch(Exception ex)
        {
        	Context context = getApplicationContext();
    		CharSequence text = "doCalc: " + ex.toString();
    		int duration = 500000 ; //Toast.LENGTH_LONG;
    		
    		Toast toast = Toast.makeText(context, text, duration);
    		toast.show();
        }
	}
}
