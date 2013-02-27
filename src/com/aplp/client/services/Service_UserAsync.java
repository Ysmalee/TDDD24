package com.aplp.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface Service_UserAsync {

	void login(String login, String password, AsyncCallback<Boolean> callback);

}
