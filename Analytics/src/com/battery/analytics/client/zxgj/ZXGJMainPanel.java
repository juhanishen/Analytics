package com.battery.analytics.client.zxgj;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;


public class ZXGJMainPanel extends VerticalPanel {
	
	private static class LogClassification {
	    private final String logLevel;
	    private final int count;

	    public LogClassification(String logLevel, int count) {
	      this.logLevel = logLevel;
	      this.count = count;
	    }
	  }

	  // The list of data to display.
	  private static List<LogClassification> LOGCLassifications = Arrays.asList(new LogClassification("WARN",
	      12), new LogClassification("INFO", 37));
	  
	  
	  public ZXGJMainPanel(){
		  
		  
		  
	  }
	
	  public void createLogClassificationTable(){
		  CellTable<LogClassification> table = new CellTable<LogClassification>();
		  table.setWidth("300px");
		  table.setHeight("100px");

		    // Create name column.
		    TextColumn<LogClassification> logLevelColumn = new TextColumn<LogClassification>() {
		      @Override
		      public String getValue(LogClassification contact) {
		        return contact.logLevel;
		      }
		    };

		    // Make the name column sortable.
		    logLevelColumn.setSortable(true);

		    // Create address column.
		    TextColumn<LogClassification> countColumn = new TextColumn<LogClassification>() {
		      @Override
		      public String getValue(LogClassification contact) {
		        return String.valueOf(contact.count);
		      }
		    };

		    // Add the columns.
		    table.addColumn(logLevelColumn, "LogLevel");
		    table.addColumn(countColumn, "Count");

		    // Create a data provider.
		    ListDataProvider<LogClassification> dataProvider = new ListDataProvider<LogClassification>();

		    // Connect the table to the data provider.
		    dataProvider.addDataDisplay(table);

		    // Add the data to the data provider, which automatically pushes it to the
		    // widget.
		    List<LogClassification> list = dataProvider.getList();
		    for (LogClassification logLevelClassfi : LOGCLassifications) {
		      list.add(logLevelClassfi);
		    }

		    // Add a ColumnSortEvent.ListHandler to connect sorting to the
		    // java.util.List.
		    ListHandler<LogClassification> columnSortHandler = new ListHandler<LogClassification>(
		        list);
		    columnSortHandler.setComparator(logLevelColumn,
		        new Comparator<LogClassification>() {
		          public int compare(LogClassification o1, LogClassification o2) {
		            if (o1 == o2) {
		              return 0;
		            }

		            // Compare the name columns.
		            if (o1 != null) {
		              return (o2 != null) ? o1.logLevel.compareTo(o2.logLevel) : 1;
		            }
		            return -1;
		          }
		        });
		    table.addColumnSortHandler(columnSortHandler);

		    // We know that the data is sorted alphabetically by default.
		    table.getColumnSortList().push(logLevelColumn);

		    // Add it to the root panel.
		    this.add(table);
	  }
	  
	

}
