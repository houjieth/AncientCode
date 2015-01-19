import java.io.*;
public class FileList {
	public static void main(String[] args) {
		System.out.println("Files in current directory:");
        list(".",0);
	}
	public static void list(String path,int space){
		File dir=new File(path);
		String files[]=dir.list();//list()只返回文件名，不含路径
		for(int i=0;i<files.length;i++){
			//print spaces
			for(int j=0;j<space;j++)
				System.out.print(" ");
			//print files
			File f=new File(path+"\\"+files[i]);//注意使用isFile()和isDirectory()时需要说明文件的完整路径
			if(f.isFile()){
				System.out.println(f.getName());//getName()得到单纯的文件名
			}
			else if(f.isDirectory()){
				System.out.println("<"+f.getName()+">");
				String newPath=new String(f.toString());//toString得到文件名及路径
				list(newPath,space+4);
			}
		}
	}

}
