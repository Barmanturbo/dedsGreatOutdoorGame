module com.example {
    requires transitive javafx.controls;
    requires transitive javafx.base;
    requires transitive javafx.graphics;
    requires transitive javafx.media;
    requires transitive javafx.fxml;

    opens com.example to javafx.fxml;
    exports com.example;
}
