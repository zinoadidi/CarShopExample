import java.util.TreeSet;

public class TreeSetExamples {

  public void firstExampleTreeSet() {
    TreeSet<String> students = new TreeSet<>();
    students.add("Eve");
    students.add("Baiba");
    students.add("Zlata");


    for (String student: students){
      System.out.println(student);
    }

   TreeSet<StudentResultComparable> studentResults = new TreeSet<>();
    studentResults.add(new StudentResultComparable("zino", 20));
    studentResults.add(new StudentResultComparable("wino", 40));
    studentResults.add(new StudentResultComparable("bino", 60));
    studentResults.add(new StudentResultComparable("dino", 80));

  }
}
