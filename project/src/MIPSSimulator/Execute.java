package MIPSSimulator;
import java.util.*;

/**
 * Execute handles the processing of the execute stage of pipelined execution.
 */
class Execute extends Stage {

    /**
	 * creates an Execute object.
	 */
      public Execute() {
           super();
      } 

	/**
	 * Processes the current instruction.  Implements the execute stage of a pipelined processor.
	 * Uses a vector of instructions, the Fetch stage and Decode stage, as well as the ListBoxes.
	 * @see Instruction
	 * @see Fetch
	 * @see Decode
	 * @see ListBoxes
	 */
      @SuppressWarnings("rawtypes")
	public void step(Vector Instructions, Fetch myFetch, Decode myDecode, ListBoxes lb) {            
 
          //WRITE CODE FOR EXECUTE IMPLEMENTATION OF EACH OPCODE
    	  //START HERE
   }

	/**
	 * returns a string representation of the current instructions and the results of its processing.
	 */
   public String toString() {
      String temp;
 
      if (myInstruction.flush == true) {
           temp = "FLUSHED: \n" + myInstruction + "\n";
           return temp;
      }
  
      switch(myInstruction.opcode) {
        case 0:
           temp = myInstruction + "\n";
           break;
        case 35:  // LW
        case 43:  // SW
            temp = "PC = " +Integer.toString(PC) + ":\n" + myInstruction + "\n" +
            "Memory\nAddress= " + Integer.toString(myInstruction.rdValue) + "\n";
            break;
        case 70:  // BEQ
            if (myInstruction.rsValue == myInstruction.rtValue)
                temp = "PC = " +Integer.toString(PC) + ":\n" + myInstruction + "\n"+
                       "Result:\nTake branch\n";
            else
                temp = "PC = " +Integer.toString(PC) + ":\n" + myInstruction + "\n"+
                       "Result:\nDon't take\nbranch\n";                  
            break;
        case 71:  // BNE
            if (myInstruction.rsValue != myInstruction.rtValue)
                temp = "PC = " +Integer.toString(PC) + ":\n" + myInstruction + "\n"+
                       "Result:\nTake branch\n";
            else
                temp = "PC = " +Integer.toString(PC) + ":\n" + myInstruction + "\n"+
                       "Result:\nDon't take\nbranch\n";                  
            break;
        default:  // R-type instructions
            temp = "PC = " +Integer.toString(PC) + ":\n" + myInstruction + "\n" +
            "ALU Operand 1 = " + Integer.toString(myInstruction.rsValue) + "\n" +
            "ALU Operand 2 = " + Integer.toString(myInstruction.rtValue) + "\n" +
            "ALU Result = " + Integer.toString(myInstruction.rdValue) + "\n";
      }
      return temp;
   }

}

