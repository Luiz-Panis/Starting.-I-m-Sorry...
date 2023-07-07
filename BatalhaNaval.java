import java.util.Random;

public class BatalhaNaval{
  
  //Solicitando do tamanho das linhas e colunas do tabuleiro.
  static int col = Entrada.leiaInt("Digite o numero de colunas que deseja: ");
  static int lin = Entrada.leiaInt("Digite o numero de linhas que deseja: ");
  
  public static void main(String[] args){
    
    int ponto = 0;
//------------------------------------------------------------------------------------------------------------
    
    //Verificacao de tamanho minimo para o tabuleiro.
    while(lin * col <= 5){
      System.out.println("Tabuleiro muito pequeno, escolha outro tamanho.");
      lin = Entrada.leiaInt("Linhas: ");
      col = Entrada.leiaInt("Colunas: ");
    }
    
//---------------------------------------------------------------------------------------------------------------   
    
    //Criacao do tabuleiro com a matriz, usando os valores solicitados no in�cio.
    String tabuleiro[][] = new String[lin][col];
    
//-----------------------------------------------------------------------------------------------------------------   
    
    //Apresentando o tabuleiro
    System.out.println("SEU TABULEIRO FOI CRIADO COM SUCESSO!!\n");
    System.out.println("----- TABULEIRO -----\n");
    System.out.println("  PONTOS: "+ponto+"\n");
    
    for(int i = 0;i < lin;i++){
      for(int j = 0;j < col;j++){
        tabuleiro[i][j] = ".";
      }
    }
    int vet[] = new int [col];
    for(int i = 0;i < col;i++){
      if(i == 0){
        System.out.print("  "); 
      }
      System.out.print(i+" ");
    }
    System.out.println();
    
    for(int i = 0;i < lin;i++){
      System.out.print(i+" ");
      for(int j = 0;j < col;j++){
        System.out.print(tabuleiro[i][j]+" "); 
      }
      System.out.println();
    } 
    
    int NC, NL, x, L, C;
    int[] PC = new int[5];
    int[] PL = new int[5];
    
//---------------------------------------------------------------------------------------------------------------   
    
    //Gera�ao das posicoes dos navios sem ter repeti�oes.
    for(int cont = 0; cont < 5;cont++){
      NC = GeradorColunas();
      NL = GeradorLinhas();
      if(cont > 0){
        x = 0;
        while(x <= 1){
          if(NC == PC[x] || NL == PL[x]){
            NC = GeradorColunas();
            NL = GeradorLinhas();
            x = 0;
          }
          else x++;
        }
      }
      PC[cont] = GeradorColunas();
      PL[cont] = GeradorLinhas();
    }
    
//---------------------------------------------------------------------------------------------------------------    
    
    //Criacao de um contador para navios.
    int Cnavios = 5;
    while(Cnavios != 0){
      int c = 0;
      System.out.println("\nEFETUE O DISPARO!");
      System.out.println("Para efetuar o disparo, escolha a LINHA e depois COLUNA que desejas.");
      int linn = Entrada.leiaInt("LINHA: ");
      int coll = Entrada.leiaInt("COLUNA: ");
      
//--------------------------------------------------------------------------------------------------------------     
      
      //Verificacaoo, caso usuario atire para fora do tabuleiro.
      while(linn > lin-1 || coll > col-1){
        System.out.println("Voce disparou para fora do tabuleiro. Tente Novamente: ");
        linn = Entrada.leiaInt("LINHA: ");
        coll = Entrada.leiaInt("COLUNA: ");
      }
      
      //Tabuleiro para quando o usuario errar o tiro.
      System.out.println("Voce disparou na posicao: (" + linn + "," + coll + "):");
      for(int i = 0; i < 5; i++){
        if(PC[i] == coll && PL[i] == linn){
          Cnavios--; 
        }
        else{
          c++; 
        }
      }
      if(c == 5){
        if(ponto != 0){
          ponto--; 
        }
        System.out.println("VOCE ERROU O DISPARO!\n");
        System.out.println("-1 PONTO");
        System.out.println("Tente Novamente!\n");
        System.out.println("Atualizando Tabuleiro...");
        System.out.println("   PONTOS: " + ponto);
        for(int j = 0;j < col;j++){
          if(j == 0){
            System.out.print("  ");
          }
          System.out.print(j+" ");
        }
        System.out.println();
        
//----------------------------------------------------------------------------------------------------------
        for(int l = 0; l < lin;l++){
          System.out.print(l+" ");
          for(int j = 0;j < col;j++){
            if(j == coll && l == linn){
              tabuleiro[l][j] ="~";
              System.out.print(tabuleiro[l][j]+" ");
            }
            else{
              System.out.print(tabuleiro[l][j]+" "); 
            }
          }
          System.out.println();
        }
      }
              //Tabuleiro caso o usuario acerte o navio
      else{
        ponto += 5;
        System.out.println("\nVOCE ACERTOU UM NAVIO!\n");
        System.out.println("+5 PONTOS\n");
        System.out.println("Atualizando Tabuleiro e pontos....\n");
        System.out.println("SEUS PONTOS: " + ponto);
        
        for(int j = 0;j < col;j++){
          if(j == 0){
            System.out.print(" ");
          }
          System.out.print(j+" ");
        }
        System.out.println();
        
//-----------------------------------------------------------------------------------------------------------------
        for(int i = 0;i < lin;i++){
          System.out.print(i+" ");
          for(int j = 0;j < col;j++){
            if(j == coll && i == linn){
              tabuleiro[i][j] ="X";
              System.out.print(tabuleiro[i][j]+" ");
            }
            else{
              System.out.print(tabuleiro[i][c]+" "); 
            }
          }
          System.out.println();
        }
      }
    }
    
    System.out.println("---------------------------------------------------\n");
    System.out.println("PARABENS!! VOCE CONSEGUIU ATINGIR TODOS OS NAVIOS!!\n");
    System.out.println("---------------------------------------------------");
    System.out.println("PONTOS: " + ponto);
  }
  
//----------------------------------------------------------------------------------------
  
  //Gerando posicoes aleatorias.
  public static int GeradorLinhas(){
    Random gerarnav = new Random();
    int l = gerarnav.nextInt(lin);
    return l;
  }
  public static int GeradorColunas(){
    Random gerarnav = new Random();
    int c = gerarnav.nextInt(col);
    return c;
  }
}