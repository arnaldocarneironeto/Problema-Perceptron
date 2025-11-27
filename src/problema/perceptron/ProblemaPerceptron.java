package problema.perceptron;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arnaldo Carneiro <acsn@a.recife.ifpe.edu.br>
 */
public class ProblemaPerceptron
{
    private static ArrayList<Sample> trainingBase;
    private static ArrayList<Sample> testBase;
    private static final double TOLERANCE = 0.001;
    private static final double TEST_PERCENTAGE = 0.9;
    private static final double ETA = 0.1;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        prepareBases();
        Perceptron perceptron = trainPerceptron();
        System.out.println("Índice de acertos: " + getHits(perceptron) + "/" + testBase.size());
        int numberClass1 = 0;
        int numberClass2;
        for(Sample sample: testBase)
        {
            if(sample.getExpectedOutput() == 1.0)
            {
                numberClass1++;
            }
        }
        numberClass2 = testBase.size() - numberClass1;
        System.out.println("Primeira classe na base de testes: " + numberClass1);
        System.out.println("Segunda  classe na base de testes: " + numberClass2);
        System.out.println("Primeira classe na base de treinamento: " + (150 - numberClass1));
        System.out.println("Segunda  classe na base de treinamento: " + (150 - numberClass2));
    }

    private static int getHits(Perceptron perceptron)
    {
        int correctCount = 0;
        for(Sample sample: testBase)
        {
            if(perceptron.test(sample))
            {
                correctCount++;
            }
        }
        return correctCount;
    }

    private static Perceptron trainPerceptron()
    {
        Perceptron perceptron = new Perceptron(ETA, 0.1, 0.1);
        perceptron.setClasses(1, 2);
        perceptron.train(trainingBase.toArray(new Sample[0]));
        return perceptron;
    }

    private static void prepareBases()
    {
        trainingBase = new ArrayList<>();
        testBase = new ArrayList<>();
        try
        {
            Scanner scanner = new Scanner(new File("perceptron.txt"));
            Random random = new Random();
            readCommonBase(scanner, random);
            adjustBiggerBase(random);
            adjustSmallerBase(random);
        }
        catch(IOException ex)
        {
            Logger.getLogger(ProblemaPerceptron.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void adjustSmallerBase(Random random)
    {
        double realPercentage = 1.0 * testBase.size() / (testBase.size() + trainingBase.size());
        while(TEST_PERCENTAGE - realPercentage > TOLERANCE)
        {
            int position = random.nextInt(trainingBase.size());
            Sample sample = trainingBase.remove(position);
            testBase.add(sample);
            realPercentage = 1.0 * testBase.size() / (testBase.size() + trainingBase.size());
        }
    }

    private static void adjustBiggerBase(Random random)
    {
        double realPercentage = 1.0 * testBase.size() / (testBase.size() + trainingBase.size());
        while(realPercentage - TEST_PERCENTAGE > TOLERANCE)
        {
            int position = random.nextInt(testBase.size());
            Sample sample = testBase.remove(position);
            trainingBase.add(sample);
            realPercentage = 1.0 * testBase.size() / (testBase.size() + trainingBase.size());
        }
    }

    private static void readCommonBase(Scanner scanner, Random random)
    {
        while(scanner.hasNext())
        {
            String[] values = scanner.nextLine().split(";");
            Sample sample = new Sample(Integer.parseInt(values[2]), Double.parseDouble(values[0]), Double.parseDouble(values[1]));;
            if(random.nextDouble() < TEST_PERCENTAGE)
            {
                testBase.add(sample);
            }
            else
            {
                trainingBase.add(sample);
            }
        }
    }

    private static void exercicioClasses()
    {
        Perceptron perceptron = new Perceptron(0.4, true, 0.5, 0.4, -0.6, 0.6);
        Sample s1 = new Sample(-1, 0, 0, 1);
        Sample s2 = new Sample(1, 1, 1, 0);
        perceptron.setClasses(-1, 1);
        perceptron.train(s1, s2);
        System.out.println(perceptron);
        System.out.println(perceptron.calculateResult(1, 1, 1));
        System.out.println(perceptron.calculateResult(0, 0, 0));
        System.out.println(perceptron.calculateResult(1, 0, 0));
        System.out.println(perceptron.calculateResult(0, 1, 1));
    }

    private static void exercicioOR()
    {
        Perceptron perceptron = new Perceptron(1, 0, 0);
        Sample s1 = new Sample(0, 0, 0);
        Sample s2 = new Sample(1, 0, 1);
        Sample s3 = new Sample(1, 1, 0);
        Sample s4 = new Sample(1, 1, 1);
        perceptron.setClasses(0, 1);
        perceptron.train(s1, s2, s3, s4);
        System.out.println(perceptron);
        System.out.println(perceptron.test(s1));
        System.out.println(perceptron.test(0, 0, 1));
        System.out.println(perceptron.test(1, 0, 1));
    }

    private static void exercicioAND()
    {
        Perceptron perceptron = new Perceptron(1, 0, 0);
        Sample s1 = new Sample(0, 0, 0);
        Sample s2 = new Sample(0, 0, 1);
        Sample s3 = new Sample(0, 1, 0);
        Sample s4 = new Sample(1, 1, 1);
        perceptron.setClasses(0, 1);
        perceptron.train(s1, s2, s3, s4);
        System.out.println(perceptron);
        System.out.println(perceptron.test(s1));
        System.out.println(perceptron.test(1, 0, 1));
        System.out.println(perceptron.test(0, 0, 1));
    }

    private static void exemploBipedeQuadrupede()
    {
        Perceptron perceptron = new Perceptron(1, true, 1, 0, 0, 0, 0);
        Sample cão      = new Sample(1,  1, -1,  1,  1);
        Sample gato     = new Sample(1,  1,  1,  1,  1);
        Sample cavalo   = new Sample(1,  1,  1, -1,  1);
        Sample homem    = new Sample(0, -1, -1, -1,  1);
        Sample galinha  = new Sample(0, -1,  1, -1,  1);
        Sample avestruz = new Sample(0,  1, -1,  1, -1);
        perceptron.setClasses(0, 1);
        perceptron.train(cão, gato, cavalo, homem, galinha, avestruz);
        System.out.println(perceptron);
        System.out.println(perceptron.test(cão));
        System.out.println(perceptron.test(0, 1, 0, 0, 1));
        System.out.println(perceptron.test(1, 1, 0, 0, 1));
    }
}