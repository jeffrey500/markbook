import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClassListTest {

    ClassList classList;
    ArrayList<ArrayList<Student>> studentList;
    ArrayList<Student> list1;
    ArrayList<Student> list2;

    @BeforeEach
    void setUp() {
        studentList = new ArrayList<>();

        list1 = new ArrayList<>();
        list1.add(new Student("Dan", new double[]{}));
        list1.add(new Student("Pan", new double[]{}));
        list1.add(new Student("Tan", new double[]{}));

        list2 = new ArrayList<>();
        list2.add(new Student("Jack", new double[]{}));
        list2.add(new Student("Jeff", new double[]{}));
        list2.add(new Student("Jason", new double[]{}));

        studentList.add(list1);
        studentList.add(list2);

        classList = new ClassList("Code1", studentList);
    }


    @Test
    void addStudentInRow() {
        classList.addStudentInRow(new Student("NewStudent", new double[]{}));

        assertEquals(2, studentList.size());
        assertEquals(4, list2.size());
        assertEquals("NewStudent", list2.get(3).getName());
    }

    @Test
    void addStudetNewRow() {
        classList.addStudetNewRow(new Student("NewStudent", new double[]{}));

        assertEquals(3, studentList.size());
        assertEquals(3, list2.size());

        assertEquals("NewStudent", studentList.get(2).get(0).getName());
    }

    @Test
    void printClassroom() {
        classList.printClassroom();
    }
}