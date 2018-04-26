package japplet;
import javax.swing.JApplet; //zaimportowanie klasy JApplet 
import java.util.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

public class SpringApplet extends JApplet implements MouseListener, MouseMotionListener,ActionListener  { 
    boolean przeciaganie; // wartoœæ logiczna przeci¹gania myszy
	int szer=600;
    int wys= 600;
    float polx;
    float poly;
	private SimEngine simengine;//stworzenie pola klasy SimEngine
	private SimTask simtask;//stworzenie pola klasy SimTask
	private Timer odstep;  //stworzenie pola klasy Timer
	private Button buttonReset;// prywatne pole przechowywuj¹ce przycisk
	private TextField Masa,Ks,C,G,L0;//prywatne pola przechowywuj¹ce Masa,Ks,C,G,L0
	
    @Override
    public void init(){ 
     przeciaganie=false;// ustawienie wartoœci 0 
    addMouseListener(this);
    addMouseMotionListener(this);
    setSize(szer,wys);
    simengine= new SimEngine(10,3,1,150,200,150,200,100);
    simtask  = new SimTask(simengine,this,0.1);
    odstep   = new Timer();
    odstep.scheduleAtFixedRate(simtask, 0, 15);
    buttonReset=new Button("Reset");//utworzenie przycisku
    buttonReset.addActionListener(this);
    add(buttonReset);
    Masa =new TextField("10",1);// utworzenie obiektów
    Ks =new TextField("3",1);
    C =new TextField("1",1);
    G =new TextField("9,81",1);
    L0=new TextField("150",1);
    add(Masa); // dodanie elementów GUI
    add(Ks);
    add(C);
    add(L0);
    add(G);

    }
    public void mousePressed(MouseEvent e){
    	polx=e.getX();// po³o¿enie kursora na osi X
    	poly=e.getY();// po³o¿enie kursora na osi Y
    	 if((polx>=(int)simengine.getpolozenieX() -25 && polx<=(int)simengine.getpolozenieX() +25)&&(poly>=(int) simengine.getpolozenieY() &&
                 poly<=(int) simengine.getpolozenieY() +50)){//sprawdzanie czy pozycja kursora znajduje sie w obrêbie masy
    		odstep.cancel(); // wy³¹czenie timera
    		simengine.zeruj();
    		przeciaganie=true;
    	}
    	e.consume();//wywo³anie metody consume
    	
    }
    public void mouseReleased(MouseEvent e){
    if(przeciaganie=true){//sprawdzenie przeci¹gniêcia myszy
    	
    	simengine.zeruj(); 
    	simtask   = new SimTask(simengine,this,0.1);
    	odstep= new Timer();
    	odstep.scheduleAtFixedRate(simtask,0,15);
    	przeciaganie=false;
    	
    }
    e.consume();
    }
    public void mouseDragged(MouseEvent e){
    	if(przeciaganie=true){ //sprawdzenie przeci¹gniêcia myszy
    		int pol_x=e.getX();
    		int pol_y=e.getY();// ustawienie masy w pozycji kursora 
    	    simengine.setpolozenie(simengine.getpolozenieX(), pol_y);
             repaint();  	
    	}
    e.consume();//wywo³anie metody consume
    }
    
    public void paint(Graphics g){ // przeci¹¿enie metody paint
    Vector2D wychylenie = simengine.polozenie;
    g.setColor(Color.white);
    g.fillRect(0, 0, szer, wys); // czyszczenie ekranu
    g.setColor(Color.GRAY);
    
    g.fillRect(245, 84, 70, 15); // rysowanie utwierdzenia linki
    g.setColor(Color.BLUE); //rysowanie linki 
    g.drawLine((int)simengine.getpolozenieX()+80,100,(int)simengine.getpolozenieX()+80,(int)((int)(wychylenie.y)+simengine.getL0()));
   
    g.setColor(Color.RED);// rysowanie masy 
    g.fillOval((int)simengine.getpolozenieX()+55, (int)(simengine.getpolozenieY()+simengine.getL0()),50,50);
    g.setColor(Color.BLACK);// rysowanie przycisków 
    g.drawString("Masa", 30, 30);
    Masa.setBounds(30,40,40,20);
    g.drawString("Wspó³czynnik sprê¿ystoœci", 30, 90);
    Ks.setBounds(30,100,40,20);
    g.drawString("Wspó³czynnik t³umienia", 30, 150);
    C.setBounds(30,160,40,20);
    g.drawString("D³ugoœæ swobodna sprê¿yny", 30, 210);
    L0.setBounds(30,220,40,20);
    g.drawString("Przyœpieszenie Ziemskie", 30, 270);
    G.setBounds(30,280,40,20);
    buttonReset.setBounds(30,340,60,40);

    }
	
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void actionPreformed(ActionEvent e){
		if(e.getSource()==buttonReset){
			odstep.cancel(); //wy³¹czenie Timera 
			double m= Double.parseDouble(Masa.getText());
			double ks= Double.parseDouble(Ks.getText());
			double c= Double.parseDouble(C.getText());
			double l0= Double.parseDouble(L0.getText());
			double g= Double.parseDouble(G.getText());
			simengine=new SimEngine(m,ks,c,l0,300,100,300,10);//utworzenie obiektu
			simengine.setG((float) g);//ustawienie przyœpieszenia ziemskiego 
			simtask=new SimTask(simengine,this,0.1);
			odstep=new Timer();// stworzenie Timera
			odstep.scheduleAtFixedRate(simtask, 0, 15);
			
		}

    }
}



