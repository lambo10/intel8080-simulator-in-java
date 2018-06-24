import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


public class PIE_8080ASM {

	public static void main(String [] args){
register reg = new register();
		 int lineCount = 0;

try{
 BufferedReader gy = new BufferedReader (new FileReader("input.txt")); 

            String line = null;
            while((line = gy.readLine())!= null){
divideLine(line);
reg.numberOfLines_of_codes++;
firstParse(reg.opcodes,reg.label,lineCount);
lineCount++;
            }

	}catch(IOException e){
			e.printStackTrace();
		}

secondParse();
int counVV = 0;
while(counVV < 10){
System.out.println(reg.memory[counVV][0]+" "+reg.memory[counVV][1]);
	counVV++;
}
System.out.println(reg.A);
	}

	static void firstParse(String Opcode,String Label,int lineNumber){
register reg = new register();
	if (checkLabel(Label,(lineNumber + 1))) {

	if (AuthenticateOpcode(Opcode,(lineNumber + 1))) {
	
if (Double_Oprand()) {
	
divide_Oprand();
	if (AuthenticateOprand(reg.oprand1,(lineNumber + 1))) {
		
if (AuthenticateOprand(reg.oprand2,(lineNumber + 1))) {
	
	reg.load_line_to_ram(lineNumber);

}

	}



}else{
	reg.oprand1 = reg.workOprand;
}

}
}
	
}

static void divideLine(String line){
String tempOpcode = "";
String temOprand1 = "";
String temOprand2 = "";
String temLabel = "";
String temworkOprand = "";
boolean foundFSemiCOLON_OR_COLON = false;
int countNumber_ofCollectedInput = 0;
int countFFG = 0;
register reg = new register();
	while(countFFG < line.length()){
char workCHAR = line.charAt(countFFG);

if (countNumber_ofCollectedInput > 2){
	break;
}
if (workCHAR == '"') {
	if (foundFSemiCOLON_OR_COLON) {
		foundFSemiCOLON_OR_COLON = false;
	}else{
		foundFSemiCOLON_OR_COLON = true;
	}
}

if (workCHAR == ' ') {
	if (foundFSemiCOLON_OR_COLON) {
		
	if(countNumber_ofCollectedInput == 0){
temLabel = temLabel + workCHAR;
	}

	if (countNumber_ofCollectedInput == 1) {
		tempOpcode = tempOpcode + workCHAR;
	}

	if (countNumber_ofCollectedInput == 2) {
		temworkOprand = temworkOprand + workCHAR;
	}

	}else{
countNumber_ofCollectedInput++;
	}  
}else{

	if(workCHAR == ';'){
break;
	}else{

	if(countNumber_ofCollectedInput == 0){
temLabel = temLabel + workCHAR;
	}

	if (countNumber_ofCollectedInput == 1) {
		tempOpcode = tempOpcode + workCHAR;
	}

	if (countNumber_ofCollectedInput == 2) {
		temworkOprand = temworkOprand + workCHAR;
	}

}
}
countFFG++;
}

boolean foundOpcodeInLabel = false;
int countSSC = 0;
while(countSSC < reg.builtIn_Opcodes.length){
	int checkOpcodeInLabel = temLabel.compareTo(reg.builtIn_Opcodes[countSSC]);
if (checkOpcodeInLabel == 0) {
	foundOpcodeInLabel = true;
}
	countSSC++;
}

if (foundOpcodeInLabel) {
	reg.label = ":";
reg.opcodes = temLabel;
reg.workOprand = tempOpcode;
}else{
	reg.label = temLabel;
reg.opcodes = tempOpcode;
reg.workOprand = temworkOprand;
}
}

static boolean checkLabel(String value,int lineNumber){
int countValue = 0;
boolean result = false;
while(countValue < value.length()){
	char workCHAR = value.charAt(countValue);
	if (countValue == 0) {
		if (workCHAR == '1' || workCHAR == '2' || workCHAR == '3' || workCHAR == '4' || workCHAR == '5' || workCHAR == '6' || workCHAR == '7' || workCHAR == '8' || workCHAR == '9' || workCHAR == '0') {
			result = false;
			throwErro("Erro at '"+value+"': line "+lineNumber+" > "+"Label schould not start with a number.");
			break;
		}else{
			result = true;
		}
	}

	if (result) {
		
	if (countValue == value.length() - 1) {
		if (workCHAR == ':') {
			result = true;
		}else{
			result = false;
			throwErro("Erro at '"+value+"': line "+lineNumber+" > "+"expected ':'");
			break;
		}
	}



	}

	countValue++;
}
return result;
}

static boolean AuthenticateOpcode(String value,int lineNumber){
	register reg = new register();
	int countValue = 0;
	boolean result = false;
	while(countValue < value.length()){
	char workCHAR = value.charAt(countValue);
	if (countValue == 0) {
		if (workCHAR == '1' || workCHAR == '2' || workCHAR == '3' || workCHAR == '4' || workCHAR == '5' || workCHAR == '6' || workCHAR == '7' || workCHAR == '8' || workCHAR == '9' || workCHAR == '0') {
			result = false;
			throwErro("Erro at '"+value+"': line "+lineNumber+" > "+"Opcode schould not start with a number.");
			break;
		}else{
			result = true;
		}
	}
countValue++;
	}
	if (result) {
		boolean identifiedOpcode = false;
		int countFFG = 0;
		while(countFFG < reg.builtIn_Opcodes.length){
			int checkSt = value.compareTo(reg.builtIn_Opcodes[countFFG]);
if (checkSt == 0) {
	identifiedOpcode = true;
	break;
}
	countFFG++;
		}

		if (identifiedOpcode) {
			result = true;
		}else{
			result = false;
			throwErro("Erro at '"+value+"': line "+lineNumber+" > '"+value+"' is an illegal opcode.");
		}
	}
	return result;
}

static boolean Double_Oprand(){
	register reg = new register();
boolean result = false;
int countFFG = 0;
while(countFFG < reg.workOprand.length()){
char workCHAR = reg.workOprand.charAt(countFFG);
if (workCHAR == ',') {
	result = true;
	break;
}
countFFG++;
}
return result;
}

static void divide_Oprand(){
		register reg = new register();
		String firstOprand = "";
		String secondOprand = "";
		boolean foundComma = false;
int countFFG = 0;
while(countFFG < reg.workOprand.length()){
char workCHAR = reg.workOprand.charAt(countFFG);
if (workCHAR == ',') {
	foundComma = true;
}
if (!foundComma) {
	firstOprand = firstOprand + workCHAR;
}else{
	if (workCHAR == ',') {
		
	}else{
secondOprand = secondOprand + workCHAR;
	}
}

countFFG++;
}

reg.oprand1 = firstOprand;
reg.oprand2 = secondOprand;
}

static boolean AuthenticateOprand(String value,int lineNumber){
boolean result = false;
int countFFG = 0;
while(countFFG < value.length()){
char workCHAR = value.charAt(countFFG);
if (workCHAR == ',' || workCHAR == '>' || workCHAR == '<' || workCHAR == '+' || workCHAR == '-' || workCHAR == '*' || workCHAR == '/' || workCHAR == '(' || workCHAR == ')' || workCHAR == '&' || workCHAR == '^' || workCHAR == '%' || workCHAR == '$' || workCHAR == '@' || workCHAR == '!' || workCHAR == ':' || workCHAR == '|' || workCHAR == '?' || workCHAR == '}' || workCHAR == '{' || workCHAR == '!' || workCHAR == '=' || workCHAR == '~') {
	result = false;
	throwErro("Erro at '"+value+"': line "+lineNumber+" > '"+value+"' is an illegal oprand. Example of oprands are [A],A,[123],123");
	break;
}else{
	result = true;
}

	countFFG++;
}
return result;
}

static void secondParse(){
	register reg = new register();
try {
	int countF = 0;
	while(countF < (reg.numberOfLines_of_codes)){
		String oprand = reg.ram[reg.IP][1];
int checkDB = oprand.compareTo("DB");
if (checkDB == 0) {
	DB();
}

int checkMOV = oprand.compareTo("MOV");
if (checkMOV == 0) {
	if (!MOV((countF+1))) {
		break;
	}
}

Thread.sleep(reg.clock);
reg.IP++;
		countF++;
	}
} catch (InterruptedException e){
e.printStackTrace();
}


	}

static void DB(){
register reg = new register();
reg.memory[reg.SP][0] = reg.ram[reg.IP][0];
reg.memory[reg.SP][1] = reg.ram[reg.IP][2];
reg.SP++;
}

static boolean MOV(int lineNumber){
	boolean result = false;
register reg = new register();
	if (Sparse_checkdoubleOprand(reg.ram[reg.IP][1],reg.ram[reg.IP][2],lineNumber,"MOV")) {
		
int foprandType = check_OprandType(reg.ram[reg.IP][2],lineNumber);
int soprandType = check_OprandType(reg.ram[reg.IP][3],lineNumber);

if (foprandType == 0 || soprandType == 0) {
	throwErro("Erro at line "+lineNumber+" > illegal oprand");
	result = false;
}else{
	if (foprandType == 1 && soprandType == 1) {
		throwErro("Erro at line "+lineNumber+" > oprand can only be moved to a register or memory location");
		result = false;
	}

	if (foprandType == 2 && soprandType == 2) {
		throwErro("Erro at line "+lineNumber+" > illegal opration: canot move from memory to memory directly");
		result = false;
	}

	if (foprandType == 4 && soprandType == 4) {
		throwErro("Erro at line "+lineNumber+" > illegal opration: canot move from memory to memory directly");
		result = false;
	}

	if (foprandType == 3 && soprandType == 3) {
		TransferFromRegToReg(reg.ram[reg.IP][2],reg.ram[reg.IP][3]);
	}

	if (foprandType == 3) {
		if (soprandType == 4) {
		if (!TransferFromMemoryToRegister(reg.ram[reg.IP][2],reg.ram[reg.IP][3])) {
			throwErro("Erro at line "+lineNumber+" > illegal oprand: Memory Address must be made up of Integers");
			result = false;
		}
	}
	}

	if (foprandType == 4) {
		if (soprandType == 3) {
		if (!TransferFromRegisterToMemory(reg.ram[reg.IP][2],reg.ram[reg.IP][3])) {
			throwErro("Erro at line "+lineNumber+" > illegal oprand: Memory Address must be made up of Integers");
			result = false;
		}
	}
	}

	if (foprandType == 3) {
		if (soprandType == 1) {
		TransferDataToRegister(reg.ram[reg.IP][2],reg.ram[reg.IP][3]);
	}
	}

	if (foprandType == 1) {
		if (soprandType == 3) {
		throwErro("Erro at line "+lineNumber+" > illegal opration: canot move Register Data to oprand.");
		result = false;
	}
	}

	if (foprandType == 4) {
		if (soprandType == 1) {
		if (!TransferDataToMemory(reg.ram[reg.IP][2],reg.ram[reg.IP][3])) {
			throwErro("Erro at line "+lineNumber+" > illegal oprand: Memory Address must be made up of Integers");
			result = false;
		}
	}
	}

if (foprandType == 1) {
		if (soprandType == 4) {
		throwErro("Erro at line "+lineNumber+" > illegal opration: canot move Memory Data to an oprand.");
		result = false;
	}
	}

	if (foprandType == 5) {
		if (soprandType == 5) {
			throwErro("Erro at line "+lineNumber+" > illegal opration: canot move from memory to memory directly");
			result = false;
	}
	}

	if (foprandType == 1) {
		if (soprandType == 5) {
		throwErro("Erro at line "+lineNumber+" > illegal opration: canot move Memory Data to an oprand.");
		result = false;
	}
	}

	if (foprandType == 3) {
		if (soprandType == 5) {

		if (!TransferFromMemoryToRegister(getRegContent(reg.ram[reg.IP][2]),reg.ram[reg.IP][3])) {
			throwErro("Erro at line "+lineNumber+" > illegal oprand: Memory Address must be made up of Integers");
			result = false;
		}
	}
	}

	if (foprandType == 5) {
		if (soprandType == 1) {
		if (!TransferDataToMemory(getRegContent(reg.ram[reg.IP][2]),reg.ram[reg.IP][3])) {
			throwErro("Erro at line "+lineNumber+" > illegal oprand: Memory Address must be made up of Integers");
			result = false;
		}
	}
	}

if (foprandType == 5) {
		if (soprandType == 3) {

		if (!TransferFromRegisterToMemory(getRegContent(reg.ram[reg.IP][2]),reg.ram[reg.IP][3])) {
			throwErro("Erro at line "+lineNumber+" > illegal oprand: Memory Address must be made up of Integers");
			result = false;
		}
	}
	}

}

	}else{
		result = false;
	}
	return result;

}


static String getRegContent(String Input_reg){
register reg = new register();
double result = 0;
String mainResult = "";
char I_reg = Input_reg.charAt(1);

if (I_reg == 'A') {
	result = reg.A;
}

if (I_reg == 'B') {
	result = reg.B;
}

if (I_reg == 'C') {
	result = reg.C;
}

if (I_reg == 'D') {
	result = reg.D;
}
mainResult = Double.toString(result);
return mainResult;
}


static boolean TransferDataToMemory(String Data,String Address){
	boolean result = true;
register reg = new register();

String collectedAddress = "";
int count = 1;
while(count < (Address.length() - 1)){
	char workCHAR = Address.charAt(count);
	if (workCHAR == '.') {
		result = false;
		break;
	}
collectedAddress = collectedAddress + workCHAR;
	count++;
}

if (result) {
int intAddress = Integer.parseInt(collectedAddress);
reg.memory[intAddress][1] = Data;
}
return result;
}






static void TransferDataToRegister(String I_reg,String Data){
register reg = new register();
int AChecker = I_reg.compareTo("A");
int BChecker = I_reg.compareTo("B");
int CChecker = I_reg.compareTo("C");
int DChecker = I_reg.compareTo("D");

if (AChecker == 0) {
	reg.A = Double.parseDouble(Data);
}

if (BChecker == 0) {
	reg.B = Double.parseDouble(Data);
}

if (CChecker == 0) {
	reg.C = Double.parseDouble(Data);
}

if (DChecker == 0) {
	reg.D = Double.parseDouble(Data);
}

}


static boolean TransferFromRegisterToMemory(String I_reg,String Address){
	boolean result = true;
register reg = new register();
int AChecker = I_reg.compareTo("A");
int BChecker = I_reg.compareTo("B");
int CChecker = I_reg.compareTo("C");
int DChecker = I_reg.compareTo("D");

String collectedAddress = "";
int count = 1;
while(count < (Address.length() - 1)){
	char workCHAR = Address.charAt(count);
	if (workCHAR == '.') {
		result = false;
		break;
	}
collectedAddress = collectedAddress + workCHAR;
	count++;
}

if (result) {
	
int intAddress = Integer.parseInt(collectedAddress);

if (AChecker == 0) {
	reg.memory[intAddress][1] = Double.toString(reg.A);
}

if (BChecker == 0) {
	reg.memory[intAddress][1] = Double.toString(reg.B);
}

if (CChecker == 0) {
	reg.memory[intAddress][1] = Double.toString(reg.C);
}

if (DChecker == 0) {
	reg.memory[intAddress][1] = Double.toString(reg.D);
}
}
return result;
}




static boolean TransferFromMemoryToRegister(String I_reg,String Address){
	boolean result = true;
register reg = new register();
int AChecker = I_reg.compareTo("A");
int BChecker = I_reg.compareTo("B");
int CChecker = I_reg.compareTo("C");
int DChecker = I_reg.compareTo("D");

String collectedAddress = "";
int count = 1;
while(count < (Address.length() - 1)){
	char workCHAR = Address.charAt(count);
	if (workCHAR == '.') {
		result = false;
		break;
	}
collectedAddress = collectedAddress + workCHAR;
	count++;
}

if (result) {
	
int intAddress = Integer.parseInt(collectedAddress);
String memoryData = reg.memory[intAddress][1];
if (memoryData == null) {
	memoryData = "0";
}
if (AChecker == 0) {
	reg.A = Double.parseDouble(memoryData);
}

if (BChecker == 0) {
	reg.B = Double.parseDouble(memoryData);
}

if (CChecker == 0) {
	reg.C = Double.parseDouble(memoryData);
}

if (DChecker == 0) {
	reg.D = Double.parseDouble(memoryData);
}
}
return result;
}

static void TransferFromRegToReg(String firstReg,String secondReg){
	register reg = new register();
	int A1Checker = firstReg.compareTo("A");
	int A2Checker = secondReg.compareTo("A");
	int B1Checker = firstReg.compareTo("B");
	int B2Checker = secondReg.compareTo("B");
	int C1Checker = firstReg.compareTo("C");
	int C2Checker = secondReg.compareTo("C");
	int D1Checker = firstReg.compareTo("D");
	int D2Checker = secondReg.compareTo("D");

	if (A1Checker == 0 && A2Checker == 0) {
		reg.A = reg.A;
	}

	if (A1Checker == 0 && B2Checker == 0) {
		reg.A = reg.B;
	}

	if (A1Checker == 0 && C2Checker == 0) {
		reg.A = reg.C;
	}

	if (A1Checker == 0 && D2Checker == 0) {
		reg.A = reg.D;
	}


	if (B1Checker == 0 && A2Checker == 0) {
		reg.B = reg.A;
	}

	if (B1Checker == 0 && B2Checker == 0) {
		reg.B = reg.B;
	}

	if (B1Checker == 0 && C2Checker == 0) {
		reg.B = reg.C;
	}

	if (B1Checker == 0 && D2Checker == 0) {
		reg.B = reg.D;
	}


	if (C1Checker == 0 && A2Checker == 0) {
		reg.C = reg.A;
	}

	if (C1Checker == 0 && B2Checker == 0) {
		reg.C = reg.B;
	}

	if (C1Checker == 0 && C2Checker == 0) {
		reg.C = reg.C;
	}

	if (C1Checker == 0 && D2Checker == 0) {
		reg.C = reg.D;
	}



	if (D1Checker == 0 && A2Checker == 0) {
		reg.D = reg.A;
	}

	if (D1Checker == 0 && B2Checker == 0) {
		reg.D = reg.B;
	}

	if (D1Checker == 0 && C2Checker == 0) {
		reg.D = reg.C;
	}

	if (D1Checker == 0 && D2Checker == 0) {
		reg.D = reg.D;
	}
}

static int check_OprandType(String oprand,int lineNumber){
	int result = 0;
int count = 0;
	boolean allChar_digits = false;
	boolean memory_regPointer = false;
	boolean Mregister = false;
	boolean foundFirstOPEN_B = false;
	boolean foundSecondOPEN_B = false;
	boolean B_digit = false;
	boolean registerInBlockBrackets = false;
while(count < oprand.length()){
	char workCHAR = oprand.charAt(count);
	if (workCHAR == '1' || workCHAR == '2' || workCHAR == '3' || workCHAR == '4' || workCHAR == '5' || workCHAR == '6' || workCHAR == '7' || workCHAR == '8' || workCHAR == '9' || workCHAR == '0') {
		allChar_digits = true;
	}

if (count == 0) {
if (workCHAR == '[') {
	foundFirstOPEN_B = true;
}	
}

if (count == (oprand.length()-1)) {
if (workCHAR == ']') {
	foundSecondOPEN_B = true;
}	
}

if (foundFirstOPEN_B) {
if (count != 0 || count != (oprand.length()-1)) {
	if (workCHAR == '1' || workCHAR == '2' || workCHAR == '3' || workCHAR == '4' || workCHAR == '5' || workCHAR == '6' || workCHAR == '7' || workCHAR == '8' || workCHAR == '9' || workCHAR == '0') {
		B_digit = true;
	}
}	
}


	count++;
}

if (count == 3) {
	if (foundFirstOPEN_B) {
if (count != 0 || count != (oprand.length()-1)) {
	if (oprand.charAt(1) == 'A' || oprand.charAt(1) == 'B' || oprand.charAt(1) == 'C' || oprand.charAt(1) == 'D') {
		registerInBlockBrackets = true;
	}
}	
}
}

if (count == 1) {
	if (oprand.charAt(0) == 'A' || oprand.charAt(0) == 'B' || oprand.charAt(0) == 'C' || oprand.charAt(0) == 'D') {
		Mregister = true;
	}
}

if (foundFirstOPEN_B || foundSecondOPEN_B) {	
if (foundFirstOPEN_B && foundSecondOPEN_B) {
	memory_regPointer = true;
}else{
	if (foundFirstOPEN_B) {
		throwErro("Erro at '"+oprand+"': line "+lineNumber+" > expected ']'");
	}

	if (foundSecondOPEN_B) {
		throwErro("Erro at '"+oprand+"': line "+lineNumber+" > expected '['");
	}
}
}


if (allChar_digits) {
	result = 1;
}

if (memory_regPointer) {
	result = 2;
}

if (Mregister) {
	result = 3;
}

if (B_digit) {
	result = 4;
}

if (registerInBlockBrackets) {
	result = 5;
}

return result;

}

static boolean Sparse_checkdoubleOprand(String oprand1,String oprand2,int lineNumber,String oprand_opcode){
	boolean result = false;
	int op1Checker = oprand1.compareTo("");
	int op2Checker = oprand2.compareTo("");
	if (op1Checker == 0 || op2Checker == 0) {
		throwErro("Erro at '"+oprand_opcode+"': line "+lineNumber+" > '"+oprand_opcode+"' must take two oprands.");
		return result;
	}else{
		result = true;
	}

	return result;
}

static void throwErro(String msg){
System.out.println(msg);
}

}