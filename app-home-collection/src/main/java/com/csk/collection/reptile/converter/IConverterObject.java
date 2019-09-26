package com.csk.collection.reptile.converter;

import com.csk.collection.reptile.ConverterNode;
import com.csk.collection.reptile.converter.model.ConverterModel;

public interface IConverterObject {
	
	/**
	 * 
	 * @param <T>
	 * @param model	转换模型
	 * @param node	当前node对象
	 * @param html	当前解析html对象
	 * @param ooo	保留对象 用于扩展使用
	 * @return
	 */
	public <T> T converter(ConverterModel model,ConverterNode node,String html,
			
			Object ...ooo);
}
