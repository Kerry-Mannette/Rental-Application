package com.example.rental_application;

import com.mysql.cj.x.protobuf.MysqlxNotice;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


public class Server extends JFrame{
    private JTextArea jta = new JTextArea();


    public static void main(String [] args) throws IOException {
        new Server();
    }

    public Server() throws IOException {
        //Place text area on teh frame

        setLayout(new BorderLayout());
        add(new JScrollPane(jta),BorderLayout.CENTER);
        setTitle("Rent & Use Server");
        Image icon = ImageIO.read(new File("Icons/Rent_And_Use_Icon.png"));
        setIconImage(icon);
        setSize(500,300);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        try{
            ServerSocket serverSocket = new ServerSocket(12345);
            jta.append("MultiThread Sever started at " + new Date() + '\n');
            //Number of client
            int clientNo = 1;

            while(true)
            {
                //Listen for a new connecton request
                Socket socket = serverSocket.accept();


                //Display the client number
                jta.append("Starting thread for client" + clientNo + "at" + new Date() + '\n');

                //find th client's host name and IP addres
                InetAddress inetAddress= socket.getInetAddress();
                jta.append("Client " + clientNo + "'s host name is " + inetAddress.getHostName());
                jta.append("Client " + clientNo + "'s IP Address is  " + inetAddress.getHostAddress());

                //Create a  new thread for the connection
                HandleAClient task = new HandleAClient(socket);

                //Starr a new thread
                new Thread(task).start();

                //Increment clientNo
                clientNo++;




            }


        }catch(IOException ex){

            System.err.println(ex);
        }
    }



    private class HandleAClient implements Runnable {
        private Socket socket; // a connected socket

        //Construct a thread
        public HandleAClient(Socket socket) {
            this.socket = socket;
        }

        @Override // Run a thread
        public void run() {

            //                //Create data input and output streams
            try {
                DataInputStream inputFromClient= new DataInputStream(socket.getInputStream());
               double cost = inputFromClient.readDouble();

               if (cost > 1800 & cost < 10000){
                   cost-= 300;
               }

               if (cost > 10000 & cost < 50000){
                    cost-= 2000;
                }


                //Continously server the client

                 System.out.println(cost);

                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream() );

                //Send area back to the client
                outputToClient.writeDouble(cost);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }



            }


        }
    }


