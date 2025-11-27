package problema.perceptron;

import java.util.Arrays;

/**
 *
 * @author Arnaldo Carneiro <acsn@a.recife.ifpe.edu.br>
 */
public class Sample
{
    private final double expectedOutput;
    private final double[] inputs;

    public Sample(double expectedOutput, double... inputs)
    {
        this.expectedOutput = expectedOutput;
        this.inputs = inputs;
    }

    public double getExpectedOutput()
    {
        return this.expectedOutput;
    }

    public int getInputsSize()
    {
        return this.inputs.length;
    }

    public double[] getInputs()
    {
        return this.inputs;
    }

    public double getInput(int index)
    {
        return this.inputs[index];
    }

    @Override
    public String toString()
    {
        return "Sample{" + "expectedOutput=" + expectedOutput + ", inputs=" + Arrays.toString(inputs) + '}';
    }
}