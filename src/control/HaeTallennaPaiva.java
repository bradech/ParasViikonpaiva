package control;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HaeTallennaPaiva")
public class HaeTallennaPaiva extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String tiedosto;
    
    public HaeTallennaPaiva() {
        super();
        System.out.println("HaeTallennaPaiva.HaeTallennaPaiva()");    //systr     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("HaeTallennaPaiva.doGet()");
		tiedosto=getServletContext().getRealPath("/tiedot.txt");	
		if(request.getParameter("paiva")!=null){				
			String [] tulokset = lueTiedostosta().split(";");		
			int paiva = Integer.parseInt(request.getParameter("paiva"));
			tulokset[paiva]=String.valueOf(Integer.parseInt(tulokset[paiva])+1);
			String uusiTulos = "";
			for(int i=0;i<7;i++){
				if(i!=6){
					uusiTulos+=tulokset[i] + ";";
				}else{
					uusiTulos+=tulokset[i]; 
				}
			}
			System.out.println(uusiTulos);
			kirjoitaTiedostoon(uusiTulos);
		}
		request.setAttribute("paivat", lueTiedostosta());		
		String jsp = "/parasviikonpaiva.jsp"; 
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("HaeTallennaPaiva.doPost()");
	}
	
	private void kirjoitaTiedostoon(String paivat){
		BufferedWriter writer;
		try {			
			writer = new BufferedWriter(new FileWriter(tiedosto));
			writer.write(paivat);
			writer.close();			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	private String lueTiedostosta(){
		BufferedReader reader = null;	
		String paivat="";
		try {			
			reader = new BufferedReader(new FileReader(tiedosto));			
		} catch (Exception e) {			
			kirjoitaTiedostoon("0;0;0;0;0;0;0");//Jos tiedostoa ei vielä ole olemassa, niin luodaan se	
			lueTiedostosta(); //ja aloitetaan alusta
		}
		try {
			paivat=reader.readLine();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paivat;
	}

}
