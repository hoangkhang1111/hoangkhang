package Servlet;

import java.util.Random;

public class Shisutemu {
	String kotoba;
	int sutetasu;

	public Shisutemu( String kotoba, int sutetasu) {
		super();
		this.kotoba = kotoba;
		this.sutetasu = sutetasu;
	}
	
	public Shisutemu() {
		super();
	}


	public String getKotoba() {
		return kotoba;
	}

	public void setKotoba(String kotoba) {
		this.kotoba = kotoba;
	}

	public int getSutetasu() {
		return sutetasu;
	}

	public void setSutetasu(int sutetasu) {
		this.sutetasu = sutetasu;
	}
	@Override
	public String toString() {
		return "Shisutemu [kotoba=" + kotoba + ", sutetasu=" + sutetasu + "]";
	}
}
