public class StudentResultComparable
    extends StudentResult implements Comparable<StudentResultComparable>{

  StudentResultComparable(String name, int score){
    super(name, score);
  }

  @Override
  public int compareTo(StudentResultComparable otherClass) {
    return otherClass.getScore() < getScore() ? 0: 1;
  }
}
