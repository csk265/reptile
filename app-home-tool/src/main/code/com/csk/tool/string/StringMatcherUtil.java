package com.csk.tool.string;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.csk.tool.single.ISingle;

public class StringMatcherUtil implements ISingle<StringMatcherUtil> {
	public List<String> findList(String body,String matcher){
		 Pattern p = Pattern.compile(matcher);
		 Matcher m = p.matcher(body);
		 List<String> result=new ArrayList<String>();
		 while(m.find()){
			 result.add(m.group());
		 }
		 return result;
	}	
	public String find(String body,String matcher){
		 Pattern p = Pattern.compile(matcher);
		 Matcher m = p.matcher(body);
		 while(m.find()){
			 return m.group();
		 }
		 return null;
	}
	@Override
	public StringMatcherUtil get() {
		return this;
	}
}
