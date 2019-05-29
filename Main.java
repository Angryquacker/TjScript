class Main {
  public static void main(String[] args) {
    try {
      Interpret test = new Interpret("test.tj");
      test.parse();
      test.execute();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
