package org.thu.ui;

public class Mips_sentence {
	/**
	 *反汇编得来的指令 
	 */
	String string_command;
	
	/**
	 * 注释
	 */
	String note;
	/**
	 *是否为breakpoint 
	 */
	boolean breakpoint;
	
	/**
	 *二进制形式的command 
	 */
	byte[] command;
	
	/**
	 * 是否为合法指令
	 */
	boolean legal;
	
	
	/**
	 * 显示该行的长度,在file_in_binary中调用时被计算
	 */
	int size;
	
	public Mips_sentence(int  c,String string_command){
		
		long com = 0;
		if(c>0) com = c;
		else com = c+0x100000000l;
		this.command = new byte[4];
		this.command[0] = (byte) (com%256);
		com/=256;
		this.command[1] = (byte) (com%256);
		com/=256;
		this.command[2] = (byte) (com%256);
		com/=256;
		this.command[3] = (byte) (com);
		this.string_command = string_command;
		this.breakpoint = false;
		this.size = 26+this.string_command.length();
	}
	
	/**
	 * 获得该语句的断点信息
	 */
	public boolean isbreakpoint(){
		return this.breakpoint;
	}
	
	/**
	 * 设置该command的breakpoint信息
	 */
	public void setbreakpoint(boolean b){
		this.breakpoint = b;
	}
	
	/**
	 * 反置breakpoint值
	 */
	public void chgbreakpoint(){
		this.breakpoint = !this.breakpoint;
	}
	
	public byte[] getCommand(){
		return command;
	}
	
	public String getstring_command(){
		return string_command;
	}
	
	public void setNote(String note){
		if(note == null)
			note ="";
		this.note = note;
		if(note.length() > 0)
			this.size+=2+note.length();
	}
	
	public String getNote(){
		if(this.note == null)
			this.note = "";
		return this.note;
	}
}


