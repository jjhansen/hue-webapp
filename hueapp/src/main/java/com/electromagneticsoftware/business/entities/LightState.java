package com.electromagneticsoftware.business.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LightState implements Serializable {
	private static final long serialVersionUID = 1L;
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

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("on ");
		s.append(on.toString());
		s.append(" hue ");
		s.append(hue);
		s.append(" sat ");
		s.append(sat);
		s.append(" bri ");
		s.append(bri);
		s.append(" alert ");
		s.append(alert);
		s.append(" colormode ");
		s.append(colormode);
		s.append(" reachable ");
		s.append(reachable);
		return s.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alert == null) ? 0 : alert.hashCode());
		result = prime * result + ((bri == null) ? 0 : bri.hashCode());
		result = prime * result + ((effect == null) ? 0 : effect.hashCode());
		result = prime * result + ((hue == null) ? 0 : hue.hashCode());
		result = prime * result + ((on == null) ? 0 : on.hashCode());
		result = prime * result + ((reachable == null) ? 0 : reachable.hashCode());
		result = prime * result + ((sat == null) ? 0 : sat.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LightState other = (LightState) obj;
		if (alert == null) {
			if (other.alert != null)
				return false;
		} else if (!alert.equals(other.alert))
			return false;
		if (bri == null) {
			if (other.bri != null)
				return false;
		} else if (!bri.equals(other.bri))
			return false;
		if (effect == null) {
			if (other.effect != null)
				return false;
		} else if (!effect.equals(other.effect))
			return false;
		if (hue == null) {
			if (other.hue != null)
				return false;
		} else if (!hue.equals(other.hue))
			return false;
		if (on == null) {
			if (other.on != null)
				return false;
		} else if (!on.equals(other.on))
			return false;
		if (reachable == null) {
			if (other.reachable != null)
				return false;
		} else if (!reachable.equals(other.reachable))
			return false;
		if (sat == null) {
			if (other.sat != null)
				return false;
		} else if (!sat.equals(other.sat))
			return false;
		return true;
	}
	
	
}
