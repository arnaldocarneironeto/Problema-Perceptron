# Perceptron Neural Network Implementation

A university Artificial Intelligence project from my undergraduate studies, now archived for documentation purposes. This project implements a single-layer perceptron neural network for binary classification tasks.

## Project Overview

This Java application implements a **Single-Layer Perceptron** - one of the fundamental building blocks of neural networks and machine learning. The perceptron learns to classify data points by adjusting weights through iterative training, demonstrating the core principles of supervised learning and linear separability.

## Algorithm Implementation

The system implements a **Standard Perceptron Learning Algorithm** with the following characteristics:

- **Activation Function**: Step function with configurable class thresholds
- **Learning Rule**: Delta rule with adjustable learning rate (η)
- **Bias Support**: Configurable bias term for shifted decision boundaries
- **Convergence Detection**: Automatic stopping when all training samples are correctly classified
- **Error Handling**: Detection of non-convergent cases with iteration limits

### Key Algorithmic Features:
- **Online Learning**: Weights updated after each misclassified sample
- **Flexible Classification**: Configurable class labels for output
- **Robust Training**: Maximum iteration limit to prevent infinite loops
- **Comprehensive Testing**: Separate training and testing phases with performance metrics

## Project Structure

- `problema.perceptron/` (root package)
  - `ProblemaPerceptron.java` - Main application with dataset handling and experiments
  - `Perceptron.java` - Core perceptron algorithm implementation
  - `Sample.java` - Data sample representation and management

## Mathematical Foundation

The perceptron implements the following learning process:

1. **Initialization**: Start with random weights and bias
2. **Forward Pass**: Calculate output: y = φ(∑(wᵢxᵢ) - θ)
3. **Error Calculation**: δ = expected_output - actual_output
4. **Weight Update**: wᵢ(new) = wᵢ(old) + η·δ·xᵢ
5. **Bias Update**: θ(new) = θ(old) - η·δ (if biased)
6. **Convergence Check**: Repeat until all samples correctly classified

Where:
- φ is the step activation function
- η is the learning rate
- θ is the bias term

## Technical Implementation

### Core Classes:

1. **Perceptron**: Main neural network implementation
   - **Weight Management**: Stores and updates connection weights
   - **Training Algorithm**: Implements perceptron learning rule
   - **Classification**: Performs forward propagation for prediction
   - **Convergence Control**: Monitors training progress and detects completion

2. **Sample**: Data point representation
   - **Feature Storage**: Maintains input feature vectors
   - **Label Management**: Stores expected output for supervised learning
   - **Data Access**: Provides structured access to features and labels

3. **ProblemaPerceptron**: Application and experimentation framework
   - **Dataset Handling**: Reads and parses input files
   - **Train-Test Split**: Implements proper machine learning evaluation
   - **Experiment Runner**: Executes multiple learning scenarios
   - **Performance Analysis**: Calculates and reports accuracy metrics

## Example Implementations

The project includes several classic perceptron problems:

### Logical Gates
- **AND Gate**: Learns logical AND operation
- **OR Gate**: Learns logical OR operation  
- **Class Separation**: Demonstrates linear classification boundaries

### Real-World Classification
- **Biped vs Quadruped**: Classifies animals based on physical characteristics
- **2D Point Classification**: Separates points in two-dimensional space
- **Custom Datasets**: Flexible support for any binary classification task

## Input File Format

The system expects input files in the following format:

```
feature1;feature2;class_label
feature1;feature2;class_label
...
```

Example:
```
2.5;1.5;1
3.0;4.0;2
1.0;2.0;1
```

Where:
- Features are real numbers
- Class labels are integers (typically 1 and 2)
- Semicolon-separated values
- One sample per line

## Usage

1. **Prepare Data**: Create input file `perceptron.txt` with training data
2. **Run Application**: Execute `ProblemaPerceptron.java`
3. **Automatic Processing**: 
   - Dataset is split into training (90%) and testing (10%)
   - Perceptron is trained until convergence
   - Performance is evaluated on test set
   - Results are displayed with accuracy metrics

## Academic Context

This project was developed for an **Artificial Intelligence** or **Neural Networks** course during my undergraduate studies, demonstrating:
- Fundamental neural network concepts
- Supervised learning principles
- Linear separability and decision boundaries
- Machine learning evaluation methodologies
- Implementation of classic AI algorithms

## Limitations and Insights

The perceptron has several important theoretical limitations:

- **Linear Separability**: Can only learn linearly separable functions
- **XOR Problem**: Cannot solve non-linearly separable problems like XOR
- **Single Layer**: Limited to simple decision boundaries

These limitations historically drove the development of multi-layer perceptrons and deep learning, making this project historically significant in understanding the evolution of neural networks.

## Note on Input File

The original input file used in the university project is no longer available. The provided `perceptron.txt` sample file contains synthetic data that demonstrates the type of classification problem the perceptron can solve. The original likely contained real experimental or benchmark data.

## Note on Archival

This repository contains code from my university studies uploaded for archival and documentation purposes. It represents my early work in neural networks and machine learning, preserved here to show the progression of my technical skills and understanding of fundamental AI algorithms.

## Developer

**Arnaldo Carneiro**  
University Project - Artificial Intelligence Course

---

*This project was completed as part of my undergraduate curriculum and is uploaded here for historical reference and skill demonstration. The perceptron implementation showcases understanding of neural network fundamentals, supervised learning, and the historical context of artificial intelligence development.*