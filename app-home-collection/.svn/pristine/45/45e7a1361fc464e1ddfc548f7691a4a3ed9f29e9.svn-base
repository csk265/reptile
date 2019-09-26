package com.csk.collection.reptile.converter.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;

import com.csk.collection.reptile.ConverterNode;
import com.csk.collection.reptile.converter.IConverterObject;
import com.csk.collection.reptile.converter.model.ConverterModel;
import com.csk.tool.single.SingleUtil;
import com.csk.tool.string.StringMatcherUtil;

public class ConverterListString implements IConverterObject{
	StringMatcherUtil stringMatcherUtil= SingleUtil.get(StringMatcherUtil.class);
	@Override
	public List<String> converter(ConverterModel model, ConverterNode node,
			String html, Object... ooo) {
		if(model.getMatchingType().equals("regular")){
			return stringMatcherUtil.findList(html, model.getMatchingValue());
		}else if(model.getMatchingType().equals("jsoup")){
			List<String> datas=new ArrayList<String>();
			Jsoup.parse(html).select(model.getMatchingValue()).forEach(m->{
				datas.add(m.html());
			});
			return datas;
		}
		return null;
	}

}
