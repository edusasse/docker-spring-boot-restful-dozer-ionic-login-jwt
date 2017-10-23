package com.edusasse.app.facade;

import java.io.Serializable;
import java.util.List;

public class PageDTO<T extends Serializable> {
	private int totalPages = 0;
	private List<T> content = null;
	
	public PageDTO(int totalPages, List<T> content){
		setTotalPages(totalPages);
		setContent(content);
	}
	
	protected void setContent(List<T> content) {
		this.content = content;
	}
	
	protected void setTotalPages(int totalPages) {
		if (totalPages < 0){
			throw new IllegalArgumentException("Total of pages cannot be negative!");
		}
		this.totalPages = totalPages;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public List<T> getContent() {
		return content;
	}
}
