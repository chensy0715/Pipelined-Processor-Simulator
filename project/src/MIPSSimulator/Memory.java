package MIPSSimulator;
/**
 * Memory handles the operation of the memory stage of pipelined execution.
 */
class Memory extends Stage {
	/**
	 * internal variable that holds the current memory address.
	 */
    private int memAddr; 

    /**
	 * creates a Memory object.
	 */
    public Memory() {
         super();
         memAddr = -1;
    }

    /**
	 * process the current instruction.  Implements the functionality of the memory stage of pipelined execution.
	 * Uses a MemoryType object as well as ListBoxes.
	 * @see MemoryType
	 * @see ListBoxes
	 */
    public void step(MemoryType mainMemory, ListBoxes lb ) { 
            
          memAddr = myInstruction.rdValue;  
      
          if (myInstruction.flush == true)
          {
          	myInstruction.wbFlush = true;
             return;
          }
          else
          	myInstruction.wbFlush = false;

          //WRITE CODE FOR IMPLEMENTING STORE WORD AND LOAD WORD INSTRUCTIONS    
          //START HERE
    }

   /**
	 * returns a string representation of the current instruction and the results of its processing.
	 */
   public String toString() {
      String temp;
   
      if (myInstruction.flush == true) {
           temp = "FLUSHED: \n" + myInstruction + "\n";
           return temp;
      }

      switch(myInstruction.opcode) {
        case 0:  // NOP
            temp = myInstruction + "\n";
            break;
        case 35:  // LW
            temp = "PC = " +Integer.toString(PC) + ":\n" + myInstruction + "\n" +
            "Load Word:\nAddress= " + Integer.toString(memAddr) + "\n" +
            "Value= " + Integer.toString(myInstruction.rdValue) + "\n";
            break;
        case 43:  // SW
            temp = "PC = " +Integer.toString(PC) + ":\n" + myInstruction + "\n" +
            "Save Word:\nAddress= " + Integer.toString(memAddr) + "\n" +
            "Value= " + Integer.toString(myInstruction.rtValue) + "\n";
            break;
        default:  // R-type instructions
            temp = "PC = " +Integer.toString(PC) + ":\n" + myInstruction + "\n";
                   
      }
      return temp;
   }

}
