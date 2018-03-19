import java.util.Random;
import java.util.Scanner;

/**
 * Created by sisucon on 2018/3/18.
 */
public class main {
	static Random random = new Random();
	static player me = new player();
	static int count = 0;
	static card[][] allCards = new card[5][14];
	public static void getMyCard(int nowFolwer,int nowNum) {
		me.allCard[count] = allCards[nowFolwer][nowNum];
		count++;
		allCards[nowFolwer][nowNum].used = true;
		if (nowNum >= 10) {
			me.Count += 10;
		}
		else
			me.Count += nowNum;
	}
	
	public static void main(String[] args) {
		System.out.println("开始游戏");
		System.out.println("现在开始发牌\n你的牌:");
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 14; j++) {
				allCards[i][j] = new card(i,j);
			}
		}
			for (int i = 0; i < 2; i++) {
			int nowFolwer = random.nextInt(4)+1;
			int nowNum = random.nextInt(13)+1;
				if (allCards[nowFolwer][nowNum].used) {
					i--;
				} else {
					getMyCard(nowFolwer,nowNum);
				}
		}
		
		boolean exit = false;
		while (!exit)
		{
			if (me.Count < 17) {
				System.out.println("由于牌少于17,所以强制抽牌");
				int nowFolwer = random.nextInt(3)+1;
				int nowNum = random.nextInt(12)+1;
				if (allCards[nowFolwer][nowNum].used) {
					//reroll
				} else {
					getMyCard(nowFolwer,nowNum);
				}
			} else {
				exit = true;
			}
		}
		System.out.println(count);
		for (int i = 0; i < count; i++) {
			if (me.allCard[i].num == 0) {
				System.out.println("count="+i+" card="+me.allCard[i].flower+" A");
			}
			else if (me.allCard[i].num == 10) {
				System.out.println("count="+i+" card="+me.allCard[i].flower+" J");
			}
			else if (me.allCard[i].num == 11) {
				System.out.println("count="+i+" card="+me.allCard[i].flower+" Q");
			}
			else if (me.allCard[i].num == 12) {
				System.out.println("count="+i+" card="+me.allCard[i].flower+" K");
			}
			else
			System.out.println("count="+i+" card="+me.allCard[i].flower+" "+me.allCard[i].num);
		}
		System.out.println("allcount:"+me.Count);
		//初始发牌
		if (me.Count == 21) {
			System.out.println("WOW,you are lucky!");
		}
		if (count < 5) {
			boolean temp = true;
			while (temp&&count<5)
			{
				temp = !getCardAgain();
				System.out.println(temp);
				for (int i = 0; i < count; i++) {
					if (me.allCard[i].num == 0) {
						System.out.println("count="+i+" card="+me.allCard[i].flower+" A");
					}
					else if (me.allCard[i].num == 10) {
						System.out.println("count="+i+" card="+me.allCard[i].flower+" J");
					}
					else if (me.allCard[i].num == 11) {
						System.out.println("count="+i+" card="+me.allCard[i].flower+" Q");
					}
					else if (me.allCard[i].num == 12) {
						System.out.println("count="+i+" card="+me.allCard[i].flower+" K");
					}
					else
						System.out.println("count="+i+" card="+me.allCard[i].flower+" "+me.allCard[i].num);
				}
				System.out.println("allcount:"+me.Count);
			}
		}
		//个人发牌结束
		
	}
	
	
	public static boolean getCardAgain() {
		System.out.println("是否继续抽牌?");
		System.out.println("Y/N");
		Scanner in = new Scanner(System.in);
		String nowIn = in.nextLine();
		nowIn.toLowerCase();
		boolean nowexit = false;
		while (!nowexit) {
			if (nowIn.equals("y") || nowIn.equals("n")) {
				nowexit = true;
			} else {
				System.out.println("重新输入!");
			}
		}
		if (nowIn.equals("y")) {
			boolean exit = false;
			while (!exit) {
				int nowFolwer = random.nextInt(3) + 1;
				int nowNum = random.nextInt(12) + 1;
				if (allCards[nowFolwer][nowNum].used) {
					//reroll
				} else {
					getMyCard(nowFolwer, nowNum);
					exit = true;
				}
				
			}
			return false;
		} else {
			return true;
		}
	}
	
	
	public static class player {
		int Count;
		card[] allCard = new card[5];
		player() {
			Count = 0;
		}
	}
	
	public static class card {
		int flower;
		int num;
		boolean used;
		 card(int flower,int num) {
			this.flower = flower;
			this.num = num;
			used = false;
		}
	}
}
