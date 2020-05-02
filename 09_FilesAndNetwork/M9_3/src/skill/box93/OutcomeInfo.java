package skill.box93;

public class OutcomeInfo {
    private double sum;
    private int code;
    private String codeStatus;

    public OutcomeInfo(double sum, int code, String codeStatus) {
        this.sum = sum;
        this.code = code;
        this.codeStatus = codeStatus;
    }

    public double getSum() {
        return sum;
    }

    public int getCode() {
        return code;
    }

    public String getCodeStatus() {
        return codeStatus;
    }
}
