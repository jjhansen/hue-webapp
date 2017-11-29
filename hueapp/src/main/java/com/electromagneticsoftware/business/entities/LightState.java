package com.electromagneticsoftware.business.entities;

import java.io.Serializable;
import java.lang.Math;

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
	public String getRgb() {
		// implements HSL -> RGB
		// see JavaScript source code, http://www.rapidtables.com/convert/color/hsl-to-rgb.htm
		float h = hue;		// h = document.calcform.h.value;
		float s = sat;		// s = document.calcform.s.value;
		float l = bri;		// l = document.calcform.l.value;
							// if( h=="" ) h=0;
							// if( s=="" ) s=0;
							// if( l=="" ) l=0;
							// h = parseFloat(h);
							// s = parseFloat(s);
							// l = parseFloat(l);
		if (h<0) h=0.0f;	// if( h<0 ) h=0;
		if (s<0) s=0.0f;	// if( s<0 ) s=0;
		if (l<0) l=0.0f;	// if l<0 ) l=0;

		if (h>= 65535)		// if( h>=360 ) h=359;
			h=65535.0f;	
		if (s>255)			// if( s>100 ) s=100;
			s=255.0f;
		if (l>255)			// if( l>100 ) l=100;
			l=255.0f;

		s = s/255.0f;		// s/=100;
		l = l/255.0f;		// l/=100;

		float C = (1-Math.abs(l*2-1))*s;	// C = (1-Math.abs(2*l-1))*s;
		float hh = h/60;					// hh = h/60;
		float X = C*(1-Math.abs(hh%2-1));	// X = C*(1-abs(hh%2-1));

		float r, g, b;
		r = g = b = 0;
		if( hh>=0 && hh<1 ) {
			r = C;
			g = X;
		} else if( hh>=1 && hh<2 ) {
			r = X;
			g = C;
		} else if( hh>=2 && hh<3 ) {
			g = C;
			b = X;
		} else if( hh>=3 && hh<4 ) {
			g = X;
			b = C;
		} else if( hh>=4 && hh<5 ) {
			r = X;
			b = C;
		} else {
			r = C;
			b = X;
		}

		float m = l-C/2;
		r += m;
		g += m;
		b += m;

		r *= 255.0;
		g *= 255.0;
		b *= 255.0;

		r = Math.round(r);
		g = Math.round(g);
		b = Math.round(b);

		int hexval = Math.round(r*65536 + g*256 + b);
		String hex_rgb_string = String.format("#%06x", hexval);
		return hex_rgb_string;
	}
	public void setRgb(Long rgb) {
		// to implement RGB -> HSL
		// see JavaScript source code, http://www.rapidtables.com/convert/color/rgb-to-hex.htm
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
