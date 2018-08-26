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
		String[] playlist = {"I'mFine.txt", "MICDrop.txt", "SpringDay.txt", "Run.txt", "WeAreBulletProof2.txt"};
		String[] titles = {"I'm Fine", "MIC Drop", "Spring Day", "Run", "We Are Bulletproof Pt. 2"};
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
		String[] titleList = {"IDOL.txt", "FakeLove.txt", "DNA.txt", "BloodSweatTears.txt",
		"Run.txt", "INeedU.txt", "Danger.txt", "NoMoreDream.txt"};
		String[] titles = {"IDOL", "Fake Love", "DNA", "Blood, Sweat, and Tears", "Run", "I Need U",
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
		String answer = "LY: Answer";
		String[] answerList = {"IDOL.txt", "I'mFine.txt", "AnswerLoveMyself.txt"};
		System.out.println("***Excluding Trivias, Remixes, and Older Releases***");
		printAlbumTotals(answer, answerList);
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
		Map<Integer, ArrayList<String>> albumTotals = calcAlbumTotals(albumName, lyricFiles);
		int k = 7;
		System.out.println("Overall "+albumName+" Totals: ");
		for(Integer i: albumTotals.keySet()) {
			ArrayList<String> names = albumTotals.get(i);
			for(int j = 0; j < names.size(); j++) {
				System.out.println(k+") "+names.get(j)+": "+i);
			}
			k-=names.size();
		}
		System.out.println();
	}
	public static Map<Integer, ArrayList<String>> calcAlbumTotals(String albumName, String[] lyricFiles)throws IOException {
		Map<Integer, ArrayList<String>> albumTotals = new TreeMap<Integer, ArrayList<String>>();
		int[] lineCount = new int[7];
		String[] members = {"Jungkook", "V", "Jimin", "RM", "JHope", "Suga", "Jin"};
		for(int i = 0; i < lyricFiles.length; i++) {
			String songI = lyricFiles[i];
			int[] songCount = calcLineDist(songI);
			lineCount[0] += songCount[0];
			lineCount[1] += songCount[1];
			lineCount[2] += songCount[2];
			lineCount[3] += songCount[3];
			lineCount[4] += songCount[4];
			lineCount[5] += songCount[5];
			lineCount[6] += songCount[6];
		}
		for(int i = 0; i < lineCount.length; i++) {
			int currentCount = lineCount[i];
			if(albumTotals.containsKey(currentCount)) {
				ArrayList<String> curMemList = albumTotals.get(currentCount);
				curMemList.add(members[i]);
				albumTotals.put(currentCount, curMemList);
			}
			else {
				ArrayList<String> newMemList = new ArrayList<String>();
				newMemList.add(members[i]);
				albumTotals.put(currentCount, newMemList);
			}
		}
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