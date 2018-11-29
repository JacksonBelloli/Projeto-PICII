package com.pic.localizafilmes.utils;

import java.util.Map.Entry;
import java.util.TreeMap;

public class Graph {
	private final int NODES_QUANTITY;
	private Integer[][] matrix;
	
	public Graph(int nodesQuantity) throws Exception {
		validateNodeQuantity(nodesQuantity);
		
		NODES_QUANTITY = nodesQuantity;
		
		createMatrix();
	}
	
	public Integer[][] getMatrix() {
		return matrix;
	}
	
	public int getNodesQuantity() {
		return NODES_QUANTITY;
	}
	
	public void insertEdge(Integer row, Integer col, Integer weight) throws Exception {
		validatePositions(row, col);
		validateWeight(weight);
		
		matrix[row][col] = weight;
	}
	
	private boolean validatePositions(Integer row, Integer col) throws Exception {
		if (row < 0 || col < 0) {
			throw new Exception("Um dos vertices s�o inv�lidos.");
		}
		
		if (row > NODES_QUANTITY || col > NODES_QUANTITY) {
			throw new Exception("Um dos vertices � maior que o tamanho de v�rtices.");
		}
		
		return true;
	}
	
	private boolean validateWeight(Integer distance) throws Exception {
		 if (distance < 0) {
			throw new Exception("Peso n�o pode ser negativo.");
		}
		
		return true;
	}
	
	private boolean validateNodeQuantity(int nodesQuantity) throws Exception {
		if (nodesQuantity < 0) {
			throw new Exception("A quantidade de v�rtices n�o pode ser menor que zero.");
		}
		
		return true;
	}
	
	private void createMatrix() {
		matrix = new Integer[NODES_QUANTITY][];
		
		for (int i = 0; i < NODES_QUANTITY; i++) {
			matrix[i] = new Integer[NODES_QUANTITY];
	
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = 0;
			}
		}
	}
	
	public void printMatrix() {
		for (int i = 0; i < NODES_QUANTITY; i++) {
	
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	protected Integer sumDistanceOfPaths(TreeMap<Integer, Edge> mapOfPaths) {
		Integer sumOfDistance = 0;
		if(mapOfPaths == null) {
			return -1;
		}
		for (Entry<Integer, Edge> entry : mapOfPaths.entrySet()) {
			sumOfDistance += entry.getValue().getAccumulatedDistance();
		}
		return sumOfDistance;
	}
}