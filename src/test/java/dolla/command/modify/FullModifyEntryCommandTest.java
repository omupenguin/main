package dolla.command.modify;

import dolla.dolladatastub.DollaDataEntryStub1;
import dolla.ModeStringList;
import dolla.model.DollaData;
import dolla.model.Entry;
import dolla.model.Record;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author omupenguin
public class FullModifyEntryCommandTest implements ModeStringList {

    @Test
    public void execute() {
        DollaData dollaData = new DollaDataEntryStub1();

        String newType = "expense";
        double newAmount = 999;
        String newDesc = "WOW";
        LocalDate newDate = LocalDate.parse("2000-11-11");
        final Entry newEntry = new Entry(newType, newAmount, newDesc, newDate);

        int indexToModify = 1;
        String currMode = MODE_ENTRY;
        dollaData.updateMode("modify " + currMode);
        dollaData.prepForModify(currMode, indexToModify);

        FullModifyEntryCommand modifyCommand = new FullModifyEntryCommand(newType, newAmount, newDesc, newDate);
        modifyCommand.execute(dollaData);

        Record cmpEntry = dollaData.getRecordFromList(MODE_ENTRY, indexToModify);
        assertEquals(newEntry.getRecordDetail(), cmpEntry.getRecordDetail());
    }
}
