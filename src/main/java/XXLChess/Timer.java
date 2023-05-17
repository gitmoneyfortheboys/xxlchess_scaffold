package XXLChess;

public class Timer {
    private int time;
    private int increment;

    public Timer(int time, int increment) {
        this.time = time;
        this.increment = increment;
    }

    public void tick() {
        if (time > 0) {
            time--;
        } else {
            throw new RuntimeException("Time's up!");
        }
    }

    public void increment() {
        time += increment;
    }

    public int getTime() {
        return time;
    }
}
