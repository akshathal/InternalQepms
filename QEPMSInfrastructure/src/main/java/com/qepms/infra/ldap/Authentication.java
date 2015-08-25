package com.qepms.infra.ldap;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.AuthenticationException;
import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

//import org.apache.log4j.Logger;

//import com.quinnox.logging.TrnsformLogger;

public class Authentication {
	
	//static Logger log =  TrnsformLogger.getLogObject();

	public static Map<String, String> authenticateAD(String userName, String passwordString) throws Exception {
		
		/*Map<String, String> userAttrMap = new HashMap<String, String>();
		try {
			userAttrMap.put(ConfigValues.getConfigValue("LDAP_USER_MAIL_FIELD"), "VinayS@quinnox.com");
			userAttrMap.put(ConfigValues.getConfigValue("LDAP_USER_NAME_FIELD"), "Vinay Gopal Settipalli");
			userAttrMap.put(ConfigValues.getConfigValue("LDAP_USER_ID_FIELD"), "VinayS");
		} catch (Exception ex) {  
			throw ex;
		}
		return userAttrMap;*/		

		Map<String, String> userAttrMap = null;
		LdapContext ldapContext = null;
		try {
			String ldapURL = ConfigValues.getConfigValue("LDAP_URL");
			String searchBase = ConfigValues.getConfigValue("LDAP_SEARCH_BASE_DN");
			//System.out.println("URl LDAP: "+ldapURL+"map data:");
		    //log.debug("URl LDAP: "+ldapURL+"map data:");
			String returnedAtts[] = { ConfigValues.getConfigValue("LDAP_USER_ID_FIELD"), ConfigValues.getConfigValue("LDAP_USER_NAME_FIELD"), ConfigValues.getConfigValue("LDAP_USER_MAIL_FIELD"),ConfigValues.getConfigValue("LDAP_MANAGER_NAME_FIELD"),ConfigValues.getConfigValue("LDAP_EMP_ID_FIELD"),ConfigValues.getConfigValue("LDAP_JOB_TITLE_FIELD") };
			String searchFilter = "("+ ConfigValues.getConfigValue("LDAP_USER_ID_FIELD") +"=" + userName + ")";
	         
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, ldapURL);
			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			env.put(Context.SECURITY_PRINCIPAL, ConfigValues.getConfigValue("LDAP_DOMAIN") + "\\" + userName);
			env.put(Context.SECURITY_CREDENTIALS, passwordString);
		
			 
			SearchControls searchControls = new SearchControls();
			searchControls.setReturningAttributes(returnedAtts);
			searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			
			ldapContext = new InitialLdapContext(env, null);
			
			NamingEnumeration<SearchResult> answer = ldapContext.search(searchBase, searchFilter, searchControls);
			
			if (answer.hasMoreElements()) {
				SearchResult sr = (SearchResult) answer.next();
				Attributes attrs = sr.getAttributes();
				
				if (attrs != null) {
					userAttrMap = new HashMap<String, String>();
					NamingEnumeration<?> ne = attrs.getAll();
					while (ne.hasMore()) {
						Attribute attr = (Attribute) ne.next();
						userAttrMap.put(attr.getID(), attr.get().toString());
					}
					ne.close();
				}
				answer.close();
			}
		} catch(CommunicationException cEx) {
			throw cEx;
		} catch (AuthenticationException aEx) {
			return null;
		} catch (NamingException nEx) {
			return null;
		} catch (Exception ex) {  
			throw ex;
		} finally {
			try {
				if(ldapContext != null) {
					ldapContext.close();
				}
			} catch (Exception e) { }
		}
		return userAttrMap;
	}
	
	
}
