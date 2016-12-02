package com.web.api.baidu.map.model;

import java.util.List;

public class SuggestionPlace extends Result {
	private List<SuggestionResult> result;

	public List<SuggestionResult> getResult() {
		return result;
	}

	public void setResult(List<SuggestionResult> result) {
		this.result = result;
	}

}
