package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private final Auth auth = new Auth();
    private final OAuth2 oauth2 = new OAuth2();

    public static class Auth {
		private String tokenSecret;
        private long tokenExpirationMsec;
        private String privateKeyFile;
        private String privateKeyPass;
        private String publicKeyFile;
        private String urlApiGovbr;
        
        public String getTokenSecret() {
            return tokenSecret;
        }

        public void setTokenSecret(String tokenSecret) {
            this.tokenSecret = tokenSecret;
        }

        public long getTokenExpirationMsec() {
            return tokenExpirationMsec;
        }

        public void setTokenExpirationMsec(long tokenExpirationMsec) {
            this.tokenExpirationMsec = tokenExpirationMsec;
        }
        
        public String getPrivateKeyFile() {
			return privateKeyFile;
		}

		public void setPrivateKeyFile(String privateKeyFile) {
			this.privateKeyFile = privateKeyFile;
		}

		public String getPrivateKeyPass() {
			return privateKeyPass;
		}

		public void setPrivateKeyPass(String privateKeyPass) {
			this.privateKeyPass = privateKeyPass;
		}

		public String getPublicKeyFile() {
			return publicKeyFile;
		}

		public void setPublicKeyFile(String publicKeyFile) {
			this.publicKeyFile = publicKeyFile;
		}

		public String getUrlApiGovbr() {
			return urlApiGovbr;
		}

		public void setUrlApiGovbr(String urlApiGovbr) {
			this.urlApiGovbr = urlApiGovbr;
		}
    }

    public static final class OAuth2 {
        private List<String> authorizedRedirectUris = new ArrayList<>();

        public List<String> getAuthorizedRedirectUris() {
            return authorizedRedirectUris;
        }

        public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }
    }

    public Auth getAuth() {
        return auth;
    }

    public OAuth2 getOauth2() {
        return oauth2;
    }
}
