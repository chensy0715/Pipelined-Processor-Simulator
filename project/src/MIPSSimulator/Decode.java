package MIPSSimulator;
import java.util.*;

/**
 * Decode handles the operation for the decode stage of pipelined execution.
 */
class Decode extends Stage {
	/**
	 * holds the Rd of the last three instruction to detect hazards
	 */
    @SuppressWarnings("rawtypes")
	private Vector hazardList;
    /**
	 * placeholder for an instruction that is stalled.
	 */
    private Instruction instructionSave;
    /**
	 * saves the program counter for an instruction that is stalled.
	 */
    private int savePC;
    /**
	 * indicates that a stall has been issued.
	 */
    public boolean isStall;
    /**
	 * indicates that a stall has been detected.
	 */
    public boolean stallflag;
    /**
	 * indicates whether forwarding is used.
	 */
    public boolean forwarding;
    /**
	 * table used to predict branches (unused).
	 */
    @SuppressWarnings("rawtypes")
	public Vector branchTable; 

    /**
	 * initialzes the fields of the object
     *
	 */
    public boolean branchPrediction;


    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Decode() {
       super();
       instructionSave = new Instruction("NOP");
       isStall = false;       
       hazardList = new Vector(3);
       branchTable = new Vector();
       //Vector to store the Hazards
       hazardList.addElement(new Integer(0));
       hazardList.addElement(new Integer(0));
       hazardList.addElement(new Integer(0));
    }

    @SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	public void step(Vector Instructions, MemoryType regFile, 
                     Memory myMemory, WriteBack myWriteBack,
                     Fetch myFetch, Execute myExecute, ListBoxes lb) {

         String str;

        // if last instruction was stalled, recall stalled instruction

        if (PC==Instructions.size())
           PC = -9;  // this is actually a NOP received from Fetch

        if (isStall == true) {                             
            myInstruction = instructionSave;   
            PC = savePC;
        }
              
        stallflag = false;

        if (((myInstruction.opcode != 0) &&
                (!myInstruction.flush) &&
                (myInstruction.rs ==
                        ((Integer)hazardList.elementAt(0)).intValue())) ||
                ((myInstruction.opcode != 0) &&
                        (!myInstruction.flush) &&
                        (myInstruction.rs ==
                                ((Integer)hazardList.elementAt(1)).intValue())) || (
                (myInstruction.opcode != 0) &&
                        (!myInstruction.flush) &&
                        (myInstruction.rs ==
                                ((Integer)hazardList.elementAt(2)).intValue()))) {
            if ((myInstruction.opcode != 0) && (!myInstruction.flush))
            {
                stallflag = true;
            }
        }


        // WRITE CODE TO CHECK FOR DATA HAZARD FOR RS AND MAKE stallflag TO TRUE IN CASE OF A HAZARD DETECTION
        // START HERE
        
        //CHECK FOR FORWARDING IF HAZARD DETECTED FOR RS
        if ((forwarding==true) && (stallflag==true)) {
//            if ((myInstruction.rs == myInstruction.rd) && (myInstruction.rs != myInstruction.rd)) {
//
//            }
//            switch (myInstruction.opcode) {
//                case 0:
//                case 35:
//                case 43:
//                case 70:
//                case 71:
//                    break;
//                default:
//                    myInstruction.rsValue = myInstruction.rdValue;
//                    stallflag = false;
//                    myInstruction.forwardRsFlag = true;
//                    String str2 = "Result forwarded from EXE: $" + myInstruction.rs + "=" +
//                            myInstruction.rsValue + ".";
//                    ListBoxes.Messages.add(str2, 0);
//                    break;
//
//                if ((myInstruction.rs == myInstruction.rd) &&
//                        (myInstruction.rs != myInstruction.rd)) {
//
//                    myInstruction.rsValue = myInstruction.rdValue;
//
//                    stallflag = false;
//                    myInstruction.forwardRsFlag = true;
//                    String str1 = "Result forwarded from MEM: $" + myInstruction.rs + "=" +
//                            myInstruction.rsValue + ".";
//                    ListBoxes.Messages.add(str1, 0);
//                }
//
//                break;
//            }

        	 // CHECK IF VALUE READY AT END OF LAST EXECUTE STAGE (NOW MEMORY STAGE) AND MODIFY THE LISTBOX MESSAGE APPROPRIATELY
        	 // START HERE
                }
           

        // WRITE CODE TO CHECK FOR DATA HAZARD FOR RT AND MAKE stallflag TO TRUE IN CASE OF A HAZARD DETECTION
        // START HERE

      //CHECK FOR FORWARDING IF HAZARD DETECTED FOR RT
        if ((!myInstruction.isImmediate())&&(forwarding==true)&&(stallflag==true)) {
        	// CHECK IF VALUE READY AT END OF LAST EXECUTE STAGE (NOW MEMORY STAGE)
        	//START HERE
                  }
             

        if (stallflag == true) {
            // DATA hazard found, so issue a stall
            if (isStall == false) {
                isStall = true;
                instructionSave = myInstruction;
                savePC = PC;
            }
            myInstruction = new Instruction("NOP");     
            myInstruction.rsValue = 0;
            myInstruction.rtValue = 0; 
            PC = -9;
        } else {            
            // no new hazards; check to see if last instruction stalled, and clean up if so
            if (isStall == true) {
                 isStall = false;
                 myInstruction = instructionSave;
            }
            
       }        


       // assume R-type--read source operand values from Reg File
       
        if (myInstruction.forwardRsFlag == false) 
            myInstruction.rsValue = regFile.getValue( myInstruction.rs );
        if (myInstruction.forwardRtFlag == false) {       
            if (myInstruction.isImmediate())  
                  // assign immediate value
                  myInstruction.rtValue = myInstruction.immediate;
            else
                  // read from register
                  myInstruction.rtValue = regFile.getValue( myInstruction.rt );
        }



       // update hazard list (the destination register number at each remaining stage)
       // to match instructions as they propagate through the stages    

       hazardList.setElementAt( hazardList.elementAt(1), 2);
       hazardList.setElementAt( hazardList.elementAt(0), 1);
       hazardList.setElementAt( new Integer(myInstruction.rd), 0);      

       if (isStall == true) {
             // throw event to tell user a stall has been issued
             str = "Stall issued for instruction "+savePC+".";
             lb.Messages.add(str, 0);
       }
    }

   /**
	 * returns a string representation of the stage's current instruction and the results of its processing.
	 */
   public String toString() {
      String temp;
 
      if (myInstruction.flush == true) {
           temp = "FLUSHED: \n" + myInstruction + "\n";
           return temp;
      }
 
      if (PC >= 0) { 
         temp = "PC = " +Integer.toString(PC) + ":\n" + myInstruction + "\n" +
        		"ALU Operand 1 = " + Integer.toString(myInstruction.rsValue) + "\n" +
        		"ALU Operand 2 = " + Integer.toString(myInstruction.rtValue) + "\n";
      } else {
         temp = myInstruction + "\n";
         if (isStall)
            temp += "Stalled:\n" + Integer.toString(savePC) + ":  " +
                    instructionSave + "\n";
      }

      return temp;
   }
   
}

