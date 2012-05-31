package com.marakana.android.fibcommon;

import android.os.Parcel;
import android.os.Parcelable;

public class Response implements Parcelable {
	private long result;
	private long time;
	
	public Response(long result, long time) {
		this.result = result;
		this.time = time;
	}
	
	public Response(Parcel parcel) {
		this(parcel.readLong(), parcel.readLong());
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeLong(result);
		parcel.writeLong(time);
	}
	
	public static final Parcelable.Creator<Response> CREATOR = new Parcelable.Creator<Response>() {

		@Override
		public Response createFromParcel(Parcel source) {
			return new Response(source);
		}

		@Override
		public Response[] newArray(int size) {
			return new Response[size];
		}
		
	};
	
	@Override
	public String toString() {
		return String.format("%d (%d ms)", result, time);
	}
	
	// Setters & Getters
	public long getResult() {
		return result;
	}
	public void setResult(long result) {
		this.result = result;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}

	
	
}
