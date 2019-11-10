package dolla.dolladatastub;

import dolla.model.Debt;
import dolla.model.Record;
import dolla.parser.ParserStringList;

import java.time.LocalDate;
import java.util.ArrayList;

//@@author omupenguin
/**
 * Allows for quick creation of an ArrayList of debts for initialising DollaData.
 */
public interface DebtArrayListInterface extends ParserStringList {

    private Debt createNewDebt1() {
        return new Debt("owe", "tatayu", 30,
                "supper", LocalDate.parse("2019-11-01"));
    }

    private Debt createNewDebt2() {
        return new Debt("borrow", "xx", 10,
                "ticket", LocalDate.parse("2019-10-27"));
    }

    private Debt createNewDebt3() {
        return new Debt("borrow", "Eve", 3,
                "tea", LocalDate.parse("2019-12-02"));
    }

    /**
     * Returns an ArrayList of debts.
     * @return an ArrayList of debts.
     */
    default ArrayList<Record> createNewDebtArrayList() {
        ArrayList<Record> newRecordList = new ArrayList<Record>();
        newRecordList.add(createNewDebt1());
        newRecordList.add(createNewDebt2());
        newRecordList.add(createNewDebt3());
        return newRecordList;
    }
}
