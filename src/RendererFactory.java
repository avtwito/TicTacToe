public class RendererFactory {
    public RendererFactory() {

    }

    /**
     * This method gets a string from the command line
     * with type of renderer to build,
     * and returns the renderer accordingly
     * @param rendererRequest "console"/"none"
     * @return Renderer ConsoleRenderer/VoidRenderer
     */
    public Renderer buildFactory(String rendererRequest) {
        switch (rendererRequest) {
            case "console":
                return new ConsoleRenderer();
            default:
                return null;
        }
    }
}
