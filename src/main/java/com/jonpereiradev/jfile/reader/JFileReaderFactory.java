package com.jonpereiradev.jfile.reader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Factory to create the {@link JFileReader} implementation.
 */
public final class JFileReaderFactory {

    private JFileReaderFactory() {
        throw new UnsupportedOperationException();
    }

    /**
     * Create a File Reader with the desired parameters.
     *
     * @param file the file instance to creates the stream.
     * @param readerConfig the config that tells how to read the file.
     *
     * @return an instance of JFileReader for the file and config specified.
     *
     * @throws IOException when the creation of the InputStream for the file fails.
     */
    public static JFileReader newJFileReader(File file, JFileReaderConfig readerConfig) throws IOException {
        return newJFileReader(file.toPath(), readerConfig);
    }

    /**
     * Create a File Reader with the desired parameters.
     *
     * @param file the file instance to creates the stream.
     * @param regex the regex pattern to split the columns in a line.
     *
     * @return an instance of JFileReader for the file and config specified.
     *
     * @throws IOException when the creation of the InputStream for the file fails.
     */
    public static JFileReader newUtf8JFileReader(File file, String regex) throws IOException {
        return newUtf8JFileReader(file.toPath(), regex);
    }

    /**
     * Create a File Reader with the desired parameters.
     *
     * @param path the path instance to creates the stream.
     * @param readerConfig the config that tells how to read the file.
     *
     * @return an instance of JFileReader for the path and config specified.
     *
     * @throws IOException when the creation of the InputStream for the path fails.
     */
    public static JFileReader newJFileReader(Path path, JFileReaderConfig readerConfig) throws IOException {
        return newJFileReader(Files.newInputStream(path), readerConfig);
    }

    /**
     * Create a File Reader with the desired parameters.
     *
     * @param path the path instance to creates the stream.
     * @param regex the regex pattern to split the columns in a line.
     *
     * @return an instance of JFileReader for the path and config specified.
     *
     * @throws IOException when the creation of the InputStream for the path fails.
     */
    public static JFileReader newUtf8JFileReader(Path path, String regex) throws IOException {
        return newUtf8JFileReader(Files.newInputStream(path), regex);
    }

    /**
     * Create a File Reader with the desired parameters.
     *
     * @param inputStream the file instance to creates the stream.
     * @param readerConfig the config that tells how to read the file.
     *
     * @return an instance of JFileReader for the input stream and config specified.
     */
    public static JFileReader newJFileReader(InputStream inputStream, JFileReaderConfig readerConfig) {
        Objects.requireNonNull(inputStream, "InputStream is required");
        Objects.requireNonNull(readerConfig, "JFileReaderConfig is required");
        return new JFileReaderEngine(inputStream, readerConfig);
    }

    /**
     * Create a File Reader for UTF-8 encoding with the desired parameters.
     *
     * @param inputStream the file instance to creates the stream.
     * @param regex the regex pattern to split the columns in a line.
     *
     * @return an instance of JFileReader for the input stream and config specified.
     */
    public static JFileReader newUtf8JFileReader(InputStream inputStream, String regex) {
        Objects.requireNonNull(inputStream, "InputStream is required");
        Objects.requireNonNull(regex, "regex is required");
        return new JFileReaderEngine(inputStream, newUtf8ReaderConfig(regex));
    }


    /**
     * Creates a JFileReaderConfig with UTF-8 charset encode.
     *
     * @param regex the regex pattern to split the columns in a line.
     *
     * @return a file reader config to read the file.
     */
    public static JFileReaderConfig newUtf8ReaderConfig(String regex) {
        return newReaderConfig(regex, StandardCharsets.UTF_8);
    }

    /**
     * Creates a JFileReaderConfig with the specified charset encode.
     *
     * @param regex the regex pattern to split the columns in a line.
     * @param charset the encode charset to read the line and columns.
     *
     * @return a file reader config to read the file.
     */
    public static JFileReaderConfig newReaderConfig(String regex, Charset charset) {
        return new JFileReaderConfigImpl(Pattern.compile(regex), charset);
    }

}
