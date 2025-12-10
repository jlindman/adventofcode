import java.util.List;
import java.util.Stack;

public class Day8
{
    static final String testInput = """
        162,817,812
        57,618,57
        906,360,560
        592,479,940
        352,342,300
        466,668,158
        542,29,236
        431,825,988
        739,650,466
        52,470,668
        216,146,977
        819,987,18
        117,168,530
        805,96,715
        346,949,466
        970,615,88
        941,993,340
        862,61,35
        984,92,344
        425,690,689
    """;

    static String input = """
            """;

    public static void main(String[] args)
    {
        Day8 event = new Day8();
        event.solvePart1();
        //event.solvePart2();
    }

    static class Box
    {
        int x, y, z;
        public Box(String[] c)
        {
            x = Integer.parseInt(c[0]);
            y = Integer.parseInt(c[1]);
            z = Integer.parseInt(c[2]);
        }

        public int distance(Box box)
        {
            return 1;
        }
    }

    static class BoxPairs implements Comparable<BoxPairs>{
        int distance;
        Box box1;
        Box box2;

        public BoxPairs(Box box1, Box box2)
        {
            this.distance = 1;
            this.box1 = box1;
            this.box2 = box2;
        }

        @Override
        public int compareTo(BoxPairs other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    private void solvePart1()
    {
        List<Box> boxes = testInput.lines().map(s -> s.trim().split(",")).map(Box::new).toList();

        List<BoxPairs> boxPairs = new java.util.ArrayList<>();

        for (Box box1 : boxes) {
            for (Box box2 : boxes) {
                if (box1 != box2) {
                    boxPairs.add(new BoxPairs(box1, box2));

                }
            }
        }

    }

    private void solvePart2()
    {
    }
}
