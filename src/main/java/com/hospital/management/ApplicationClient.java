/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Zrael
 */
public class ApplicationClient {
    
    private final int port = 1234;
    private final String host = "localhost";
    private InputStream input;
    private OutputStream output;
    private Socket socket;

    public ApplicationClient() {
        try {
            socket = new Socket(host, port);
            input = socket.getInputStream();
            output = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public InputStream getInput() {
        return input;
    }

    public OutputStream getOutput() {

        return output;

    }

    public Socket getSocket() {
        return socket;
    }

}
