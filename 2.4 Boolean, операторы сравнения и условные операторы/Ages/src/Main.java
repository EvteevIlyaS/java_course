public class Main {
    public static void main(String[] args) {
        int vasyaAge = 5;
        int katyaAge = 65;
        int mishaAge = 25;

        int min = vasyaAge;
        int max = vasyaAge;
        int middle = vasyaAge;

        min = min > katyaAge ? katyaAge : min;
        min = min > mishaAge ? mishaAge : min;

        max = max < katyaAge ? katyaAge : max;
        max = max < mishaAge ? mishaAge : max;

        middle = katyaAge > min && katyaAge < max ? katyaAge : middle;
        middle = mishaAge > min && mishaAge < max ? mishaAge : middle;

        System.out.println(min + " " + middle + " " + max);
        System.out.println();
    }
}
