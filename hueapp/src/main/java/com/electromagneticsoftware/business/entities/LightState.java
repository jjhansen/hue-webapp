package com.electromagneticsoftware.business.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LightState {
	private Boolean on;
	private Long bri;
	private Long hue;
	private Long sat;
	private String effect;
	private Float[] xy;
	private String alert;
	private String colormode;
	private Boolean reachable;

	public Boolean getOn() {
		return on;
	}
	public void setOn(Boolean on) {
		this.on = on;
	}
	public Long getBri() {
		return bri;
	}
	public void setBri(Long bri) {
		this.bri = bri;
	}
	public Long getHue() {
		return hue;
	}
	public void setHue(Long hue) {
		this.hue = hue;
	}
	public Long getSat() {
		return sat;
	}
	public void setSat(Long sat) {
		this.sat = sat;
	}
	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}
	public Float[] getXy() {
		return xy;
	}
	public void setXy(Float[] xy) {
		this.xy = xy;
	}
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	public String getColormode() {
		return colormode;
	}
	public void setColormode(String colormode) {
		this.colormode = colormode;
	}
	public Boolean getReachable() {
		return reachable;
	}
	public void setReachable(Boolean reachable) {
		this.reachable = reachable;
	}
	
	
}
