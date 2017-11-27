package com.electromagneticsoftware.business.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class LightStateUpdate implements Serializable {
	private static final long serialVersionUID = 1L;
	private Boolean on;
	private Long bri;
	private Long hue;
	private Long sat;
	private String effect;
	private Float[] xy;
	private String alert;
	private Long ct;
	private Long transitionTime;
	private Long bri_inc;
	private Long sat_inc;
	private Long hue_inc;
	private Long ct_inc;
	private Long xy_inc;

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
	public Long getCt() {
		return ct;
	}
	public void setCt(Long ct) {
		this.ct = ct;
	}
	public Long getTransitionTime() {
		return transitionTime;
	}
	public void setTransitionTime(Long transitionTime) {
		this.transitionTime = transitionTime;
	}
	public Long getBri_inc() {
		return bri_inc;
	}
	public void setBri_inc(Long bri_inc) {
		this.bri_inc = bri_inc;
	}
	public Long getSat_inc() {
		return sat_inc;
	}
	public void setSat_inc(Long sat_inc) {
		this.sat_inc = sat_inc;
	}
	public Long getHue_inc() {
		return hue_inc;
	}
	public void setHue_inc(Long hue_inc) {
		this.hue_inc = hue_inc;
	}
	public Long getCt_inc() {
		return ct_inc;
	}
	public void setCt_inc(Long ct_inc) {
		this.ct_inc = ct_inc;
	}
	public Long getXy_inc() {
		return xy_inc;
	}
	public void setXy_inc(Long xy_inc) {
		this.xy_inc = xy_inc;
	}
}
