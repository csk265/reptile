package com.csk.collection.reptile.converter.impl;

import org.jsoup.Jsoup;

import com.csk.collection.reptile.ConverterNode;
import com.csk.collection.reptile.converter.IConverterObject;
import com.csk.collection.reptile.converter.model.ConverterModel;
import com.csk.tool.single.SingleUtil;
import com.csk.tool.string.StringMatcherUtil;

/**
 * �ַ�����������
 * @author Administrator
 *
 */
public class ConverterString implements IConverterObject{
	StringMatcherUtil stringMatcherUtil= SingleUtil.get(StringMatcherUtil.class);
	
	@Override
	public String converter(ConverterModel model, ConverterNode node,
			String html,Object ...ooo) {
		if(model.getMatchingType().equals("regular")){
			return stringMatcherUtil.find(html, model.getMatchingValue());
		}else if(model.getMatchingType().equals("jsoup")){
			String targer=model.getTarget();
			if("text".equals(targer)||"".equals(targer)){
				return Jsoup.parse(html).select(model.getMatchingValue()).text();
			}else{ 
				return Jsoup.parse(html).select(model.getMatchingValue()).attr(targer);
			}
			 
		}
		return null;
	}

}
