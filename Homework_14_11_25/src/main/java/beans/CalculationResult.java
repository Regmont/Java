package beans;

/**
 * Класс для хранения результата вычислений.
 */
public class CalculationResult {
    private double[] numbers;
    private double result;
    private String operation;
    private String operationSymbol;

    public CalculationResult(double[] numbers, double result, String operation, String operationSymbol) {
        this.numbers = numbers;
        this.result = result;
        this.operation = operation;
        this.operationSymbol = operationSymbol;
    }

    public double[] getNumbers() { return numbers; }
    public void setNumbers(double[] numbers) { this.numbers = numbers; }

    public double getResult() { return result; }
    public void setResult(double result) { this.result = result; }

    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }

    public String getOperationSymbol() { return operationSymbol; }
    public void setOperationSymbol(String operationSymbol) { this.operationSymbol = operationSymbol; }
}
