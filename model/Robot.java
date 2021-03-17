package model;

import reflection.Ref;

public class Robot extends Unit {
	public Game game;
	public void Exploded() {
		Ref.Call(this, "Exploded", null);
	}
	
	public void Work() {
		Ref.Call(this, "Work", null);
	}
	
}
