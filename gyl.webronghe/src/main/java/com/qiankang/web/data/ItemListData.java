package com.qiankang.web.data;

import java.util.ArrayList;
import java.util.List;

public class ItemListData implements JsonEntity {
	private List<ItemData> items = new ArrayList<ItemData>();
	
	public ItemListData() {
		ItemData item = new ItemData();
		item.setItemCode("-1");
		item.setItemName("    ");//4个空格
		items.add(item);
	}

	public List<ItemData> getItems() {
		return items;
	}

	public void setItems(List<ItemData> items) {
		this.items = items;
	}

	
	
	
}
