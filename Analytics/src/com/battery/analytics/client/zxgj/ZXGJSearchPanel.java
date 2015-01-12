package com.battery.analytics.client.zxgj;

import java.util.Date;

import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.Series;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

public class ZXGJSearchPanel extends VerticalPanel {
	
	
	
    public ZXGJSearchPanel(){}
    
    public void createSearchComponents(){
    	//search highcharts code are here: start:		
        final Chart searchChart = new Chart()
			   .setType(Series.Type.SPLINE)
			   .setChartTitleText("Lawn Tunnels")
			   .setMarginRight(10);

			 Series searchSeries = searchChart.createSeries()
			   .setName("Moles per Yard")
			   .setPoints(new Number[] { 163, 203, 276, 408, 547, 729, 628 });
			searchChart.addSeries(searchSeries);
			
		
			TextBox keybox = new TextBox();
		    keybox.setText("Please input key words:");
		    TextBox tb = new TextBox();
		    DateBox startDateBox = new DateBox();
		    DateBox endDateBox = new DateBox();
		    startDateBox.setValue(new Date());
		    endDateBox.setValue(new Date());
		    // Make some radio buttons, all in one group.
		    RadioButton rbEPA = new RadioButton("searchRadioGroup", "serach in EPA log file only");
		    RadioButton rbALL = new RadioButton("searchRadioGroup", "search in ALL log files");
		    rbEPA.setValue(true);
		    Button searchButton = new Button("Search");
		    final Button showSearchResultChartButton = new Button("Show search result in chart");
		    showSearchResultChartButton.setVisible(false);
		    
		    final TextArea ta = new TextArea();
		    ta.setCharacterWidth(35);
		    ta.setVisibleLines(10);

		    // TODO(ECC) must be tested.
		    tb.addKeyPressHandler(new KeyPressHandler() {

		      public void onKeyPress(KeyPressEvent event) {
		        if (!Character.isDigit(event.getCharCode())) {
		          ((TextBox) event.getSource()).cancelKey();
		        }
		      }
		    });

		    
		    searchButton.addClickHandler(new ClickHandler() {
		    	public  void onClick(ClickEvent event){
		    	   	ta.setText("EPAlog1: [2014-08-23 10:59:58] [INFO] [CSEComPlugIn.Run] InvokeSECSEvent Mess"+"\n"
                              +"EPAlog2: [2014-08-23 10:59:58] [INFO] [CSEComPlugIn.Run] IMTT02 received"+"\n"
                              +"EPAlog3: [2014-08-23 10:59:58] [WARN] [SECSWrapper.InvokeSECSEvent] SECSWrapper Con");
		    	   	showSearchResultChartButton.setVisible(true);

		    	}	    
		    }); 
		    
		    final VerticalPanel panel = new VerticalPanel();
		    panel.add(tb);
		    panel.add(keybox);
		    panel.add(startDateBox);
		    panel.add(endDateBox);
		    panel.add(searchButton);
		    panel.add(rbEPA);
		    panel.add(rbALL);
		    panel.add(ta);
		    panel.add(showSearchResultChartButton);
		   	
		    showSearchResultChartButton.addClickHandler(new ClickHandler() {
		    	public  void onClick(ClickEvent event){
		    		panel.add(searchChart);
		    	   
		    	}	    
		    });
		    
		   
		    
		    this.add(panel);		
    }
}
