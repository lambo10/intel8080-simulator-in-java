public class register{
	static String opcodes = "";
public static String oprand1 = "";
public static String oprand2 = "";
public static String label = "";
public static String workOprand = "";

public static double A = 0;
public static double B = 0;
public static double C = 0;
public static double D = 0;
public static boolean z = false;
public static boolean c = false;
public static boolean f = false;
public static int SP = 0;
public static int IP = 0;
public static int clock = 1000;
public static int numberOfLines_of_codes = 0;

public static String [] builtIn_Opcodes = {"MOV","INC","DEC","ADD","SUB","JMP","MUL","DIV","AND","OR","XOR","NOT","CMP","JC","JNC","DB"};
public static String [][] memory = new String[500][2];
public static String [][] ram = new String[500][4];

public void load_line_to_ram(int lineNumber){
int count = 0;
ram[lineNumber][0] = label;
ram[lineNumber][1] = opcodes;
ram[lineNumber][2] = oprand1;
ram[lineNumber][3] = oprand2; 
}

}