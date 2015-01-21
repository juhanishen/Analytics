package com.battery.analytics.client;

import com.battery.analytics.client.zxgj.ZXGJEPACommentPanel;
import com.battery.analytics.client.zxgj.ZXGJEPAEventPanel;
import com.battery.analytics.client.zxgj.ZXGJKnowledgePanel;
import com.battery.analytics.client.zxgj.ZXGJMainPanel;
import com.battery.analytics.client.zxgj.ZXGJSearchPanel;
import com.battery.analytics.client.zxgj.ZXGJTimeSeriesPanel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;


public class Analytics implements EntryPoint {
	
	public void onModuleLoad() {
		TabPanel tp = new TabPanel();
		
		ZXGJMainPanel mainPanel = new ZXGJMainPanel();
		mainPanel.createLogClassificationTable();
		
		ZXGJTimeSeriesPanel plotPanel = new ZXGJTimeSeriesPanel();
		plotPanel.createPlotComponent();
		
		ZXGJSearchPanel searchPanel = new ZXGJSearchPanel();
		searchPanel.createSearchComponents();	
		
		ZXGJEPAEventPanel eventPanel = new ZXGJEPAEventPanel();
		eventPanel.createEventComponent();
		
		ZXGJEPACommentPanel commentPanel = new ZXGJEPACommentPanel();
		commentPanel.createCommentComponent();
		
		ZXGJKnowledgePanel knowledgePanel = new ZXGJKnowledgePanel();
		knowledgePanel.createKnowledgeComponent();
		
	    tp.add(mainPanel, "Main");	
	    tp.add(plotPanel, "时域分布图");
	    tp.add(searchPanel, "Search");	 
	    tp.add(eventPanel,"EPAEvent");
	    tp.add(commentPanel,"EPAComments");
	    tp.add(knowledgePanel,"专家系统");
	    
	    //set parameters of tp:
	    tp.setWidth("1200px");
	    tp.setHeight("800px");
	    // Show the 'bar' tab initially.
	    tp.selectTab(0);

	    // Add it to the root panel.
	    RootPanel.get("tabPanel").add(tp);
	}

}
