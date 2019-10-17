package dolla.parser;

import dolla.DollaData;
import dolla.Ui;
import dolla.command.*;
import dolla.task.LogList;
import sort.SortDescription;

public class DebtsParser extends Parser {
    private static LogList debtList;

    public DebtsParser(String inputLine) {
        super(inputLine);
    }

    @Override
    public Command handleInput(String mode, String inputLine) {
        if (commandToRun.equals("debts")) { //show debt list
            return new ShowListCommand(mode);
        } else if (commandToRun.equals("owe") || commandToRun.equals("borrow")) {
            String type = commandToRun;
            String name = null;
            double amount = 0.0;
            try {
                name = inputArray[1];
                amount = stringToDouble(inputArray[2]);
                description = inputArray[3];
            } catch (IndexOutOfBoundsException e) {
                Ui.printInvalidDebtFormatError();
                return new ErrorCommand();
            } catch (Exception e) {
                return new ErrorCommand();
            }
            return new AddDebtsCommand(type, name, amount, description);
        } else if (commandToRun.equals("sort")) {
            return new SortCommand(mode, inputArray[1]);
        } else {
            return invalidCommand();
        }
    }
}
