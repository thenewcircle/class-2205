package com.marakana.android.fibcommon;

import android.os.Parcel;
import android.os.Parcelable;

public class Request implements Parcelable {
	public static final int JAVA_RECURSIVE = 1;
	public static final int JAVA_ITERATIVE = 2;
	public static final int NATIVE_RECURSIVE = 3;
	public static final int NATIVE_ITERATIVE = 4;
	
	private int algorithm;
	private long n;

	public Request(int algorithm, long n) {
		this.algorithm = algorithm;
		this.n = n;
	}

	public Request(Parcel parcel) {
		this(parcel.readInt(), parcel.readLong());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeInt(algorithm);
		parcel.writeLong(n);
	}

	public static final Parcelable.Creator<Request> CREATOR = new Parcelable.Creator<Request>() {

		@Override
		public Request createFromParcel(Parcel source) {
			return new Request(source);
		}

		@Override
		public Request[] newArray(int size) {
			return new Request[size];
		}

	};

	// Setters & Getters
	public int getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(int algorithm) {
		this.algorithm = algorithm;
	}

	public long getN() {
		return n;
	}

	public void setN(long n) {
		this.n = n;
	}

}
