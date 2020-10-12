/**
 * Copyright (c) DTAI - KU Leuven - All rights reserved.
 * Proprietary, do not copy or distribute without permission.
 * Written by Maaike Van Roy, 2020
 */
import java.util.ArrayList;


/**
 * To use the provided KMeans implementation, add the jar to the build path, 
 * import like this and use like any other class.
 */
import assignment.KMeans;

public class PQkNN {
	
	private int n; // Amount of subvectors
	private int k; // k of k-Means
	private int[] trainlabels;
	private int[][] compressedData;
	private ArrayList<double[][]> subvectorCentroids;
	private double[][] distances;
	
	/**
	 * Contruct a new instance of kNN with Product Quantization
	 * @param n Amount of subvectors
	 * @param c Determines the amount of clusters for KMeans, i.e., k = 2**c
	 */
	public PQkNN(int i, int c) {
		
		n =i;
		k= 258;
		
		System.out.println("constructor");
		
		
		//throw new RuntimeException();
	}
	
	
	/**
	 * Compress the given training examples via the product quantization method (see assignment for paper and blog post).
	 * The necessary data structures are to be stored within class instantiation.
	 * @param traindata The training examples, 2D integer matrix where each row represents an image.
	 * @param trainlabels Labels for the training examples, 0..9
	 */
	public void compress(int[][] traindata, int[] trainlabels) 
	{
		this.trainlabels = trainlabels; // You will need these during prediction.
		this.compressedData = new int[traindata.length][this.n];
		this.subvectorCentroids = new ArrayList<>();
		
		int am_example = 60000;
		int n_centroids=256;
		KMeans kmeans = new KMeans(am_example, n_centroids);
		
		int d = traindata[0].length;
		int n = this.n;
		int size = d/n;
		int[][] intermediateMatrix = new int[traindata.length][size];
		System.out.println("compress");
		
		int start =0;
		int nCount=0;
		
		while(nCount < n)
		{
			for(int i=0;i<traindata.length;i++)
			{
				for(int j=start;j<size+start;j ++)
				{	
					intermediateMatrix[i][j] = traindata[i][j];	
				}
			}
			
			double[][] centroids = kmeans.fit(intermediateMatrix);
			
			for(int i=0; i<intermediateMatrix.length;i++)
			{
				int clusterID = kmeans.predict(intermediateMatrix[i], centroids);
				compressedData[i][nCount] = clusterID;
			}
			
			start += size;
			nCount++;
			
		}
		
		

		
		// TODO Your code here
		System.out.println("compress2");
		
		 throw new RuntimeException();
	}

	/**
	 * Predicts the label of a given 1D-image example based on the PQkNN algorithm.
	 * @param testsample The given out-of-sample 1D image.
	 * @param nearestNeighbors k in kNN
	 * @return test image classification (0..9)
	 */
	public int predict(int[] testsample, int nearestNeighbors) {
		this.distances = new double[this.k][this.n];
		
		//TODO Your code here
		
		 throw new RuntimeException();	
	}
	
	/**
	 * Calculate the distance between the given example and a centroid.
	 * @param example1 The given example.
	 * @param example2 The given centroid.
	 * @return The distance.
	 */
	private static double calculateDistance(int[] example1, double[] example2) {
		
		//TODO Your code here
		
		double distance=0;
		double numbersquared=0;
		double total=0;
		double number=0;
		for(int i=0;i<example1.length;i++)
		{
			number = example1[i]-example2[i];
			numbersquared = Math.pow(number, 2);
			total += numbersquared;
		}
		
		distance = Math.sqrt(total);
	
	
		
		return distance;
	}
	
	public static void main(String[] args) 
	{
		PQkNN test = new PQkNN(1,2);
		
		//DataReader word = new DataReader();
		
		DataReader.DataSet object = DataReader.readData("mnist_train.csv", 60000);
	
		int[][] traindata = object.getData();
		int[] trainlabels = object.getLabels();
		
		test.compress(traindata,trainlabels);

		
		//word.readData("mnist_train.csv", 60000);
			
		//DataSet data = readData("training.csv", 60000);
		//int[][] data = data.getData();
		//int [] labels = data.getLabels();
	}

}

