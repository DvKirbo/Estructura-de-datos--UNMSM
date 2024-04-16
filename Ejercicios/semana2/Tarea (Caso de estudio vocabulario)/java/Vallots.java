package semana2;

import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;

public class Vallots {
	public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File(PathFile()));
        ArrayList<Ballot> ballots = readFile(input);
        int round = 1;
        boolean done = false;
        while (!done) {
            System.out.println("Round #" + round);
            Collections.sort(ballots);
            done = oneRound(ballots);
            System.out.println("------------------------------");
            round++;
        }
    }

    public static ArrayList<Ballot> readFile(Scanner input) {
        ArrayList<Ballot> result = new ArrayList<>();
        while (input.hasNextLine()) {
            String text = input.nextLine().trim();
            if (!text.isEmpty()) {
                String[] votes = text.split(",");
                result.add(new Ballot(votes));
            }
        }
        return result;
    }

    public static boolean oneRound(ArrayList<Ballot> ballots) {
        String top = null;
        String bottom = null;
        int topCount = 0;
        int bottomCount = ballots.size() + 1;
        
        Map<String, Integer> voteCounts = new HashMap<>();
        for (Ballot ballot : ballots) {
            String candidate = ballot.getCandidate();
            voteCounts.put(candidate, voteCounts.getOrDefault(candidate, 0) + 1);
        }
        
        for (Map.Entry<String, Integer> entry : voteCounts.entrySet()) {
            String candidate = entry.getKey();
            int count = entry.getValue();
            if (count > topCount) {
                topCount = count;
                top = candidate;
            }
            if (count < bottomCount) {
                bottomCount = count;
                bottom = candidate;
            }
        }
        
        for (Map.Entry<String, Integer> entry : voteCounts.entrySet()) {
            String candidate = entry.getKey();
            int count = entry.getValue();
            double percent = 100.0 * count / ballots.size();
            System.out.printf("%d votes for %s (%4.1f%%)\n", count, candidate, percent);
        }
        
        if (topCount == bottomCount) {
            System.out.println("Election has no winner");
            return true;
        } else if (topCount > ballots.size() / 2.0) {
            System.out.println("Winner is " + top);
            return true;
        } else {
            System.out.println("No winner, eliminating " + bottom);
            eliminate(bottom, ballots);
            
            for (Ballot ballot : ballots) {
                if (!ballot.isEmpty()) {
                    ballot.eliminate(bottom);
                }
            }
            
            if (!ballots.isEmpty()) {
                return oneRound(ballots);
            } else {
                System.out.println("All ballots are empty. No winner.");
                return true;
            }
        }
    }
    
    public static String PathFile() {
    	String rutaAbsoluta="";
    	JFileChooser fileChooser = new JFileChooser();
    	FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto", "txt");
    	fileChooser.setFileFilter(filter);
    	
    	int resultado = fileChooser.showOpenDialog(null);
    	
    	if (resultado == JFileChooser.APPROVE_OPTION) {
    		java.io.File archivoSeleccionado = fileChooser.getSelectedFile();
    		
    		rutaAbsoluta = archivoSeleccionado.getAbsolutePath();
    		System.out.println("Ruta absoluta del archivo seleccionado: " + rutaAbsoluta);
    	} else {
    		System.out.println("No se seleccionó ningún archivo.");
    	}
    	return rutaAbsoluta;
	}

    public static int processVotes(String name, int index, ArrayList<Ballot> ballots) {
        int count = 0;
        while (index < ballots.size() && ballots.get(index).getCandidate().equals(name)) {
            index++;
            count++;
        }
        double percent = 100.0 * count / ballots.size();
        System.out.printf("%d votes for %s (%4.1f%%)\n", count, name, percent);
        return count;
    }

    public static void eliminate(String candidate, ArrayList<Ballot> ballots) {
        for (Ballot b : ballots) {
            b.eliminate(candidate);
        }
    }
    
    
    private static class Ballot implements Comparable<Ballot> {
        private ArrayList<String> preferences;

        public Ballot(String[] names) {
            preferences = new ArrayList<>(Arrays.asList(names));
        }

        public String getCandidate() {
            if (!preferences.isEmpty()) {
                return preferences.get(0);
            } else {
                return "none";
            }
        }

        public void eliminate(String name) {
            preferences.remove(name);
        }

        public int compareTo(Ballot other) {
            return getCandidate().compareTo(other.getCandidate());
        }
        
        public boolean isEmpty() {
            return preferences.isEmpty();
        }
    }
}


