package sort;

import dolla.Ui;
import dolla.task.Entry;
import dolla.task.Log;

import java.util.ArrayList;
import java.util.Collections;

public class SortDescription extends Sort {

    public SortDescription(ArrayList<Log> list) {
        super(list);
        Collections.sort(list,DescriptionComparator.DescComparator());
        Ui.printSortedList(list,"description");
    }
}
