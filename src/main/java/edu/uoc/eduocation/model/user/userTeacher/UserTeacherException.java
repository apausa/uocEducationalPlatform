package edu.uoc.eduocation.model.user.userTeacher;

public class UserTeacherException extends Exception {
  public static final String INVALID_DEPARTMENT = "[ERROR] Teacher department can't be null, empty or blank";

    /**
     * Exception thrown for errors related to teacher operations.
     * @param message the detail message.
     */
  public UserTeacherException(String message) {
        super(message);
    }
}
