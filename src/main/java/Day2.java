import java.util.Arrays;
import java.util.List;

public class Day2
{
    static final String testInput = """
        11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124
    """;

    static String input = """
82853534-82916516,2551046-2603239,805115-902166,3643-7668,4444323719-4444553231,704059-804093,32055-104187,7767164-7799624,25-61,636-1297,419403897-419438690,66-143,152-241,965984-1044801,1-19,376884-573880,9440956-9477161,607805-671086,255-572,3526071225-3526194326,39361322-39455443,63281363-63350881,187662-239652,240754-342269,9371-26138,1720-2729,922545-957329,3477773-3688087,104549-119841
            """;

    public static void main(String[] args)
    {
        Day2 event = new Day2();
        //event.solvePart1();
        event.solvePart2();
    }

    record Range(long from, long to) {};

    private void solvePart1()
    {
        List<Range> ranges = Arrays.stream(input.trim().split(","))
                                   .map(s -> s.split("-"))
                                   .map(arr -> new Range(Long.parseLong(arr[0]), Long.parseLong(arr[1])))
                                   .toList();
        long total = 0;

        for (Range range : ranges) {
            for (long i = range.from; i <= range.to; i++) {
                String number = Long.toString(i);
                if (number.length() % 2 == 0 && number.charAt(0) != '0') {
                    String firstHalf = number.substring(0, number.length()/2);
                    String secondHalf = number.substring(number.length()/2);
                    System.out.println("Testing number " + number + ": firstHalf = " + firstHalf + ", secondHalf = " + secondHalf);
                    if (firstHalf.equals(secondHalf)) {
                        System.out.println("** Invalid ID: " + number);
                        total += Long.parseLong(number);
                    }
                }
            }
        }
        System.out.println("Sum: " + total);
    }

    private void solvePart2()
    {
        List<Range> ranges = Arrays.stream(input.trim().split(","))
                                   .map(s -> s.split("-"))
                                   .map(arr -> new Range(Long.parseLong(arr[0]), Long.parseLong(arr[1])))
                                   .toList();
        long total = 0;

        for (Range range : ranges) {
            for (long i = range.from; i <= range.to; i++) {
                String number = Long.toString(i);
                if (number.charAt(0) != '0') {
                    for (int j=1; j <= number.length()/2; j++) {
                        String part = number.substring(0, j);
                        StringBuilder sb = new StringBuilder();
                        while (sb.length() + part.length() <= number.length()) {
                            sb.append(part);
                        }
                        if (number.contentEquals(sb)) {
                            System.out.println("** Invalid ID: " + number);
                            total += Long.parseLong(number);
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("Sum: " + total);
    }
}
