package lab2.controller;

import utils.file.FileManager;

import java.io.File;

import static utils.file.FileList.*;

public class ResultSaver {
    public final File zipFile;
    public final File compressedFile;

    public ResultSaver(File inputFile, byte[] codedText, String decodedText) {
        this(inputFile, codedText, decodedText.getBytes());
    }

    public ResultSaver(File inputFile, byte[] codedText, byte[] decodedText) {
        var fileManager = new FileManager();
        zipFile = fileManager.archiveFileToZip(inputFile, PATH_TO_FOLDER + "" + ZIP_FILE_NAME);
        compressedFile = new File(PATH_TO_FOLDER + "" + CODED_OUTPUT_FILE_NAME);
        fileManager.byteWrite(compressedFile, fileManager.binaryStringToBytes(codedText));
        fileManager.write(PATH_TO_FOLDER + "" + CODED_OUTPUT_FILE_AS_TEXT_NAME, codedText);
        fileManager.write(PATH_TO_FOLDER + "" + DECODED_OUTPUT_FILE_NAME, decodedText);
    }
}