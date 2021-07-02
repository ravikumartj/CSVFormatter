package com.ajira.type;

public interface Transform {

    public void read();

    public void convert(String[] dataLine);

    public void write();

    public void transform();

    static final int TRANSFORM_CHANGE = 1;
    static final int TRANSFORM_REMOVE = 2;
    static final int TRANSFORM_MERGE = 3;
    static final int TRANSFORM_CREATE = 4;

    static final String COMMA_DELIMITER = ",";
    static final String SPACE = " ";
    static final String PIPE_DELIMITER = "|";
    static final String NEW_LINE = "\n";
}
