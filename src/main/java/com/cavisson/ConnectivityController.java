package com.cavisson;

import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectivityController
{
  /*@RequestMapping("/")
  public String index() {
      return "Greetings from Spring Boot!";
  }*/
  
  @RequestMapping("/checkconnection")
  public String connectivity() {
     
    String ndcHost = System.getenv("CAV_NDC_HOST");
    String ndcPort = System.getenv("CAV_NDC_PORT");
    StringBuffer sf = new StringBuffer();
    sf.append("Connection Status is:");
    sf.append("\n");
    String error = "";
    Socket s;
    try
    {
      SocketAddress address = new InetSocketAddress(ndcHost, Integer.parseInt(ndcPort));
      s = new Socket();
      s.connect(address, 30*1000);
      DataOutputStream dout = new DataOutputStream(s.getOutputStream());
      dout.writeUTF("Hello Server");
      dout.flush();
      dout.close();
      s.close();
    }
    catch(Exception e)
    {
      /*StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      e.printStackTrace(pw);
      //sf.append(sw.toString());
      error += sw.toString();*/
      error = "Can't Connect";
    }
     
    if(!"".equals(error) )
      sf.append("ERROR!!!!").append(error);
    else
      sf.append("Connection Successfull with Host:"+ ndcHost + "  Port:"+ ndcPort);
     
     return sf.toString();
  }
  @RequestMapping("/restconnection")
  public String restConnectivity(@RequestParam(value="host") String ndcHost, @RequestParam(value="port") String ndcPort) {
     
    //String ndcHost = System.getenv("CAV_NDC_HOST");
    //String ndcPort = System.getenv("CAV_NDC_PORT");
    StringBuffer sf = new StringBuffer();
    sf.append("Connection Status is:");
    sf.append("\n");
    String error = "";
    Socket s;
    try
    {
      //s = new Socket(ndcHost, Integer.parseInt(ndcPort));
      SocketAddress address = new InetSocketAddress(ndcHost, Integer.parseInt(ndcPort));
      s = new Socket();
      s.connect(address, 30*1000);
      DataOutputStream dout = new DataOutputStream(s.getOutputStream());
      dout.writeUTF("Hello Server");
      dout.flush();
      dout.close();
      s.close();
    }
    catch(Exception e)
    {
      /*StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      e.printStackTrace(pw);
      error = sw.toString();*/
      error = "Can't Connect";
    }
     
    if(!"".equals(error))
      sf.append("ERROR!!!!").append("\n").append(error);
    else
      sf.append("Connection Successfull with Host:"+ ndcHost + "  Port:"+ ndcPort);
     
     return sf.toString();
  }

}
