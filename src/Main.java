public class Main{
	/*
	Kevin Bacon-game
	Implementera ett program som frågar efter namnet på en skådespelare och som skriver ut dennes Bacon-nummer. Se
	kursboken uppgift 9.53 för en en förklaring till Bacon-nummer. För båda betygen kan det vara lämpligt att inkludera
	ett kommandoradsgränssnitt som låter användaren mata in namn och få tillbaka Bacon-nummer (och väg för VG).

	För betyget G ska du definiera, implementera och dokumentera en datastruktur och en sökalgoritm för problemet. Det
	ställs inga speciella krav på att sökningen ska vara effektiv, men den ska fungera med den givna filen. En
	rekommendation är att du kapar ner filerna betydligt när du testar.

	För betyget VG ska din lösning vara effektiv och du måste kunna motivera alla val ordentligt. Effektiv betyder i
	detta sammanhang att den ska klara hela filen och kunna läsa in och bearbeta data under två minuter på en “normal”
	dator. För VG tillkommer också att programmet ska kunna skriva ut den väg som resulterade i ett visst Bacon-nummer.
	*/
	
	public static void main(String[] args){
		programLoop();
	}
	public static void programLoop(){
		InputManager input = new InputManager();
		KevinBacon kb = new KevinBacon();
		
		boolean isRunning = true;
		while(isRunning){
			System.out.println("Options: [K]evin Bacon, [E]xit.");
			char userInput = input.getChar("Command");
			
			switch(userInput){
				case 'K':
				case 'k':
					String actor = input.getString("Actor");
					kb.printBaconNumber(actor);
					break;
				case 'E':
				case 'e':
					isRunning = false;
					break;
				default:
					System.out.println("Error: Unknown command!");
			}
		}
	}
}
