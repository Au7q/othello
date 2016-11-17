import java.util.*;

class Main{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();

		Field field = new Field();

		String select;//遊び方を選ぶ変数
		int x;//配置するx座標
		int y;//配置するy座標

		System.out.println("オセロゲーム開始");

		while(true){
			System.out.print("0=対人戦、1=プレイヤーが黒のCPU戦、2=プレイヤーが白のCPU戦 >");
			select = scan.next();

			if(select.equals("0")){
				System.out.println("対人戦スタート");
				break;
			}
			if(select.equals("1")){
				System.out.println("プレイヤー黒のCPU戦スタート");
				break;
			}
			if(select.equals("2")){
				System.out.println("プレイヤー白のCPU戦スタート");
				break;
			}

			System.out.println();
			System.out.println("やり直し");
		}

		System.out.println("----othello-----");

		field.refreshField();//オセロのマスを初期状態にする
		field.printField();//オセロのマスを表示する

		if(select.equals("0")){//対人戦
			while(true){//ゲームが終わるまで繰り返し

				if(field.canPutStoneTimeBlack() == 1){//黒が置けるか
					System.out.println("黒は置けます");

					while(true){//黒のターンが終わるまで繰り返し

						while(true){//範囲内のxが入力されるまで繰り返し
							System.out.print("配置するx座標を選択してください(1~8):");
							x = scan.nextInt();
							if(x < 1 || x > 8){
								System.out.println("やり直し");
							}else{
								break;
							}
						}

						while(true){//範囲内のyが入力されるまで繰り返し
							System.out.print("配置するy座標を選択してください(1~8):");
							y = scan.nextInt();
							if(y < 1 || y > 8){
								System.out.println("やり直し");
							}else{
								break;
							}
						}

						if(field.canPutStoneHereBlack(x,y) == 1){//指定された座標に置けるか
							field.turnOverBlack(x,y);
							field.printField();
							break;
						}else{
							System.out.println("配置できません");
						}
					}
				}else{
					System.out.println("黒は置けません");
				}

				if(field.canPutStoneTimeWhite() == 1){//白が置けるか
					System.out.println("白は置けます");

					while(true){//白のターンが終わるまで繰り返し

						while(true){//範囲内のyが入力されるまで繰り返し
							System.out.print("配置するx座標を選択してください(1~8):");
							x = scan.nextInt();
							if(x < 1 || x > 8){
								System.out.println("やり直し");
							}else{
								break;
							}
						}

						while(true){//範囲内のyが入力されるまで繰り返し
							System.out.print("配置するy座標を選択してください(1~8):");
							y = scan.nextInt();
							if(y < 1 || y > 8){
								System.out.println("やり直し");
							}else{
								break;
							}
						}

						if(field.canPutStoneHereWhite(x,y) == 1){//指定された座標に置けるか
							field.turnOverWhite(x,y);
							field.printField();
							break;
						}else{
							System.out.println("配置できません");
						}
					}
				}else{
					System.out.println("白は置けません");
				}

				if(field.canPutStoneTimeBlack() == 0 && field.canPutStoneTimeWhite() == 0){//終了判定
					System.out.println("ゲームを終了します");
					break;
				}
			}
		}

		if(select.equals("1")){//プレイヤー黒、CPU白
			while(true){//ゲームが終わるまで繰り返し

				if(field.canPutStoneTimeBlack() == 1){//黒が置けるか
					System.out.println("黒は置けます");

					while(true){//黒のターンが終わるまで繰り返し

						while(true){//範囲内のxが入力されるまで繰り返し
							System.out.print("配置するx座標を選択してください(1~8):");
							x = scan.nextInt();
							if(x < 1 || x > 8){
								System.out.println("やり直し");
							}else{
								break;
							}
						}

						while(true){//範囲内のyが入力されるまで繰り返し
							System.out.print("配置するy座標を選択してください(1~8):");
							y = scan.nextInt();
							if(y < 1 || y > 8){
								System.out.println("やり直し");
							}else{
								break;
							}
						}

						if(field.canPutStoneHereBlack(x,y) == 1){//指定された座標に置けるか
							field.turnOverBlack(x,y);
							field.printField();
							break;
						}else{
							System.out.println("配置できません");
						}
					}
				}else{
					System.out.println("黒は置けません");
				}

				if(field.canPutStoneTimeWhite() == 1){//白が置けるか
					System.out.println("白は置けます");

					while(true){//白のターンが終わるまで繰り返し

						x = rand.nextInt(8) + 1;//xを1~8の乱数で入力
						y = rand.nextInt(8) + 1;//yを1~8の乱数で入力

						if(field.canPutStoneHereWhite(x,y) == 1){//指定された座標に置けるか
							System.out.println("x:" + x);
							System.out.println("y:" + y);
							field.turnOverWhite(x,y);
							field.printField();
							break;
						}
					}
				}else{
					System.out.println("白は置けません");
				}

				if(field.canPutStoneTimeBlack() == 0 && field.canPutStoneTimeWhite() == 0){//終了判定
					System.out.println("ゲームを終了します");
					break;
				}
			}
		}

		if(select.equals("2")){//プレイヤー白、CPU黒
			while(true){//ゲームが終わるまで繰り返し

				if(field.canPutStoneTimeBlack() == 1){//黒が置けるか
					System.out.println("黒は置けます");

					while(true){//黒のターンが終わるまで繰り返し

						x = rand.nextInt(8) + 1;//xを1~8の乱数で入力
						y = rand.nextInt(8) + 1;//yを1~8の乱数で入力

						if(field.canPutStoneHereBlack(x,y) == 1){//指定された座標に置けるか
							System.out.println("x:" + x);
							System.out.println("y:" + y);
							field.turnOverBlack(x,y);
							field.printField();
							break;
						}
					}
				}else{
					System.out.println("黒は置けません");
				}

				if(field.canPutStoneTimeWhite() == 1){//白が置けるか
					System.out.println("白は置けます");

					while(true){//白のターンが終わるまで繰り返し

						while(true){//範囲内のyが入力されるまで繰り返し
							System.out.print("配置するx座標を選択してください(1~8):");
							x = scan.nextInt();
							if(x < 1 || x > 8){
								System.out.println("やり直し");
							}else{
								break;
							}
						}

						while(true){//範囲内のyが入力されるまで繰り返し
							System.out.print("配置するy座標を選択してください(1~8):");
							y = scan.nextInt();
							if(y < 1 || y > 8){
								System.out.println("やり直し");
							}else{
								break;
							}
						}

						if(field.canPutStoneHereWhite(x,y) == 1){//指定された座標に置けるか
							field.turnOverWhite(x,y);
							field.printField();
							break;
						}else{
							System.out.println("配置できません");
						}
					}
				}else{
					System.out.println("白は置けません");
				}

				if(field.canPutStoneTimeBlack() == 0 && field.canPutStoneTimeWhite() == 0){//終了判定
					System.out.println("ゲームを終了します");
					break;
				}
			}
		}

		if(field.victory() == 1){//勝敗判定
			System.out.println("黒の勝ちです");
		}else if(field.victory() == 2){
			System.out.println("白の勝ちです");
		}else{
			System.out.println("引き分けです");
		}

		System.out.println("game over");
	}
}