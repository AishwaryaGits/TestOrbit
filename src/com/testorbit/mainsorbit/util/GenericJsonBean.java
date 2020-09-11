/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */
package com.testorbit.mainsorbit.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class GenericJsonBean {
    private String STS;
    private String MSG="";
    private Object CONTENT;
    private String T="";

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        if(MSG!=null)
        this.MSG = MSG;
    }

    public String getSTS() {
        return STS;
    }

    public void setSTS(String STS) {
        this.STS = STS;
    }

    public Object getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(Object CONTENT) {
        this.CONTENT = CONTENT;
    }

   
    public String getT() {
        return T;
    }

    public void setT(String T) {
        this.T = T;
    }
    
    public static String createJsonString(String status,String message,Object content)
    {
    	GenericJsonBean genericJsonBean = new GenericJsonBean();
    	genericJsonBean.setSTS(status);
		genericJsonBean.setMSG(message);
		genericJsonBean.setCONTENT(content);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String json = gson.toJson(genericJsonBean);
		return json;
    }
}
