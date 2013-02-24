package com.aplp.shared.businessObjects;

public class Category extends BusinessObject {
	
	private static final long serialVersionUID = -452441124858265937L;
	
	private String _name;
	private String _description;
	
	
	public Category() {
		this._name = null;
		this._description = null;
	}
	
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
