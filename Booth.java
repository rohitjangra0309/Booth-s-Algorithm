import java.util.Scanner;
import java.lang.*;

public class Booth {
    public static String decToBinary(int n) {
        int i = 0;
        int Number = n;
        int[] binaryNum = new int[32];
        String binaryString = "";
        for(int k=Number;k>0;k=k/2) {
            binaryNum[i] = k % 2;
            i++;
        }
        int j = i-1;
        while(j>=0) {
            binaryString += Integer.toString(binaryNum[j]);
            j--;
        }
        return binaryString;
    }
    public static String[] corrector(String m0, String m1){
        String[] ans = new String[2];
        if(m0.length()==m1.length()) {
            m1="0"+m1;
            m0="0"+m0;
            ans[0] = m0;
            ans[1] = m1;
            return ans;
        }
        if(m0.length()>m1.length()){
            while (m1.length()!=m0.length()){
                m1="0"+m1;
            }
            m1="0"+m1;
            m0="0"+m0;
            ans[0] = m0;
            ans[1] = m1;
            return ans;
        }
        if(m0.length()<m1.length()){
            while (m0.length()!=m1.length()){
                m0="0"+m0;
            }
            m1="0"+m1;
            m0="0"+m0;
            ans[0] = m0;
            ans[1] = m1;
            return ans;
        }
        return ans;
    }
    static String twosComplement(StringBuffer str) {
        int n = str.length();
        int i = n-1;
        while( i >= 0 ) {
            if (str.charAt(i) == '1')
                break;
            i--;
        }
        if (i == -1)
            return "1" + str;
        int k = i-1 ;
        while(k >= 0) {
            if (str.charAt(k) == '0')
                str.replace(k, k+1, "1");
            else
                str.replace(k, k+1, "0");
            k--;
        }
        return str.toString();
    }
    static String addBinary(String a, String b) {
        String result = "";
        int s = 0;
        int i = a.length() - 1, j = b.length() - 1;
        while (i >= 0 || j >= 0 || s == 1)
        {
            s += ((i >= 0)? a.charAt(i) - '0': 0);
            s += ((j >= 0)? b.charAt(j) - '0': 0);
            result = (char)(s % 2 + '0') + result;
            s /= 2;
            i--; j--;
        }
        if(result.length()>a.length()) result = result.substring(1);
        return result;
    }
    public static int binaryToDec(String input){
        int result;
        result = Integer.parseInt(input,2);
        return result;
    }
    static String[] shiftRight(String a, String q,String q1){
        String[] ans = new String[3];
        q1 = q.substring(q.length()-1);
        q = a.substring(a.length()-1)+q.substring(0,q.length()-1);
        a = a.substring(0,1)+a.substring(0,a.length()-1);
        ans[0] = q1;
        ans[1] = q;
        ans[2] = a;
        return ans;
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the value of Multiplicand in Decimal: ");
        int multiplicand = s.nextInt();
        System.out.print("Enter the value of Multiplier in Decimal: ");
        int multiplier = s.nextInt();
        if(multiplicand>0 && multiplier>0){
        String posM = decToBinary(multiplicand);
        String Q = decToBinary(multiplier);
        String[] k = corrector(posM,Q);
        posM = k[0];
        Q = k[1];
        String Q0 = "0";
        String AC = "";
        while(AC.length()!=posM.length()) AC+="0";
        int counter = posM.length();
        StringBuffer tempM = new StringBuffer();
        tempM = tempM.append(posM);
        String negM = twosComplement(tempM);
        String[] ASR;
        String resultBinary;
            String fixQ = Q;
            System.out.println("Initial Condition");
            System.out.println("AC -> "+AC+" | Multiplier -> "+Q+" | Q0 -> "+Q0);
            System.out.println("Booth's Algorithm at Work");
        for(int i=0;i<counter;i++){
            String conditionChecker=Q.substring(Q.length()-1)+Q0;
            switch (conditionChecker){
            case "11":
            case "00":
                ASR = shiftRight(AC,Q,Q0);
                AC = ASR[2];
                Q = ASR[1];
                Q0 = ASR[0];
                System.out.println("AC -> "+AC+" | Multiplier -> "+Q+" | Q0 -> "+Q0);
                break;
            case "10":
                AC = addBinary(AC,negM);
                if(AC.length()>counter) AC = AC.substring(1);
                ASR = shiftRight(AC,Q,Q0);
                AC = ASR[2];
                Q = ASR[1];
                Q0 = ASR[0];
                System.out.println("AC -> "+AC+" | Multiplier -> "+Q+" | Q0 -> "+Q0);
                break;
            case "01":
                AC = addBinary(AC,posM);
                if(AC.length()>counter) AC = AC.substring(1);
                ASR = shiftRight(AC,Q,Q0);
                AC = ASR[2];
                Q = ASR[1];
                Q0 = ASR[0];
                System.out.println("AC -> "+AC+" | Multiplier -> "+Q+" | Q0 -> "+Q0);
                break;

        }
        }
        resultBinary = AC+Q;
        System.out.println("Binary Representation of the Multiplicand "+"-> "+posM);
            System.out.println("Binary Representation of the Multiplier "+"-> "+fixQ);
            System.out.println("Binary Representation of the Product "+"-> "+resultBinary);
            System.out.println("Decimal Representation of the Product "+"-> "+binaryToDec(resultBinary));
        }
        if(multiplicand<0 && multiplier<0){
            multiplicand = Math.abs(multiplicand);
            multiplier = Math.abs(multiplier);
            String postiveRep = decToBinary(multiplicand);
            String Q = decToBinary(multiplier);
            String[] k = corrector(postiveRep,Q);
            postiveRep = k[0];
            Q = k[1];
            StringBuffer tempQ = new StringBuffer();
            tempQ = tempQ.append(Q);
            Q = twosComplement(tempQ);
            String Q0 = "0";
            String AC = "";
            while(AC.length()!=postiveRep.length()) AC+="0";
            int counter = postiveRep.length();
            StringBuffer tempM = new StringBuffer();
            tempM = tempM.append(postiveRep);
            String negativeRep = twosComplement(tempM);
            String[] ASR;
            String resultBinary;

            System.out.println("AC -> "+AC+" | Multiplier -> "+Q+" | Q0 -> "+Q0);
            System.out.println("Booth's Algorithm at Work");
            String fixQ = Q;
            for(int i=0;i<counter;i++){
                String conditionChecker=Q.substring(Q.length()-1)+Q0;
                switch (conditionChecker){
                    case "11":
                    case "00":
                        ASR = shiftRight(AC,Q,Q0);
                        AC = ASR[2];
                        Q = ASR[1];
                        Q0 = ASR[0];
                        System.out.println("AC -> "+AC+" | Multiplier -> "+Q+" | Q0 -> "+Q0);
                        break;
                    case "10":
                        AC = addBinary(AC,postiveRep);
                        if(AC.length()>counter) AC = AC.substring(1);
                        ASR = shiftRight(AC,Q,Q0);
                        AC = ASR[2];
                        Q = ASR[1];
                        Q0 = ASR[0];
                        System.out.println("AC -> "+AC+" | Multiplier -> "+Q+" | Q0 -> "+Q0);
                        break;
                    case "01":
                        AC = addBinary(AC,negativeRep);
                        if(AC.length()>counter) AC = AC.substring(1);
                        ASR = shiftRight(AC,Q,Q0);
                        AC = ASR[2];
                        Q = ASR[1];
                        Q0 = ASR[0];
                        System.out.println("AC -> "+AC+" | Multiplier -> "+Q+" | Q0 -> "+Q0);
                        break;

                }
            }
            resultBinary = AC+Q;
            if(multiplicand==0||multiplier==0) resultBinary = "0";
            System.out.println("Binary Representation of the Multiplicand "+"-> "+negativeRep +" (-ve number 2's complement)");
            System.out.println("Binary Representation of the Multiplier "+"-> "+fixQ+" (-ve number 2's complement)");
            System.out.println("Binary Representation of the Product "+"-> "+resultBinary);
            System.out.println("Decimal Representation of the Product "+"-> "+binaryToDec(resultBinary));

        }
        if((multiplicand<0 && multiplier>0)) {
            multiplicand = Math.abs(multiplicand);
            String postiveRep = decToBinary(multiplicand);
            String Q = decToBinary(multiplier);
            String[] k = corrector(postiveRep,Q);
            postiveRep = k[0];
            Q = k[1];
            String Q0 = "0";
            String AC = "";
            while(AC.length()!=postiveRep.length()) AC+="0";
            int counter = postiveRep.length();
            StringBuffer tempM = new StringBuffer();
            tempM = tempM.append(postiveRep);
            String negativeRep = twosComplement(tempM);
            String[] ASR;
            String resultBinary;
            String fixQ = Q;
            System.out.println("AC -> "+AC+" | Multiplier -> "+Q+" | Q0 -> "+Q0);
            System.out.println("Booth's Algorithm at Work");
            for(int i=0;i<counter;i++){
                String conditionChecker=Q.substring(Q.length()-1)+Q0;
                switch (conditionChecker){
                    case "11":
                    case "00":
                        ASR = shiftRight(AC,Q,Q0);
                        AC = ASR[2];
                        Q = ASR[1];
                        Q0 = ASR[0];
                        System.out.println("AC -> "+AC+" | Multiplier -> "+Q+" | Q0 -> "+Q0);
                        break;
                    case "10":
                        AC = addBinary(AC,postiveRep);
                        if(AC.length()>counter) AC = AC.substring(1);
                        ASR = shiftRight(AC,Q,Q0);
                        AC = ASR[2];
                        Q = ASR[1];
                        Q0 = ASR[0];
                        System.out.println("AC -> "+AC+" | Multiplier -> "+Q+" | Q0 -> "+Q0);
                        break;
                    case "01":
                        AC = addBinary(AC,negativeRep);
                        if(AC.length()>counter) AC = AC.substring(1);
                        ASR = shiftRight(AC,Q,Q0);
                        AC = ASR[2];
                        Q = ASR[1];
                        Q0 = ASR[0];
                        System.out.println("AC -> "+AC+" | Multiplier -> "+Q+" | Q0 -> "+Q0);
                        break;

                }
            }
            resultBinary = AC+Q;
            String resultBinary1 = resultBinary;
            if(multiplicand==0||multiplier==0) resultBinary = "0";
            StringBuffer tempR = new StringBuffer();
            tempR = tempR.append(resultBinary);
            resultBinary = twosComplement(tempR); // -ve ka 2's complement toh +ve;
            System.out.println("Binary Representation of the Multiplicand "+"-> "+negativeRep +" (-ve number 2's complement)");
            System.out.println("Binary Representation of the Multiplier "+"-> "+fixQ);
            System.out.println("Binary Representation of the Product "+"-> "+resultBinary1+" (-ve number 2's complement)");
            System.out.println("Decimal Representation of the Product "+"-> "+(-1*binaryToDec(resultBinary)));

        }
        if(multiplicand>0 && multiplier<0){
            multiplier = Math.abs(multiplier);
            String posM = decToBinary(multiplicand);
            String Q = decToBinary(multiplier);
            String[] k = corrector(posM,Q);
            posM = k[0];
            Q = k[1];
            String Q0 = "0";
            String AC = "";
            StringBuffer tempQ = new StringBuffer();
            tempQ = tempQ.append(Q);
            Q = twosComplement(tempQ);
            while(AC.length()!=posM.length()) AC+="0";
            int counter = posM.length();
            StringBuffer tempM = new StringBuffer();
            tempM = tempM.append(posM);
            String negM = twosComplement(tempM);
            String[] ASR;
            String resultBinary;
            String fixQ = Q;
            System.out.println("AC -> "+AC+" | Multiplier -> "+Q+" | Q0 -> "+Q0);
            System.out.println("Booth's Algorithm at Work");
            for(int i=0;i<counter;i++){
                String conditionChecker=Q.substring(Q.length()-1)+Q0;
                switch (conditionChecker){
                    case "11":
                    case "00":
                        ASR = shiftRight(AC,Q,Q0);
                        AC = ASR[2];
                        Q = ASR[1];
                        Q0 = ASR[0];
                        System.out.println("AC -> "+AC+" | Multiplier -> "+Q+" | Q0 -> "+Q0);
                        break;

                    case "10":
                        AC = addBinary(AC,negM);
                        if(AC.length()>counter) AC = AC.substring(1);
                        ASR = shiftRight(AC,Q,Q0);
                        AC = ASR[2];
                        Q = ASR[1];
                        Q0 = ASR[0];
                        System.out.println("AC -> "+AC+" | Multiplier -> "+Q+" | Q0 -> "+Q0);
                        break;
                    case "01":
                        AC = addBinary(AC,posM);
                        if(AC.length()>counter) AC = AC.substring(1);
                        ASR = shiftRight(AC,Q,Q0);
                        AC = ASR[2];
                        Q = ASR[1];
                        Q0 = ASR[0];
                        System.out.println("AC -> "+AC+" | Multiplier -> "+Q+" | Q0 -> "+Q0);
                        break;

                }
            }
            resultBinary = AC+Q;
            if(multiplicand==0||multiplier==0) resultBinary = "0";
            String resultBinary1 = resultBinary;
            StringBuffer tempR = new StringBuffer();
            tempR = tempR.append(resultBinary);
            resultBinary = twosComplement(tempR);
            System.out.println("Binary Representation of the Multiplicand "+"-> "+posM);
            System.out.println("Binary Representation of the Multiplier "+"-> "+fixQ+" (-ve number 2's complement)");
            System.out.println("Binary Representation of the Product "+"-> "+resultBinary1+" (-ve number 2's complement)");
            System.out.println("Decimal Representation of the Product "+"-> "+(-1*binaryToDec(resultBinary)));
        }
        if(multiplicand==0||multiplier==0){
            System.out.print("Fact 0*anything = 0, So Product is equal to ");
            System.out.println("0000000000000000000000000000");
        }
    }
}
