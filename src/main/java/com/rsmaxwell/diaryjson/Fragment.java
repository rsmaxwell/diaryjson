package com.rsmaxwell.diaryjson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Fragment implements Comparable {

	public int year;
	public int month;
	public int day;
	public String order;
	public String reference;
	public String html;

	@JsonInclude(Include.NON_EMPTY)
	public String notes;

	@Override
	public int compareTo(Object o) {
		Fragment other = (Fragment) o;
		if (year != other.year) {
			return year - other.year;
		}
		if (month != other.month) {
			return month - other.month;
		}
		if (day != other.day) {
			return day - other.day;
		}
		if (!order.equals(other.order)) {
			return order.compareTo(other.order);
		}
		return reference.compareTo(other.reference);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("{ \"year\" :" + year);
		sb.append(", \"month\" : " + month);
		sb.append(", \"day\" : " + day);
		sb.append(", \"order\" : " + order);
		sb.append(", \"reference\" : " + reference);
		sb.append(" }");

		return sb.toString();
	}
}
