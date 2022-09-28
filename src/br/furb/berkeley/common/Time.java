package br.furb.berkeley.common;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

/**
 * 
 * @author Wagner Ribeiro
 *
 */
public class Time implements Serializable {

	private static final long serialVersionUID = 1L;
	private LocalDateTime time;

	public Time() {
		Random random = new Random();
		LocalDateTime now = LocalDateTime.now();
		time = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), random.nextInt(23),
				random.nextInt(59), 0);
	}

	public long getEpochSeconds() {
		return time.toEpochSecond(ZoneOffset.UTC);
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime ofInstant) {
		this.time = ofInstant;
	}

	@Override
	public String toString() {
		return time.toString();
	}

}
