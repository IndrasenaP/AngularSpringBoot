package com.mkyong;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimePart;

public class OpenMail {
	
	/*public static void main(String[] args) {  
        //// TODO Auto-generated method stub  
       Desktop desktop = Desktop.getDesktop();  
        String url = "";  
        URI mailTo;  
        try {  
            url = "mailTo:test@gmail.com" + "?subject=" + "TEST%20SUBJECT" 
                       + "&body=" + "TEST%20BODY";  
             mailTo = new URI(url);  
             desktop.mail(mailTo);  
        } catch (URISyntaxException e) {  
            e.printStackTrace();  
       } catch (IOException e) {  
            e.printStackTrace();  
       }  
   }  */
	
	public static void main(String[] args) throws Exception {
		Properties props = System.getProperties();
		//props.put("mail.smtp.host", A_VALID_IP_OF_MAIL_SERVER);
		/*Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});*/
		Session session = null;
	    //Create message envelope.
	     MimeMessage msg = new MimeMessage((Session) session);
	    msg.addFrom(InternetAddress.parse("you@foo.com"));
	    msg.setRecipients(Message.RecipientType.TO,
	            InternetAddress.parse("support@bar.com"));
	    msg.setRecipients(Message.RecipientType.CC,
	            InternetAddress.parse("manager@baz.com"));
	    msg.setSubject("Hello Outlook");
	    //msg.setHeader("X-Unsent", "1");

	    MimeMultipart mmp = new MimeMultipart();
	    MimeBodyPart body = new MimeBodyPart();
	    body.setDisposition(MimePart.INLINE);
	    body.setContent("This is the body", "text/plain");
	    mmp.addBodyPart(body);

	    MimeBodyPart att = new MimeBodyPart();
	    att.attachFile("C:/Users/690308/Desktop/java-mail/src/main/resources/Sample.txt");
	    mmp.addBodyPart(att);

	    msg.setContent(mmp);
	    msg.saveChanges();


	    File resultEmail = File.createTempFile("test", ".eml");
	    try (FileOutputStream fs = new FileOutputStream(resultEmail)) {
	        msg.writeTo(fs);
	        fs.flush();
	        fs.getFD().sync();
	    }

	    System.out.println(resultEmail.getCanonicalPath());

	    ProcessBuilder pb = new ProcessBuilder();
	    pb.command("cmd.exe", "/C", "start", "outlook.exe",
	            "/eml", resultEmail.getCanonicalPath());
	    Process p = pb.start();
	    try {
	        p.waitFor();
	    } finally {
	        p.getErrorStream().close();
	        p.getInputStream().close();
	        p.getErrorStream().close();
	        p.destroy();
	    }
	}

	
	//public static void main(String[] args) throws URISyntaxException {
	/*	try {
			File myFile=new File("C:/Users/690308/Desktop/java-mail/src/main/resources/Sample.txt");  // C:\Users\690308\Desktop\java-mail\src\main\resources\Sample.txt
			
			System.out.println(myFile.getAbsolutePath());
			URL path = myFile.toURI().toURL();
			System.out.println(path);
			//new URI('');
			//mailto:joe@somewhere.com?subject=Yoursubjecthere&cc=ccpeople@some.com&body=This is the body&Attach=c:\tmp\thisfile.txt
			Desktop.getDesktop().mail( new URI( "mailto:address@somewhere.com?attachment:file:/C:/Users/690308/Desktop/java-mail/src/main/resources/Sample.txt" ));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
//	public void sendEMail()
//	{
//
//	    OleFrame frame = new OleFrame(getShell(), SWT.NONE);
//
//	    // This should start outlook if it is not running yet
//	    OleClientSite site = new OleClientSite(frame, SWT.NONE, "OVCtl.OVCtl");
//	    site.doVerb(OLE.OLEIVERB_INPLACEACTIVATE);
//
//	    // Now get the outlook application
//	    OleClientSite site2 = new OleClientSite(frame, SWT.NONE, "Outlook.Application");
//	    OleAutomation outlook = new OleAutomation(site2);
//
//	    OleAutomation mail = invoke(outlook, "CreateItem", 0 /* Mail item */).getAutomation();
//
//	    setProperty(mail, "BodyFormat", 2 /* HTML */);
//	    setProperty(mail, "Subject", subject);
//	    setProperty(mail, "HtmlBody", content);
//
//	    if (null != attachmentPaths)
//	    {
//	        for (String attachmentPath : attachmentPaths)
//	        {
//	            File file = new File(attachmentPath);
//	            if (file.exists())
//	            {
//	                OleAutomation attachments = getProperty(mail, "Attachments");
//	                invoke(attachments, "Add", attachmentPath);
//	            }
//	        }
//	    }
//
//	    invoke(mail, "Display" /* or "Send" */);
//
//	}
}

