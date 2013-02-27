package com.aplp.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("service_user")
public interface Service_User extends RemoteService {

	Boolean login(String login, String password);
	
}
