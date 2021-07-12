import java.util.HashSet;
import java.util.Set;

public class SetExamples {
  public void exampleHashSet(){
   HashSet<String> students = new HashSet<>();
    System.out.println(students.isEmpty());
    if (students.isEmpty()){
      System.out.println("No students yet");
    }
    students.add("Jelena");
    students.add("Santa");
    students.add("Zlata");

    if(students.contains("zino")) {
      System.out.println("Zino found");
    }

    if (!students.contains("Santa")){
      System.out.println("Student did not do test");
    }


   for (String student : students){
     System.out.println(student);
   }
  }
  public void anotherExampleCustomType() {
    HashSet<StudentResult> results = new HashSet<>();

    results.add(new StudentResult("Zino", 20));
    results.add(new StudentResult());
    results.add(new StudentResult());
    results.add(new StudentResult());

    for (StudentResult studentResult: results){
      System.out.println(studentResult.getName());
      processResult(studentResult);
      try {
        doSomethingImportant(studentResult);
      }catch (Exception ex){

      }
    }
  }

  private void doSomethingImportant(StudentResult studentResult) throws Exception {
  }

  private void processResult(StudentResult studentResult) {

  }
}
