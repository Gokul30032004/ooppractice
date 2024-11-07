package demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class School {

    public class Student {
        String studentName;
        int rollNumber;
        int age;
        String standard;
        Map<Subject, Integer> marks;

        public Student(String studentName, int rollNumber, int age, String standard) {
            this.studentName = studentName;
            this.rollNumber = rollNumber;
            this.age = age;
            this.standard = standard;
            this.marks = new HashMap<>();
        }

        public void assignMark(Subject subject, int mark) {
            marks.put(subject, mark);
        }

        public double calculateAverage() {
            if (marks.isEmpty()) {
                return 0;
            }
            int totalMarks = marks.values().stream().mapToInt(Integer::intValue).sum();
            return (double) totalMarks / marks.size();
        }

        @Override
        public String toString() {
            return "Name: " + studentName + ", Roll Number: " + rollNumber +
                    ", Age: " + age + ", Standard: " + standard;
        }
    }


    public enum Subject {
        Tamil, English, Maths, Science, Social
    }


    public class SCHOOL {
        ArrayList<Student> students;

        public SCHOOL() {
            students = new ArrayList<>();
            initializeStudents();
            assignInitialMarks();
        }

        private void initializeStudents() {
            students.add(new Student("Barath", 101, 15, "Tenth grade"));
            students.add(new Student("Dinesh", 102, 15, "Tenth grade"));
            students.add(new Student("Gokul", 103, 15, "Tenth grade"));
        }

        private void assignInitialMarks() {
            assignMarks("Barath", Subject.Tamil, 85);
            assignMarks("Barath", Subject.English, 90);
            assignMarks("Barath", Subject.Maths, 88);
            assignMarks("Barath", Subject.Science, 92);
            assignMarks("Barath", Subject.Social, 80);

            assignMarks("Dinesh", Subject.Tamil, 75);
            assignMarks("Dinesh", Subject.English, 80);
            assignMarks("Dinesh", Subject.Maths, 85);
            assignMarks("Dinesh", Subject.Science, 90);
            assignMarks("Dinesh", Subject.Social, 88);

            assignMarks("Gokul", Subject.Tamil, 95);
            assignMarks("Gokul", Subject.English, 89);
            assignMarks("Gokul", Subject.Maths, 85);
            assignMarks("Gokul", Subject.Science, 92);
            assignMarks("Gokul", Subject.Social, 91);
        }

        public void assignMarks(String studentName, Subject subject, int mark) {
            for (Student student : students) {
                if (student.studentName.equals(studentName)) {
                    student.assignMark(subject, mark);
                    return;
                }
            }
            System.out.println("Student " + studentName + " not found.");
        }

        public Student getTopRankedStudent() {
            Student topStudent = null;
            int highestTotalMarks = 0;

            for (Student student : students) {
                int totalMarks = student.marks.values().stream().mapToInt(Integer::intValue).sum();
                if (totalMarks > highestTotalMarks) {
                    highestTotalMarks = totalMarks;
                //    topStudent = student;
                }
            }

            return topStudent;
        }

        public void displayAllStudents() {
            for (Student student : students) {
                System.out.println(student);
                double average = student.calculateAverage();
                System.out.println("Average Marks: " + average);
            }
        }
    }
    public static void main(String[] args) {
        School school = new School();
        SCHOOL schoolManager = school.new SCHOOL();

        Student topStudent = schoolManager.getTopRankedStudent();
        if (topStudent != null) {
            System.out.println("Top Ranked Student: " + topStudent);
        }

        schoolManager.displayAllStudents();
    }
}

