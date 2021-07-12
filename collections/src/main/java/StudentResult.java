public class StudentResult {

  private final String name;
  private final int score;

  public String getName() {
    return name;
  }

  public int getScore() {
    return score;
  }

  public StudentResult(String name, int score) {

    this.name = name;
    this.score = score;
  }

  public StudentResult() {
    this.name = "";
    this.score = 0;
  }
}
