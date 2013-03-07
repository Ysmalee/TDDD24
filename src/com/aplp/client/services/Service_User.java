package com.aplp.client.services;

import com.aplp.shared.businessObjects.User;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("service_user")
public interface Service_User extends RemoteService {

	User login(String login, String password);
	User getUserById(Integer id);
	
}
