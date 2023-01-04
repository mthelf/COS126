public class NoonSnooze {
    public static void main(String[] args) {

        // take in string and store as int
        int n = Integer.parseInt(args[0]);

        // define hrs and minutes, hrs start at 12
        int hrs = (n / 60);
        int mins = (n % 60);

        // define string variable for am or pm time
        String amPm;

        // starts at 12pm, check if hours are in first 12 and set to PM
        if ((hrs % 24) < 12) {
            amPm = "pm";
        }
        // otherwise, must be am
        else {
            amPm = "am";
        }

        // mods hrs to keep between 0 and 11
        hrs = hrs % 12;

        // sets 0 mod to 12
        if (hrs == 0) {
            hrs = 12;
        }

        // check if mins are less than 10, if so add frontal 0 for 
        if (mins < 10) {
            String sinceSnooze = hrs + ":0" + mins + amPm;
            System.out.println(sinceSnooze);
        }
        else {
            String sinceSnooze = hrs + ":" + mins + amPm;
            System.out.println(sinceSnooze);
        }

    }
}
