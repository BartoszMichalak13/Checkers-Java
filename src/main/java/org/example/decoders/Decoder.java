package org.example.decoders;

/**
 * to have this project open for extensions, interface of decoder was used. all decoders will have to use this method
 */
public interface Decoder {
    String decode(String message);
}
