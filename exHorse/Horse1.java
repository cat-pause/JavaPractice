package exHorse;

public class Horse1 extends Thread{
	Area a;
	String name = "골든위너";
	
	Horse1(Area a){
		this.a = a;
	}
	public void run() {
		//100m 달리기
		for(int i = 0; i < 5; i++) {
			synchronized(a) {
				try {
					a.wait(1000);
					System.out.println("지점 통과");
				} catch (InterruptedException e) {e.printStackTrace();}
			}//syn
		}//for
	}//run()
}
