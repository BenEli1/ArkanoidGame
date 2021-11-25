package listeners;
/**
 * @author Ben-Binyamin Eli
 * This represents Counter class that holds a counter and updates it accordiangly to the methods.
 */
public class Counter {
    private int count;
    /**
     * constructor.
     * @param count number.
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * add number to current count.
     * @param number an integer nubmer
     */
    public void increase(int number) {
        this.count = this.count + number;
    }
    /**
     * substract number to current count.
     * @param number an integer nubmer
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }

    /**
     * return current count.
     * @return the counter.
     */
    public int getValue() {
        return this.count;
    }
}

