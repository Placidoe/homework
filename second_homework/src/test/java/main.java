/**
 * TODO
 *
 * @Description
 * @Author Lx
 * @Date 2024/9/26 下午1:27
 **/
public class main {
    public static void main(String[] args) {
        Object[] a = new Object[2];
        a[0]=new String("1");
        a[1]= 1;
        for (int i = 0; i < a.length; i++) {
            if(a[i] instanceof String b){
                System.out.println("String"+b);
            }else if(a[i] instanceof Integer c){
                System.out.println("Integer" + c);
            }
        }
    }
}
