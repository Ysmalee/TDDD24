package com.aplp.client.services;

import com.aplp.shared.businessObjects.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface Service_UserAsync {

	void login(String login, String password, AsyncCallback<User> callback);

}
