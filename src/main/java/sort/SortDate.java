package sort;

import dolla.Ui;
import dolla.task.Entry;
import dolla.task.Log;

import java.util.ArrayList;
import java.util.Collections;

public class SortDate extends Sort{

    public SortDate(ArrayList<Log> list) {
        super(list);
        Collections.sort(list,DateComparator.DateComparator());
        Ui.printSortedList(list,"date");
    }
}
