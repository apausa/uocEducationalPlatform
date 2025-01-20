package edu.uoc.eduocation.model.course.CourseWithExam;

public class CourseWithExamException extends Exception {
  public static final String INVALID_DATE = "[ERROR] Exam can't be null";
  public static final String INVALID_TIME = "[ERROR] Exam time can't be null";
  public static final String INVALID_LOCATION = "[ERROR] Exam location can't be null, empty or blank";
  public static final String INVALID_ROOM = "[ERROR] Exam room can't be null, empty or blank";

  public CourseWithExamException(String message) {
        super(message);
    }
}
