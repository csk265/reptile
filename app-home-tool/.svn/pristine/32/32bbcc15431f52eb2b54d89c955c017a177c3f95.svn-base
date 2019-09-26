package com.csk.tool.notice;

import java.util.ArrayList;
import java.util.List;

public class Notice implements INotice{
	private boolean isNotice;
	private List<INotice> data=new ArrayList<INotice>();
	public void add(INotice notice){
		data.add(notice);
	}
	public void remover(INotice notice){
		data.remove(notice);
	}
	public void clear(){
		data.clear();
	}
	
	public boolean isNotice() {
		return isNotice;
	}
	public void setNotice(boolean isNotice) {
		this.isNotice = isNotice;
	}
	@Override
	public void broadcast(String title, String content) {
		if(isNotice){
			for(INotice notice:data){
				notice.broadcast(title, content);
			}
		}
	}
	
}
