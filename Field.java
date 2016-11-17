import java.util.*;

class Field{
	private String[][] field = new String[10][10];
	private String black = "⚫️ ";
	private String white = "⚪️ ";
	private String asterisk = "* ";
	private String widthLine = "- ";
	private String heightLine = "|";
	private ArrayList<Integer> countBlack = new ArrayList<Integer>();//黒が挟める向きを記録する。
	private ArrayList<Integer> countWhite = new ArrayList<Integer>();//白が挟める向きを記録する。

	public Field(){

	}

	public void refreshField(){//マスの初期化
		for(int i = 0;i < 10;i++){
			for(int j = 0;j < 10;j++){
				if(i == 0 || i == 9){
					field[i][j] = widthLine;
				}else if(j == 0 || j == 9 && i != 0 && i != 9){
					field[i][j] = heightLine;
				}else{
					field[i][j] = asterisk;
				}
			}
		}

		field[4][4] = white;
		field[4][5] = black;
		field[5][4] = black;
		field[5][5] = white;
	}

	public void printField(){//マスの表示
		for(int i = 0;i < 10;i++){
			for(int j = 0;j < 10;j++){
				System.out.print(field[i][j]);
			}
			System.out.println();
		}
	}

	public int canPutStoneTimeBlack(){//黒が置ける状況が判断
		int a = 0;
		int k;

		for(int i = 1;i < 9;i++){
			for(int j = 1;j < 9;j++){

				//i-1

				if(field[i - 1][j - 1].equals(white) && field[i][j].equals(asterisk)){//(i,j)から見て、(i-1),(j-1)に白があるか、(i,j)に石が置かれていないか
					for(k = 2;i - k > 0 && j - k > 0;k++){//壁にぶつかるまで(i-k),(j-k)に黒があるか調べる
						if(field[i - k][j - k].equals(asterisk)){//(i,j),(i-1,j-1)の直線上に黒より先に*が見つかればループを抜ける
							break;
						}

						if(field[i - k][j - k].equals(black)){//(i,j),(i-1,j-1)の直線上に黒があるか
							a = 1;
						}
					}
				}

				if(field[i - 1][j].equals(white) && field[i][j].equals(asterisk)){//(i,j)から見て、(i-1),(j)に白があるか
					for(k = 2;i - k > 0;k++){
						if(field[i - k][j].equals(asterisk)){
							break;
						}

						if(field[i - k][j].equals(black)){
							a = 1;
						}
					}
				}

				if(field[i - 1][j + 1].equals(white) && field[i][j].equals(asterisk)){//(i,j)から見て、(i-1),(j+1)に白があるか
					for(k = 2;i - k > 0 && j + k < 9;k++){
						if(field[i - k][j + k].equals(asterisk)){
							break;
						}

						if(field[i - k][j + k].equals(black)){
							a = 1;
						}
					}
				}

				//i

				if(field[i][j - 1].equals(white) && field[i][j].equals(asterisk)){//(i,j)から見て、(i),(j-1)に白があるか
					for(k = 2;j - k > 0;k++){
						if(field[i][j - k].equals(asterisk)){
							break;
						}

						if(field[i][j - k].equals(black)){
							a = 1;
						}
					}
				}

				if(field[i][j + 1].equals(white) && field[i][j].equals(asterisk)){//(i,j)から見て、(i),(j+1)に白があるか
					for(k = 2;j + k < 9;k++){
						if(field[i][j + k].equals(asterisk)){
							break;
						}

						if(field[i][j + k].equals(black)){
							a = 1;
						}
					}
				}

				//i+1

				if(field[i + 1][j - 1].equals(white) && field[i][j].equals(asterisk)){//(i,j)から見て、(i+1),(j-1)に白があるか
					for(k = 2;i + k < 9 && j - k > 0;k++){
						if(field[i + k][j - k].equals(asterisk)){
							break;
						}

						if(field[i + k][j - k].equals(black)){
							a = 1;
						}
					}
				}

				if(field[i + 1][j].equals(white) && field[i][j].equals(asterisk)){//(i,j)から見て、(i+1),(j)に白があるか
					for(k = 2;i + k < 9;k++){
						if(field[i + k][j].equals(asterisk)){
							break;
						}

						if(field[i + k][j].equals(black)){
							a = 1;
						}
					}
				}

				if(field[i + 1][j + 1].equals(white) && field[i][j].equals(asterisk)){//(i,j)から見て、(i+1),(j+1)に白があるか
					for(k = 2;i + k < 9 && j + k < 9;k++){
						if(field[i + k][j + k].equals(asterisk)){
							break;
						}

						if(field[i + k][j + k].equals(black)){
							a = 1;
						}
					}
				}

			}
		}

		return a;
	}

	public int canPutStoneHereBlack(int x,int y){//黒がこの場所に置けるか判断
		int a = 0;
		int k;

		countBlack.clear();//countBlackの初期化

		//y-1

		if(field[y - 1][x - 1].equals(white) && field[y][x].equals(asterisk)){//(x,y)から見て、(x-1),(y-1)に白があるか,(x,y)に石が置かれていないか
			for(k = 2;y - k > 0 && x - k > 0;k++){//壁にぶつかるまで(x-k),(y-k)に黒があるか調べる
				if(field[y - k][x - k].equals(asterisk)){//(x,y),(x-1,y-1)の直線上に黒より先に*が見つかればループを抜ける
					break;
				}

				if(field[y - k][x - k].equals(black)){//(x,y),(x-1,y-1)の直線上に黒があるか
					a = 1;
					countBlack.add(1);//(x-1),(y-1)方向は挟めるので、挟める向きを1と記憶する
				}
			}
		}

		if(field[y - 1][x].equals(white) && field[y][x].equals(asterisk)){//(x,y)から見て、(x-1),(y)に白があるか
			for(k = 2;y - k > 0;k++){
				if(field[y - k][x].equals(asterisk)){
					break;
				}

				if(field[y - k][x].equals(black)){
					a = 1;
					countBlack.add(2);//挟める向きを2と記憶する
				}
			}
		}

		if(field[y - 1][x + 1].equals(white) && field[y][x].equals(asterisk)){//(x,y)から見て、(x-1),(y+1)に白があるか
			for(k = 2;y - k > 0 && x + k < 9;k++){
				if(field[y - k][x + k].equals(asterisk)){
					break;
				}

				if(field[y - k][x + k].equals(black)){
					a = 1;
					countBlack.add(3);//挟める向きを3と記憶する
				}
			}
		}

		//y

		if(field[y][x - 1].equals(white) && field[y][x].equals(asterisk)){//(x,y)から見て、(x),(y-1)に白があるか
			for(k = 2;x - k > 0;k++){
				if(field[y][x - k].equals(asterisk)){
					break;
				}

				if(field[y][x - k].equals(black)){
					a = 1;
					countBlack.add(4);//挟める向きを4と記憶する
				}
			}
		}

		if(field[y][x + 1].equals(white) && field[y][x].equals(asterisk)){//(x,y)から見て、(x),(y+1)に白があるか
			for(k = 2;x + k < 9;k++){
				if(field[y][x + k].equals(asterisk)){
					break;
				}

				if(field[y][x + k].equals(black)){
					a = 1;
					countBlack.add(5);//挟める向きを5と記憶する
				}
			}
		}

		//y+1

		if(field[y + 1][x - 1].equals(white) && field[y][x].equals(asterisk)){//(x,y)から見て、(x+1),(y-1)に白があるか
			for(k = 2;y + k < 9 && x - k > 0;k++){
				if(field[y + k][x - k].equals(asterisk)){
					break;
				}

				if(field[y + k][x - k].equals(black)){
					a = 1;
					countBlack.add(6);//挟める向きを6と記憶する
				}
			}
		}

		if(field[y + 1][x].equals(white) && field[y][x].equals(asterisk)){//(x,y)から見て、(x+1),(y)に白があるか
			for(k = 2;y + k < 9;k++){
				if(field[y + k][x].equals(asterisk)){
					break;
				}

				if(field[y + k][x].equals(black)){
					a = 1;
					countBlack.add(7);//挟める向きを7と記憶する
				}
			}
		}

		if(field[y + 1][x + 1].equals(white) && field[y][x].equals(asterisk)){//(x,y)から見て、(x+1),(y+1)に白があるか
			for(k = 2;y + k < 9 && x + k < 9;k++){
				if(field[y + k][x + k].equals(asterisk)){
					break;
				}

				if(field[y + k][x + k].equals(black)){
					a = 1;
					countBlack.add(8);//挟める向きを8と記憶する
				}
			}
		}

		return a;
	}

	public void turnOverBlack(int x,int y){//黒を置いて、黒が白をひっくり返す
		field[y][x] = black;//指定された座標に黒を置く

		int k;

		for(int i = 0;i < countBlack.size();i++){

			//y-k

			if(countBlack.get(i) == 1){
				for(k = 1;field[y - k][x - k] != black;k++){//(x,y),(x-1,y-1)方向の黒のある場所まで繰り返す
					field[y - k][x - k] = black;
				}
			}

			if(countBlack.get(i) == 2){
				for(k = 1;field[y - k][x] != black;k++){//(x,y),(x,y-1)方向の黒のある場所まで繰り返す
					field[y - k][x] = black;
				}
			}

			if(countBlack.get(i) == 3){
				for(k = 1;field[y - k][x + k] != black;k++){//(x,y),(x+1,y-1)方向の黒のある場所まで繰り返す
					field[y - k][x + k] = black;
				}
			}

			//y

			if(countBlack.get(i) == 4){
				for(k = 1;field[y][x - k] != black;k++){//(x,y),(x-1,y)方向の黒のある場所まで繰り返す
					field[y][x - k] = black;
				}
			}

			if(countBlack.get(i) == 5){
				for(k = 1;field[y][x + k] != black;k++){//(x,y),(x+1,y)方向の黒のある場所まで繰り返す
					field[y][x + k] = black;
				}
			}

			//y+k

			if(countBlack.get(i) == 6){
				for(k = 1;field[y + k][x - k] != black;k++){//(x,y),(x-1,y+1)方向の黒のある場所まで繰り返す
					field[y + k][x - k] = black;
				}
			}

			if(countBlack.get(i) == 7){
				for(k = 1;field[y + k][x] != black;k++){//(x,y),(x,y+1)方向の黒のある場所まで繰り返す
					field[y + k][x] = black;//白を黒に置き換える
				}
			}

			if(countBlack.get(i) == 8){
				for(k = 1;field[y + k][x + k] != black;k++){//(x,y),(x+1,y+1)方向の黒のある場所まで繰り返す
					field[y + k][x + k] = black;
				}
			}
		}
	}

	public int canPutStoneTimeWhite(){//白が置ける状況か判断
		int a = 0;
		int k;

		for(int i = 1;i < 9;i++){
			for(int j = 1;j < 9;j++){

				//i-1

				if(field[i - 1][j - 1].equals(black) && field[i][j].equals(asterisk)){//(i,j)から見て、(i-1),(j-1)に黒があるか、(i,j)に石が置かれていないか
					for(k = 2;i - k > 0 && j - k > 0;k++){//壁にぶつかるまで(i-k),(j-k)に黒があるか調べる
						if(field[i - k][j - k].equals(asterisk)){//(i,j),(i-1,j-1)の直線上に白より先に*が見つかればループを抜ける
							break;
						}

						if(field[i - k][j - k].equals(white)){//(i,j),(i-1,j-1)の直線上に白があるか
							a = 1;
						}
					}
				}

				if(field[i - 1][j].equals(black) && field[i][j].equals(asterisk)){//(i,j)から見て、(i-1),(j)に黒があるか
					for(k = 2;i - k > 0;k++){
						if(field[i - k][j].equals(asterisk)){
							break;
						}

						if(field[i - k][j].equals(white)){
							a = 1;
						}
					}
				}

				if(field[i - 1][j + 1].equals(black) && field[i][j].equals(asterisk)){//(i,j)から見て、(i-1),(j+1)に黒があるか
					for(k = 2;i - k > 0 && j + k < 9;k++){
						if(field[i - k][j + k].equals(asterisk)){
							break;
						}

						if(field[i - k][j + k].equals(white)){
							a = 1;
						}
					}
				}

				//i

				if(field[i][j - 1].equals(black) && field[i][j].equals(asterisk)){//(i,j)から見て、(i),(j-1)に黒があるか
					for(k = 2;j - k > 0;k++){
						if(field[i][j - k].equals(asterisk)){
							break;
						}

						if(field[i][j - k].equals(white)){
							a = 1;
						}
					}
				}

				if(field[i][j + 1].equals(black) && field[i][j].equals(asterisk)){//(i,j)から見て、(i),(j+1)に黒があるか
					for(k = 2;j + k < 9;k++){
						if(field[i][j + k].equals(asterisk)){
							break;
						}

						if(field[i][j + k].equals(white)){
							a = 1;
						}
					}
				}

				//i+1

				if(field[i + 1][j - 1].equals(black) && field[i][j].equals(asterisk)){//(i,j)から見て、(i+1),(j-1)に黒があるか
					for(k = 2;i + k < 9 && j - k > 0;k++){
						if(field[i + k][j - k].equals(asterisk)){
							break;
						}

						if(field[i + k][j - k].equals(white)){
							a = 1;
						}
					}
				}

				if(field[i + 1][j].equals(black) && field[i][j].equals(asterisk)){//(i,j)から見て、(i+1),(j)に黒があるか
					for(k = 2;i + k < 9;k++){
						if(field[i + k][j].equals(asterisk)){
							break;
						}

						if(field[i + k][j].equals(white)){
							a = 1;
						}
					}
				}

				if(field[i + 1][j + 1].equals(black) && field[i][j].equals(asterisk)){//(i,j)から見て、(i+1),(j+1)に黒があるか
					for(k = 2;i + k < 9 && j + k < 9;k++){
						if(field[i + k][j + k].equals(asterisk)){
							break;
						}

						if(field[i + k][j + k].equals(white)){
							a = 1;
						}
					}
				}
			}
		}

		return a;
	}

	public int canPutStoneHereWhite(int x,int y){//白がこの場所に置けるか判断
		int a = 0;
		int k;

		countWhite.clear();//countBlackの初期化

		//y-1

		if(field[y - 1][x - 1].equals(black) && field[y][x].equals(asterisk)){//(x,y)から見て、(x-1),(y-1)に黒があるか,(x,y)に石が置かれていないか
			for(k = 2;y - k > 0 && x - k > 0;k++){//壁にぶつかるまで(x-k),(y-k)に白があるか調べる
				if(field[y - k][x - k].equals(asterisk)){//(x,y),(x-1,y-1)の直線上に白より先に*が見つかればループを抜ける
					break;
				}

				if(field[y - k][x - k].equals(white)){//(x,y),(x-1,y-1)の直線上に白があるか
					a = 1;
					countWhite.add(1);//(x-1),(y-1)方向は挟めるので、挟める向きを1と記憶する
				}
			}
		}

		if(field[y - 1][x].equals(black) && field[y][x].equals(asterisk)){//(x,y)から見て、(x-1),(y)に黒があるか
			for(k = 2;y - k > 0;k++){
				if(field[y - k][x].equals(asterisk)){
					break;
				}

				if(field[y - k][x].equals(white)){
					a = 1;
					countWhite.add(2);//挟める向きを2と記憶する
				}
			}
		}

		if(field[y - 1][x + 1].equals(black) && field[y][x].equals(asterisk)){//(x,y)から見て、(x-1),(y+1)に黒があるか
			for(k = 2;y - k > 0 && x + k < 9;k++){
				if(field[y - k][x + k].equals(asterisk)){
					break;
				}

				if(field[y - k][x + k].equals(white)){
					a = 1;
					countWhite.add(3);//挟める向きを3と記憶する
				}
			}
		}

		//y

		if(field[y][x - 1].equals(black) && field[y][x].equals(asterisk)){//(x,y)から見て、(x),(y-1)に黒があるか
			for(k = 2;x - k > 0;k++){
				if(field[y][x - k].equals(asterisk)){
					break;
				}

				if(field[y][x - k].equals(white)){
					a = 1;
					countWhite.add(4);//挟める向きを4と記憶する
				}
			}
		}

		if(field[y][x + 1].equals(black) && field[y][x].equals(asterisk)){//(x,y)から見て、(x),(y+1)に黒があるか
			for(k = 2;x + k < 9;k++){
				if(field[y][x + k].equals(asterisk)){
					break;
				}

				if(field[y][x + k].equals(white)){
					a = 1;
					countWhite.add(5);//挟める向きを5と記憶する
				}
			}
		}

		//y+1

		if(field[y + 1][x - 1].equals(black) && field[y][x].equals(asterisk)){//(x,y)から見て、(x+1),(y-1)に黒があるか
			for(k = 2;y + k < 9 && x - k > 0;k++){
				if(field[y + k][x - k].equals(asterisk)){
					break;
				}

				if(field[y + k][x - k].equals(white)){
					a = 1;
					countWhite.add(6);//挟める向きを6と記憶する
				}
			}
		}

		if(field[y + 1][x].equals(black) && field[y][x].equals(asterisk)){//(x,y)から見て、(x+1),(y)に黒があるか
			for(k = 2;y + k < 9;k++){
				if(field[y + k][x].equals(asterisk)){
					break;
				}

				if(field[y + k][x].equals(white)){
					a = 1;
					countWhite.add(7);//挟める向きを7と記憶する
				}
			}
		}

		if(field[y + 1][x + 1].equals(black) && field[y][x].equals(asterisk)){//(x,y)から見て、(x+1),(y+1)に黒があるか
			for(k = 2;y + k < 9 && x + k < 9;k++){
				if(field[y + k][x + k].equals(asterisk)){
					break;
				}

				if(field[y + k][x + k].equals(white)){
					a = 1;
					countWhite.add(8);//挟める向きを8と記憶する
				}
			}
		}

		return a;
	}

	public void turnOverWhite(int x,int y){//白を置いて,白が黒をひっくり返す
		field[y][x] = white;//指定された座標に白を置く

		int k;

		for(int i = 0;i < countWhite.size();i++){

				//y-k

			if(countWhite.get(i) == 1){
				for(k = 1;field[y - k][x - k] != white;k++){//(x,y),(x-1,y-1)方向の白のある場所まで繰り返す
					field[y - k][x - k] = white;
				}
			}

			if(countWhite.get(i) == 2){
				for(k = 1;field[y - k][x] != white;k++){//(x,y),(x,y-1)方向の白のある場所まで繰り返す
					field[y - k][x] = white;
				}
			}

			if(countWhite.get(i) == 3){
				for(k = 1;field[y - k][x + k] != white;k++){//(x,y),(x+1,y-1)方向の白のある場所まで繰り返す
					field[y - k][x + k] = white;
				}
			}

			//y

			if(countWhite.get(i) == 4){
				for(k = 1;field[y][x - k] != white;k++){//(x,y),(x-1,y)方向の白のある場所まで繰り返す
					field[y][x - k] = white;
				}
			}

			if(countWhite.get(i) == 5){
				for(k = 1;field[y][x + k] != white;k++){//(x,y),(x+1,y)方向の白のある場所まで繰り返す
					field[y][x + k] = white;
				}
			}

			//y+k

			if(countWhite.get(i) == 6){
				for(k = 1;field[y + k][x - k] != white;k++){//(x,y),(x-1,y+1)方向の白のある場所まで繰り返す
					field[y + k][x - k] = white;
				}
			}

			if(countWhite.get(i) == 7){
				for(k = 1;field[y + k][x] != white;k++){//(x,y),(x,y+1)方向の白のある場所まで繰り返す
					field[y + k][x] = white;//黒を白に置き換える
				}
			}

			if(countWhite.get(i) == 8){
				for(k = 1;field[y + k][x + k] != white;k++){//(x,y),(x+1,y+1)方向の白のある場所まで繰り返す
					field[y + k][x + k] = white;
				}
			}
		}	
	}

	public int victory(){
		int battleBlack = 0;//黒のマスを数える
		int battleWhite = 0;//白のマスを数える

		for(int i = 1;i < 9;i++){//マスを数える
			for(int j = 1;j < 9;j++){
				if(field[i][j] == black){
					battleBlack = battleBlack + 1;
				}
				if(field[i][j] == white){
					battleWhite = battleWhite + 1;
				}
			}
		}

		if(battleBlack > battleWhite){//黒が白より多い時
			return 1;
		}else if(battleBlack < battleWhite){//白が黒より多い時
			return 2;
		}else{
			return 3;
		}
	}
}