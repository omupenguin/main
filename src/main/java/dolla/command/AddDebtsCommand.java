package dolla.command;

import dolla.DollaData;
import dolla.Ui;
import dolla.action.redo;
import dolla.action.undo;
import dolla.task.Debt;


public class AddDebtsCommand extends Command {

    private String type;
    private String name;
    private double amount;
    private String description;
    private int prevPosition;

    public AddDebtsCommand(String type, String name, double amount, String description, int prePosition) { //prePosition is -1 by default
        this.type = type;
        this.name = name;
        this.amount = amount;
        this.description = description;
        this.prevPosition = prePosition;
    }

    @Override
    public void execute(DollaData dollaData) {
        String mode = "debt";
        Debt newDebt = new Debt(type, name, amount, description);
        index = dollaData.getLogList(mode).size();
        if(prevPosition == -1) {
            dollaData.addToLogList(mode, newDebt);
            undo.removeCommand(mode,index);
            redo.clearRedo(mode);
        } else if(prevPosition == -2) {
            dollaData.addToLogList(mode, newDebt);
            undo.removeCommand(mode,index);
            prevPosition = -1;
        } else {
            dollaData.addToPrevPosition(mode, newDebt, prevPosition);
            redo.removeCommand(mode, prevPosition);
            prevPosition = -1;
        }
        Ui.echoAddDebt(newDebt);
    }
}
