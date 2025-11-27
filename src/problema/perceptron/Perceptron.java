package problema.perceptron;

import java.util.Arrays;

/**
 *
 * @author Arnaldo Carneiro <acsn@a.recife.ifpe.edu.br>
 */
public class Perceptron
{
    private final double eta;
    private boolean biased;
    private final double[] weights;
    private final double[] classes;

    public Perceptron(double eta, boolean biased, double bias, double... weights)
    {
        this.eta = eta;
        this.weights = new double[weights.length + 1];
        System.arraycopy(weights, 0, this.weights, 0, weights.length);
        this.weights[weights.length] = bias;
        this.biased = biased;
        this.classes = new double[2];
    }

    public Perceptron(double eta, double... weights)
    {
        this(eta, false, 0, weights);
    }

    public void setClasses(double... classes)
    {
        System.arraycopy(classes, 0, this.classes, 0, this.classes.length);
    }

    public double getClass(int index)
    {
        return this.classes[index];
    }

    public void train(Sample... trainingSet)
    {
        int correctMatchsCount = 0;
        int cycles = 0;
        for(int i = 0; correctMatchsCount < trainingSet.length; i++)
        {
            cycles++;
            Sample sample = trainingSet[i % trainingSet.length];
            double result = calculateResult(sample);
            double error = sample.getExpectedOutput() - result;
            if(error != 0)
            {
                correctMatchsCount = 0;
                correctWeights(error, sample);
            }
            else
            {
                correctMatchsCount++;
            }
            if(i >= 100000)
            {
                throw new ArithmeticException("Does not converge.");
            }
        }
        System.out.println("ciclos:" + cycles);
    }

    public double calculateResult(double... inputs)
    {
        double sum = 0.0;
        for(int j = 0; j < this.weights.length - 1; j++)
        {
            sum += this.weights[j] * inputs[j];
        }
        sum -= this.weights[this.weights.length - 1];
        return sum > 0? this.getClass(1): this.getClass(0);
    }

    private double calculateResult(Sample sample)
    {
        return calculateResult(sample.getInputs());
    }

    private void correctWeights(double error, Sample sample)
    {
        double correction = error * this.eta;
        for(int j = 0; j < this.weights.length - 1; j++)
        {
            this.weights[j] += correction * sample.getInput(j);
        }
        if(this.biased == false)
        {
            this.weights[this.weights.length - 1] -= correction;
        }
    }

    public boolean test(Sample s)
    {
        return test(s.getExpectedOutput(), s.getInputs());
    }

    public boolean test(double expectedOutput, double... inputs)
    {
        return expectedOutput == this.calculateResult(inputs);
    }

    @Override
    public String toString()
    {
        return "Perceptron{" + "weights=" + Arrays.toString(weights) + '}';
    }
}