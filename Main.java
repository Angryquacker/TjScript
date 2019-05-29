class Main {
  public static void main(String[] args) {
    try {
      Interpret test = new Interpret("Test.tj");
      test.parse();
      test.execute();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
