package com.aplp.shared.businessObjects;

public class User extends BusinessObject {
	private String _login;
	private String _password;
	private Boolean _moderator;
	
	
	public User(Integer id, String login, String password, Boolean moderator) {
		super(id);
		this._login = login;
		this._password = password;
		this._moderator = moderator;
	}
	
	public User(String login, String password, Boolean moderator) {
		this._login = login;
		this._password = password;
		this._moderator = moderator;
	}
	
	
	
	public String get_login() {
		return _login;
	}
	public void set_login(String _login) {
		this._login = _login;
	}
	public String get_password() {
		return _password;
	}
	public void set_password(String _password) {
		this._password = _password;
	}
	public Boolean get_moderator() {
		return _moderator;
	}
	public void set_moderator(Boolean _moderator) {
		this._moderator = _moderator;
	}
	
	

}
