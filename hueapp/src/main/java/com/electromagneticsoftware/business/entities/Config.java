package com.electromagneticsoftware.business.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Config {
	private String name;
	private String datastoreversion;
	private String swversion;
	private String apiversion;
	private int zigbeechannel;
	private String mac;
	private String bridgeid;
	private boolean factorynew;
	private String modelid;
	private String starterkitid;
	private boolean dhcp;
	private String ipaddress;
	private String netmask;
	private String gateway;
	private String proxyaddress;
	private int proxyport;
	private String UTC;
	private String localtime;
	private String timezone;
//	private SwUpdate swupdate;  // deprecated
	private SwUpdate2 swupdate2;
	private boolean linkbutton;
	private boolean portalservices;
	private String portalconnection;
	private PortalState portalState;
	private InternetServices internetServices;
	private String replacesbridgeid;
	private Backup backup;
	private WhiteList whitelist;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDatastoreversion() {
		return datastoreversion;
	}
	public void setDatastoreversion(String datastoreversion) {
		this.datastoreversion = datastoreversion;
	}
	public String getSwversion() {
		return swversion;
	}
	public void setSwversion(String swversion) {
		this.swversion = swversion;
	}
	public String getApiversion() {
		return apiversion;
	}
	public void setApiversion(String apiversion) {
		this.apiversion = apiversion;
	}
	public int getZigbeechannel() {
		return zigbeechannel;
	}
	public void setZigbeechannel(int zigbeechannel) {
		this.zigbeechannel = zigbeechannel;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getBridgeid() {
		return bridgeid;
	}
	public void setBridgeid(String bridgeid) {
		this.bridgeid = bridgeid;
	}
	public boolean isFactorynew() {
		return factorynew;
	}
	public void setFactorynew(boolean factorynew) {
		this.factorynew = factorynew;
	}
	public String getModelid() {
		return modelid;
	}
	public void setModelid(String modelid) {
		this.modelid = modelid;
	}
	public String getStarterkitid() {
		return starterkitid;
	}
	public void setStarterkitid(String starterkitid) {
		this.starterkitid = starterkitid;
	}
	public boolean isDhcp() {
		return dhcp;
	}
	public void setDhcp(boolean dhcp) {
		this.dhcp = dhcp;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getNetmask() {
		return netmask;
	}
	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}
	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	public String getProxyaddress() {
		return proxyaddress;
	}
	public void setProxyaddress(String proxyaddress) {
		this.proxyaddress = proxyaddress;
	}
	public int getProxyport() {
		return proxyport;
	}
	public void setProxyport(int proxyport) {
		this.proxyport = proxyport;
	}
	public String getUTC() {
		return UTC;
	}
	public void setUTC(String uTC) {
		UTC = uTC;
	}
	public String getLocaltime() {
		return localtime;
	}
	public void setLocaltime(String localtime) {
		this.localtime = localtime;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public SwUpdate2 getSwupdate2() {
		return swupdate2;
	}
	public void setSwupdate2(SwUpdate2 swupdate2) {
		this.swupdate2 = swupdate2;
	}
	public boolean isLinkbutton() {
		return linkbutton;
	}
	public void setLinkbutton(boolean linkbutton) {
		this.linkbutton = linkbutton;
	}
	public boolean isPortalservices() {
		return portalservices;
	}
	public void setPortalservices(boolean portalservices) {
		this.portalservices = portalservices;
	}
	public String getPortalconnection() {
		return portalconnection;
	}
	public void setPortalconnection(String portalconnection) {
		this.portalconnection = portalconnection;
	}
	public PortalState getPortalState() {
		return portalState;
	}
	public void setPortalState(PortalState portalState) {
		this.portalState = portalState;
	}
	public InternetServices getInternetServices() {
		return internetServices;
	}
	public void setInternetServices(InternetServices internetServices) {
		this.internetServices = internetServices;
	}
	public String getReplacesbridgeid() {
		return replacesbridgeid;
	}
	public void setReplacesbridgeid(String replacesbridgeid) {
		this.replacesbridgeid = replacesbridgeid;
	}
	public Backup getBackup() {
		return backup;
	}
	public void setBackup(Backup backup) {
		this.backup = backup;
	}
	public WhiteList getWhitelist() {
		return whitelist;
	}
	public void setWhitelist(WhiteList whitelist) {
		this.whitelist = whitelist;
	}

}
