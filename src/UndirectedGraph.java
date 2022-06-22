import java.util.*;

public class UndirectedGraph<T> {
	private final HashMap<T, HashSet<Edge<T>>> graph = new HashMap<>();
	
	public void add(T node){
		if(graph.containsKey(node)){return;}
		graph.put(node, new HashSet<>());
	}
	
	public void connect(T node1, T node2, String name, int weight){
		if(!graph.containsKey(node1) || !graph.containsKey(node2)){throw new NoSuchElementException();}
		if(weight < 0){throw new IllegalArgumentException();}
		if(getEdgeBetween(node1, node2) != null){throw new IllegalStateException();}
		
		Edge<T> edge1 = new Edge<>(node2, name, weight);
		Edge<T> edge2 = new Edge<>(node1, name, weight);
		graph.get(node1).add(edge1);
		graph.get(node2).add(edge2);
	}
	
	public void setConnectionWeight(T node1, T node2, int weight){
		if(!graph.containsKey(node1) || !graph.containsKey(node2)){throw new NoSuchElementException();}
		if(weight < 0){throw new IllegalArgumentException();}
		
		getEdgeBetween(node1, node2).setWeight(weight);
		getEdgeBetween(node2, node1).setWeight(weight);
	}
	
	public Set<T> getNodes(){
		return graph.keySet();
	}
	
	public Collection<Edge<T>> getEdgesFrom(T node){
		if(!graph.containsKey(node)){throw new NoSuchElementException();}
		return graph.get(node);
	}
	
	public Edge<T> getEdgeBetween(T node1, T node2){
		if(!graph.containsKey(node1) || !graph.containsKey(node2)){throw new NoSuchElementException();}
		
		for(Edge<T> edge : graph.get(node1)){
			if(edge.getDestination().equals(node2)){
				return edge;
			}
		}
		return null;
	}
	
	public void disconnect(T node1, T node2){
		if(!graph.containsKey(node1) || !graph.containsKey(node2)){throw new NoSuchElementException();}
		if(getEdgeBetween(node1, node2) == null){throw new IllegalStateException();}
		
		Edge<T> edge1 = getEdgeBetween(node1, node2);
		Edge<T> edge2 = getEdgeBetween(node2, node1);
		graph.get(node1).remove(edge1);
		graph.get(node2).remove(edge2);
	}
	
	public void remove(T node){
		if(!graph.containsKey(node)){throw new NoSuchElementException();}
		
		for(HashSet<Edge<T>> edges : graph.values()){
			edges.removeIf(edge -> edge.getDestination().equals(node));
		}
		
		graph.remove(node);
	}
	
	public boolean pathExists(T from, T to){
		if(!graph.containsKey(from) || !graph.containsKey(to)){return false;}
		
		HashSet<T> visited = new HashSet<>();
		depthFirstSearch(from, visited);
		return visited.contains(to);
	}
	
	public List<Edge<T>> getPath(T from, T to){
		if(!graph.containsKey(from) || !graph.containsKey(to)){throw new NoSuchElementException();}
		if(!pathExists(from, to)){return null;}
		
		HashSet<T> visited = new HashSet<>();
		LinkedList<T> queue = new LinkedList<>();
		HashMap<T, T> via = new HashMap<>();
		visited.add(from);
		queue.add(from);
		while(!queue.isEmpty()){
			T node = queue.pollFirst();
			for(Edge<T> e : graph.get(node)){
				T toNode = e.getDestination();
				if(!visited.contains(toNode)){
					queue.add(toNode);
					visited.add(toNode);
					via.put(toNode, node);
				}
			}
		}
		return gatherPath(from, to, via);
	}
	
	public String toString(){
		StringBuilder output = new StringBuilder();
		output.append("Nodes: \n");
		for(T data : graph.keySet()){
			output.append(data.toString());
			output.append(graph.get(data).toString()).append("\n");
		}
		return output.toString();
	}
	
	public void clear(){
		graph.clear();
	}
	
	private void depthFirstSearch(T from, HashSet<T> visited){
		visited.add(from);
		for(Edge<T> edge : graph.get(from)){
			if(!visited.contains(edge.getDestination())){
				depthFirstSearch(edge.getDestination(), visited);
			}
		}
	}
	
	private List<Edge<T>> gatherPath(T from, T to, HashMap<T, T> via){
		List<Edge<T>> path = new ArrayList<>();
		T where = to;
		while(!where.equals(from)){
			T node = via.get(where);
			Edge<T> e = getEdgeBetween(node, where);
			path.add(e);
			where = node;
		}
		Collections.reverse(path);
		return path;
	}
}