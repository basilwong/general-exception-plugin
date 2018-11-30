TARGETED BUG PATTERN:
    Catching a general exception within try block. By catching ALL exceptions, users run the risk of masking issues in the code that have nothing to do with the libary.

CONDITIONS THE ANALYSIS SHOULD REPORT THE BUG:
    When the generic "Exception" is caught within a try block rather than providing specific exceptions.

EXAMPLE:

Problematic code:

public static void main(String[] args) {

    String class_name = null;
    try {
        Class c = Class.forName(class_name);
        System.out.println("Class = " + c.getName());
    }
    catch (Exception e) {
        log.error("Unable to create class: " + class_name);
    }
}

Correct code:

public static void main(String[] args) {

    String class_name = null;
    try {
        Class c = Class.forName(class_name);
        System.out.println("Class = " + c.getName());
    }
    catch (ClassNotFoundException e) {
        log.error(e.toString());
    }
}
