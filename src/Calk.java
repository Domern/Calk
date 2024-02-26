import java.util.HashMap;
import java.util.Scanner;

public class Calk {

    final private HashMap<String, Integer> rimToArab;
    final private HashMap<Integer, String> arabToRim;
    final private Scanner scanner=new Scanner(System.in);
    private int a;
    private int b;
    private String res;
    private boolean arab=true;

    public Calk() {
        rimToArab=new HashMap<>();
        rimToArab.put("I",1);
        rimToArab.put("V",5);
        rimToArab.put("X",10);
        rimToArab.put("L",50);
        rimToArab.put("C",100);

        arabToRim=new HashMap<>();
        arabToRim.put(1,"I");
        arabToRim.put(5,"V");
        arabToRim.put(10,"X");
        arabToRim.put(50,"L");
        arabToRim.put(100,"C");

        init();

    }

    private void init(){
        System.out.println("Введите выражение, используя пробелы");
        String inFormula=scanner.nextLine();
        String[] str=inFormula.split(" ");
        if(str.length>3){
            throw new RuntimeException("Формат математической операции не удовлетворяет заданию");
        }
        String a1;
        String b1;
        boolean good1=false;
        boolean good2=false;
        try{
            int number1 = Integer.parseInt(str[0]);
            a=number1;
            good1=true;
        }catch(NumberFormatException e){
            a=rimToArab(str[0]);
            arab = false;
        }

        try{
            int number2 = Integer.parseInt(str[2]);
            b=number2;
            good2=true;
        }catch(NumberFormatException e){
            b=rimToArab(str[2]);
            arab = false;
        }
        if(good1==good2){
            extracted(str[1]);
        } else throw new RuntimeException("Нельзя использовать арабские и римские цифры в одном выражении");

    }

    private int rimToArab(String number){
        int resultat=0;
        String[]str=number.split("");
        for (String s : str) {
            resultat=resultat+rimToArab.get(s);
        }
        return resultat;
    }



    private void extracted(String strt) {
        int res1;
        if(a>10||b>10){
            throw new RuntimeException("Число больше 10");
        }
        if (strt.equals("+")) {
            res1=a + b;
        } else if (strt.equals("-")) {
            res1=a - b;
        } else if (strt.equals("*")) {
            res1=a * b;
        } else if (strt.equals("/")) {
            res1=a / b;
        } else {
            throw new RuntimeException("Не верный знак математического выражения");
        }

        if(arab==false){
            arabToRim(res1);
        }else {res= String.valueOf(res1);
        }

        System.out.println("Результат= "+res);

    }

    private void arabToRim(int number){
        if(number<=0){
            throw new RuntimeException("Римское выражение не может быть отрицательным");
        }
        String res2="";
        while (number>=100){
            res2=res2+arabToRim.get(100);
            number=number-100;
        }
        while (number>=50){
            res2=res2+arabToRim.get(50);
            number=number-50;
        }
        while (number>=10){
            res2=res2+arabToRim.get(10);
            number=number-10;
        }
        while (number>=5){
            res2=res2+arabToRim.get(5);
            number=number-5;
        }
        while (number>=1){
            res2=res2+arabToRim.get(1);
            number=number-1;
        }

        res=res2;

    }
}
