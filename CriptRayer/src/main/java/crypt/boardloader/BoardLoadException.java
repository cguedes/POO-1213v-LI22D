package crypt.boardloader;

@SuppressWarnings("serial")
public class BoardLoadException extends RuntimeException {

  public BoardLoadException(String message, Throwable exception) {
    super(message, exception);
  }
}
