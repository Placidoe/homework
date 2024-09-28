import com.lx.single.DataStore;

/**
 * TODO
 *
 * @Description
 * @Author Lx
 * @Date 2024/9/26 下午1:27
 **/
public class main {

    public static void main(String[] args) {
        DataStore dataStore = new DataStore();
        DataStore instance = DataStore.getInstance();
        instance.InitData(args);
        instance.Run();
    }
}
