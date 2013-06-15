package main.java.bg.fmi.hanoi;

public class GraphicDirector {

    private final IGraphicBuilder myBuilder;

    public GraphicDirector(IGraphicBuilder builder) {
        myBuilder = builder;
    }

    public void constructGraphic() {
        myBuilder.addSticks();
        myBuilder.addRings();
    }
}
