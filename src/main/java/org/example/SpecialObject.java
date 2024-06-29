package org.example;
public interface SpecialObject {

    int getStart();
    default int getEnd()
    {
        return getStart();
    }
}
