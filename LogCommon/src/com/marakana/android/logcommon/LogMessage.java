package com.marakana.android.logcommon;

import android.os.Parcel;
import android.os.Parcelable;

public class LogMessage implements Parcelable {
	private int priority;
	private String tag;
	private String message;
	
	public LogMessage(int priority, String tag, String message) {
		this.priority = priority;
		this.tag = tag;
		this.message = message;
	}
	
	// --- Parcelable methods ---
	public LogMessage(Parcel parcel) {
		this(parcel.readInt(), parcel.readString(), parcel.readString());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeInt(priority);
		parcel.writeString(tag);
		parcel.writeString(message);
	}
	
	public static final Parcelable.Creator<LogMessage> CREATOR = new Parcelable.Creator<LogMessage>() {

		@Override
		public LogMessage createFromParcel(Parcel source) {
			return new LogMessage(source);
		}

		@Override
		public LogMessage[] newArray(int size) {
			return new LogMessage[size];
		}
	};

	// --- Setters & Getters
	
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
