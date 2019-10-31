package dolla.command.modify;

import dolla.DollaData;
import dolla.command.Command;
import dolla.task.Entry;
import dolla.task.Record;
import dolla.ui.ModifyUi;

import java.time.LocalDate;

public class PartialModifyEntryCommand extends Command {

    private int index;
    private String type;
    private double amount;
    private String description;
    private LocalDate date;

    private static final String MODE_ENTRY = "entry";

    public PartialModifyEntryCommand(int recordNum, String type, double amount, String description, LocalDate date) {
        this.index = recordNum-1;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    @Override
    public void execute(DollaData dollaData) {
        dollaData.prepForModify(MODE_ENTRY, index);
        Record originalEntry = dollaData.getRecordFromList(MODE_ENTRY, index);
        overwriteComponents(originalEntry);
        Entry newEntry = new Entry(type, amount, description, date);
        dollaData.modifyRecordList(newEntry);
        ModifyUi.echoModifyRecord(newEntry);
        dollaData.updateMode("entry");
    }

    private void overwriteComponents(Record ogEntry) {
        if (type == null) {
            type = ogEntry.getType();
        }
        if (amount == -1) {
            amount = ogEntry.getAmount();
        }
        if (description == null) {
            description = ogEntry.getDescription();
        }
        if (date == null) {
            date = ogEntry.getDate();
        }
    }

    @Override
    public String getCommandInfo() {
        return null;
    }
}
