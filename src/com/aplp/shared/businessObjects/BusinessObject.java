package com.aplp.shared.businessObjects;

public abstract class BusinessObject {
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
