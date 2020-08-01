import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.net.Socket;

public class School {

    private int numOfStudents;
    private Socket netSocket;

    public School(Socket socket) {
        netSocket = socket;
        getNumOfStudents();
    }

    public String getPrelimStudentNum() {
        String prelimStudentNum = String.format("%05d",(numOfStudents + 1));
        return prelimStudentNum;
    }

    /*
    Increments the numOfStudents variable when a new student is created.
     */
    public int incrementStudentNum() {
        return ++numOfStudents;
    }

    /*
    Sends student object to server using connection established when the program started.
     */
    public void transmitStudent(Student s) {
        try {
        OutputStream outputStream1 = netSocket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream1);
        dataOutputStream.writeInt(-1);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream1);
        objectOutputStream.writeObject(s);
        new MessageBox("You have been added to the SIS.", "Success!");
        } catch (IOException ex) {
            new MessageBox("Connection has been interrupted.", "ERROR");
            System.exit(0);
        } catch (NullPointerException ex) {
            new MessageBox("NullPointerException", "ERROR");
        }
    }

    /*
    Accepts connection from server and retrieves number of students from database and updates the numOfStudents
    variable.
     */
    public void getNumOfStudents() {
        try {
            InputStream inputStream = netSocket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            numOfStudents = dataInputStream.readInt();
        } catch (IOException ex) {
            new MessageBox("Connection has been interrupted.", "ERROR");
            System.exit(0);
        }
    }

    /*
    Send email address or student ID number to server to try to pull student info from database.
     */
    public void requestStudentData(int id) {
        try {
            OutputStream outputStream = netSocket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeInt(-2);
            dataOutputStream.writeInt(id);
        } catch (IOException ex) {
            System.out.println("requestStudentData." + ex);
            new MessageBox("Connection has been interrupted.", "ERROR");
            System.exit(0);
        }
    }

    /*
    Receives student from server.
     */
    public Student receiveStudent() {
        Student student = new Student();
        try {
            InputStream inputStream = netSocket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            student = (Student) objectInputStream.readObject();
        } catch (IOException ex) {
            System.out.println("receiveStudent." + ex);
            new MessageBox("Connection has been interrupted.", "ERROR");
            System.exit(0);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return student;
    }

    public boolean checkOutOfRange(int n) {
        if (n > numOfStudents) return false;
        else return true;
    }

    /*
    Submits a request to the server for the list of classes that the school has available.
     */
    public ObservableList<Course> getCourses() {
        int numCourses = 0;
        ObservableList<Course> courseList = FXCollections.observableArrayList();

        try {
            OutputStream outputStream = netSocket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeInt(-3);
        } catch (IOException ex) {
            new MessageBox("Connection has been interrupted.", "ERROR");
            System.exit(0);
        }
        try {
            InputStream inputStream = netSocket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            numCourses = dataInputStream.readInt();
        } catch (IOException ex) {
            new MessageBox("Connection has been interrupted.", "ERROR");
            System.exit(0);
        }
        try {
            while (courseList.size() != numCourses) {
                InputStream inputStream = netSocket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                courseList.add((Course) objectInputStream.readObject());
            }
        } catch (IOException ex) {
            new MessageBox("Connection has been interrupted.", "ERROR");
            System.exit(0);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return courseList;
    }

    /*
    Submits request to server to add the selected class on the table to the student's schedule.
     */
    public void addCourse(Course course, int studentId) {
        try {
            OutputStream outputStream = netSocket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeInt(-4);
        } catch (IOException ex) {
            new MessageBox("Connection has been interrupted.", "ERROR");
            System.exit(0);
        }
        try {
            OutputStream outputStream = netSocket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.println(studentId + "," + course.getId());
            printWriter.flush();
        } catch (IOException ex) {
            new MessageBox("Connection has been interrupted.", "ERROR");
            System.exit(0);
        }
        try {
            InputStream inputStream = netSocket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            int outcome = dataInputStream.readInt();

            if (outcome == 1) {
                new MessageBox(course.getName() + " has been added to your schedule.",
                        "SUCCESS");
            } else if (outcome == -1) {
                new MessageBox(course.getName() + " could not be added to your schedule.",
                        "ERROR");
            }
        } catch (IOException ex) {
            new MessageBox("Connection has been interrupted.", "ERROR");
            System.exit(0);
        }
    }

    public ObservableList<Course> getStudentCourses(Student student) {
        int numCourses = 0;
        ObservableList<Course> courseList = FXCollections.observableArrayList();

        try {
            OutputStream outputStream = netSocket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeInt(-5);
            dataOutputStream.writeInt(student.getStudentNumber());
        } catch (IOException ex) {
            new MessageBox("Connection has been interrupted.", "ERROR");
            System.exit(0);
        }
        try {
            InputStream inputStream = netSocket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            numCourses = dataInputStream.readInt();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        try {
            while (courseList.size() != numCourses) {
                InputStream inputStream = netSocket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                courseList.add((Course) objectInputStream.readObject());
            }
        } catch (IOException ex) {
            new MessageBox("Connection has been interrupted.", "ERROR");
            System.exit(0);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return courseList;
    }

    public void dropClass(Course course, int studentId) {
        try {
            OutputStream outputStream = netSocket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeInt(-6);
        } catch (IOException ex) {
            new MessageBox("Connection has been interrupted.", "ERROR");
            System.exit(0);
        }
        try {
            OutputStream outputStream = netSocket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.println(studentId + "," + course.getId());
            printWriter.flush();
        } catch (IOException ex) {
            new MessageBox("Connection has been interrupted.", "ERROR");
            System.exit(0);
        }
        try {
            InputStream inputStream = netSocket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            int outcome = dataInputStream.readInt();

            if (outcome == 1) {

                new MessageBox(course.getName() + " has been removed from your schedule.",
                        "SUCCESS");
            } else if (outcome == -1) {
                new MessageBox("There was a problem with your request.",
                        "ERROR");
            }
        } catch (IOException ex) {
            new MessageBox("Connection has been interrupted.", "ERROR");
            System.exit(0);
        }
    }
}
