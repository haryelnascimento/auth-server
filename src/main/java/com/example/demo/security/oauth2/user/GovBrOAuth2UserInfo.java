package com.example.demo.security.oauth2.user;

import java.util.Map;

public class GovBrOAuth2UserInfo extends OAuth2UserInfo {
    public GovBrOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

	@Override
	public String getImageUrl() {
		return null;
	}
	
	@Override
    public String getPhone() {
        return (String) attributes.get("phone_number");
	}
    
}
