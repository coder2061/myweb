package com.web.api.baidu.map.model;

import java.util.List;

public class SearchPlace extends Result {
	private List<SearchResult> results;

	public List<SearchResult> getResults() {
		return results;
	}

	public void setResult(List<SearchResult> results) {
		this.results = results;
	}

}
