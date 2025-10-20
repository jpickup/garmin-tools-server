module com.johnpickup.parser {
    requires antlr4.runtime;
    requires com.johnpickup.common;
    requires static lombok;
    exports com.johnpickup.garmin.parser;
}