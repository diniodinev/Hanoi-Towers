package main.java.bg.fmi.hanoi;

public class GraphicDirector {

	private IGraphicBuilder myBuilder;

	public GraphicDirector(IGraphicBuilder builder) {
		myBuilder = builder;
	}

	public void ConstructGraphic() {
		myBuilder.addSticks();
		myBuilder.addRings();
	}
}
