package com.aplp.shared.businessObjects;

public class Category extends BusinessObject {
	private String _name;
	private String _description;
	
	
	
	
	public Category(Integer id, String name, String description) {
		super(id);
		this.set_name(name);
		this.set_description(description);
	}
	
	public Category(String name, String description) {
		this.set_name(name);
		this.set_description(description);
	}

	
	
	
	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_description() {
		return _description;
	}

	public void set_description(String _description) {
		this._description = _description;
	}
}
