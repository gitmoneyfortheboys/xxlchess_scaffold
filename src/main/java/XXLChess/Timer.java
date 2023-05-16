package XXLChess;

public class Timer {
    private int seconds;
    private int increment;

    public Timer(int seconds, int increment) {
        this.seconds = seconds;
        this.increment = increment;
    }

    public void decrement() {
        if (this.seconds > 0) {
            this.seconds--;
        }
    }

    public void increment() {
        this.seconds += this.increment;
    }

    @Override
    public String toString() {
        return String.format("%d:%02d", seconds / 60, seconds % 60);
    }
}

