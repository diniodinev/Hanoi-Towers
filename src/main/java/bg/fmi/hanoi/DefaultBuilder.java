package main.java.bg.fmi.hanoi;

public class DefaultBuilder implements IGraphicBuilder {

	private Graphic myGraphic = null;

	@Override
	public void addSticks() {
		myGraphic = new Graphic();
		for (int i = 0; i < 3; i++) {
			Stick newStick = new Stick(i);
			myGraphic.AddChild(newStick);
		}
	}

	@Override
	public void addRings() {
		Graphic myFirstStick = myGraphic.GetChild(0);
		for (int i = 0; i < 5; i++) {
			Ring newRing = new Ring(5 - i, 0, i);
			myFirstStick.AddChild(newRing);
		}
	}

	@Override
	public Graphic GetGraphic() {
		// TODO Auto-generated method stub
		return myGraphic;
	}

}
