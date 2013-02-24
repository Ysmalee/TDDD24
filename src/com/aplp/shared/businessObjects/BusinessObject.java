package com.aplp.shared.businessObjects;

import java.io.Serializable;

public abstract class BusinessObject implements Serializable {
	private static final long serialVersionUID = 5459744707220131584L;
	private Integer _id;
	
	
	protected BusinessObject() {
		this._id = null;
	}
	
	protected BusinessObject(Integer id) {
		this._id = id;
	}

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}
}
