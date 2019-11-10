package dolla.command.modify;

import dolla.model.DollaData;
import dolla.command.Command;
import dolla.ui.ModifyUi;

//@@author omupenguin
public class RevertFromModifyCommand extends Command {

    @Override
    public void execute(DollaData dollaData) {
        dollaData.updateMode(dollaData.getPrevMode());
        ModifyUi.printCancelModifyMsg();
    }

    @Override
    public String getCommandInfo() {
        return null;
    }

}
