import java.io.*;
public class concat {
	public static void main(String[] args) {
		//definitions
		FileReader fr;
		FileWriter fw;
		BufferedReader br;
		BufferedWriter bw;
		String s;//temp string for transfer
		try{
		fr=new FileReader(args[1]);//read from the second file
		fw=new FileWriter(args[0],true);//write to the first file(the use of
		                                //argument true is to append contents to the opened file) 
		
		br=new BufferedReader(fr);
		bw=new BufferedWriter(fw);
		
		s=br.readLine();
        while(s!=null){
          	bw.write(s);
          	bw.newLine();
          	s=br.readLine();
        }
		bw.flush();
		fw.close();
		bw.close();
		fr.close();
		br.close();
		
		//delete the second file
		File f=new File(args[1]);
		//System.out.println(f.isFile());
		f.delete();
		
		}catch(FileNotFoundException e){
			System.out.println(e);
		}catch(IOException e){
			System.out.println(e);
		}
	}
		

}

