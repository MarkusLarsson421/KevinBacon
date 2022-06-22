import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

public class KevinBacon{
	private final UndirectedGraph<String> graph = new UndirectedGraph<>();

	public KevinBacon(){
		getData();
	}

	private void getData(){
		try {
			BufferedReader br = Files.newBufferedReader(Path.of("movieDataSmall.txt"));

			String lastActor = null;
			String line;
			while((line = br.readLine()) != null){
				if(line.charAt(1) == 'a'){
					line = formatLine(line);
					graph.add(line);
					lastActor = line;
					System.out.println("Actor: " + line);
				}else if(line.charAt(1) == 't'){
					line = formatLine(line);
					graph.add(line);
					graph.connect(lastActor, line, 1);
					System.out.println("Movie: " + line);
				}
			}
		} catch (IOException e) {
			System.out.println("Error: Couldn't find the data file!");
			e.printStackTrace();
		}
	}
	
	public void printBaconNumber(String actor){
		System.out.println(graph.toString());
		actor = formatLine(actor);

		if(actor.equals("bacon kevin")){
			System.out.println("Bacon Kevin is 0 steps away from Bacon Kevin.");
			return;
		}else if(graph.add(actor)){
			System.out.println("Error: Actor doesn't exist!");
			return;
		}

		List<String> path = graph.breadthFirstSearch(actor, "bacon kevin");
		
		System.out.println(actor + " is " + path.size() + " steps away from Kevin Bacon.");
		System.out.println("Path: " + path);
	}

	private String formatLine(String line){
		line = line.substring(3);
		line = line.trim().toLowerCase();
		return line;
	}

	/*
	Example of output:
	? Garbo, Greta
	"Garbo, Greta" is 2 steps away from Kevin B. The path is <a>Bacon, Kevin
	(I)<a><t>FrostNixon (2008)<t><a>Ford, Gerald (I)<a><t>Brother Can You Spare a Dime
	(1975)<t><a>Garbo, Greta<a>
	 */
}
