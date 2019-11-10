package dolla.command.modify;

import dolla.model.DollaData;
import dolla.model.Entry;
import dolla.model.Record;
import dolla.ui.ModifyUi;

import java.time.LocalDate;

//@@author omupenguin
public class PartialModifyEntryCommand extends ModifyEntryCommand {

    /**
     * Instantiates a new PartialModifyEntryCommand.
     * @param recordNum number of entry in list to modify.
     * @param type type of entry.
     * @param amount of money.
     * @param description description.
     * @param date date.
     */
    public PartialModifyEntryCommand(int recordNum, String type, double amount, String description, LocalDate date) {
        this.index = recordNum - 1;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    @Override
    public void execute(DollaData dollaData) {
        dollaData.prepForModify(MODE_ENTRY, index);
        if (isIndexInList(dollaData.getRecordListObj(MODE_ENTRY), MODE_ENTRY)) {
            Record originalEntry = dollaData.getRecordFromList(MODE_ENTRY, index);
            overwriteComponents(originalEntry);
            updateUndoState(dollaData);
            Entry newEntry = new Entry(type, amount, description, date);
            dollaData.modifyRecordList(newEntry);
            ModifyUi.echoModifyRecord(newEntry);
            dollaData.updateMode(MODE_ENTRY);
        } else {
            return;
        }
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
