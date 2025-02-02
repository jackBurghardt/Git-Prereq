import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class TEST {
private static Index index;
private static File file, file1, file2;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File file = new File("file.txt");
		FileWriter f = new FileWriter (file);
		f.write ("hello file zero how are you doing");
		f.close();
		
		File file1 = new File("file1.txt");
		FileWriter fi = new FileWriter (file1);
		fi.write ("hello file one how are you doing");
		fi.close();
		
		File file2 = new File("file2.txt");
		FileWriter fil = new FileWriter (file2);
		fil.write ("hello file two how are you doing");
		fil.close();
		
		index = new Index();
		index.init();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		file.delete();
		file1.delete();
		file2.delete();
		
	}

	
	
	@Test
	void testBlob() throws NoSuchAlgorithmException, IOException {
		Blob blob = new Blob("file.txt");
		String Sha = blob.getName();
		assertTrue(new File("tester/objects/" + Sha).exists());
	}
	
	@Test
	void testInit() {
		assertTrue(new File("tester/objects").exists() && new File("tester/index.txt").exists());
	}
	
	@Test
	void testIndex() throws Exception {
		index.add ("file.txt");
		Blob blobby = new Blob ("file.txt");
	String fileSha = blobby.getName();
	
	 index.add("file1.txt");
	Blob blo = new Blob ("file1.txt");
	String fileSha1 = blo.getName();
	
	index.add ("file2.txt");
	Blob b = new Blob ("file2.txt");
	String fileSha2 = b.getName();

	
	File folder = new File("tester/Objects");
	ArrayList<String> fileNames = new ArrayList<String>(Arrays.asList(folder.list()));
	if (!fileNames.contains(fileSha) || !fileNames.contains(fileSha1) ||!fileNames.contains(fileSha2))
	{
		fail ("fail");
	}
	
	
	index.remove("file.txt");
	
	
	File fol = new File("tester/objects");
	ArrayList<String> fileNam = new ArrayList<String>(Arrays.asList(fol.list()));
	fileNam.contains(fileSha);
	
	Scanner scanner = new Scanner("tester/index.txt");
	int count = 3;
	while (scanner.hasNextLine()) {
		count= count - 1;
		scanner.nextLine();
	}
	scanner.close();
	if (count!=1) {
		fail("fail");
	}
}
}
