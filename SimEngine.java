package japplet;

public class SimEngine {//utowrzenie klasy SimEngine
    
	  private double Masa; // Masa
	  private double Ks;  //wsp�czynnik spr�zystosci 
	  private double C;   //wsp�czynnik t�umienia
	  private double L0;  //d�ugo�� swobodna spr�yny
	  private double X0m; //po�o�enie pocz�tkowe x masy 
	  private double Y0m; //po�o�enie pocz�tkowe y masy
	  private double X0;  //wsp�rz�dne punktu x zaczepienia spr�yny
	  private double Y0;  //wsp�rz�dnie punk y zaczepienia spr�yny
	  private double Vxm; // pr�dko�� x masy
	  private double Vym; // pr�dko�� y masy
	  private static double G=9.81f; //przy�pieszenie ziemskie
	  
	  public void setMasa(float m){ //mutatory
		  Masa=m;
	  }
	  public void setKs(float k){
		  Ks=k;
	  }
	  public void setC(float c){
		  C=c;
	  }
	  public void setL0(float l0){
		  L0=l0;
	  }
	  public void setX0m(float x0m){
		  X0m=x0m;
	  }
	  public void setY0m(float y0m){
		  Y0m=y0m;
	  }
	  public void setX0(float x0){
		  X0=x0;
	  }
	  public void setY0(float y0){
		  Y0=y0;
	  }
	  public void setVxm(float vxm){
		  Vxm=vxm;
	  }
	  public void setVym(float vym){
		  Vym=vym;
	  }
	  public void setG(float m){
		  Masa=m;
	  }
	  public double getMasa(){ //akcesory
		  return Masa;
	  }
	  public double getKs(){
		  return Ks;
	  }
	  public double getC(){
		  return C;
	  }
	  public double getL0(){
		  return L0;
	  }
	  public double getX0m(){
		  return X0m;
	  }
	  public double getY0m(){
		  return Y0m;
	  }
	  public double getX0(){
		  return X0;
	  }
	  public double getY0(){
		  return Y0;
	  }
	  public double getVxm(){
		  return Vxm;
	  }
	  public double getVym(){
		  return Vym;
	  }
	  public double getG(){
		  return G;
	  }
	  public Vector2D polozenie; 	  public double a;
	  public SimEngine(double m, double ks2,double c2, double l02,
			  float X0m,float Y0m, float X0,float Y0 ){
	  this.Masa=m;
	  this.Ks=ks2;
	  this.C=c2;
	  this.L0=l02;
	  this.X0m=X0m;
	  this.Y0m=Y0m;
	  this.X0=X0;
	  this.Y0=Y0;
	  this.Vym=0;
	  this.a=0;
	  polozenie = new Vector2D(X0m,Y0m);
	  }
	  public float getpolozenieX(){
		  return polozenie.x;
	  }
	  public float getpolozenieY(){
		  System.out.println(polozenie.y);
		
		  return polozenie.y;
	  }
	  public void setpolozenie(float x,float y){
		  this.polozenie=new Vector2D(x,y);
	  }
	  
	  public double t;
	  public void Simulate(double odswiezanie){ //metoda z parametrem obliczaj�ca
		  t=odswiezanie;                        //przy�pieszenie i po�o�enie y masy
		  a=(1/Masa)*(-Ks*polozenie.y-C*Vym+Masa*G);
		  Vym= Vym +a*t;
		  polozenie.y=(float) (polozenie.y+(Vym*t+a*t*t/2) );
		  }
	  public void zeruj(){ //metoda bez parametr�w resetuj�ca symulacje 
	   Vym=0;
	   this.a=0;
	  }
	  }
	  