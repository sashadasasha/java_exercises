public class Line {
    private String lineName;
    private Double lineNumber;
    private String lineColor;

    public Line(String lineName, Double lineNumber, String lineColor) {
        this.lineName = lineName;
        this.lineNumber = lineNumber;
        this.lineColor = lineColor;
    }

    public String getLineName() {
        return lineName;
    }

    public Double getLineNumber() {
        return lineNumber;
    }

    public String getLineColor() {
        return lineColor;
    }
}
