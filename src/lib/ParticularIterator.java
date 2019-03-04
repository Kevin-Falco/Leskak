package lib;

import java.util.Iterator;
import java.util.List;

public class  ParticularIterator<T extends Cell> implements Iterator<T> {
    private int count = 0;
    private List<Cell> cells;
    private Class<T> type;

    public ParticularIterator(Class<T> type, List<Cell> cells) {
        this.type = type;
        this.cells = cells;
    }

    @Override
    public boolean hasNext() {
        for(int i = count; i < cells.size(); ++i) {
                if (this.type.isInstance(cells.get(i))){
                    return true;
                }
        }
        this.count = 0;
        return false;
    }

    @Override
    public T next() {
        if (hasNext()) {
            for (int i = count; i < cells.size(); ++i) {
                if (this.type.isInstance(cells.get(i))) {
                    ++this.count;
                    return (T)cells.get(i);
                }
            }
        }
        return null;
    }

    public void reset(){
        this.count = 0;
    }
}
