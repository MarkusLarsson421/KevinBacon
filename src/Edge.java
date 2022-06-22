public class Edge<T>{
	private T destination;
	private String name;
	private int weight;
	
	Edge(T destination, String name, int weight){
		if(weight < 0){throw new IllegalArgumentException();}
		
		this.name = name;
		this.weight = weight;
		this.destination = destination;
	}
	
	public T getDestination(){
		return destination;
	}
	public int getWeight(){
		return weight;
	}
	public void setWeight(int input){
		if(weight < 0){throw new IllegalArgumentException();}
		weight = input;
	}
	public String getName(){
		return name;
	}
	public String toString(){
		return "till " + destination.toString() + " med " + name + " tar " + weight;
	}
}