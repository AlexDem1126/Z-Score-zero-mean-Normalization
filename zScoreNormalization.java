public class zScoreNormalization {
	private int numOfPoints;
	private int numOfDimension;
	private double[][] dataset;
	private double[] mean;
	private double[] stDeviation;
	private double[][] zScoreNormalizedDataset;



	public zScoreNormalization(double[][] dataset2, int nPoints, int nDimensions) {
			dataset = dataset2;
			numOfPoints = nPoints;
			numOfDimension = nDimensions;
		}



	public double[][] getzScoreNormalizedDataset() {
		return zScoreNormalizedDataset;
	}

	public void setzScoreNormalizedDataset(double[][] zScoreNormalizedDataset) {
		this.zScoreNormalizedDataset = zScoreNormalizedDataset;
	}
	


	// Z-Score (zero-mean) Normalization
	public double[][] zScoreNormalization(double[][] datasetF) {
		mean = zMean(dataset);
		stDeviation = standartDeviation(dataset, mean);

		zScoreNormalizedDataset = new double[numOfPoints][];
		for (int i = 0; i < numOfPoints; i++) {
			zScoreNormalizedDataset[i] = new double[numOfDimension];
		}

		for (int i = 0; i < numOfPoints; i++) {
			for (int j = 0; j < numOfDimension; j++) {
				zScoreNormalizedDataset[i][j] = ((datasetF[i][j] - mean[j]) / stDeviation[j]);
			}
		}
		 printFile(zScoreNormalizedDataset);
		return zScoreNormalizedDataset;
	}



	// mean
	public double[] zMean(double[][] datasetF) {
		double[] meanF = new double[numOfDimension];
		for (int i = 0; i < numOfDimension; i++) {
			meanF[i] = 0;
		}
		int col = 0;

		while (col != numOfDimension) {
			meanF[col] = 0;
			double sum = 0;
			for (int i = 0; i < datasetF.length; i++) {
				sum += datasetF[i][col];
			}
			meanF[col] = sum / datasetF.length;
			// System.out.println("mean = " + meanF[col]);
			col++;
		}
		return meanF;
	}

	
	
	// standard deviation
	public double[] standartDeviation(double[][] datasetF, double[] meanF) {
		double[] stDeviationF = new double[numOfDimension];
		for (int i = 0; i < numOfDimension; i++) {
			stDeviationF[i] = 0;
		}
		int col = 0;

		while (col != numOfDimension) {
			stDeviationF[col] = 0;
			double variance = 0;
			double difference = 0;
			for (int i = 0; i < datasetF.length; i++) {
				difference += Math.pow((datasetF[i][col] - meanF[col]), 2);
				variance = difference / datasetF.length;
			}
			stDeviationF[col] = Math.sqrt(variance);
			// System.out.println("stDeviationF = " + stDeviationF[col]);
			col++;
		}
		return stDeviationF;
	}

	

	public void printFile(double[][] datasetF) {
		// print multi-dimensional array
		for (int i = 0; i < numOfPoints; i++) {
			for (int j = 0; j < numOfDimension; j++) {
				System.out.print(datasetF[i][j] + " ");
			}
			System.out.println();
		}
	}

}
