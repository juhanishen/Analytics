package com.battery.analytics.solr.datafeeding.zxgj;

import java.util.concurrent.atomic.AtomicLong;

public class ZXGJParserHelper {
	public static final String attributeEnd = "]";
    public static final String attributeBegin = "[";
    public static final String attributeEndAndSpace = "] ";
    public static final String IDField = "id";
    public static final String lineValueField = "lineValue_s";
	public static final String timestampField = "ts_dt";
	public static final String recordTypeField = "recordType";
	public static final String fileTypeField = "fileType";
	public static final String lineNumField = "lineNum_l";
	public static final String recordNumField = "recordNum_l";
	public static final String lineTypeField = "lineType_s";
    public static final String logLevelField = "logLevel_s";
	public static final String normalLineEventField = "eventField_s";
	public static final String normalLineCommentField = "normalLineComment_s";
    
	public static final String lineBeginWithCommnets = "//";
	public static final String lineBeginWithXML = "<";
	public static final String attributeSpaceAndBegin=" [";
	public static final String ownAttributesSeporator = "---";
	public static final String space= " ";
	
	public static final int executorPoolSize = 1;
	
	public static final String lineString = "lineString";
	public static final String lineTypeComments = "comments";
	public static final String lineTypeEAPNormal = "EAPNormal";
	
	public static final String logLevel = "logLevel";
	
	public static AtomicLong idGenerator = new AtomicLong(0);
	
}
