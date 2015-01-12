package com.battery.analytics.client;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.moxieapps.gwt.highcharts.client.Axis;
import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.ChartSubtitle;
import org.moxieapps.gwt.highcharts.client.ChartTitle;
import org.moxieapps.gwt.highcharts.client.Color;
import org.moxieapps.gwt.highcharts.client.Legend;
import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.ToolTip;
import org.moxieapps.gwt.highcharts.client.plotOptions.AreaPlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.Marker;

import com.battery.analytics.client.zxgj.ZXGJEPACommentPanel;
import com.battery.analytics.client.zxgj.ZXGJEPAEventPanel;
import com.battery.analytics.client.zxgj.ZXGJKnowledgePanel;
import com.battery.analytics.client.zxgj.ZXGJMainPanel;
import com.battery.analytics.client.zxgj.ZXGJSearchPanel;
import com.battery.analytics.client.zxgj.ZXGJTimeSeriesPanel;
import com.battery.analytics.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.ListDataProvider;

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
	    tp.setWidth("600px");
	    tp.setHeight("400px");

	    // Show the 'bar' tab initially.
	    tp.selectTab(0);

	    // Add it to the root panel.
	    RootPanel.get("tabPanel").add(tp);
	}

}
