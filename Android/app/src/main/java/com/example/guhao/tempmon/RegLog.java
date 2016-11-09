package com.example.guhao.tempmon;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RegLog {

	public static List<Person> check(Person person) {
		OutputStream os = null;
		InputStream is = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		List<Person> list=new ArrayList<Person>();
		try {
			// connect to servlet
			URL url = new URL(/*your url address here*/);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-type", "application/x-java-serialized-object");  
			connection.connect();
			System.out.println("connection sucessful!");
			os = connection.getOutputStream();
			oos = new ObjectOutputStream(os);
			oos.writeObject(person);
		
			System.out.println("oos build...");
			os.close();
			oos.flush();
			oos.close();
			is=connection.getInputStream();
			ois = new ObjectInputStream(is);
			list = (List<Person>) ois.readObject();
			is.close();
			ois.close();
			connection.disconnect();
			return list;
		} catch (Exception e){
			e.printStackTrace();
		}
		return list;
		// output Stream
		// OutputStream os = connection.getOutputStream();
		// OutputStreamWriter osw = new OutputStreamWriter(os);
		// BufferedWriter bw = new BufferedWriter(osw);

		// JSON
		// JSONArray ja = new JSONArray();
		// JSONObject jo = new JSONObject();
		// jo.put("username", name);
		// jo.put("password", password);
		// jo.put("adress", adress);
		// jo.put("gender", gender);
		// ja.put(jo);
		//
		// bw.write(ja.toString());
		//
		// InputStream is = connection.getInputStream();
		// InputStreamReader isr = new InputStreamReader(is);
		// BufferedReader br = new BufferedReader(isr);
		// String userjson = br.readLine();
		// if (userjson.equals("error"))
		// user = null;

	}
}