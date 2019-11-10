package cooperation;
/**
 * 生产者消费者：信号灯
 * 借助标志位
 * @author dell
 *
 */

public class CoTest02 {
	public static void main(String[] args) {
		Tv tv=new Tv();
		new Actor(tv).start();
		new Watched(tv).start();
	}

}
class Actor extends Thread{
	Tv tv;

	public Actor(Tv tv) {
		super();
		this.tv = tv;
	}
	
	public void run() {
		for(int i=0;i<20;i++) {
			if(i%2==0) {
			tv.play("aaa");
			}else {
				tv.play("bbb");
			}
		}
	}
}
class Watched extends Thread{
	Tv tv;

	public Watched(Tv tv) {
		super();
		this.tv = tv;
	}
	
	public void run() {
		for(int i=0;i<20;i++) {
			tv.watched();
		}
	}
}
class Tv{
	String voice;
	boolean flag=true;
	public synchronized void play(String voice) {
		if(!flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("表演了："+voice);
		this.voice=voice;
		this.notifyAll();
		flag=!flag;
	}
	public synchronized void watched() {
		if(flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("听到了："+voice);
		this.notifyAll();
		flag=!flag;
	}
}
