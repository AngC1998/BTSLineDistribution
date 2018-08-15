import java.io.*;
import java.util.*;

public class BTSLinesClean {
	public static void main(String [] args)throws IOException {
		System.out.println("Albums line distribution: ");
		System.out.println("***************************");
		albums();
		System.out.println();
		System.out.println("Title song distribution: ");
		System.out.println("***************************");
		titleSongs();
		System.out.println();
		System.out.println("Favorite songs distribution: ");
		System.out.println("***************************");
		favSongs();
	}
	public static void favSongs()throws IOException {
		String[] playlist = {"MICDrop.txt", "SpringDay.txt", "Run.txt"};
		String[] titles = {"MIC Drop", "Spring Day", "Run"};
		for(int j = 0; j < playlist.length; j++) {
			Map<Integer, ArrayList<String>> song = calcLineDistOrder(titles[j], playlist[j]);
			int k = 7;
			int h = 0;
			System.out.println("Overall "+titles[j]+" Totals: ");
			for(Integer i: song.keySet()) {
				ArrayList<String> names = song.get(i);
				if(names.size() > 1 && h == (song.size()-1)) {
					k = 1;
				}
				System.out.print(k+") ");
				for(int m = 0; m < names.size(); m++) {
					System.out.print(names.get(m));
					if(m < names.size() - 1)
						System.out.print(", ");
				}
				System.out.println(": "+i);
				k-=names.size();
				h++;
			}
			System.out.println();
		}
	}
	public static void titleSongs()throws IOException {
		String[] titleList = {"FakeLove.txt", "DNA.txt", "BloodSweatTears.txt",
		"Run.txt", "INeedU.txt", "Danger.txt", "NoMoreDream.txt"};
		String[] titles = {"Fake Love", "DNA", "Blood, Sweat, and Tears", "Run", "I Need U",
		"Danger", "No More Dream"};
		for(int j = 0; j < titleList.length; j++) {
			Map<Integer, ArrayList<String>> song = calcLineDistOrder(titles[j], titleList[j]);
			int k = 7;
			int h = 0;
			System.out.println("Overall "+titles[j]+" Totals: ");
			for(Integer i: song.keySet()) {
				ArrayList<String> names = song.get(i);
				if(names.size() > 1 && h == (song.size()-1)) {
					k = 1;
				}
				System.out.print(k+") ");
				for(int m = 0; m < names.size(); m++) {
					System.out.print(names.get(m));
					if(m < names.size() - 1)
						System.out.print(", ");
				}
				System.out.println(": "+i);
				k-=names.size();
				h++;
			}
			System.out.println();
		}
	}
	public static void albums()throws IOException {
		String tear = "LY: Tear";
		String[] tearList = {"FakeLove.txt", "TruthUntold.txt", "134340.txt",
		"Paradise.txt", "LoveMaze.txt", "MagicShop.txt", "AirplanePt2.txt",
		"Anpanman.txt", "SoWhat.txt"};
		System.out.println("***Excluding Intro and Outro***");
		printAlbumTotals(tear, tearList);
		System.out.println("***Also Excluding Truth Untold***");
		String[] tearListTwo = {"FakeLove.txt", "134340.txt", "Paradise.txt",
		"LoveMaze.txt", "MagicShop.txt", "AirplanePt2.txt", "Anpanman.txt",
		"SoWhat.txt"};
		printAlbumTotals(tear, tearListTwo);
		String wings = "Wings";
		String[] wingsList = {"BloodSweatTears.txt", "Lost.txt", "AmIWrong.txt",
		"BTSCypher4.txt", "21stCenturyGirls.txt", "2!3!.txt"};
		System.out.println("***Excluding Intro and Outro and Solos***");
		printAlbumTotals(wings, wingsList);
		String[] wingsListTwo = {"BloodSweatTears.txt", "AmIWrong.txt",
		"21stCenturyGirls.txt", "2!3!.txt"};
		System.out.println("***Also excluding Lost and BTS Cypher Pt. 4***");
		printAlbumTotals(wings, wingsListTwo);
	}
	public static void printAlbumTotals(String albumName, String[] lyricFiles)throws IOException {
		Map<Integer, String> albumTotals = calcAlbumTotals(albumName, lyricFiles);
		int k = 7;
		System.out.println("Overall "+albumName+" Totals: ");
		for(Integer i: albumTotals.keySet()) {
			String name = albumTotals.get(i);
			System.out.println(k+") "+name+": "+i);
			k--;
		}
		System.out.println();
	}
	public static Map<Integer, String> calcAlbumTotals(String albumName, String[] lyricFiles)throws IOException {
		Map<Integer, String> albumTotals = new TreeMap<Integer, String>();
		int jungkookTotal = 0;
		int vTotal = 0;
		int jiminTotal = 0;
		int rmTotal = 0;
		int jhopeTotal = 0;
		int sugaTotal = 0;
		int jinTotal = 0;
		for(int i = 0; i < lyricFiles.length; i++) {
			String songI = lyricFiles[i];
			int[] songCount = calcLineDist(songI);
			jungkookTotal += songCount[0];
			vTotal += songCount[1];
			jiminTotal += songCount[2];
			rmTotal += songCount[3];
			jhopeTotal += songCount[4];
			sugaTotal += songCount[5];
			jinTotal += songCount[6];
		}
		albumTotals.put(jungkookTotal, "Jungkook");
		albumTotals.put(vTotal, "V");
		albumTotals.put(jiminTotal, "Jimin");
		albumTotals.put(rmTotal, "RM");
		albumTotals.put(jhopeTotal, "JHope");
		albumTotals.put(sugaTotal, "Suga");
		albumTotals.put(jinTotal, "Jin");
		return albumTotals;
	}
	public static int[] calcLineDist(String inputFile)throws IOException {
		int[] count = new int[7];
		Scanner scan = new Scanner(new File(inputFile));
		ArrayList<String> current = new ArrayList<String>();
		int jungkook = 0;
		int v = 0;
		int jimin = 0;
		int rm = 0;
		int jhope = 0;
		int suga = 0;
		int jin = 0;
		boolean collectLines = false;
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			if((line.equals("Jungkook") || line.equals("V") || line.equals("Jimin") || line.equals("RM")
				|| line.equals("JHope") || line.equals("Suga") || line.equals("Jin") || line.equals("BTS"))
					 && collectLines) {
					current.clear();
					current.add(line);
					collectLines = false;
				}
			else if((line.equals("Jungkook") || line.equals("V") || line.equals("Jimin") || line.equals("RM")
				|| line.equals("JHope") || line.equals("Suga") || line.equals("Jin") || line.equals("BTS"))
					 && !collectLines) {
					current.add(line);
			}
			else {
				collectLines = true;
				if(current.contains("Jungkook")) {
					jungkook++;
				}
				if(current.contains("V")) {
					v++;
				}
				if(current.contains("Jimin")) {
					jimin++;
				}
				if(current.contains("RM")) {
					rm++;
				}
				if(current.contains("JHope")) {
					jhope++;
				}
				if(current.contains("Suga")) {
					suga++;
				}
				if(current.contains("Jin")) {
					jin++;
				}
				if(current.contains("BTS")) {
					jungkook++;
					v++;
					jimin++;
					rm++;
					jhope++;
					suga++;
					jin++;
				}
			}
		}
		count[0] = jungkook;
		count[1] = v;
		count[2] = jimin;
		count[3] = rm;
		count[4] = jhope;
		count[5] = suga;
		count[6] = jin;
		return count;
	}
	public static Map<Integer, ArrayList<String>> calcLineDistOrder(String song, String inputFile)throws IOException {
		Map<Integer, ArrayList<String>> songTotals = new TreeMap<Integer, ArrayList<String>>();
		int[] count = calcLineDist(inputFile);
		String[] names = {"Jungkook", "V", "Jimin", "RM", "JHope", "Suga", "Jin"};
		for(int i = 0; i < count.length; i++) {
			int num = count[i];
			String name = names[i];
			if(songTotals.get(num) == null) {
				ArrayList<String> namesList = new ArrayList<>();
				namesList.add(name);
				songTotals.put(num, namesList);
			}
			else {
				ArrayList<String> namesList = songTotals.get(num);
				namesList.add(name);
				songTotals.put(num, namesList);
			}
		}
		return songTotals;
	}
}