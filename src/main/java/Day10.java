import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Day10
{
    static final String testInput = """
[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}
    """;

    static String input = """
""";

    public static void main(String[] args)
    {
        Day10 event = new Day10();
        event.parseInput(testInput);
        event.solvePart1();
        //event.solvePart2();
    }

    static class LightPanel {
        int state = 0;
        private final int numberOfLights;
        int goalState;
        List<Integer> buttons;

        LightPanel(int numberOfLights, int goalState, List<Integer> buttons)
        {
            this.numberOfLights = numberOfLights;
            this.goalState = goalState;
            this.buttons = buttons;
        }

        public boolean pressButton(int button)
        {
            state = state ^ button;
            return state == goalState;
        }

        public void reset()
        {
            state = 0;
        }

        @Override
        public String toString()
        {
            return "Lights: " + toBinaryString(goalState) + ", Buttons: " + buttons.stream().map(this::toBinaryString).toList();
        }

        private String toBinaryString(int number)
        {
            return String.format("%" + numberOfLights + "s", Integer.toBinaryString(number)).replace(' ', '0');
        }
    }

    List<LightPanel> lightPanels = new ArrayList<>();

    private void parseInput(String input)
    {
        input.lines().forEach(line -> {
            // Extract lights string (inside [])
            String lightsString = line.substring(line.indexOf('[') + 1, line.indexOf(']'));
            int lights = getLightsAsIntFromString(lightsString);

            // Extract all parentheses groups
            Pattern pattern = Pattern.compile("\\(([^)]+)\\)");
            var matcher = pattern.matcher(line);

            var buttons = new java.util.ArrayList<Integer>();
            while (matcher.find()) {
                String group = matcher.group(1);
                System.out.println("group = " + group);
                List<Integer> ints = Arrays.stream(group.split(",")).map(Integer::parseInt).toList();
                char[] charArray = new char[lightsString.length()];
                Arrays.fill(charArray, '0');
                for (int i : ints) {
                    charArray[i] = '1';
                }
                System.out.println("charArray = " + String.valueOf(charArray));
                buttons.add(Integer.parseInt(String.valueOf(charArray), 2));
            }

            System.out.println("Buttons: " + buttons);
            lightPanels.add(new LightPanel(lightsString.length(), lights, buttons));

            // The light and buttons are now numbers
            // Pressing a button will mean: lights ^ button (XOR)

            System.out.println("Light panels:");
            lightPanels.forEach(System.out::println);
        });
    }

    private static int getLightsAsIntFromString(String lightsString)
    {
        return Integer.parseInt(lightsString.replaceAll("\\.", "0").replaceAll("#", "1"), 2);
    }

    private void solvePart1()
    {
        LightPanel panel = lightPanels.get(0);

        int maxPresses = 3;
        for (int numberOfButtons = 1; numberOfButtons <= maxPresses; numberOfButtons++) {
            int[] buttons = new int[numberOfButtons];

            for (int buttonIdx = 0; buttonIdx < numberOfButtons; buttonIdx++) {
                System.out.println("buttonIdx = " + buttonIdx);

                for (int currentButton = 0; currentButton < numberOfButtons; currentButton++) {
                    for (int i = 0; i < panel.buttons.size(); i++) {
                        System.out.println(" currentButton = " + currentButton +", i = " + i);
                        buttons[currentButton] = i; // panel.buttons.get(i);
                        System.out.println("Buttons: " + Arrays.toString(buttons));
                    }
                }
            }
        }
    }
}
