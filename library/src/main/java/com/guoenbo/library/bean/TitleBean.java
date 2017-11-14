package com.guoenbo.library.bean;

import java.io.Serializable;

/**
 * @Describe 
 * @Author leeandy007
 * @Date 2016-9-4 下午8:17:02
 */
public class TitleBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	
	private String title;
	
	private int type;

	public TitleBean(int id, String title, int type) {
		super();
		this.id = id;
		this.title = title;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "TitleBean [id=" + id + ", title=" + title + ", type=" + type
				+ "]";
	}
	
}
