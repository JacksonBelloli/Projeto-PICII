package com.pic.localizafilmes.utils;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Dijkstra extends Graph {
	//Results
	private int distanceToDestination;
	private TreeMap<Integer, Edge> pathToDestination = new TreeMap<Integer, Edge>();;
	private TreeMap<Integer, String> processingLog;
	
	public Dijkstra(int vertexQuantity) throws Exception {
		super(vertexQuantity);
	}
	
	public void findSmallestPath(int origin, int destination) throws Exception {
		try {
			//Reseta o status do �ltimo processamento
			resetProcessingState();
			
			//Validate origin and destination
			if (origin < 0) {
				throw new Exception("Origem deve ser maior ou igual a zero");
			}
			
			if (destination >= getNodesQuantity()) {
				throw new Exception("Destino deve ser menor ou igual a " + (getNodesQuantity() - 1));
			}
			
			TreeMap<Integer, Integer> unsolvedNodes = new TreeMap<Integer, Integer>();
			TreeMap<Integer, Integer> solvedNodes = new TreeMap<Integer, Integer>();
			TreeMap<Integer, Edge> processedPaths = new TreeMap<Integer, Edge>();
			
			for (int i = 0; i < getNodesQuantity(); i++) {
				//Cria o vetor de n�s restantes
				unsolvedNodes.put(unsolvedNodes.size(), new Integer(i));
			}

			//Move n� de origem no map de resolvidos
			solvedNodes.put(origin, 0);
			unsolvedNodes.remove(new Integer(origin));

			//Enquanto houver n�s n�o resolvidos
			while (!unsolvedNodes.isEmpty()) {
				//Vari�veis de controle
				int smallestResolvedNode = -1;
				int smallestAdjacentNode = -1;
				int smallestAdjacentValue = 0;
				
				for (Map.Entry<Integer, Integer> entry : solvedNodes.entrySet()) {
					Integer currentNode = entry.getKey();
					Integer acummulatedDistance = entry.getValue();
					
					//Logging
					addLog("Itera sobre n� resolvido [" + currentNode + "]");
					
					//Apartir do n� resolvido (linha na matriz), itera sobre os n�s adjacentes (colunas na matriz)
					for (int i = 0; i < getNodesQuantity(); i++) {
						//S� considera n�s com valor maior que zero e que seja adjascente a uma n� n�o resolvido
						if (getMatrix()[currentNode][i] > 0 && solvedNodes.get(i) == null) {
							//Soma do peso do n� adjascente ao valor acumulado do n� resolvido
							int adjacentDistance = getMatrix()[currentNode][i] + acummulatedDistance;
							
							//Logging
							String logMessage = "  N� adjascente [" + i + "] - Dist�ncia: " + adjacentDistance + "(" + acummulatedDistance + ")";
							
							//Se for a primeira itera��o
							if (smallestAdjacentNode == -1 || adjacentDistance <= smallestAdjacentValue) {
								smallestResolvedNode = currentNode;
								smallestAdjacentNode = i;
								smallestAdjacentValue = adjacentDistance;
								
								//Logging
								logMessage += " ***"; //Identifica o n� adjascente como o de menor caminho at� o momento
							}
							
							//Logging
							addLog(logMessage);
						}
					}
				}
				
				//Logging
				addLog("[Menor caminho: " + smallestResolvedNode + "->" + smallestAdjacentNode + " = " + smallestAdjacentValue + "]");
				
				//Adiciona o caminho para lista de processados
				processedPaths.put(processedPaths.size(), new Edge(smallestResolvedNode, smallestAdjacentNode, smallestAdjacentValue));
				
				//Move menor n� adjacente dos n�o resolvidos para os resolvidos
				solvedNodes.put(smallestAdjacentNode, smallestAdjacentValue);
				unsolvedNodes.remove(new Integer(smallestAdjacentNode));
				
				addLog(".........................................................");
				
				//Menor caminho poss�vel encontrado
				if (smallestAdjacentNode == destination) {
					//Logging
					addLog("----- Menor caminho encontrado: " + smallestAdjacentValue + " -----");
					
					distanceToDestination = smallestAdjacentValue;
					
					break;
				}
			}
			
			//Gera o caminho at� o destino apartir dos caminhos processados no c�lculo
			generatePathToDestination(processedPaths);
		} catch (Exception error) {
			if (error.getMessage().equals("-1")) {
				throw new Exception("N�o existe um caminho vi�vel entre os pontos");
			}
			
			throw new Exception(error.getMessage());
		}
	}
	
	public TreeMap<Integer, Edge> getPathToDestination() {
		return pathToDestination;
	}
	
	public Integer getDistanceToDestination() {
		return distanceToDestination;
	}
	
	public void printLog() {
		processingLog.forEach((index, log) -> System.out.println(log));
	}
	
	private void resetProcessingState() {
		processingLog = new TreeMap<Integer, String>();
		
		distanceToDestination = 0;
		pathToDestination.clear();
	}
	
	
	
	private void generatePathToDestination(TreeMap<Integer, Edge> processedPaths) {
		int lastOriginToFound = -1;
		for (Entry<Integer, Edge> entry : processedPaths.entrySet().stream()
				.sorted((p1, p2) -> p2.getKey().compareTo(p1.getKey()))
				.collect(Collectors.toList())
		) {
			if (lastOriginToFound == -1) {
				lastOriginToFound = entry.getValue().getNodeOrigin();
				
				pathToDestination.put(entry.getKey(), entry.getValue());
			} else {
				if (entry.getValue().getNodeDestin() == lastOriginToFound) {
					lastOriginToFound = entry.getValue().getNodeOrigin();
					
					pathToDestination.put(entry.getKey(), entry.getValue());
				}
			}
		}
	}
	
	private void addLog(String message) {
		processingLog.put(processingLog.size(), message);
	}

	public static void main(String[] args) {

		try {
			Dijkstra dij = new Dijkstra(8);

			dij.insertEdge(0, 1, 4);
			dij.insertEdge(0, 2, 4);
			dij.insertEdge(0, 4, 2);
			dij.insertEdge(0, 6, 6);

			dij.insertEdge(1, 0, 4);
			dij.insertEdge(1, 2, 2);
			dij.insertEdge(1, 3, 7);
			dij.insertEdge(1, 6, 3);

			dij.insertEdge(2, 0, 4);
			dij.insertEdge(2, 1, 2);
			dij.insertEdge(2, 3, 3);
			dij.insertEdge(2, 4, 2);

			dij.insertEdge(3, 1, 7);
			dij.insertEdge(3, 2, 3);
			dij.insertEdge(3, 7, 2);

			dij.insertEdge(4, 0, 2);
			dij.insertEdge(4, 2, 2);
			dij.insertEdge(4, 5, 7);
			dij.insertEdge(4, 7, 4);

			dij.insertEdge(5, 4, 7);
			dij.insertEdge(5, 7, 3);

			dij.insertEdge(6, 0, 6);
			dij.insertEdge(6, 1, 3);

			dij.insertEdge(7, 3, 2);
			dij.insertEdge(7, 4, 4);
			dij.insertEdge(7, 5, 3);
			
			System.out.println("########### Test Case ##########");
			
			long startTime = System.currentTimeMillis();
			
			dij.findSmallestPath(2, 5);
			
			long finishTime = System.currentTimeMillis();
			System.out.println("Tempo de execu��o: " + (finishTime - startTime) + "ms");
			
			System.out.println("Rota:");
			dij.getPathToDestination().forEach((key, edge) -> {
				System.out.println(edge.getNodeOrigin() + "->" + edge.getNodeDestin() + "=" + edge.getAccumulatedDistance());
			});

			System.out.println("Menor dist�ncia:");
			System.out.println(dij.getDistanceToDestination());
			
			/*System.out.println("Log:");
			dij.printLog();*/
			
			System.out.println("#");
			System.out.println("########### Test Case 2 ##########");
			///////
			
			//Case 2
			dij.findSmallestPath(3, 5);
			
			System.out.println("Rota:");
			dij.getPathToDestination().forEach((key, edge) -> {
				System.out.println(edge.getNodeOrigin() + "->" + edge.getNodeDestin() + "=" + edge.getAccumulatedDistance());
			});

			System.out.println("Menor dist�ncia:");
			System.out.println(dij.getDistanceToDestination());
			
			/*System.out.println("Log:");
			dij.printLog();*/
			///////
		} catch (Exception ex) {
			if (ex.getMessage() == null)
				System.out.println("Ocorreu um erro de " + ex + " no main");
			else
				System.out.println("Erro: " + ex.getMessage());
		}

	}
}