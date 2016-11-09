package com.example.guhao.tempmon;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class TempUtil {

	// upload the temperature to server
	public static void upload(Temp temp) throws Exception {
		URL url = new URL(/*your url address here*/);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setRequestProperty("Content-type", "application/x-java-serialized-object");
		connection.connect();
		ObjectOutputStream oos = new ObjectOutputStream(connection.getOutputStream());
		oos.writeObject(temp);
		System.out.println("temp"+temp.getNumber());
		//****************************************
		oos.close();
		System.out.println("oos is closed");
		InputStream is=connection.getInputStream();
		is.read();
		is.close();
		//*****************************************
		connection.disconnect();
	}


	public static List<Temp> download(Temp_query tp) throws Exception {
		URL url = new URL(/*your url address here*/);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setRequestProperty("Content-type", "application/x-java-serialized-object");  
		connection.connect();
		ObjectOutputStream oos = new ObjectOutputStream(connection.getOutputStream());
		oos.writeObject(tp);
		oos.flush();
		oos.close();
		System.out.println("oos closed...");
		
		InputStream is=connection.getInputStream();
		
		ObjectInputStream ois = new ObjectInputStream(is);
		@SuppressWarnings("unchecked")
		List<Temp> list = (List<Temp>) ois.readObject();
		is.close();
		ois.close();
		connection.disconnect();
		return list;

	}
	
	
}
