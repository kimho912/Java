public class Clock {
    private int hours;
    private int minutes;
    private int seconds;

    public Clock(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }
    public String toString() {
        return hours + ":" + minutes + ":" + seconds;
    }
}
// @Override
// public String toString() {
//     return hours + ":" + minutes + ":" + seconds;
// }