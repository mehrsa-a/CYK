import java.util.Scanner;

class NotTerminal{
    char name;
    int size=0;
    String[] principles=new String[size];

    public NotTerminal(char name){
        this.name=name;
    }

    public void addPrinciple(String principle){
        String[] copy=new String[size];
        System.arraycopy(principles, 0, copy, 0, size);
        size++;
        principles=new String[size];
        if (size - 1 >= 0) System.arraycopy(copy, 0, principles, 0, size - 1);
        principles[size-1]=principle;
    }
}

public class CYK {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String w=scanner.next();
        int wl=w.length();
        int n=scanner.nextInt();
        NotTerminal[] notTerminals=new NotTerminal[n];
        for(int i=0; i<n; i++){
            String s=scanner.next();
            notTerminals[i]=new NotTerminal(s.charAt(0));
            String[] p=cut(s.substring(s.indexOf('>')+1));
            for (String value : p) {
                notTerminals[i].addPrinciple(value);
            }
        }

        if(w.equals("-")){
            for(NotTerminal notTerminal: notTerminals){
                if(notTerminal.name=='S'){
                    for(String s: notTerminal.principles){
                        if(s.equals("-")){
                            System.out.println("YES");
                            return;
                        }
                    }
                }
            }
        }

        String[][] table=new String[wl][wl];
        for(int i=0; i<wl; i++){
            for(int j=0; j<wl; j++){
                table[i][j]="";
            }
        }

        for(int i=0; i<wl; i++){
            for(NotTerminal notTerminal: notTerminals){
                for(String s: notTerminal.principles){
                    if(s.length()==1){
                        char c=s.charAt(0);
                        if(w.charAt(i)==c){
                            table[i][i]+=notTerminal.name+"";
                        }
                    }
                }
            }
        }

        for(int l=2; l<=wl; l++){
            for(int i=0; i<wl-l+1; i++){
                int j = i+l-1;
                for(int k=i; k<j; k++){
                    for(NotTerminal notTerminal: notTerminals){
                        for(String s: notTerminal.principles){
                            if(s.length()==2){
                                if(table[i][k].contains(s.charAt(0)+"") && table[k+1][j].contains(s.charAt(1)+"")){
                                    table[i][j]+=notTerminal.name+"";
                                }
                            }
                        }
                    }
                }
            }
        }

        if(table[0][wl-1].contains("S")){
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }

    public static String[] cut(String s){
        String[] commands = {};
        if(s.contains("|")){
            commands = s.replace("|", " ").split(" ");
        } else {
            commands=new String[1];
            commands[0]=s;
        }
        return commands;
    }
}
