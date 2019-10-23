package dolla.command;

import dolla.DollaData;
import dolla.action.redo;
import dolla.action.repeat;
import dolla.action.undo;
import dolla.parser.DebtsParser;
import dolla.parser.EntryParser;
import dolla.parser.MainParser;

public class AddActionCommand extends Command{
    private String mode;
    private String command;
    private String userInput;
    private int prevPosition;

    public AddActionCommand(String mode, String command) {
        this.mode = mode;
        this.command = command;
    }

    private void undoCommand() {
        userInput = undo.processCommand(mode);
        String[] parser = userInput.split(" ",2);
        if(parser[0].equals("remove")) {
            userInput = parser[0] + " " + parser[1];
        } else {
            prevPosition = Integer.parseInt(parser[0]);
            userInput = parser[1];
        }
    }

    private void redoCommand() {
        userInput = redo.processRedo();
    }

    @Override
    public void execute(DollaData dollaData) throws Exception {
        switch (command) {
        case "undo":
            undoCommand();
            setUndoPrevPosition();
            break;
        case "redo":
            setRedo();
            redo.redoReady(mode);
            redoCommand();
            break;
        case "repeat":
            userInput = repeat.getUserInput(mode);
            break;
        }
        Command c = MainParser.handleInput(mode, userInput);
        c.execute(dollaData);
        resetPrevPosition();
        resetRedo();
    }

    private void setUndoPrevPosition() {
        if(mode.equals("debt")) {
            DebtsParser.setPrevPosition(prevPosition);
        } else if(mode.equals("entry")) {
            EntryParser.setPrePosition(prevPosition);
        }
    }

    private void resetPrevPosition() {
        if(mode.equals("debt")) {
            DebtsParser.resetPrevPosition();
        } else if(mode.equals("entry")) {
            EntryParser.resetPrePosition();
        }
    }

    private void setRedo() {
        if(mode.equals("debt")) {
            DebtsParser.setRedoFlag();
        } else if(mode.equals("entry")) {
            EntryParser.setRedoFlag();
        }
    }

    private void resetRedo() {
        if(mode.equals("debt")) {
            DebtsParser.resetRedoFlag();
        } else if(mode.equals("entry")) {
            EntryParser.resetRedoFlag();
        }
    }
}
