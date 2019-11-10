package dolla.dolladatastub;

import dolla.model.BillList;
import dolla.model.DebtList;
import dolla.model.DollaData;
import dolla.model.EntryList;
import dolla.model.LimitList;
import dolla.model.Record;
import dolla.model.ShortcutList;

import java.util.ArrayList;

//@@author omupenguin
public class DollaDataLimitStub1 extends DollaData implements LimitArrayListInterface {

    /**
     * Creates an instance of DollaData to store and manipulate data.
     */
    public DollaDataLimitStub1() {
        this.entryList = new EntryList(new ArrayList<Record>());
        this.limitList = new LimitList(createNewLimitArrayList());
        this.debtList = new DebtList(new ArrayList<Record>());
        this.shortcutList = new ShortcutList(new ArrayList<Record>());
        this.billList = new BillList(new ArrayList<Record>());
    }
}
