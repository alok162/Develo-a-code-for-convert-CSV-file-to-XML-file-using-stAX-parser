package Staxpackage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedReader;

public class Csvtoxml {
   public static void main(String[] args) throws IOException, XMLStreamException {
      boolean flag = true;
      String line="";
      Scanner scanner=null;
      String[] a=new String[100];
      int i=0,k=0,j=0;
      try {
    	  StringWriter stringwriter=new StringWriter();
          BufferedReader reader =new BufferedReader(new FileReader("user_info.csv"));
          XMLOutputFactory xmloutputfactory=XMLOutputFactory.newInstance();
          XMLStreamWriter xmlstreamwriter=xmloutputfactory.createXMLStreamWriter(stringwriter);
          xmlstreamwriter.writeStartDocument();
          xmlstreamwriter.writeStartElement("root");
               while((line=reader.readLine())!=null)
               {
            	   scanner=new Scanner(line);
            	   scanner.useDelimiter(",");
            	   /*if(k>0)
         	      {
         		   xmlstreamwriter.writeStartElement("root-row");
         		  }*/
            	   int temp=0;
            	   while(scanner.hasNext())
            	   {
            		   String str=scanner.next();
            		   if(i==0)
            		    {  
            			      a[j++]=str ;
            			}
            		   else
            		   {
            			   xmlstreamwriter.writeStartElement(a[temp]);
            			   xmlstreamwriter.writeCharacters(str);
            			   xmlstreamwriter.writeEndElement();
            			   temp++;
            		   }
            	   }
            	   //xmlstreamwriter.writeEndElement();
            	  // xmlstreamwriter.flush();
            	   i++;
            	   //k++;
               } 
               xmlstreamwriter.writeEndDocument();
             reader.close();
             xmlstreamwriter.flush();
             xmlstreamwriter.close();
             String xmlString = stringwriter.getBuffer().toString();
             stringwriter.close();
             System.out.println(xmlString);
      }
          
      catch (FileNotFoundException e) 
      {
            e.printStackTrace();
      } 
      catch (IOException e)
      {
            e.printStackTrace();
       }
   }
}