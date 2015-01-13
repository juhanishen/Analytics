package com.battery.analytics.client.zxgj;

import java.util.Date;

import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.Series;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
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
			
	
    		Label keyLabel = new Label("Key Search:");
    		keyLabel.setStyleName("searchPanelLable");
			TextBox tb = new TextBox();	
		   
		    DateBox startDateBox = new DateBox();
		    DateBox endDateBox = new DateBox();
		    startDateBox.setValue(new Date());
		    endDateBox.setValue(new Date());
		    // Make some radio buttons, all in one group.
		    RadioButton rbEPA = new RadioButton("searchRadioGroup", "serach in EPA log file only");
		    RadioButton rbALL = new RadioButton("searchRadioGroup", "search in ALL log files");
		    rbEPA.setValue(true);
		    rbEPA.setStyleName("searchKeyButton");
		    rbALL.setStyleName("searchKeyButton");
		    
		    Button searchButton = new Button("Search");
		    searchButton.setStyleName("searchKeyButton");   
		    
		    final Button showSearchResultChartButton = new Button("Show search result in chart");
		    showSearchResultChartButton.setVisible(false);
		    showSearchResultChartButton.setStyleName("searchKeyButton");
		    final Button showSearchResultSequenceButton = new Button("Show search result in sequence");
		    showSearchResultSequenceButton.setVisible(false);
		    showSearchResultSequenceButton.setStyleName("searchKeyButton");
		    
		    final TextArea ta = new TextArea();
		    ta.setCharacterWidth(35);
		    ta.setVisibleLines(10);

		   	    
		    searchButton.addClickHandler(new ClickHandler() {
		    	public  void onClick(ClickEvent event){
		    	   	ta.setText("EPAlog1: [2014-08-23 10:59:58] [INFO] [CSEComPlugIn.Run] InvokeSECSEvent Mess"+"\n"
                              +"EPAlog2: [2014-08-23 10:59:58] [INFO] [CSEComPlugIn.Run] IMTT02 received"+"\n"
                              +"EPAlog3: [2014-08-23 10:59:58] [WARN] [SECSWrapper.InvokeSECSEvent] SECSWrapper Con");
		    	   	showSearchResultChartButton.setVisible(true);
		    	   	showSearchResultSequenceButton.setVisible(true);
		    	}	    
		    }); 
		    
		    showSearchResultSequenceButton.addClickHandler(new ClickHandler() {
		    	public  void onClick(ClickEvent event){
		    	   	ta.setText("EPAlog1: [2014-08-23 10:59:58] [INFO] [CSEComPlugIn.Run] InvokeSECSEvent Mess"+"\n"
                              +"EPAlog2: [2014-08-23 10:59:58] [INFO] [CSEComPlugIn.Run] IMTT02 received"+"\n"
                              +"EPAlog3: [2014-08-23 10:59:58] [WARN] [SECSWrapper.InvokeSECSEvent] SECSWrapper Con");
		    	   	showSearchResultChartButton.setVisible(true);
		    	   	showSearchResultSequenceButton.setVisible(true);
		    	}	    
		    }); 
		    
		    final VerticalPanel panel = new VerticalPanel();

		    HorizontalPanel searchKeyPanel = new HorizontalPanel();
		    searchKeyPanel.add(keyLabel);
		    searchKeyPanel.add(tb);		    
		    panel.add(searchKeyPanel);
		    
		    HorizontalPanel timePanel = new HorizontalPanel();
		    timePanel.add(startDateBox);
		    timePanel.add(endDateBox);
		    panel.add(timePanel);

		    panel.add(searchButton);
		    
		    HorizontalPanel rbPanel = new HorizontalPanel();
		    rbPanel.add(rbEPA);
		    rbPanel.add(rbALL);
		    panel.add(rbPanel);
		    
		    panel.add(ta);
		    HorizontalPanel hbuttons = new HorizontalPanel();
		    hbuttons.add(showSearchResultChartButton);
		    hbuttons.add(showSearchResultSequenceButton);
		    panel.add(hbuttons);
		    
		    
		    showSearchResultChartButton.addClickHandler(new ClickHandler() {
		    	public  void onClick(ClickEvent event){
		    		panel.add(searchChart);
		    				    	}	    
		    });
		    
		    showSearchResultSequenceButton.addClickHandler(new ClickHandler() {
		    	public  void onClick(ClickEvent event){
		    		Canvas canvas = Canvas.createIfSupported();
		    		canvas.setWidth("600px");
		    		canvas.setHeight("400px");
		            final Context2d context2d = canvas.getContext2d();
		    		drawSequenceLine(context2d);
		    	    panel.add(canvas);
		    	}	    
		    });
		    
		    add(panel);		
    }
    
    private void drawSequenceLine(final Context2d context2d){
        //draw straight line
        context2d.beginPath();
        context2d.moveTo(20,20);
        context2d.lineTo(160,20);   
        context2d.stroke();
        context2d.closePath();
        
        //draw end arrow
        context2d.beginPath();
        context2d.moveTo(155,15);
        context2d.lineTo(160,20);   
        context2d.stroke();
        context2d.closePath();
        
        context2d.beginPath();
        context2d.moveTo(155,25);
        context2d.lineTo(160,20);   
        context2d.stroke();
        context2d.closePath();
        
        //draw straight line        
        context2d.beginPath();
        context2d.moveTo(20,60);
        context2d.lineTo(160,60);   
        context2d.stroke();
        context2d.closePath();
        
        //draw receiving arrow
        context2d.beginPath();
        context2d.moveTo(25,55);
        context2d.lineTo(20,60);   
        context2d.stroke();
        context2d.closePath();
        
        context2d.beginPath();
        context2d.moveTo(25,65);
        context2d.lineTo(20,60);   
        context2d.stroke();
        context2d.closePath();
        
        //straight line 
        context2d.beginPath();
        context2d.moveTo(20,120);
        context2d.lineTo(160,120);   
        context2d.stroke();
        context2d.closePath();   
        
      //draw sending arrow
        context2d.beginPath();
        context2d.moveTo(155,115);
        context2d.lineTo(160,120);   
        context2d.stroke();
        context2d.closePath();
        
        context2d.beginPath();
        context2d.moveTo(155,125);
        context2d.lineTo(160,120);   
        context2d.stroke();
        context2d.closePath();
        
        
        context2d.fillText("this is send message",20, 10);
        context2d.fillText("this is receving message", 20,50);
        context2d.fillText("I am going to enhance sequence diagram",20,110);
    }
}
