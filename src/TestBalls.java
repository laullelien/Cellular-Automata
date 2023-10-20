public class TestBalls {
    public static void main(String[] args) {
        Balls balls = new Balls();
        System.out.println("");
        System.out.println("Balls initialisation:");
        System.out.println(balls);
        balls.translate(10, -60);
        System.out.println("");
        System.out.println("Translation by (10, -60):");
        System.out.println(balls);
        balls.reInit();
        System.out.println("");
        System.out.println("Balls Reinitialisation:");
        System.out.println(balls);
        System.out.println("");
    }
}
