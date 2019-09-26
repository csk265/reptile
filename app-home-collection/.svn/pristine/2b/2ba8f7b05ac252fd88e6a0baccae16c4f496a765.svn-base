package com.csk.collection.reptile.cache.impl;
 
import com.csk.collection.reptile.cache.IReptileCache;
import com.csk.tool.cache.impl.FileCacheClient;
import com.csk.tool.xml.Node;

public class FileReotileCacheClient extends FileCacheClient implements IReptileCache {

	@Override
	public void init(Node node) {
		super.setFilePath(node.findNodeValue("value"));
	}

}
