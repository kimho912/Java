public class Clock {
    private int hours;
    private int minutes;
    private int seconds;

    public Clock(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;

        
        if(hours<0 || hours>24 || minutes<0 || minutes>59 || seconds<0 || seconds>59) {
            this.hours = 00;
            this.minutes = 00;
            this.seconds = 00;
        }
    }
    public String toString() {
        return hours + ":" + minutes + ":" + seconds;
    }

}
// @Override
// public String toString() {
//     return hours + ":" + minutes + ":" + seconds;
// }
// private String TwoDigit(int i) {
//     return i<10 ? '0' : '';
// }
// private String rationalize() {
//     return 
// }