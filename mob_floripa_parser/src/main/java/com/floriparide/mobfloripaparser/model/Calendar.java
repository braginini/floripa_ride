package com.floriparide.mobfloripaparser.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mikhail Bragin
 */
public class Calendar {

	Long serviceId;

	boolean monday;

	boolean tuesday;

	boolean wednesday;

	boolean thursday;

	boolean friday;

	boolean saturday;

	boolean sunday;

	//YYYYMMDD
	String startDate;

	//YYYYMMDD
	String endDate;

	public Calendar(Long serviceId,
	                boolean monday,
	                boolean tuesday,
	                boolean wednesday,
	                boolean thursday,
	                boolean friday,
	                boolean saturday,
	                boolean sunday,
	                String startDate,
	                String endDate) {

		this.serviceId = serviceId;
		this.monday = monday;
		this.tuesday = tuesday;
		this.wednesday = wednesday;
		this.thursday = thursday;
		this.friday = friday;
		this.saturday = saturday;
		this.sunday = sunday;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Calendar(boolean monday,
	                boolean tuesday,
	                boolean wednesday,
	                boolean thursday,
	                boolean friday,
	                boolean saturday,
	                boolean sunday,
	                String startDate,
	                String endDate) {

		this.monday = monday;
		this.tuesday = tuesday;
		this.wednesday = wednesday;
		this.thursday = thursday;
		this.friday = friday;
		this.saturday = saturday;
		this.sunday = sunday;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public boolean isMonday() {
		return monday;
	}

	public void setMonday(boolean monday) {
		this.monday = monday;
	}

	public boolean isTuesday() {
		return tuesday;
	}

	public void setTuesday(boolean tuesday) {
		this.tuesday = tuesday;
	}

	public boolean isWednesday() {
		return wednesday;
	}

	public void setWednesday(boolean wednesday) {
		this.wednesday = wednesday;
	}

	public boolean isThursday() {
		return thursday;
	}

	public void setThursday(boolean thursday) {
		this.thursday = thursday;
	}

	public boolean isFriday() {
		return friday;
	}

	public void setFriday(boolean friday) {
		this.friday = friday;
	}

	public boolean isSaturday() {
		return saturday;
	}

	public void setSaturday(boolean saturday) {
		this.saturday = saturday;
	}

	public boolean isSunday() {
		return sunday;
	}

	public void setSunday(boolean sunday) {
		this.sunday = sunday;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public enum CalendarType {

		WEEK(0, "Dia da semana"),
		SATURDAY(1, "Sábado"),
		SUNDAY(2, "Domingo");

		int id;

		String ptName;

		private static Map<String, CalendarType> ptMap = new HashMap<>();

		static {

			for (CalendarType calendarType : CalendarType.values()) {
				ptMap.put(calendarType.getPtName(), calendarType);
			}

		}

		private CalendarType(int id, String ptName) {
			this.id = id;
			this.ptName = ptName;
		}

		public int getId() {
			return id;
		}

		public String getPtName() {
			return ptName;
		}

		public CalendarType lookupPt(String ptName) {
			return ptMap.get(ptName);
		}
	}
}
