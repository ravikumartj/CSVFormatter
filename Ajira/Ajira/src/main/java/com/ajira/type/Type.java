package com.ajira.type;

public abstract class Type implements Transform {

    public Type() {
        throw new RuntimeException("Please invoke Parametrized Constructor " +
                "with params String inputFile, String outputFile");
    }

    public Type(String inputFile, String outputFile) {
        if(null == inputFile || null == outputFile){
            throw new RuntimeException("Please pass inputFile, outputFile");
        }
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    @Override
    public void transform() {
        read();
        write();
    }

    protected String inputFile;
    protected String outputFile;
}
