package MIPSSimulator;
import java.awt.*;

/**
 * The UI class contains the components involving user interaction with the simulator.
 */
@SuppressWarnings("serial")
public class UI extends Panel
{
	/**
	 * used to inform the simulator to step through one cycle.
	 */
	Button step = new Button("Step");
	/**
	 * resets the simulator.
	 */
	Button reset = new Button("Reset");
	/**
	 * attempts to add the instruction entered in the textfield tfai.
	 */
	Button addinst = new Button("Add Instruction");
	/**
	 * textfield to enter instruction.
	 */
	TextField tfai = new TextField();
	/**
	 * resets the simulator and clears all entered instructions.
	 */
	Button clear = new Button("Clear Instructions");
	/**
	 * deletes an instruction that is highlighted in the Instruction List.
	 * @see ListBoxes
	 */
	Button delete = new Button("Delete Instruction");
	/**
	 * changes an instruction that is highlighted in the Instruction List to the one entered in the textfield tfedit.
	 * @see ListBoxes
	 */
	Button edit = new Button("Change Instruction");
	/**
	 * displays a help screen.
	 */
	Button help = new Button("Help!");
	/**
	 * sets forwarding.
	 */
	Checkbox forward = new Checkbox("Forwarding");
	/**
	 * textfield used to enter an instruction that will replace a highlighted instruction from the Instruction List.
	 * @see ListBoxes
	 */
	TextField tfedit = new TextField();

	/**
	 * performs the layout for the object.
	 */
	UI()
	{
		setLayout(new GridLayout(4, 4, 5, 5));
		
		Canvas filler1 = new Canvas();
		Canvas filler2 = new Canvas();
		Canvas filler3 = new Canvas();
		Canvas filler4 = new Canvas();
		
		add(filler1);
		add(forward);

		add(filler2);
		add(filler3);
		add(filler2);
		add(filler4);
		add(reset);

		add(step);

		
		add(clear);
		add(delete);
		add(addinst);
		add(tfai);
		
		add(help);
		add(filler3);
		add(edit);
		add(tfedit);
	}
	
	/**
	 * returns a boolean indicating whether forwarding has been checked.
	 */
	public boolean doForwarding()
	{
		return forward.getState();
	}
	
}
